package distributed.objects.map.demo.interceptor;

import com.hazelcast.core.IMap;
import distributed.objects.map.configure.Member;

/**
 * Created by bijoy on 18/6/16.
 */
public class ServerInstance {
    public static void main(String[] args) {
        IMap<Object, Object> map = Member.getInstance().getMap("testMap");
        map.addInterceptor(new CustomMapInterceptor());

        try{
            System.out.println(map.put("1", "ONE"));
            System.out.println(map.put("lock", "LOCK"));
            System.out.println(map.put("1", 1));
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(map.get("1"));
        try {
            System.out.println(map.remove("lock"));
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }
    }
}
