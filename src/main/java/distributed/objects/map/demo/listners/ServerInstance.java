package distributed.objects.map.demo.listners;

import com.hazelcast.config.EntryListenerConfig;
import com.hazelcast.core.IMap;
import instance.Server;

import java.util.Map;

/**
 * Created by bijoy on 18/6/16.
 */
public class ServerInstance {
    public static void main(String[] args) {
        Server.memberConfig()
                .getMapConfig("testMap")
                .addEntryListenerConfig(
                        new EntryListenerConfig()
                                .setImplementation(new CustomEntryListner())
                );

        IMap<String, String> map = Server.memberInstance().getMap("testMap");
        System.out.println("Updating--------------");
        for (int i = 0; i < 10; i++) {
            map.put("" + i, "" + i);
        }

        System.out.println("Reading ---------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }

        map.evict("5");
        map.remove("7");
        map.replace("4", "FOUR");
        map.put("3", "THREE");

        System.out.println("Reading ---------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }

        map.lock("2");
        map.lock("8");

        map.evictAll();

        System.out.println("Reading ---------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
        map.unlock("2");
        map.unlock("8");
        map.clear();
        System.out.println("Reading ---------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}
