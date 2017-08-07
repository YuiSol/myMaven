package wordstatistics;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by YuiSol on 2017/7/30.
 */
public class WordReducer extends Reducer<Text,Map1,Text,Text> {

    @Override
    protected void reduce(Text key, Iterable<Map1> values, Context context) throws IOException, InterruptedException {
        Map1 map=new Map1(key.toString(),0);
        for(Iterator<Map1> iterator = values.iterator();iterator.hasNext();){
                Map1 map1=iterator.next();
                map.setNumber((map.getNumber()+map1.getNumber()));
        }
        context.write(new Text(map.getKey()),new Text(String.valueOf(map.getNumber())));
    }
}
