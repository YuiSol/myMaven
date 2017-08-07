package phoneData.rondomtodata.model;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/1.
 */
public class CallLog {
    private static long id;
    private static String typeOfService;
    private static String callStartTime;//通话起始时间
    private static String activePhone;//主动方电话
    private static String passivePhone;//被叫方电话
    private static String callSumTime;//通话时长（如果业务类型是语音聊天，时长，如果是短信聊天，次数）
    private static String callType ;//呼叫类型 （主叫/被叫）
    private static String callLocation;//呼叫地址
    private static String dataCallType;//通话类型，国内通话



    private static final String TYPE_SERVICE[]={"语音通话","短信聊天"};
    private static final String CALL_TYPE[]={"主叫","被叫"};
    private static final String DATA_CALL_TYPE[]={"国际漫游","国内通话"};
    private static final String CALL_LOCATION[]="北京市，天津市，上海市，重庆市，河北省，山西省，辽宁省，吉林省，黑龙江省，江苏省，浙江省，安徽省，福建省，江西省，山东省，河南省，湖北省，湖南省，广东省，海南省，四川省，贵州省，云南省，陕西省，甘肃省，青海省，台湾省，内蒙古自治区，广西壮族自治区，西藏自治区，宁夏回族自治区，新疆维吾尔自治区，香港特别行政区，澳门特别行政区".split("，");//呼叫地址

    private static final Random random=new Random();
    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int RANGE=1000;
    private static  String start_time="2015-04-05 12:32:44";

    public CallLog() {
    }

    public CallLog(long id, String typeOfService, String callStartTime, String activePhone, String passivePhone, String callSumTime, String callType, String callLocation, String dataCallType) {
        this.id = id;
        this.typeOfService = typeOfService;
        this.callStartTime = callStartTime;
        this.activePhone = activePhone;
        this.passivePhone = passivePhone;
        this.callSumTime = callSumTime;
        this.callType = callType;
        this.callLocation = callLocation;
        this.dataCallType = dataCallType;
    }
    public static void main(String[] args)  throws Exception{
        for (int i=0;i<50000000;i++) {
            typeOfService=TYPE_SERVICE[random.nextInt(TYPE_SERVICE.length)];
            callStartTime=getTime(start_time);
            start_time=callStartTime;
            activePhone=PhoneUtils.getRandomPhoneNumber();
            passivePhone=PhoneUtils.getRandomPhoneNumber();
            if(TYPE_SERVICE[0].equals(typeOfService)){
             callSumTime=getCallSumTime(callStartTime, getTime(callStartTime));
            }else{
              callSumTime=String.valueOf(getSendTextNumber()+"次");
            }

            callType=CALL_TYPE[random.nextInt(CALL_TYPE.length)];
            callLocation=CALL_LOCATION[random.nextInt(CALL_LOCATION.length)];
            dataCallType=DATA_CALL_TYPE[random.nextInt(DATA_CALL_TYPE.length)];
            CallLog callLog = new CallLog(id, typeOfService, callStartTime, activePhone, passivePhone, callSumTime, callType, callLocation, dataCallType);
            id++;
            //System.out.println(callLog);
            write2file(callLog.toString(),"d:/buffer/call_log.log");
        }


    }
    public static void write2file(String data,String path) throws Exception{
        FileWriter fw = new FileWriter(new File(path),true);
        fw.write(data);
        fw.write(System.lineSeparator());
        fw.flush();
        fw.close();
        fw=null;
    }

    public static int getSendTextNumber(){
        int num = random.nextInt(10);
        return num==0?1:num;
    }

    public static String getCallSumTime(String startTime,String endTime) throws Exception{
       long time=sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime();//ms值
        return String.valueOf(time/1000)+"s";
    }

    public static String getTime(String time) throws Exception{
        Calendar instance = Calendar.getInstance();
        Date date = sdf.parse(time);
        instance.setTime(date);
        instance.add(Calendar.SECOND, random.nextInt(RANGE));
        return sdf.format(instance.getTime());
    }


    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",id,typeOfService,callStartTime,activePhone,passivePhone,
                callSumTime,callType,callLocation,dataCallType);
    }
}
