package distributed.objects.map.demo.basic;

import com.hazelcast.core.*;
import com.hazelcast.nio.Address;
import instance.Client;

import java.util.Map;
import java.util.Random;

/**
 * Created by bijoy on 18/6/16.
 */
public class BasicFunctionality {
    public static void main(String[] args) {
        int j = new Random().nextInt(100);
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<String, String> map = hazelcastInstance.getMap("testMap");
        for (int i = j; i < j + 10; i++) {
            map.put(hazelcastInstance.getName() + " " + i, "" + i);
        }
        mapString(hazelcastInstance);
    }


    public static void mapString(HazelcastInstance hazelcastInstance) {
        IMap<String, String> map = hazelcastInstance.getMap("testMap");
        PartitionService partitionService = hazelcastInstance.getPartitionService();
        Partition partition;
        Address address;

        System.out.println("No of entries : " + map.size());
        System.out.println("-----------------------------");
        StringBuilder sb;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb = new StringBuilder();
            partition = partitionService.getPartition(entry.getKey());
            address = partition.getOwner().getAddress();
            sb.append("{ [key -> ");
            sb.append(entry.getKey());
            sb.append("] : [value -> ");
            sb.append(entry.getValue());
            sb.append("] : [partition id -> ");
            sb.append(partition.getPartitionId());
            sb.append("] : [owner -> <");
            sb.append(partition.getOwner().getStringAttribute("user"));
            sb.append(" -> Instance ");
            sb.append(address.getPort() % 10);
            sb.append(">]");
            sb.append(" }");
            System.out.println(sb);
        }
    }
}
