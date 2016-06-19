package distributed.objects.collection.queue;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.IdGenerator;
import distributed.objects.collection.MemberInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        HazelcastInstance hazelcastInstance = MemberInstance.getHazelcastInstance();
        IdGenerator idGenerator = hazelcastInstance.getIdGenerator("idGenerator");
        IQueue<String> queue = hazelcastInstance.getQueue("distributedQueue");
        String nextId;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                nextId = System.getProperty("user.name") + " : " + Thread.currentThread().getName() + " -> " + idGenerator.newId();
                System.out.println("publish :: " + nextId);
                queue.put(nextId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
