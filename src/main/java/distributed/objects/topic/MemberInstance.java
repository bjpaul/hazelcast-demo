package distributed.objects.topic;

import com.hazelcast.config.Config;
import com.hazelcast.config.ReliableTopicConfig;
import com.hazelcast.config.RingbufferConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.TopicOverloadPolicy;
import instance.Server;

/**
 * Created by bijoy on 18/6/16.
 */
public class MemberInstance {
    private static Config config;
    private static HazelcastInstance hazelcastInstance;

    static {
        config = Server.memberConfig();
        config
                .addRingBufferConfig(
                        new RingbufferConfig("distributedReliableTopic")
                                .setBackupCount(1)
                                .setCapacity(100 * 1000)
                                .setTimeToLiveSeconds(60 * 60)
                )
                .addReliableTopicConfig(
                        new ReliableTopicConfig("distributedReliableTopic")
                        .setTopicOverloadPolicy(TopicOverloadPolicy.DISCARD_OLDEST)
                );
        hazelcastInstance = Server.memberInstance();
    }

    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
