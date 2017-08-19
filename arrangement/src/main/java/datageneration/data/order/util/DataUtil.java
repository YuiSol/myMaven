package datageneration.data.order.util;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class DataUtil {
    private DataUtil(){}
    private static Random random=new Random();
    private static StringBuffer sb=new StringBuffer();
    public String getUUid_Min(){
        String uuid=UUID.randomUUID().toString();
        int index=uuid.indexOf("-");
        return uuid.substring(0,index);
    }
    public static String getUUid_Max(){
        return UUID.randomUUID().toString().replaceAll("-","");

    }
    public static String randomName(int min_size,int max_size){
        sb.setLength(0);
        if (min_size <= 0||max_size<min_size) {
            return null;
        }
        sb.append((char) (random.nextInt(26)+65));
        int size=0;
        while (size<min_size){
            size=random.nextInt(max_size);
        }
        for (int i = 1; i < size; i++) {
            sb.append((char) (random.nextInt(26)+97));
        }
        return sb.toString();
    }
    public  static int randomNumber(int size) {
        return random.nextInt(size);
    }
    public static String randomUUID(){
        String str=UUID.randomUUID().toString();
        int index=str.indexOf("-");
        return str.substring(0,index);
    }
}
