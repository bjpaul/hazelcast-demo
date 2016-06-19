package distributed.objects.map.demo.basic;

import com.hazelcast.core.EntryView;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bijoy on 19/6/16.
 */
public class OtherFeatures {
    public static void main(String[] args){

        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<String, String> map = hazelcastInstance.getMap("otherMap");
        /*
        ===============================
         Add or update methods
        ================================
        */
        map.putAsync("key", "value");
        map.putAsync("key", "value", 10, TimeUnit.MINUTES);
        map.replace("key", "value");
        map.set("key", "value");
        map.set("key", "value", 10, TimeUnit.MINUTES);

        /*
        ===============================
         Read methods
        ================================
         */
          map.getAsync("key"); // returns future object
          map.getOrDefault("key","default");

        /*
        ===============================
         Entry statistics
        ================================
         */
        map.put("testKey", "testValue", 10, TimeUnit.MINUTES);
        EntryView entryView = map.getEntryView("testKey");
        System.out.println("Cost -> " + entryView.getCost()+" bytes");
        System.out.println("Creation time -> " + new Date(entryView.getCreationTime()));
        System.out.println("Expiration time -> " + new Date(entryView.getExpirationTime()));
        System.out.println("version -> " + entryView.getVersion());
        System.out.println("No of hits -> " + entryView.getHits());
        System.out.println("Last access time -> " + new Date(entryView.getLastAccessTime()));
        System.out.println("Last updated time -> " + new Date(entryView.getLastUpdateTime()));
        System.out.println("Ttl -> " + entryView.getTtl()+" sec");

        /*
        ===============================
         Remove or delete methods
        ================================
        */
        map.delete("key");
        map.remove("key", "value");
        map.removeAsync("key"); //returns future object
    }
}
