package RPCTest.customRPC.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Tourbis on 2017/7/29.
 */
public class ClientRPCProtocol {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("192.168.0.111",58581);
        //发送消息到服务
        OutputStream socketOutputStream = socket.getOutputStream();
        //把数据加载到socket流
        PrintWriter printWriter = new PrintWriter(socketOutputStream);
        printWriter.write("RPCTest.customRPC.operation.OperationImpl,add,-324211000,-33123004,-235343251,12231,123,3423");
        printWriter.flush();
        //客户端接收服务器调用该add方法返回来的计算结果
        InputStream socketInputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(socketInputStream);
        char[] buffer=new char[1024];
        int len = inputStreamReader.read(buffer);
        System.out.println(new String(buffer,0,len));
        //关闭socket流
        inputStreamReader.close();
        printWriter.close();
        socket.close();
    }
}
