package executors;

import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import instance.Server;

import java.util.UUID;

/**
 * Created by bijoy on 18/6/16.
 */
public class LiteMemberInstance {
    private static HazelcastInstance hazelcastInstance;

    static {
        Config config = Server.memberConfig();
        config.setInstanceName(UUID.randomUUID().toString())
                .setLiteMember(true);
        hazelcastInstance = Server.memberInstance();
    }

    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
