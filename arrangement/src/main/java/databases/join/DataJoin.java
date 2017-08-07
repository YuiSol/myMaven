package databases.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/8/2.
 */
public class DataJoin {
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(DataJoin.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]),new Path(args[1]));
        Path path=new Path(args[2]);
        FileSystem fileSystem = FileSystem.get(config);
        if(fileSystem.exists(path)){
            fileSystem.delete(path,true);
        }
        FileOutputFormat.setOutputPath(job,path);
        System.exit(job.waitForCompletion(true)?0:1);
    }

    public static class MyMapper extends Mapper<LongWritable,Text,Text,Text>{
        private StringBuilder sb;
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            sb=new StringBuilder();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            sb.delete(0, sb.length());
            String fileName=((FileSplit) context.getInputSplit()).getPath().getName();
            String[] strs=value.toString().split("\t");
            int pointer=0;
            if(fileName.equals("t_order.log")){
                sb.append("1");
                pointer=2;

            }else if(fileName.equals("t_product.log")){
                sb.append("2");
                pointer=0;
            }else{
                sb.append("3");
                pointer=-1;
            }
            sb.append("\t");
            for(int i=0;i<strs.length;i++){
                if(i==strs.length-1){
                    sb.append(strs[i]);
                }else if(i!=pointer){
                    sb.append(strs[i]);
                    sb.append("\t");
                }
            }
            context.write(new Text(pointer==-1?
                                    null:strs[pointer]),new Text(sb.toString()));
        }


    }

    public static class MyReducer extends Reducer<Text,Text,NullWritable,Text>{
        private ArrayList<String[]> t_order;
            private ArrayList<String> t_product;
        private StringBuilder sb;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            sb=new StringBuilder();
        }

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int count=0;
            t_order=new ArrayList();
            t_product=new ArrayList();
            for(Iterator<Text> iterator = values.iterator();iterator.hasNext();){
                String ss=iterator.next().toString();
                String[] strs=ss.split("\t");
                    if(strs[0].equals("1")){
                        t_order.add(strs);
                    }else if(strs[0].equals("2")){
                        for(int i=1;i<strs.length;i++){
                            t_product.add(strs[i]);
                        }
                }
            }
            for(int i=0;i<t_order.size();i++){
                sb.delete(0, sb.length());
                    for(int j=1;j<t_order.get(i).length;j++){
                        sb.append(t_order.get(i)[j]);
                        sb.append("\t");
                    }
                for(String s1:t_product){
                        sb.append(s1);
                        sb.append("\t");
                }
                context.write(NullWritable.get(), new Text(sb.toString()));
            }
        }
    }
}
