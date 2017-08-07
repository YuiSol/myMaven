package ZooKeeper;

/**
 * Created by YuiSol on 2017/8/3.
 */

import org.apache.hadoop.fs.Path;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 增删改查
 */
public class Basic {
    private ZooKeeper zooper;
    /**
     * 所有的端口，以逗号分开
     */
    private final String CONNECT="192.168.1.81:2181,192.168.1.82:2181,192.168.1.83:2181,";
    /**
     * 超时的毫秒值
     */
    private final int SESSION_TIME_OUT=3000;
    public void init(){
        try {
            /**
             * 服务器端口号
             * 超时毫秒值
             * 监听器
             */
            zooper=new ZooKeeper(CONNECT, SESSION_TIME_OUT, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(watchedEvent.getState());
                    System.out.println(watchedEvent.getType());
                    System.out.println(watchedEvent.getPath());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Basic basic = new Basic();
        basic.init();
     /*   basic.add("/data");*/
        basic.select("/data");
        basic.reset("/data");
        basic.select("/data");
        basic.delete("/data");
    }

    public void add(String path){
        /**
         * ACL 权限 ZooDefs.Perms下对应的权限
         *  Ids下有对应的实现,可以直接获取
         *  CreateMode 节点类型
         */
        ArrayList<ACL> list=new ArrayList<ACL>();
        list.add(new ACL(ZooDefs.Perms.WRITE, new Id("world", "anyone")));
        try {
            try {
                zooper.create(path,"你好".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void reset(String path){
        try {
            zooper.setData(path,"你不好".getBytes("utf-8"),-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void select(String path){
        try {
            byte[] bytes=zooper.getData(path,false,null);
            System.out.println(new String(bytes));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void delete(String path){
        try {
            zooper.delete(path,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
