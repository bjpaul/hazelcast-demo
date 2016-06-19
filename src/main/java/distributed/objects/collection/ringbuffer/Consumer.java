package distributed.objects.collection.ringbuffer;
import com.hazelcast.ringbuffer.Ringbuffer;
import instance.Client;

/**
 * Created by bijoy on 19/6/16.
 */
public class Consumer {
    public static void main(String[] args) {
        Ringbuffer<String> ringbuffer = Client.instance().getRingbuffer("testRB");
        long sequence = ringbuffer.headSequence();
        System.out.println("Start reading from: " + sequence);
        while (true) {
            try {
                System.out.println("Received :: " + ringbuffer.readOne(sequence));
                sequence++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
