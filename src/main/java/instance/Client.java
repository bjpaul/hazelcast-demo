package instance;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

/**
 * Created by bijoy on 17/6/16.
 */
public class Client {
    public static HazelcastInstance instance() {
        return ClientInstance.clientInstance();
    }

    public static ClientConfig config(){
        return ClientInstance.clientConfig;
    }

    private static class ClientInstance implements LifecycleListener {
        private static ClientConfig clientConfig;
        private static HazelcastInstance hazelcastInstance;
        private static ClientInstance client;

        static {
            clientConfig = clientConfig();
            client = new ClientInstance();
        }

        public static HazelcastInstance clientInstance() {
            while (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()) {
                try {
                    hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
                    hazelcastInstance.getLifecycleService().addLifecycleListener(client);
                } catch (Exception ex) {
                    System.out.println("Exception " + ex.getMessage());
                }
            }
            return hazelcastInstance;
        }

        private static ClientConfig clientConfig() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setInstanceName("CLIENT : "+System.getProperty("user.name"));
            clientConfig.getGroupConfig()
                    .setName("intellimeet")
                    .setPassword("june");
            clientConfig.getNetworkConfig()
                    .addAddress("localhost")
                    .setConnectionAttemptLimit(5) // by default 2
                    .setConnectionAttemptPeriod(1000) // by default 3000 ms
                    .setConnectionTimeout(3000) // by default 5000 ms
                    .setSmartRouting(true) // by default true
                    .setRedoOperation(true); // by default true for read only operation
            return clientConfig;
        }

        @Override
        public void stateChanged(LifecycleEvent event) {
            if (event.getState().equals(LifecycleEvent.LifecycleState.SHUTDOWN)) {
                clientInstance();
            }
        }
    }
}
