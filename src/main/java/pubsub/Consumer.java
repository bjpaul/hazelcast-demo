package pubsub;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

/**
 * Created by bijoy on 15/6/16.
 */
public class Consumer {
    public static void main(String[] args){
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        ITopic<Long> topic = hz.getTopic("myTopic");
        topic.addMessageListener(new MessageListnerIml());
    }
}
