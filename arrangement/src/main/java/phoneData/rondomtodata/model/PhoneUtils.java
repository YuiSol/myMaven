package phoneData.rondomtodata.model;

import com.alibaba.fastjson.JSON;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PipedWriter;
import java.net.URL;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/1.
 * https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=18825151514
 */
public class PhoneUtils {
    private PhoneUtils(){}
    private static final Random random=new Random();
    private  static  final String[] PHONE_PREFIX={"134","135","136",
                                    "137","138","159",
                                    "181","183","187",
                                    "188","150","151",
                                    "152","157","158"};
    private static  final StringBuilder sb = new StringBuilder();
    public static Object getInstance(long phoneNumber,Class clazz) throws Exception{
        return getInstance(Long.toString(phoneNumber),clazz);
    }
    public static Object getInstance(String phoneNumber,Class clazz) throws Exception{
        URL url = new URL(String.format("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=%s",phoneNumber));
        InputStream inputStream = url.openStream();
        BufferedInputStream bs = new BufferedInputStream(inputStream);
        byte[] buffer=new byte[1024];
        int len = bs.read(buffer);
        String json = new String(buffer, 0, len, "GBK");
        int i = json.indexOf("{");
        String substring = json.substring(i, json.length());
        // json="{'mts'='广东','catName'='中国移动'}";
        Object obj = JSON.parseObject(substring, clazz);
        return obj;
    }
    public static String getRandomPhoneNumber(){
        sb.delete(0,sb.length());//清空容器
        int index = random.nextInt(PHONE_PREFIX.length);
        String phoneNumber=PHONE_PREFIX[index];
        sb.append(phoneNumber);
        for(int i=0;i<8;i++){
           sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
