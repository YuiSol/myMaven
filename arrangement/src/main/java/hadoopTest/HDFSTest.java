package hadoopTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileChecksum;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by YuiSol on 2017/7/30.
 */
public class HDFSTest {
    private static final String URI="hdfs://192.168.1.82:9000";
    public static void main(String[] args) throws Exception{
        /*delete(("/log/log/t_origin_1.log"));
        update("D:\\4b\\离线业务项目实战数据\\数据清洗\\t_origin_1.log");*/
        delete("/root");
    }
    public static void update(String path)throws Exception{
        Configuration config = new Configuration();
        //FileSystem.get(URI,配置，账号);
        FileSystem fileSystem = FileSystem.get(new URI(URI),config,"root");
        fileSystem.copyFromLocalFile(false, new Path(path), new Path("/log/log/"));
        fileSystem.close();
    }
    public static void delete(String path)throws Exception{
        Configuration config = new Configuration();
        //FileSystem.get(URI,配置，账号);
        FileSystem fileSystem = FileSystem.get(new URI(URI),config,"root");
        fileSystem.delete(new Path(path),true);
    }
    public static void down(String path)throws Exception{
        Configuration config = new Configuration();
        //FileSystem.get(URI,配置，账号);
        FileSystem fileSystem = FileSystem.get(new URI(URI),config,"root");
        fileSystem.copyToLocalFile(false,new Path(path),new Path("d://buffer"));
        fileSystem.close();
    }
    public static void select(String path) throws Exception{
        Configuration config = new Configuration();
        //FileSystem.get(URI,配置，账号);
        FileSystem fileSystem = FileSystem.get(new URI(URI),config,"root");
        boolean flag=fileSystem.exists(new Path(path));
        if(flag){
            FileStatus[] fs=fileSystem.listStatus(new Path("1"));
            System.out.println(fs.length);
            for(FileStatus f:fs){
                System.out.println(f.getLen());
                System.out.println(f.getPath().getName());

            }
        }else {
            FileChecksum fileChecksum = fileSystem.getFileChecksum(new Path(path));
            System.out.println(fileChecksum.getAlgorithmName());
        }
            fileSystem.close();

    }

}
