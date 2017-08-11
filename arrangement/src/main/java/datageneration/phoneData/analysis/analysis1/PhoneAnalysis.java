package datageneration.phoneData.analysis.analysis1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Created by YuiSol on 2017/8/1.
 */
public class PhoneAnalysis {
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(PhoneAnalysis.class);
        job.setMapperClass(PhoneMapper.class);
        job.setReducerClass(PhoneReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileSystem fileSystem = FileSystem.get(config);
        String path=args[1];
        if(fileSystem.exists(new Path(path))){
            fileSystem.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job,new Path(path));
        boolean flag=job.waitForCompletion(true);
        System.exit(flag?0:1);

    }
}
