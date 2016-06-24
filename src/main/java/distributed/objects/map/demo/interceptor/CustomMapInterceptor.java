package distributed.objects.map.demo.interceptor;

import com.hazelcast.map.MapInterceptor;

/**
 * Created by bijoy on 19/6/16.
 */
public class CustomMapInterceptor implements MapInterceptor {
    @Override
    public Object interceptGet(Object value) {
        return "interceptGet -> "+value;
    }

    @Override
    public void afterGet(Object value) {
        System.out.println("------------afterGet---------------");
    }

    @Override
    public Object interceptPut(Object oldValue, Object newValue) {
        System.out.println("------------interceptPut "+newValue+" ---------------");
        if(!(newValue instanceof String)){
            throw new IllegalStateException("other than string type value can not be set");
        }
        return newValue;
    }

    @Override
    public void afterPut(Object value) {
        System.out.println("***********afterPut "+value+"*********************");
    }

    @Override
    public Object interceptRemove(Object removedValue) {
        System.out.println("------------interceptRemove "+removedValue+" ---------------");
        if(removedValue.equals("LOCK")){
            throw new IllegalStateException("LOCK values cannot be removed");
        }
        return removedValue;
    }

    @Override
    public void afterRemove(Object value) {
        System.out.println("~~~~~~~~~~~~~~~~~~~afterRemove "+value+"~~~~~~~~~~~~~~~");
    }
}
