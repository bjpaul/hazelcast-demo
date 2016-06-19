package distributed.objects.map.demo.lock;

import com.hazelcast.core.IMap;
import instance.Client;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bijoy on 19/6/16.
 */
public class TestLock {
    private static Random random = new Random();

    public static void main(String[] args) {
        IMap<String, Long> map = Client.instance().getMap("testLock");
        map.put("key", 0l);
        ExecutorService executor = Executors.newFixedThreadPool(15);

        System.out.println("Starting ...");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 15; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    update();
                }
            });
        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("---------Complete---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));

        System.out.println(map.getOrDefault("key", 0l));
    }

    private static void update() {
        for (int i = 0; i < 100; i++) {
            racy();
        }
    }

    private static void racy() {
        IMap<String, Long> map = Client.instance().getMap("testLock");
        Long l = map.get("key");
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("key", ++l);
    }


    private static void pessimisticLock() {
        IMap<String, Long> map = Client.instance().getMap("testLock");
        Long l;
        try {
//            map.lock("key");
            while(!map.tryLock("key", 1, TimeUnit.SECONDS));
            l = map.get("key");
            Thread.sleep(random.nextInt(10));
            map.put("key", ++l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            map.unlock("key");
        }
    }

    private static void optimisticLock() {
        IMap<String, Long> map = Client.instance().getMap("testLock");
        Long oldVal;
        Long newVal;
        while (true) {
            try {
                oldVal = map.get("key");
                Thread.sleep(random.nextInt(10));
                newVal = oldVal + 1;
                if (map.replace("key", oldVal, newVal)) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
