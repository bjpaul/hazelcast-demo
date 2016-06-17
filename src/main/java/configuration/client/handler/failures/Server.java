package configuration.client.handler.failures;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {
    public static void main(String[] args) {
        Config config = new Config();
        config.getGroupConfig()
                .setName("intellimeet")
//                .setName("intellimeet1")
                .setPassword("june");
//        config.setLiteMember(true);
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig()
                .setEnabled(false);
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                .addMember("localhost");
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        Set<String> strings = hazelcastInstance.getSet("mySet");
        IAtomicLong iAtomicLong = hazelcastInstance.getAtomicLong("uniqueId");
        strings.add("Instance " + iAtomicLong.incrementAndGet());
    }
}
