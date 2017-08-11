package datageneration.data.model.day15_offlinepro.app;


import datageneration.data.model.day15_offlinepro.domain.RegisterUserInfo;
import datageneration.data.model.day15_offlinepro.utils.TimeUtils;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by tourbis on 2017/8/11.
 */
public class WriteRegisterUser2File {
    public static void main(String[] args) throws Exception{
        String prefix="User";
        //create 20 user
        String fileName="F:\\个人资料\\离线业务数据\\day14-离线业务项目实战\\log";
        File file = new File(fileName, "register_user.log");
        FileWriter fw = new FileWriter(file,true);
        for(int i=0;i<100;i++){
            String userName=prefix.concat(String.valueOf(i));
            RegisterUserInfo user = new RegisterUserInfo(RegisterUserInfo.getIsVipFlag(),
                    userName,
                    TimeUtils.timeFilter(TimeUtils.getTimeWiths(TimeUtils.CallMethodID.WithMinute, 666)),
                    RegisterUserInfo.getUUID());
            System.out.println(user);
            fw.write(user.toString() + System.lineSeparator());
            fw.flush();
        }
        fw.close();
    }

}
