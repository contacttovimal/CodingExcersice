package careercup.cache;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ICacheImpl<K,V> implements ICache<K,V>{
    private Map<K,ValueContainer<V>> cache = null;
    private String name;
    ScheduledExecutorService scheduledExecutorService;
    private long delayMs =1000;
    volatile boolean isActive = false;

    public ICacheImpl(String name){
        this.name = name;
        cache = new ConcurrentHashMap<>();
        isActive = true;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(()-> {
            LocalDateTime executionTime = LocalDateTime.now();
            Set<K> keys= null;
            if(cache.values().size() > 0){
                keys = cache.entrySet().stream().filter((entry) -> {
                    return entry.getValue().isExpired();
                }).map(Map.Entry::getKey).collect(Collectors.toSet());
                cache.keySet().removeAll(keys);
            }
            System.out.println( executionTime+  " : size : " + cache.size() +  " : removed" + keys);
        },0,delayMs,TimeUnit.MILLISECONDS);
    }

    @Override
    public V put(K key, V value, long timeToLiveMs) {
        if(!validateCacheState()){
            throw new RuntimeException("Cache is not activated");
        }
        ValueContainer valueContainer = new ValueContainer(value,timeToLiveMs);
        return (V) cache.put(key,valueContainer);
    }

    @Override
    public void putAll(Map<K, V> map,long timeToLiveMs) {
        if(!validateCacheState()){
            throw new RuntimeException("Cache is not activated");
        }
        Map<K,ValueContainer<V>> wrapperVMap = new HashMap<>(map.size());
        map.forEach((key,value) -> wrapperVMap.put(key,new ValueContainer<>(value,timeToLiveMs)));
        cache.putAll(wrapperVMap);
    }

    @Override
    public V get(K key) {
        if(!validateCacheState()){
            throw new RuntimeException("Cache is not activated");
        }
        ValueContainer<V> valueContainer = cache.get(key);
        return valueContainer!=null?valueContainer.getValue():null;
    }

    @Override
    public V remove(K key) {
        ValueContainer<V> valueContainer = cache.remove(key);
        return valueContainer!= null?valueContainer.getValue():null;
    }

    @Override
    public void invalidateAll(Set<K> keys) {
        cache.keySet().removeAll(keys);
    }

    @Override
    public void flushAndStop() {
        System.out.println("Shutting-down");
        cache.keySet().clear();
        scheduledExecutorService.shutdown();
    }

    @Override
    public long size() {
        return cache.values().stream().filter(value->!value.isExpired()).count();
    }

    private boolean validateCacheState(){
       return isActive;
    }

    private class ValueContainer<K>{
        private V value;
        private LocalDateTime creationTime;
        private LocalDateTime lastAccessTime;
        private long timeToLiveMs;

        ValueContainer(V value, long timeToLiveMs){
            this.value = value;
            creationTime = LocalDateTime.now();
            lastAccessTime = this.creationTime;
            this.timeToLiveMs = timeToLiveMs;
        }

        public V getValue() {
            return value;
        }

        public LocalDateTime getCreationTime() {
            return creationTime;
        }

        public LocalDateTime getLastAccessTime() {
            return lastAccessTime;
        }

        public boolean isExpired(){
            LocalDateTime executionTime = LocalDateTime.now();
            return (lastAccessTime.getLong(ChronoField.MILLI_OF_DAY) + timeToLiveMs) < executionTime.getLong(ChronoField.MILLI_OF_DAY);
        }
    }

}
