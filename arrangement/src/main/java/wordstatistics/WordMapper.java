package wordstatistics;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by YuiSol on 2017/7/30.
 */
public class WordMapper extends Mapper<LongWritable,Text,Text,Map1> {
    HashMap<String,Integer> hash=new HashMap<String, Integer>();
    {
        for(int i=0;i<26;i++){
            hash.put(String.valueOf(((char)(65+i))),new Integer(0));
            hash.put(String.valueOf(((char)(97+i))),new Integer(0));
        }
    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strs=value.toString().split("");
        for(int i=0;i<strs.length;i++) {
            String str = strs[i];
            Integer number = hash.get(str);
            if (number != null  ) {
                hash.remove(str);
                hash.put(str, ++number);
            }
        }
        for (int j=0;j<26;j++){
                    String font1=(String.valueOf((char)(65+j)));
                    Map1 m=new Map1(font1.toString(),hash.get(font1.toString()));
                    context.write(new Text(font1.toString()),m);
                    font1=(String.valueOf((char)(97+j)));
                    context.write(new Text(font1.toString()),new Map1(font1.toString(),hash.get(font1.toString())));
                }


    }
}
