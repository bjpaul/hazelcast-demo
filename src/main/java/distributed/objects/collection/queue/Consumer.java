package distributed.objects.collection.queue;
import com.hazelcast.core.IQueue;
import instance.Client;

/**
 * Created by bijoy on 19/6/16.
 */
public class Consumer {
    public static void main(String[] args) {
        IQueue<String> queue = Client.instance().getQueue("distributedQueue");
        while (true) {
            try {
                System.out.println("Received :: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
