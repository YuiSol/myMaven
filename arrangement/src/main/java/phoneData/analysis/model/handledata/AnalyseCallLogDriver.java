package phoneData.analysis.model.handledata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by tourbis on 2017/8/1.
 * 统计每个省份语音通话总时长排行榜
 * <省份,通话时长>
 */
public class AnalyseCallLogDriver {
    public static class AnalyseCallLogMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line[] = value.toString().split("\t");
            String typeOfService=line[1];
            String catName=line[5];
            if("语音通话".equals(typeOfService)&&!catName.equals("null")){
               // line[4] line[9]
                context.write(new Text(line[4]), new IntWritable(Integer.parseInt(line[9].replaceAll("\\D",""))));
            }
        }
    }
    public static class AnalyseCallLogReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for(Iterator<IntWritable> iterator = values.iterator();iterator.hasNext();){
                IntWritable value = iterator.next();
                count+=value.get();
            }
            context.write(key,new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        Job wcjob = Job.getInstance(config);
        wcjob.setJarByClass(AnalyseCallLogDriver.class);

        wcjob.setMapperClass(AnalyseCallLogMapper.class);
        wcjob.setReducerClass(AnalyseCallLogReducer.class);

        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);

        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(IntWritable.class);

        wcjob.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.setInputPaths(wcjob, new Path(args[0]));

        wcjob.setOutputFormatClass(TextOutputFormat.class);
        FileSystem fs= FileSystem.get(config);
        if(fs.exists(new Path(args[1]))){
            fs.delete(new Path(args[1]), true);
        }
        FileOutputFormat.setOutputPath(wcjob, new Path(args[1]));

        //任务提交到集群
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res?0:1);
    }
}
