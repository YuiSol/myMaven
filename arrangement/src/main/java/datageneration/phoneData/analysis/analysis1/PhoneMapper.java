package datageneration.phoneData.analysis.analysis1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by YuiSol on 2017/8/1.
 */
public class PhoneMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] str=value.toString().split("\t");
        context.write(new Text(str[7]),new Text(str[6]));
    }
}
