package distributed.objects.map.configure;

import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import instance.Server;

/**
 * Created by bijoy on 17/6/16.
 */
public class Member {

    public static HazelcastInstance getInstance() {
        return MemberConfig.getInstance();
    }


    private static class MemberConfig {
        private static HazelcastInstance getInstance() {
            return Server.memberInstance();
        }

        static {
            addMapConfig(Server.memberConfig());
        }

        private static void addMapConfig(Config config) {
            MapConfig mapConfig = new MapConfig()
                    .setName("*MyMap");
                /*Seting configuration for a specific or group of map*/

                /*Set backup copy -> performance decreases with the count */
                    mapConfig.setBackupCount(1) // default 1
                /* Backup asynchronously*/
                    .setAsyncBackupCount(0) // default 0
                    .setReadBackupData(true) // default false
                    .setInMemoryFormat(InMemoryFormat.BINARY) // data will be stored in serialized binary format (default)
//                .setInMemoryFormat(InMemoryFormat.OBJECT)
        /*data will be stored in deserialized form. This configuration is good for maps where entry processing
        and queries form the majority of all operations and the objects are complex, making the serialization cost
        respectively high*/
/*
        map.get() call read data from backup copy
        improve performance
        may cause data inconsistency
        Hits to the keys in the backups are not related as hits to the original keys, this has an impact on maximum-idle-second, time-to-live expiration
*/
                    .setTimeToLiveSeconds(60 * 5) // default 0, infinite
                    .setMaxIdleSeconds(60 * 5) // default 0, infinite
                    .setEvictionPolicy(EvictionPolicy.LRU) // default NONE
                    .setEvictionPercentage(25); // default 25
            mapConfig.getMaxSizeConfig()
                    .setSize(5000) // no of entry per node
                    .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.PER_NODE);
                /*.setSize(250) // no of entry per partition
                .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.PER_PARTITION);*/
                /*.setSize(100) // in MB per map in each instance
                .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE);*/
                /*.setSize(60) // in percentage per map in each instance
                .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.USED_HEAP_PERCENTAGE);*/
                /*.setSize(500) // in MB per map in each instance
                .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE);*/
                /*.setSize(40) // in percentage per map in each instance
                .setMaxSizePolicy(MaxSizeConfig.MaxSizePolicy.FREE_HEAP_PERCENTAGE);*/
            mapConfig.getMapIndexConfigs()
                    .add(
                            new MapIndexConfig()
                                    .setAttribute("testField")
                                    .setOrdered(true)
                    );
            config.addMapConfig(mapConfig);
        }
    }

}
