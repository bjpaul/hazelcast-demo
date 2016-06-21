package executors.tasks;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by bijoy on 20/6/16.
 */
public class SumTask implements Callable<Integer>, Serializable, HazelcastInstanceAware {

    final static long serialVersionUID = 1L;
    private transient HazelcastInstance hz;
    private boolean readAll = false;

    public SumTask(){}
    public SumTask(boolean readAll){
        this.readAll = readAll;
    }
    public void setHazelcastInstance(HazelcastInstance hz) {
        this.hz = hz;
    }

    public Integer call() throws Exception {
        IMap<String, Integer> map = hz.getMap("testExecutorMap");

        Set<String> keys;
        if(readAll){
            keys = map.keySet();
        }else {
            keys = map.localKeySet();
        }

        int result = 0;
        for (String key : keys) {
            result += map.get(key);
        }
        System.out.println(hz.getName()+" | Total : " + result);
        return result;
    }
}
