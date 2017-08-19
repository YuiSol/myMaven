package datageneration.data.day15_offlinepro.domain;

import datageneration.data.day15_offlinepro.constance.ConstancePools;
import datageneration.data.day15_offlinepro.utils.IPUtils;
import datageneration.data.day15_offlinepro.utils.TimeUtils;
import datageneration.data.day15_offlinepro.utils.UrlUtils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by tourbis on 2017/8/13.
 */
public class VisitorInfo {
    private String valid;
    private String remote_address;
    private String remote_user;
    private String time_local;
    private String request;
    private String status;
    private String body_bytes_sent;
    private String http_referer;
    private String http_user_agent;
    private static String REQUEST_STARTS[]= ConstancePools.REQUEST_STARTS;

    private static final Random random=new Random();
    static {
        TimeUtils.setTimeBegin("2012-04-17 08:45:12");
    }
    private static List<RegisterUserInfo> data=UrlUtils.getRegisterUserInfo();
    public VisitorInfo(){
    }
    //TODO 不推荐使用,url应该为访问的url
    @Deprecated
    public VisitorInfo(String url) {
        this(UrlUtils.getValid(url)?String.valueOf("false"):String.valueOf("true"),
             IPUtils.getIp(),
             getRemoteUser(),
             TimeUtils.timeFilter(TimeUtils.getTimeWiths(TimeUtils.CallMethodID.WithMinute,7500)),
             UrlUtils.getVisitorUrl(),
             REQUEST_STARTS[random.nextInt(REQUEST_STARTS.length)],
             getRandomFlow(),
             UrlUtils.getVisitorUrl(),
             ConstancePools.USER_AGENT[random.nextInt(ConstancePools.USER_AGENT.length)]
             );
    }

    public VisitorInfo(String valid, String remote_address, String remote_user, String time_local, String request, String status, String body_bytes_sent, String http_referer, String http_user_agent) {
        this.valid = valid;
        this.remote_address = remote_address;
        this.remote_user = remote_user;
        this.time_local = time_local;
        this.request = request;
        this.status = status;
        this.body_bytes_sent = body_bytes_sent;
        this.http_referer = http_referer;
        this.http_user_agent = http_user_agent;
    }
    public void init(String url){

    }
    private static String getRemoteUser(){
        String user[] ={"_", UUID.randomUUID().toString().substring(0,16).replaceAll("-","")};
        return user[random.nextInt(user.length)];
    }
    private static String getRandomFlow(){
        return String.valueOf(random.nextInt(888));
    }

    @Override
    public String toString() {
        return
                "valid='" + valid + '\'' +
                ", remote_address='" + remote_address + '\'' +
                ", remote_user='" + remote_user + '\'' +
                ", time_local='" + time_local + '\'' +
                ", request='" + request + '\'' +
                ", status='" + status + '\'' +
                ", body_bytes_sent='" + body_bytes_sent + '\'' +
                ", http_referer='" + http_referer + '\'' +
                ", http_user_agent='" + http_user_agent + '\'';
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getRemote_address() {
        return remote_address;
    }

    public void setRemote_address(String remote_address) {
        this.remote_address = remote_address;
    }

    public String getRemote_user() {
        return remote_user;
    }

    public void setRemote_user(String remote_user) {
        this.remote_user = remote_user;
    }

    public String getTime_local() {
        return time_local;
    }

    public void setTime_local(String time_local) {
        this.time_local = time_local;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody_bytes_sent() {
        return body_bytes_sent;
    }

    public void setBody_bytes_sent(String body_bytes_sent) {
        this.body_bytes_sent = body_bytes_sent;
    }

    public String getHttp_referer() {
        return http_referer;
    }

    public void setHttp_referer(String http_referer) {
        this.http_referer = http_referer;
    }

    public String getHttp_user_agent() {
        return http_user_agent;
    }

    public void setHttp_user_agent(String http_user_agent) {
        this.http_user_agent = http_user_agent;
    }

    //TODO test()
    public static void test(){
        VisitorInfo visitorInfo = new VisitorInfo();
        IPPool ipPool = new IPPool();
        ipPool.init();//初始化线程池
        for (int i=0;i<60;i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String request = UrlUtils.getVisitorUrl();
            String http_referer = UrlUtils.getVisitorUrl();
            String remote_address =ipPool.getIPFromPool(random);
            String remote_user = getRemoteUser();
            String valid = UrlUtils.getValid(request) ? String.valueOf("false") : String.valueOf("true");
            String time_local = TimeUtils.timeFilter(TimeUtils.getTimeWiths(TimeUtils.CallMethodID.WithMinute, 5));
            String status = REQUEST_STARTS[random.nextInt(REQUEST_STARTS.length)];
            String http_user_agent = ConstancePools.USER_AGENT[random.nextInt(ConstancePools.USER_AGENT.length)];
            String body_bytes_sent = getRandomFlow();
            //set data
            visitorInfo.setValid(valid);
            visitorInfo.setRemote_address(remote_address);
            visitorInfo.setRemote_user(remote_user);
            visitorInfo.setTime_local(time_local);
            visitorInfo.setRequest(request);
            visitorInfo.setStatus(status);
            visitorInfo.setBody_bytes_sent(body_bytes_sent);
            visitorInfo.setHttp_referer(http_referer);
            visitorInfo.setHttp_user_agent(http_user_agent);

            System.out.println(visitorInfo);
        }
    }

    public static void main(String[] args) throws Exception{
        VisitorInfo.test();

    }

}
