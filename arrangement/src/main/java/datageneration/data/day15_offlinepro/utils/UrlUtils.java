package datageneration.data.day15_offlinepro.utils;

import datageneration.data.day15_offlinepro.constance.ConstancePools;
import datageneration.data.day15_offlinepro.dao.RegisterUserInfoSQLDataImpl;
import datageneration.data.day15_offlinepro.domain.Article;
import datageneration.data.day15_offlinepro.domain.RegisterUserInfo;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/11.
 * 文章作者 --对应唯一的文章ID
 * 文章所属类别
 * 文章内容
 */
public class UrlUtils {
    private UrlUtils(){}
    private static RegisterUserInfoSQLDataImpl registerUserInfoSQLData;
    private static RegisterUserInfo registerUserInfo;
    private static List<RegisterUserInfo> data;
    private static final Random random=new Random();
    private static final String URL[]={ConstancePools.HOME_PAGE,ConstancePools.NAVIGATION_PHOTO,ConstancePools.NAVIGATION_COMMUNITY,
                                        ConstancePools.NAVIGATION_CULTURE,ConstancePools.NAVIGATION_LIFE,ConstancePools.ERROR_PAGE};
    private static final String CONTEXT[]= Article.PART_OF;
    static {
        registerUserInfo=new RegisterUserInfo();
        registerUserInfoSQLData=new RegisterUserInfoSQLDataImpl();
        data=getRegisterUserInfo();
    }

    public static String urlEncode(String url)throws Exception{
        return URLEncoder.encode(url, ConstancePools.ENCODE);
    }
    public static String urlDecode(String url) throws Exception{
        return URLDecoder.decode(url, ConstancePools.ENCODE);
    }

    /**
     * 获取访问的URL
     * @return
     */
    public static String getVisitorUrl(){
        String url = getRandomUrl();
        return url.equals(URL[URL.length - 1])?URL[URL.length-1]:
                String.format("%s%s%s",url,getRandomRegisterUserInfo().getUserName(),getRandomContext());
    }

    private static RegisterUserInfo getRandomRegisterUserInfo(){
        int index = random.nextInt(data.size());
        return data.get(index);
    }
    private static String getRandomUrl(){
        return URL[random.nextInt(URL.length)];
    }
    private static String getRandomContext(){
        return String.format("%s%s","/",Article.getRandomTitle());
    }
    @Deprecated
    public List<RegisterUserInfo> getRegisterUserInfo(RegisterUserInfoSQLDataImpl registerUserInfoSQLData,RegisterUserInfo registerUserInfo){
        return getRegisterUserInfo();
    }
    public static List<RegisterUserInfo> getRegisterUserInfo(){
        return registerUserInfoSQLData.query("SELECT * FROM `register_info`.`t_user`", registerUserInfo);
    }

    public static String getPathInfo(String urls) throws Exception{
        URL url = new URL(urls);
        return url.getPath();
    }

    public static boolean getValid(String url){
        return url.contains(ConstancePools.ERROR_FLAG);
    }
}
