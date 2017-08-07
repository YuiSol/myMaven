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
 * 客户端1
 */
public class Client1 implements Runnable,Client{

    private static ZooKeeper zooKeeper=null;
    static {

    }
    {

    }

    public static ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void run() {

    }
    public static void init(){
        if (zooKeeper==null){
            try {
                zooKeeper = new ZooKeeper(ConfProtocol.CONECT, ConfProtocol.SESSION_TIME_OUT,new MyWacher());
                try {
                    if(zooKeeper.exists(ConfProtocol.Path+"/client1",false)==null){
                        zooKeeper.create(ConfProtocol.Path+"/client1","Client1上线".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    }

                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void create(){
        init();

    }

    public static void main(String[] args) throws Exception{
        create();
        zooKeeper.getData("/data/client1",new MyWacher(),null);



        while (true){

        }
    }
    public void send(){

    }
}
