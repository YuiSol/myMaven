package ZooKeeper.listener.demo1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuiSol on 2017/8/4.
 */
public class MyWacher implements Watcher {
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        if(this.list!=null) {
            this.list.clear();
        }
        this.list = Collections.synchronizedList(list);
    }

    private  List<String> list;
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType()== Event.EventType.None) return;
        if(watchedEvent.getType()==Event.EventType.NodeChildrenChanged){
            System.out.println("触发了子节点");
            Server server = Server.getServer();
            server.init();
            try {
                List<String> children = null;
                try {
                    children = server.getZooKeeper().getChildren(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
                if(children.size()<1&&list.size()<1){
                    return;
                }
                String url=null;
                int port=0;
                StringBuffer data=new StringBuffer();
                List<String> li=new ArrayList<String>();
                boolean flag=false;
                if(children.size()>list.size()){
                    children.removeAll(list);
                    li.addAll(children);
                    data.append("上线");
                }
                else {
                    list.removeAll(children);
                    li.addAll(children);
                   data.append("下线");
                }

                for(String lisi:li){
                    if(lisi.equals(ConfProtocol.CLIENT1_NAME)){
                        data.insert(0, ConfProtocol.CLIENT1_NAME);
                        System.out.println(data);
                        server.online("/data/client2",data.toString());
                    }else {
                        data.insert(0, ConfProtocol.CLIENT2_NAME);
                        System.out.println(data);
                        server.online("/data/client1",data.toString());
                    }
                }

                server.getZooKeeper().getData(watchedEvent.getPath(), true, null);
                return;
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       if(watchedEvent.getType()==Event.EventType.NodeDataChanged){
           System.out.println("11111");
            new Client1().init();
    }
    }
}
