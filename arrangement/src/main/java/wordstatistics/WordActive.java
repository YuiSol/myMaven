package wordstatistics;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


import java.net.URI;
import java.util.HashMap;

/**
 * Created by YuiSol on 2017/7/30.
 */
public class WordActive {
    public static void main(String[] args) throws Exception{
        long starttime=System.currentTimeMillis();
        Configuration config= new Configuration();
      /*  FileSystem fileSystem=FileSystem.get(new URI("hdfs://192.168.1.81:9000"),config,"root");*/
        config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        Job job = Job.getInstance(config);
        job.setJarByClass(WordActive.class);

        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Map1.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.setInputPaths(job,args[0]);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileSystem fs= FileSystem.get(config);
        String path=args[1];
        if(fs.exists(new Path(path))){
            fs.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job, new Path(path));
        boolean res=false;
        res=job.waitForCompletion(true);

        long endtime=System.currentTimeMillis();
        System.out.println("这个一共用了:"+(endtime-starttime)+"秒");

        System.exit(res ? 0 : 1);
    }

}
