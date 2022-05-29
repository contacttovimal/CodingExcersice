package company;

import java.util.Map;
import java.util.TreeMap;

    class SnapShotManagerImpl implements SnapShotManager {
        Map<CustomKey, Double> marketDataMap = new TreeMap<CustomKey, Double>();
        private int capacity;

        public SnapShotManagerImpl() {
            this.capacity = 3;
        }

        public SnapShotManagerImpl(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public SnapShot getSnapShot() {
            StringBuilder data = new StringBuilder();
            int count=0;
        marketDataMap.entrySet().forEach(entry->{
            data.append(entry.getKey()).append("-");
            data.append(entry.getValue()).append("\n");

        });
            return new SnapShot(data.toString());
        }

        @Override
        public void onTick(String symbol, double price) {
            marketDataMap.put(new CustomKey(symbol, System.currentTimeMillis()), price);
        }
    }

    class CustomKey implements Comparable<CustomKey> {
        private String RIC;
        private long millis;
        public CustomKey(String RIC, long millis){
            this.RIC = RIC;
            this.millis = millis;
        }
        public String getRIC(){return RIC;}

        @Override
        public int compareTo(CustomKey o) {
            if(o instanceof CustomKey){
                CustomKey other= (CustomKey) o;
                return other.getMillis()> this.getMillis()?1:-1;
            }
            return 0;
        }

        public long getMillis(){return millis;}

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof CustomKey){
                CustomKey other = (CustomKey) obj;
                return other.RIC.equals(this.RIC);
            }
            return true;
        }

    }

    interface SnapShotManager{
        void onTick(String symbol, double price);
        SnapShot getSnapShot();
    }

    class SnapShot {
        private String dataString;
        public SnapShot(String data){
            this.dataString = data;
        }
        public String getDataString(){return dataString;}

        @Override
        public String toString() {
            return dataString;
        }
    }

public class Solution {
    public static void main(String[] args) {

        SnapShotManager snapShotManagerImpl = new SnapShotManagerImpl(3);

        snapShotManagerImpl.onTick("IBM",50.0);
        snapShotManagerImpl.onTick("MSFT",120.0);
        snapShotManagerImpl.onTick("GOOG",200.0);
        snapShotManagerImpl.onTick("MSFT",121.0);
        snapShotManagerImpl.onTick("GOOG",200.2);
        snapShotManagerImpl.onTick("GOOG",200.1);
        snapShotManagerImpl.onTick("IBM",49.2);
        snapShotManagerImpl.onTick("MSFT",121.0);

        System.out.println(snapShotManagerImpl.getSnapShot());

    }

}
