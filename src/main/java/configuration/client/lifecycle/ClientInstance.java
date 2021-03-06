package configuration.client.lifecycle;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

/**
 * Created by bijoy on 16/6/16.
 */
public class ClientInstance implements LifecycleListener{
    private static ClientConfig clientConfig ;
    private static HazelcastInstance hazelcastInstance ;
    private static ClientInstance clientInstance ;

    static {
        clientConfig = clientConfig();
        clientInstance = new ClientInstance();
    }

    public static HazelcastInstance instance(){
        while (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()){
            try{
                hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
                hazelcastInstance.getLifecycleService().addLifecycleListener(clientInstance);
            }catch (Exception ex){
                System.out.println("Exception "+ex.getMessage());
            }
        }
        return hazelcastInstance;
    }

    private static ClientConfig clientConfig(){
        ClientConfig clientConfig = new ClientConfig();
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
        System.out.println("Client -> "+event);
        if(event.getState().equals(LifecycleEvent.LifecycleState.SHUTDOWN)){
            instance();
        }
    }
}
