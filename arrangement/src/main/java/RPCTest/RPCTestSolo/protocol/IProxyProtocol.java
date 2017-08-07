package RPCTest.RPCTestSolo.protocol;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * Created by YuiSol on 2017/7/31.
 */
public interface IProxyProtocol extends VersionedProtocol  {
        static final long versionID =123456L;
        int add(int number1,int number);
}
