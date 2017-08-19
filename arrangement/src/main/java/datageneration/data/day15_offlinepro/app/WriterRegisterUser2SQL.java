package datageneration.data.day15_offlinepro.app;

import datageneration.data.day15_offlinepro.dao.RegisterUserInfoSQLDataImpl;
import datageneration.data.day15_offlinepro.domain.RegisterUserInfo;
import datageneration.data.day15_offlinepro.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by tourbis on 2017/8/11.
 */
public class WriterRegisterUser2SQL {
    public static void main(String[] args) {
        //addData();
    }
    static void addData(){
        String prefix="user";
        RegisterUserInfoSQLDataImpl sqlData = new RegisterUserInfoSQLDataImpl();
        ArrayList<RegisterUserInfo> list = new ArrayList<RegisterUserInfo>();
        for(int i=0;i<380;i++){
            RegisterUserInfo userInfo = new RegisterUserInfo(prefix.concat(String.valueOf(i)),
                    TimeUtils.timeFilter(TimeUtils.getTimeWiths(TimeUtils.CallMethodID.WithMinute,9999)));
            list.add(userInfo);
        }
        //inset data into mysql
        String sql="INSERT INTO `register_info`.`t_user`(`isVip`,`userName`,`registerTime`,`articleID`) value(?,?,?,?)";
       sqlData.addData(list,sql);
    }
}
