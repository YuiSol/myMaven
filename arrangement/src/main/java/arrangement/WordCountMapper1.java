package arrangement;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by YuiSol on 2017/7/29.
 */
public class WordCountMapper1 extends Mapper<LongWritable,Text,Phone1,Text> {

    @Override
    protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
        String[] str=value.toString().split("\t");
        String tel=str[0];
        int upData=Integer.parseInt(str[str.length - 3]);
        int downData=Integer.parseInt(str[str.length-2]);
        Phone1 phone1=new Phone1(tel,upData,downData);
        context.write(phone1,new Text(new String(tel+","+upData+","+downData)));
    }
}
