package phoneData.analysis.analysis2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import phoneData.analysis.analysis1.PhoneMapper;
import phoneData.analysis.analysis1.PhoneReducer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/8/1.
 */
public class Analysis1 {
    public static class AnalysisMapper extends Mapper<LongWritable,Text,Text,PhoneData>{

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] strs=value.toString().split("\t");
            String key1=strs[7]+"\t"+strs[2].substring(0,7);
            PhoneData phoneData = new PhoneData(strs[6], strs[2], strs[7]);
            context.write(new Text(key1),phoneData);
        }
    }
    public static class AnalysisReducer extends Reducer<Text,PhoneData,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<PhoneData> values, Context context) throws IOException, InterruptedException {
            int count=0;
            int size=0;
            for(Iterator<PhoneData> iterator = values.iterator();iterator.hasNext();){
                PhoneData next = iterator.next();
                if(next.getType().equals("主叫")){
                    count++;
                }
                size++;
            }
            float fl=(float)(count)/(size)*100;
            context.write(key,new Text(String.valueOf("主叫率："+fl+"%")));
        }
    }
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(Analysis1.class);
        job.setMapperClass(AnalysisMapper.class);
        job.setReducerClass(AnalysisReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneData.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileSystem fileSystem = FileSystem.get(config);
        job.setPartitionerClass(MyParitioner.class);
        job.setNumReduceTasks(Call_Location.CALL_LOCATION.length+1);
        String path=args[1];
        if(fileSystem.exists(new Path(path))){
            fileSystem.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job, new Path(path));
        boolean flag=job.waitForCompletion(true);
        System.exit(flag?0:1);
    }


}
