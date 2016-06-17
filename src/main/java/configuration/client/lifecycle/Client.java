package configuration.client.lifecycle;


/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        while (true){
            try {
                 System.out.println("Receive ==>> " + ClientInstance.instance().getQueue("testQueue").take());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
