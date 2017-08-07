package RPCTest.RPCTestSolo.service;

import RPCTest.RPCTestSolo.protocol.IProxyProtocol;
import RPCTest.RPCTestSolo.protocol.IProxyProtocolImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;


/**
 * Created by YuiSol on 2017/7/31.
 */
public class RPCService extends IProxyProtocolImpl {
    public static void main(String[] args) throws Exception{
        RPC.Server build = new RPC.Builder(new Configuration())
                .setProtocol(IProxyProtocol.class)
                .setInstance(new RPCService())
                .setBindAddress("192.168.0.103")
                .setPort(9994)
                .setVerbose(false)
                .build();
        build.start();
    }
    public int add(int number1,int number2){
        return new IProxyProtocolImpl().add(number1,number2);
    }
}
