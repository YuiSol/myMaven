package ZooKeeper.listener.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * Created by YuiSol on 2017/8/4.
 * 客户端2
 */
public class Client2 implements Runnable,Client {
    private ConfProtocol conf;
    private static ZooKeeper zooKeeper=null;
    static {
        init();
        create();
    }
    public void run() {

    }
    {
        try {
            conf = RPC.getProxy(ConfProtocol.class, ConfProtocol.versionID, new InetSocketAddress(ConfProtocol.CLIENT2, ConfProtocol.PORT2), new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void init(){
        if (zooKeeper==null){
            try {
                zooKeeper = new ZooKeeper(ConfProtocol.CONECT, ConfProtocol.SESSION_TIME_OUT,new MyWacher());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void send(){
        System.out.println(conf.sendData());
    }
    public static void create(){
        try {
            zooKeeper.create(ConfProtocol.Path+"/client2","Client2上线".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
