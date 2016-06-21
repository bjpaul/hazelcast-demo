package query;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.HazelcastInstance;
import instance.Server;

/**
 * Created by bijoy on 18/6/16.
 */
public class MemberInstance {
    private static HazelcastInstance hazelcastInstance;

    static {
        Config config = Server.memberConfig();
        config.getMapConfig("employeeQueryMap")
                .addMapIndexConfig(
                        new MapIndexConfig()
                                .setAttribute("active")
                                .setOrdered(false)
                )
                .addMapIndexConfig(
                        new MapIndexConfig()
                                .setAttribute("age")
                                .setOrdered(true)
                )
                .setInMemoryFormat(InMemoryFormat.OBJECT);
        hazelcastInstance = Server.memberInstance();
    }

    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }


}
