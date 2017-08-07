package Test;

import java.util.Objects;

/**
 * Created by YuiSol on 2017/8/2.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        String str="aaa";
        Class clazz=str.getClass();
        Object o = clazz.newInstance();
        System.out.println(o.equals("aaa"));
    }
}
