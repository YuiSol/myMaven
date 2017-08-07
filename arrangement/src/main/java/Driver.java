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
 * Created by YuiSol on 2017/7/28.\
 * 把你的参数全部封装起来，以job的对象方式提交到集群
 * Driver-->> WordCountMappers -->WordCountReducer
 */
public class Driver {
    public static void main(String[] args) throws Exception{
        //创建驱动文件
        Configuration configuration = new Configuration();

           //获取一个job对象
        Job job = Job.getInstance(configuration);

        //设置jar main class
        job.setJarByClass(Driver.class);

        //设置Mapper和Reducer的类的字节码对象
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置Map的key，value的输出类型
        job.setMapOutputKeyClass(Phone.class);
        job.setMapOutputValueClass(Text.class);

        //设置Reducer的key，value的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

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
