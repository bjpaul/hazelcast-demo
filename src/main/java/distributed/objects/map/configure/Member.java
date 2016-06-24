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
            config.addMapConfig(mapConfig);
        }
    }

}
