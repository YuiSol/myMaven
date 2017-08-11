package datageneration.data.model.day15_offlinepro.test;

import java.net.URL;

/**
 * Created by tourbis on 2017/7/31.
 */
public class Test {
    public enum CallMethodID{
        WithSecond(13),WithMinute(12),WithHour(10),WithDay(5),WithMonth(2);
        private int id;
        CallMethodID(int id){
            this.id=id;
        }
        public int getId() {
            return id;
        }
    }
    public static void main(String[] args) throws Exception{

       /* System.out.println(CallMethodID.WithSecond.getId());*/
        URL url = new URL(String.format("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?" +
                "query=%s&co=&resource_id=6006&t=1502411312124&ie=utf8&oe=gbk&" +
                "cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110207843300302270397_1502411151781&_=" +
                "1502411151787","116.23.231.214"));
        System.out.println(url.toURI());
    }
}
