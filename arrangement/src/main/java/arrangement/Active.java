package arrangement;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by YuiSol on 2017/7  /29.
 */
public class Active {
    public static void main(String[] args) throws Exception{
        /*Configuration configuration = new Configuration();
        Job job=null;
        try {
            job = Job.getInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        job.setJarByClass(Active.class);
        job.setMapperClass(WordCountMapper1.class);
        job.setReducerClass(WordCountReducer1.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Phone1.class);
        job.setOutputKeyClass(Phone1.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.setInputPaths(job, "D:\\buffer\\flow.log");

        String path="D://3b";
        job.setOutputFormatClass(TextOutputFormat.class);
        FileSystem fs=FileSystem.get(configuration);
        if(fs.exists(new Path(path))){
            fs.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job, new Path(path));
        boolean flag=job.waitForCompletion(true);
        System.out.println(flag);
        System.exit(flag ? 0 : 1);
*/
        System.out.println("aaa");
        Configuration configuration = new Configuration();

        //获取一个job对象
        Job job = Job.getInstance(configuration);

        //设置jar main class
        job.setJarByClass(Active.class);

        //设置Mapper和Reducer的类的字节码对象
        job.setMapperClass(WordCountMapper1.class);
        job.setReducerClass(WordCountReducer1.class);

        //设置Map的key，value的输出类型
        job.setMapOutputKeyClass(Phone1.class);
        job.setMapOutputValueClass(Text.class);

        //设置Reducer的key，value的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Phone1.class);

        //设置这个类用什么方式读取数据
        //TextInputFormat 字符串的方式
        job.setInputFormatClass(TextInputFormat.class);

        //设置从哪里读取数据
        FileInputFormat.setInputPaths(job, new Path("D:\\buffer\\flow.log"));

        //设置以什么方式读出去
        job.setOutputFormatClass(TextOutputFormat.class);

        //设置读到哪里去,先判断改文件是否存在，存在则删除
        FileSystem fs= FileSystem.get(configuration);
        String path="D://3b";
        if(fs.exists(new Path(path))){
            fs.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job, new Path(path));
        //任务提交到集群

        boolean res=false;
        res=job.waitForCompletion(true);
        /*try {
            Field field=Job.class.getDeclaredField("state");
            field.setAccessible(true);
            System.out.println(Job.JobState.DEFINE+"+++++++++++++++"+field.get(job));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(NullPointerException e){
            System.out.println("NullPointerException");
        }*/
        System.exit(res?0:1);

    }
}
