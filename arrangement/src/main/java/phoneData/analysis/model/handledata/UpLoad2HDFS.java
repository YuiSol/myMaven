package phoneData.analysis.model.handledata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by tourbis on 2017/8/1.
 */
public class UpLoad2HDFS {
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        URI uri = new URI("hdfs://192.168.1.88:9000");
        FileSystem fileSystem = FileSystem.get(uri,config,"root");
        fileSystem.copyFromLocalFile(new Path("F:/call_log.log"),new Path("/"));

    }
}
