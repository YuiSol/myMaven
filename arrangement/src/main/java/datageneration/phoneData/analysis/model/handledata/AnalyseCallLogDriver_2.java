package datageneration.phoneData.analysis.model.handledata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
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
 */
public class AnalyseCallLogDriver_2 {
    public static class AnalyseCallLogMapper_2 extends Mapper<LongWritable,Text,IntWritable,Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String words[] = value.toString().split("\t");
            context.write(new IntWritable(Integer.parseInt(words[1])),new Text(words[0]));
        }
    }
    public static class AnalyseCallLogReducer_2 extends Reducer<IntWritable,Text,Text,IntWritable> {
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Iterator<Text> iterator = values.iterator();iterator.hasNext();){
                Text value = iterator.next();
                context.write(value,key);
            }
        }
    }

    public static void main(String[] args)throws Exception{
        Configuration config = new Configuration();
        Job wcjob = Job.getInstance(config);
        wcjob.setJarByClass(AnalyseCallLogDriver_2.class);

        wcjob.setMapperClass(AnalyseCallLogMapper_2.class);
        wcjob.setReducerClass(AnalyseCallLogReducer_2.class);

        wcjob.setMapOutputKeyClass(IntWritable.class);
        wcjob.setMapOutputValueClass(Text.class);

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
        wcjob.setSortComparatorClass(SecondSort.class);
        //任务提交到集群
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res?0:1);
    }
}
