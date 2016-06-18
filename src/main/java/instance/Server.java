package instance;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by bijoy on 17/6/16.
 */
public class Server {

    public static Config memberConfig(){
        return ServerInstance.serverConfig();
    }

    public static HazelcastInstance memberInstance(){
        return ServerInstance.serverInstance();
    }


    private static class ServerInstance{
        private static HazelcastInstance hazelcastInstance;
        private static Config config;
        static {
            config = config();
        }


        private static HazelcastInstance serverInstance(){
            if(hazelcastInstance == null){
                hazelcastInstance = createInstance();
            }
            return hazelcastInstance;
        }

        private static HazelcastInstance createInstance() {
            return Hazelcast.newHazelcastInstance(config);
        }

        private static Config serverConfig(){
            return config;
        }

        private static Config config() {
            Config config = new Config();
            config.getGroupConfig()
                    .setName("intellimeet")
                    .setPassword("june");
            JoinConfig joinConfig = config.getNetworkConfig().getJoin();
            joinConfig.getMulticastConfig()
                    .setEnabled(false);
            joinConfig.getTcpIpConfig()
                    .setEnabled(true)
                    .addMember("localhost");
            return config;
        }

    }

}