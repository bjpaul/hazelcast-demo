package pubsub;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

/**
 * Created by bijoy on 15/6/16.
 */
public class MessageListnerIml implements MessageListener<Long> {

    @Override
    public void onMessage(Message<Long> m) {
        System.out.println("Received: " + m.getMessageObject());
    }
}
