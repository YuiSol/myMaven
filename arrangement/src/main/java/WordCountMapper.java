import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by YuiSol on 2017/7/28.
 * 输入输出
 * LongWritable 输入key
 * Text  输入value
 * Text 输出key
 * IntWriable  输出value
 */
public class WordCountMapper extends Mapper<LongWritable,Text, Phone,Text> {
    @Override
    protected void map(LongWritable key,Text value,Context context)throws IOException {
        String[] str=value.toString().split("\t");
        String phone=str[0];
        int upData=Integer.parseInt(str[str.length-3]);
        int downData=Integer.parseInt(str[str.length-2]);
        Phone phone1 = new Phone(null, upData, downData);
        try {
            context.write(phone1,new Text(phone));
        } catch (InterruptedException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
