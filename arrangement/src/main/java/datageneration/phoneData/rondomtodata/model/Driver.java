package datageneration.phoneData.rondomtodata.model;


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
 */
public class Driver {
    public static class HandleMap extends Mapper<LongWritable,Text,NullWritable,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line= value.toString();
            String[] words = line.split("\t");
            String activePhone = words[3];
            String passivePhone=words[4];
            StringBuffer sb = new StringBuffer();
            sb.append(words[0]);
            sb.append("\t");
            sb.append(words[1]);
            sb.append("\t");
            sb.append(words[2]);
            sb.append("\t");
            sb.append(words[3]);
            sb.append("\t");
            try {
                Phone active_phone = (Phone) PhoneUtils.getInstance(activePhone, Phone.class);
                //获取主叫方的归属地和运营商
                String active_province = active_phone.getProvince();
                String active_catName = active_phone.getCatName();
                Phone passive_phone = (Phone) PhoneUtils.getInstance(passivePhone, Phone.class);
                String passive_province = passive_phone.getProvince();
                String passive_catName = passive_phone.getCatName();
                sb.append(active_province);
                sb.append("\t");
                sb.append(active_catName);
                sb.append("\t");
                sb.append(words[4]);
                sb.append("\t");
                sb.append(passive_province);
                sb.append("\t");
                sb.append(passive_catName);
                sb.append("\t");
                sb.append(words[5]);
                sb.append("\t");
                sb.append(words[6]);
                sb.append("\t");
                sb.append(words[7]);
                sb.append("\t");
                sb.append(words[8]);
                //System.out.println(line);
                context.write(NullWritable.get(), new Text(sb.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static class HandleReduce extends Reducer<NullWritable,Text,NullWritable,Text>{
        @Override
        protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
           for(Iterator<Text> iterator = values.iterator();iterator.hasNext();){
               Text next = iterator.next();
               context.write(NullWritable.get(), next);
           }
        }
    }
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        //获取一个job对象
        Job wcjob = Job.getInstance(config);
        wcjob.setJarByClass(Driver.class);
        /*wcjob.setJar("HandleMap");*/
        wcjob.setMapperClass(HandleMap.class);
        wcjob.setReducerClass(HandleReduce.class);

        wcjob.setMapOutputKeyClass(NullWritable.class);
        wcjob.setMapOutputValueClass(Text.class);

        wcjob.setOutputKeyClass(NullWritable.class);
        wcjob.setOutputValueClass(Text.class);
       // wcjob.setPartitionerClass();//指定分区器

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





