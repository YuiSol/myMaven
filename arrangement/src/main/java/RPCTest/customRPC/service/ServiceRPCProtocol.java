package RPCTest.customRPC.service;

import RPCTest.customRPC.operation.OperationImpl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Tourbis on 2017/7/29.
 * B服务器，启动一个服务
 */
public class ServiceRPCProtocol {
    class HandleData implements Runnable{
        private Socket socket;
        public HandleData(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                System.out.println(String.format("IP地址为%s连接上来啦",socket.getInetAddress().getHostAddress()));
                //TODO 处理业务逻辑 -----RPC
                InputStream socketInputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(socketInputStream);
                char[] buffer=new char[1024];
                int len = inputStreamReader.read(buffer);
                String s = new String(buffer, 0, len);
                String[] split = s.split(",");
                Method method = OperationImpl.class.getDeclaredMethod(split[1],Integer.TYPE,Integer.TYPE);
                method.setAccessible(true);
                String className = split[0];
                Class<?> clazz = Class.forName(className);
                Object operation =  clazz.newInstance();
                Object invoke = method.invoke(operation, Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                System.out.println(invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void init() throws Exception{
        ServerSocket serverSocket = new ServerSocket();//创建serverSocket
        serverSocket.bind(new InetSocketAddress("192.168.0.111",9898));
        System.out.println("服务器上线");
        //表示当有客户端连接上该服务器的时候，就会开出一条线程单独去处理该客户端的业务逻辑
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new HandleData(socket)).start();
        }
    }

    public static void main(String[] args)throws Exception{
        ServiceRPCProtocol serviceRPCProtocol = new ServiceRPCProtocol();
        serviceRPCProtocol.init();
    }
}
