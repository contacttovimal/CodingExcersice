package careercup.cache;

import java.util.Map;
import java.util.Set;

public interface ICache<K,V>  {
    public V put(K key,V value,long timeToLiveMs);
    public void putAll(Map<K,V> map,long timeToLiveMs);
    public V get(K key);
    public V remove(K key);
    public void invalidateAll(Set<K> keys);
    public void flushAndStop();
    public long size();
}
