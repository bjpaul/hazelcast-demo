package executors.tasks;

import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;
import com.hazelcast.core.MultiExecutionCallback;

import java.util.Map;

/**
 * Created by bijoy on 20/6/16.
 */
public class ExecutorsMethods {

    public static void submitToAllMembers(IExecutorService executor){
        executor.submitToAllMembers(new SumTask(), new MultiExecutionCallback() {
            @Override
            public void onResponse(Member member, Object value) {
                System.out.println(member.getAddress()+" | Local result: " + value);
            }

            @Override
            public void onComplete(Map<Member, Object> values) {
                int sum = 0;
                for(Object o:values.values()){
                    Integer integer = (Integer)o;
                    sum += integer;
                }
                System.out.println("Total result: " + sum);
            }
        });
    }
}
