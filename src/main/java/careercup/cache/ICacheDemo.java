package careercup.cache;

public class ICacheDemo {

    public static void main(String[] args) throws InterruptedException {
        ICache<String,String> iCache = new ICacheImpl<>("localCache");
        iCache.put("One","One",10000);
        iCache.put("Two","Two",11000);
        iCache.put("Three","Three",12000);
        iCache.put("Four","Four",13000);
        iCache.put("Five","Five",150000);
        iCache.put("Six","Six",20000);

        Thread.sleep(20000);
        System.out.println("resume!");
        iCache.flushAndStop();
        System.out.println("Done!");

    }
}
