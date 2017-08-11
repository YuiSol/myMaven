package datageneration.phoneData.analysis.model.handledata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
 *
 */
public class AnalyseCallLogDriver_3 {
    public static class AnalyseCallLogMapper_3 extends Mapper<LongWritable,Text,Text,Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] words = value.toString().split("\t");
            String callType= words[6];//主叫或被叫
            System.out.println(callType+"----------------");
            context.write(new Text(callType),value);
        }
    }
        public static class AnalyseCallLogReducer_3 extends Reducer<Text,Text,NullWritable,Text> {
            @Override
            protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                for( Iterator<Text> iterator = values.iterator();iterator.hasNext();){
                    Text next = iterator.next();
                    context.write(NullWritable.get(), next);
                }
            }
        }

    public static void main(String[] args)throws Exception{
        Configuration config = new Configuration();
        Job wcjob = Job.getInstance(config);
        wcjob.setJarByClass(AnalyseCallLogDriver_3.class);

        wcjob.setMapperClass(AnalyseCallLogMapper_3.class);
        wcjob.setReducerClass(AnalyseCallLogReducer_3.class);

        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(Text.class);

        wcjob.setOutputKeyClass(NullWritable.class);
        wcjob.setOutputValueClass(Text.class);

        wcjob.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.setInputPaths(wcjob, new Path(args[0]));

        wcjob.setOutputFormatClass(TextOutputFormat.class);
        FileSystem fs= FileSystem.get(config);
        if(fs.exists(new Path(args[1]))){
            fs.delete(new Path(args[1]), true);
        }
        FileOutputFormat.setOutputPath(wcjob, new Path(args[1]));
        wcjob.setNumReduceTasks(3);
        wcjob.setPartitionerClass(CustomPartitioner.class);
        //任务提交到集群
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res?0:1);
    }
}
