package configuration.client.handler.failures;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig()
                .setName("intellimeet")
                .setPassword("june");
        clientConfig.getNetworkConfig()
                /*.addAddress("localhost")
                .addAddress("127.0.0.1")*/
                .addAddress("localhost")
                .setConnectionAttemptLimit(10) // by default 2
                .setConnectionAttemptPeriod(1000) // by default 3000 ms
                .setConnectionTimeout(3000) // by default 5000 ms
                .setSmartRouting(true) // by default true
                .setRedoOperation(true); // by default true for read only operation

        HazelcastClient.newHazelcastClient(clientConfig);
    }
}
