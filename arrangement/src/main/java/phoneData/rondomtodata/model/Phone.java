package phoneData.rondomtodata.model;

/**
 * Created by tourbis on 2017/8/1.
 * -------通话记录日志
 * id
 * 时间（通话起始时间）
 * （通话结束时间）
 * 计费
 * 主动呼叫/被动呼叫
 * 手机号码归属地
 * 手机号码
 *
 *
 */
public class Phone {
    private String mts;
    private String province;//手机号码归属地
    private String catName;//归属运营商
    private String telString;//手机号码
    private String areaVid;//
    private String ispVid;
    private String carrier;//

    public Phone() {
    }

    public Phone(String mts, String province, String catName, String telString, String areaVid, String ispVid, String carrier) {
        this.mts = mts;
        this.province = province;
        this.catName = catName;
        this.telString = telString;
        this.areaVid = areaVid;
        this.ispVid = ispVid;
        this.carrier = carrier;
    }

    public String getMts() {
        return mts;
    }

    public void setMts(String mts) {
        this.mts = mts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getAreaVid() {
        return areaVid;
    }

    public void setAreaVid(String areaVid) {
        this.areaVid = areaVid;
    }

    public String getIspVid() {
        return ispVid;
    }

    public void setIspVid(String ispVid) {
        this.ispVid = ispVid;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
