package RPCTest.RPCTestSolo.protocol;

import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by YuiSol on 2017/7/31.
 */
public class IProxyProtocolImpl implements IProxyProtocol {

    public int add(int number1, int number2) {
        return number1+number2;
    }

    public long getProtocolVersion(String s, long l) throws IOException {

        return IProxyProtocol.versionID;
    }

    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }
}
