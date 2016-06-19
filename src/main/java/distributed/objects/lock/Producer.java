package distributed.objects.lock;

import com.hazelcast.core.*;
import instance.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bijoy on 19/6/16.
 */
public class Producer {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Starting ...");

        for (int i = 0; i < 3; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    publish();
                }
            });
        }
        executor.shutdown();
    }

    private static void publish() {
        HazelcastInstance hazelcastInstance = Client.instance();
        IdGenerator idGenerator = hazelcastInstance.getIdGenerator("idGenerator");
        IList<String> list = hazelcastInstance.getList("distributedList");
        ILock lock = hazelcastInstance.getLock("distributedLock");
        ICondition publisher = lock.newCondition("publisherCondition");
        ICondition consumer = lock.newCondition("consumerCondition");
        String nextId;
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (list.size() == 100 * 100){
                    publisher.await();
                }
                nextId = System.getProperty("user.name") + " : " + Thread.currentThread().getName() + " -> " + idGenerator.newId();
                System.out.println("publish :: " + nextId);
                list.add(nextId);
                consumer.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
