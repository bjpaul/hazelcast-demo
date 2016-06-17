package pubsub;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.util.Random;

/**
 * Created by bijoy on 15/6/16.
 */
public class Publisher {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<Long> topic = hz.getTopic("myTopic");
        Random random = new Random();
        long messageId = 0;
        while (true) {
            topic.publish(messageId);
            messageId++;

            System.out.println("Written: " + messageId);

            // add a bit of randomization
            Thread.sleep(random.nextInt(100));
        }
    }
}
