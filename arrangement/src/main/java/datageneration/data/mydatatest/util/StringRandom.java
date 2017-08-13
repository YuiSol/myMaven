package datageneration.data.mydatatest.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by YuiSol on 2017/8/12.
 */
public class StringRandom {
    private static Random random = new Random();

    private StringRandom() {
    }

    public static int count = 26;
    public static int size = 97;
    public static int size1 = 65;
    public static int[] c1 = {size, size1};
    private static StringBuffer sb = new StringBuffer();
    public static String getName(int size,boolean flag){
        if(flag==false) return getName(size);
        else return getName(random.nextInt(size));
    }
    public static String getName(int size) {
        sb.setLength(0);
        if (size <= 0) {
            return null;
        }
        int c = random.nextInt(count);
        sb.append((char) (c + size1));
        for (int i = 1; i < size; i++) {
            c = random.nextInt(count);
            int c2 = random.nextInt(c1.length);
            int c3 = random.nextInt(count);
            sb.append((char) (c1[c2] + c3));
        }
        return sb.toString();
    }

    public static String getNO() {
        String uuid = UUID.randomUUID().toString();
        int inall = uuid.indexOf("-");
        return uuid.substring(0, inall);
    }
    public static int getNO(int size){
        return random.nextInt(size);
    }
}