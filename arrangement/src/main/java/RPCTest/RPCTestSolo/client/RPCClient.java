package RPCTest.RPCTestSolo.client;

import RPCTest.RPCTestSolo.protocol.IProxyProtocol;
import RPCTest.RPCTestSolo.service.RPCService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolProxy;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.VersionedProtocol;


import java.net.InetSocketAddress;

/**
 * Created by YuiSol on 2017/7/31.
 */
public class RPCClient {
    public static void main(String[] args) throws Exception{
        IProxyProtocol proxy = RPC.getProxy(IProxyProtocol.class, IProxyProtocol.versionID, new InetSocketAddress("192.168.0.103", 9994), new Configuration());
        int number=proxy.add(21,123);
        System.out.println(number);
    }
}
