package RPCTest.customRPC.operation;

/**
 * Created by Tourbis on 2017/7/29.
 */
public class OperationImpl implements Operation {
    public String add(int num1, int num2) {
        return String.format("两数之和:%d",(num1+num2));
    }
}
