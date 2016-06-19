package distributed.objects.topic.reliable;

import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import instance.Client;

/**
 * Created by bijoy on 19/6/16.
 */
public class Consumer {
    public static void main(String[] args) {
        ITopic<String> topic = Client.instance().getReliableTopic("distributedReliableTopic");
        topic.addMessageListener(new MessageListenerImpl());
    }

    private static class MessageListenerImpl implements MessageListener<String> {
        public void onMessage(Message<String> m) {
            System.out.println("Received :: " + m.getMessageObject());
        }
    }
}
