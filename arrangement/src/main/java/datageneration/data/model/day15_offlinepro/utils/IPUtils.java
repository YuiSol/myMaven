package datageneration.data.model.day15_offlinepro.utils;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/11.
 */
public class IPUtils {
    private IPUtils(){}
    private static final Random random=new Random();
    private static final StringBuilder sb=new StringBuilder();
    public static String getIp(){
        sb.delete(0, sb.length());
        for(int i=0;i<4;i++){
            int num = random.nextInt(256);
            if(i<3){
                sb.append(num).append(".");
            }else{
                sb.append(num);
            }
        }
        return sb.toString();
    }
    public static class Data{
        private String location;
        private String titlecont;
        private String origip;
        private String origipquery;
        private String showlamp;
        private String showLikeShare;
        private String shareImage;
        private String extendedLocation;
        private String originQuery;
        private String tplt;
        private String resourceid;
        private String fetchkey;
        private String appinfo;
        private String role_id;
        private String disp_type;

        public Data() {
        }

        public Data(String location, String titlecont, String origip, String origipquery, String showlamp, String showLikeShare, String shareImage, String extendedLocation, String originQuery, String tplt, String resourceid, String fetchkey, String appinfo, String role_id, String disp_type) {
            this.location = location;
            this.titlecont = titlecont;
            this.origip = origip;
            this.origipquery = origipquery;
            this.showlamp = showlamp;
            this.showLikeShare = showLikeShare;
            this.shareImage = shareImage;
            this.extendedLocation = extendedLocation;
            this.originQuery = originQuery;
            this.tplt = tplt;
            this.resourceid = resourceid;
            this.fetchkey = fetchkey;
            this.appinfo = appinfo;
            this.role_id = role_id;
            this.disp_type = disp_type;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTitlecont() {
            return titlecont;
        }

        public void setTitlecont(String titlecont) {
            this.titlecont = titlecont;
        }

        public String getOrigip() {
            return origip;
        }

        public void setOrigip(String origip) {
            this.origip = origip;
        }

        public String getOrigipquery() {
            return origipquery;
        }

        public void setOrigipquery(String origipquery) {
            this.origipquery = origipquery;
        }

        public String getShowlamp() {
            return showlamp;
        }

        public void setShowlamp(String showlamp) {
            this.showlamp = showlamp;
        }

        public String getShowLikeShare() {
            return showLikeShare;
        }

        public void setShowLikeShare(String showLikeShare) {
            this.showLikeShare = showLikeShare;
        }

        public String getShareImage() {
            return shareImage;
        }

        public void setShareImage(String shareImage) {
            this.shareImage = shareImage;
        }

        public String getExtendedLocation() {
            return extendedLocation;
        }

        public void setExtendedLocation(String extendedLocation) {
            this.extendedLocation = extendedLocation;
        }

        public String getOriginQuery() {
            return originQuery;
        }

        public void setOriginQuery(String originQuery) {
            this.originQuery = originQuery;
        }

        public String getTplt() {
            return tplt;
        }

        public void setTplt(String tplt) {
            this.tplt = tplt;
        }

        public String getResourceid() {
            return resourceid;
        }

        public void setResourceid(String resourceid) {
            this.resourceid = resourceid;
        }

        public String getFetchkey() {
            return fetchkey;
        }

        public void setFetchkey(String fetchkey) {
            this.fetchkey = fetchkey;
        }

        public String getAppinfo() {
            return appinfo;
        }

        public void setAppinfo(String appinfo) {
            this.appinfo = appinfo;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getDisp_type() {
            return disp_type;
        }

        public void setDisp_type(String disp_type) {
            this.disp_type = disp_type;
        }

        @Override
        public String toString() {
            return
                    "location='" + location + '\'' +
                    ", titlecont='" + titlecont + '\'' +
                    ", origip='" + origip + '\'' +
                    ", origipquery='" + origipquery + '\'' +
                    ", showlamp='" + showlamp + '\'' +
                    ", showLikeShare='" + showLikeShare + '\'' +
                    ", shareImage='" + shareImage + '\'' +
                    ", extendedLocation='" + extendedLocation + '\'' +
                    ", originQuery='" + originQuery + '\'' +
                    ", tplt='" + tplt + '\'' +
                    ", resourceid='" + resourceid + '\'' +
                    ", fetchkey='" + fetchkey + '\'' +
                    ", appinfo='" + appinfo + '\'' +
                    ", role_id='" + role_id + '\'' +
                    ", disp_type='" + disp_type;
        }
    }
    public static class IP{
        private String status;
        private String t;
        private String set_cache_time;
        private List<Data> data;

        public IP() {
        }

        public IP(String status, String t, String set_cache_time, List<Data> data) {
            this.status = status;
            this.t = t;
            this.set_cache_time = set_cache_time;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getSet_cache_time() {
            return set_cache_time;
        }

        public void setSet_cache_time(String set_cache_time) {
            this.set_cache_time = set_cache_time;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return
                    "status='" + status + '\'' +
                    ", t='" + t + '\'' +
                    ", set_cache_time='" + set_cache_time + '\'' +
                    ", data=" + data;
        }
    }

    /**
     * 把IP封装到对象当中
     * @param ip
     * @return
     * @throws Exception
     */
    public static IP ip2site(String ip)throws Exception{
        URL url = new URL(String.format("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?" +
                "query=%s&co=&resource_id=6006&t=1502411312124&ie=utf8&oe=gbk&" +
                "cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110207843300302270397_1502411151781&_=" +
                "1502411151787",ip));
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));
        char[] buffer = new char[1024 * 1024];
        int len = br.read(buffer);
        String ips = new String(buffer, 0, len);
        int start = ips.indexOf("(")+1;
        int end = ips.lastIndexOf(")");
        String result_ip = ips.substring(start, end);
        IP obj_ip = JSON.parseObject(result_ip, IP.class);
        return obj_ip;
    }
}
