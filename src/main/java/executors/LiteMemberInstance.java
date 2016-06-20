package executors;

import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.core.HazelcastInstance;
import instance.Server;

/**
 * Created by bijoy on 18/6/16.
 */
public class LiteMemberInstance {
    private static HazelcastInstance hazelcastInstance;

    static {
        Config config = Server.memberConfig();
        config.setLiteMember(true);
        hazelcastInstance = Server.memberInstance();
    }

    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
