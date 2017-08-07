package ZooKeeper.listener.demo1;

import RPCTest.RPCTestSolo.protocol.IProxyProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.RPC;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuiSol on 2017/8/4.
 * 服务器
 */
public class Server implements ConfProtocol {
    private ZooKeeper zooKeeper;
    private List<String> path=new ArrayList<String>();
    private MyWacher myWacher=new MyWacher();
    public void setPath(List<String> path) {
        this.path = path;
    }
    private  static String data;
    private static Server server = new Server();

    public static Server getServer() {
        return server;
    }



    public List<String> getPath() {
        return path;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void init(){
        if (zooKeeper==null) {
            try {
                zooKeeper = new ZooKeeper(ConfProtocol.CONECT, ConfProtocol.SESSION_TIME_OUT,myWacher);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
        }
        try {
            path = zooKeeper.getChildren(ConfProtocol.Path, true);
            myWacher.setList(path);
            System.out.println("启动监听");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    {

    }
    public static void main(String[] args) {
        server.init();
        while (true){}
    }

    public  String getData() {
        return data;

    }

    public  void setData(String data) {
        this.data = data;

    }

    public String getData(String path){

        try {
            byte[] data = zooKeeper.getData(path, true, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void send(String url,int port){
        try {
            RPC.Server build = new RPC.Builder(new Configuration())
                    .setProtocol(ConfProtocol.class)
                    .setInstance(new Server())
                    .setBindAddress(url)
                    .setPort(port)
                    .setVerbose(false)
                    .build();
            build.start();
            System.out.println("启动链接url:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void online(String path,String data){
        try {
            zooKeeper.setData(path,data.getBytes("utf-8"),-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public String sendData() {
        System.out.println(this.data);
        return this.data;
    }

    public long getProtocolVersion(String s, long l) throws IOException {
        return this.versionID;
    }

    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }
    public  void sendData(Client client){
       client.send();
    }


}
