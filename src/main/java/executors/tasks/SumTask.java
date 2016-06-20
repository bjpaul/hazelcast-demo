package executors.tasks;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by bijoy on 20/6/16.
 */
public class SumTask implements Callable<Integer>, Serializable, HazelcastInstanceAware {

    private transient HazelcastInstance hz;

    public void setHazelcastInstance(HazelcastInstance hz) {
        this.hz = hz;
    }

    public Integer call() throws Exception {
        IMap<String, Integer> map = hz.getMap("testExecutorMap");
        int result = 0;
        for (String key : map.localKeySet()) {
            System.out.println(hz.getName()+" | Calculating for key: " + key);
            result += map.get(key);
        }
        return result;
    }
}
