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
                .setPassword("june");
        Hazelcast.newHazelcastInstance(config);
    }

}
