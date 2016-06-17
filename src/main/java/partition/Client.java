package partition;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Partition;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    private static HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
    public static void main(String[] args){

        System.out.println("<=========== All partitions ============>");
        for(Partition partition:hazelcastInstance.getPartitionService().getPartitions()){
            System.out.println("partition id : "+partition.getPartitionId()+", owner : "+partition.getOwner());
        }
        System.out.println("*****************************************");
        Partition partition = hazelcastInstance.getPartitionService().getPartition("Hello");
        System.out.println("Partition ID for object Hello : "+partition.getPartitionId()+", Owner : "+partition.getOwner());
    }
}
