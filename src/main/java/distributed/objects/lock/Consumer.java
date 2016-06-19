package distributed.objects.lock;
import com.hazelcast.core.*;
import instance.Client;

/**
 * Created by bijoy on 19/6/16.
 */
public class Consumer {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Client.instance();
        IList<String> list = hazelcastInstance.getList("distributedList");
        ILock lock = hazelcastInstance.getLock("distributedLock");
        ICondition publisher = lock.newCondition("publisherCondition");
        ICondition consumer = lock.newCondition("consumerCondition");
        int lastIndex = 0;
        while (true) {
            lock.lock();
            try {
                while (list.isEmpty() || (lastIndex > list.size() - 1)){
                    System.out.println("list size -> "+list.size());
                    consumer.await();
                }
                System.out.println("Received :: " + list.get(lastIndex));
                lastIndex++;
                publisher.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
