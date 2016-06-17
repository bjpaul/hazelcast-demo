package configuration.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;

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
//                .addMember("localhost.127.0.0.1")
                .addMember("localhost");
        Hazelcast.newHazelcastInstance(config);
    }
}
