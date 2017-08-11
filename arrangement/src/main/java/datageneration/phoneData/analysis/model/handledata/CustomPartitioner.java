package datageneration.phoneData.analysis.model.handledata;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by tourbis on 2017/8/1.
 */
public class CustomPartitioner extends Partitioner<Text,Text> {
    @Override
    public int getPartition(Text key, Text value, int numReduceTasks) {
        if("被叫".equals(key.toString())){
            return 0;
        }else if("主叫".equals(key.toString())){
            return 1;
        }else{
            return 2;
        }
    }
}
