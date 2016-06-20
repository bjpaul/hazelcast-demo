package map.store;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.HazelcastInstance;
import instance.Server;

/**
 * Created by bijoy on 18/6/16.
 */
public class MemberInstance {
    private static HazelcastInstance hazelcastInstance;

    static {
        Config config = Server.memberConfig();
        MapConfig mapConfig = config.getMapConfig("testMapStore");
        MapStoreConfig mapStoreConfig = new MapStoreConfig()
                .setClassName("map.store.configure.cache.EmpMapStore");
/*
        mapStoreConfig.setWriteDelaySeconds(5)
                .setWriteCoalescing(true);
*/

        mapConfig.setMapStoreConfig(mapStoreConfig);

        hazelcastInstance = Server.memberInstance();
    }

    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
