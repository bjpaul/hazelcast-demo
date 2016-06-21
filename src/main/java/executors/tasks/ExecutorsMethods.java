package executors.tasks;

import com.hazelcast.core.*;

import java.util.Map;

/**
 * Created by bijoy on 20/6/16.
 */
public class ExecutorsMethods {

    public static void submitToAllMembers(IExecutorService executor) {
        executor.submitToAllMembers(new SumTask(), new MyMultiExecutionCallback());
    }

    public static void submitToMembers(IExecutorService executor) {
        executor.submitToMembers(new SumTask(true), new LiteMemberSelector(), new MyMultiExecutionCallback());
    }

    public static void submit(IExecutorService executor) {
        executor.submit(new SumTask(true), new LiteMemberSelector(), new MyExecutionCallback());
    }

    public static void submitToMember(IExecutorService executor, Member member) {
        executor.submitToMember(new SumTask(), member, new MyExecutionCallback());
    }

    public static void submitToKeyOwner(IExecutorService executor, Object key) {
        executor.submitToKeyOwner(new SumTask(), key, new MyExecutionCallback());
    }

    public static void submitToAny(IExecutorService executor) {
        executor.submit(new SumTask(), new MyExecutionCallback());
    }

    private static class MyExecutionCallback implements ExecutionCallback<Integer> {

        @Override
        public void onResponse(Integer value) {
            System.out.println(" Total result: " + value);
        }

        @Override
        public void onFailure(Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static class MyMultiExecutionCallback implements MultiExecutionCallback {
        @Override
        public void onResponse(Member member, Object value) {
            System.out.println(member.getAddress() + " | Local result: " + value);
        }

        @Override
        public void onComplete(Map<Member, Object> values) {
            int sum = 0;
            for (Object o : values.values()) {
                Integer integer = (Integer) o;
                sum += integer;
            }
            System.out.println("Total result: " + sum);
        }
    }

    private static class LiteMemberSelector implements MemberSelector {
        @Override
        public boolean select(Member member) {
            return member.isLiteMember();
        }
    }
}
