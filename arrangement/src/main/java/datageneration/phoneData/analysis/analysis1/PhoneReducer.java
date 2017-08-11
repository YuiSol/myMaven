package datageneration.phoneData.analysis.analysis1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/8/1.
 */
public class PhoneReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for( Iterator<Text> iterator = values.iterator();iterator.hasNext();){
            if(iterator.next().toString().equals("主叫")){
                count++;
            }
        }
        context.write(key,new Text(String.valueOf(count)));
    }
}
