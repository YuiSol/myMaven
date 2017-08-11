package dataclean.access_time;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/8/11.
 */
public class Access_Time_Clean {
    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();

        //获取一个job对象
        Job job = Job.getInstance(configuration);

        //设置jar main class
        job.setJarByClass(Access_Time_Clean.class);

        //设置Mapper和Reducer的类的字节码对象
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        //设置Map的key，value的输出类型
        job.setMapOutputKeyClass(Access_Time.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置Reducer的key，value的输出类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //设置这个类用什么方式读取数据
        //TextInputFormat 字符串的方式
        job.setInputFormatClass(TextInputFormat.class);

        //设置从哪里读取数据
        FileInputFormat.setInputPaths(job, new Path("F:\\zhengyuelai\\大数据\\ShareFolder\\day14 hive分区表 sqoop使用\\log\\access_time.log"));

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
    public static class MyMapper extends Mapper<LongWritable,Text,Access_Time,IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
           String[] spt=value.toString().split("\\|");
            context.write(new Access_Time(spt[0],spt[1]),new IntWritable(Integer.parseInt(spt[spt.length-1])));
        }
    }
    public static class MyReducer extends Reducer<Access_Time,IntWritable,NullWritable,Text>{

        @Override
        protected void reduce(Access_Time key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            StringBuffer sb = new StringBuffer(key.getVisitor());
            sb.append("|");
            sb.append(key.getMonth());
            int count=0;
            for(Iterator<IntWritable> iterator = values.iterator();iterator.hasNext();){

                int count1=iterator.next().get();

                if(count1>count){
                    count=count1;
                    if(key.getVisitor().equals("A")&&key.getMonth().equals("2014-01-22")){
                        System.out.println(count);
                    }
                }
            }



            if(sb.toString().equals("A|2014-01-22")) System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+count);
             sb.append("|");
            sb.append(count);
            context.write(NullWritable.get(), new Text(sb.toString()));
        }

    }

    public static class Access_Time implements WritableComparable<Access_Time>{
        private String visitor;//访客
        private String month;//月份
        private int times;

        public Access_Time(String visitor, String month) {
            this.visitor = visitor;
            this.times = times;
            this.month = month;
        }

        public Access_Time() {

        }

        public String getVisitor() {
            return visitor;
        }

        public void setVisitor(String visitor) {
            this.visitor = visitor;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int compareTo(Access_Time o) {
            return this.visitor.equals(o.getVisitor())?this.getMonth().hashCode()-o.getMonth().hashCode():this.getVisitor().hashCode()-o.getVisitor().hashCode();
        }

        public void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeUTF(this.getVisitor());
            dataOutput.writeUTF(this.getMonth());
            dataOutput.writeInt(this.getTimes());
        }

        public void readFields(DataInput dataInput) throws IOException {
            this.setVisitor(dataInput.readUTF());
            this.setMonth(dataInput.readUTF());
            this.setTimes(dataInput.readInt());
        }
    }

}
