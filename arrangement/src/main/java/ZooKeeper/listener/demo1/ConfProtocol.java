package ZooKeeper.listener.demo1;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * Created by YuiSol on 2017/8/4.
 */
public interface ConfProtocol extends VersionedProtocol {
    long versionID =123456L;
    String CONECT="192.168.1.81:2181,192.168.1.82:2181,192.168.1.83:2181,";
    int SESSION_TIME_OUT=5000;
    String CLIENT1="localhost";
    String CLIENT2="192.168.0.103";
    int PORT1=9001;
    int PORT2=9292;
    String Path="/server";
    String CLIENT1_NAME="client1";
    String CLIENT2_NAME="client2";
    String sendData();

}
