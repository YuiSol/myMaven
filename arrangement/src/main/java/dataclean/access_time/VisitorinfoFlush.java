package dataclean.access_time;

import datageneration.data.day15_offlinepro.utils.IPUtils;
import org.apache.hadoop.conf.Configurable;
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
import java.util.*;

/**
 * Created by YuiSol on 2017/8/16.
 */
public class VisitorinfoFlush {
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
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(MyData.class);

        //设置Reducer的key，value的输出类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //设置这个类用什么方式读取数据
        //TextInputFormat 字符串的方式
        job.setInputFormatClass(TextInputFormat.class);

        //设置从哪里读取数据
        FileInputFormat.setInputPaths(job,new Path("D:\\buffer\\离线业务项目实战数据\\log\\log\\user_agent.txt"), new Path("D:\\buffer\\离线业务项目实战数据\\log\\" +
                "log\\t_origin.log"));


        //设置以什么方式读出去
        job.setOutputFormatClass(TextOutputFormat.class);

        //设置读到哪里去,先判断改文件是否存在，存在则删除
        FileSystem fs= FileSystem.get(configuration);
        String path="D:\\4b\\离线业务项目实战数据\\数据清洗";
        if(fs.exists(new Path(path))){
            fs.delete(new Path(path),true);
        }
        FileOutputFormat.setOutputPath(job, new Path(path));
        //任务提交到集群

        boolean res=false;
        res=job.waitForCompletion(true);

    }

    /**
     * 增加地区，浏览器字段字段，清洗数据
     * 用ArrayList<Text>发送
     * 根据使用机型作为mapper的key，可以把两个文件的数据连接起来
     * 地区在mapper进行查询，浏览器在reducer进行合并
     * mapper要把数据进行筛选，对于valid为FALSE的数据，IP查询没有地址的数据和status为failed的数据不要发送
     *
     */
    public static class MyMapper extends Mapper<LongWritable,Text,Text,MyData>{
        private List<String> li;
        private Map<String,String> map;
        private static int count=0;
        private static int count1=0;
        private static int count2=0;
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            System.out.println("Mapper 0% Reducer 0%");
            count=0;
            count1=0;
            count2=0;
            li=new ArrayList<String>();
            map=new Hashtable<String, String>();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            count++;
            li.clear();
            String[] strs=value.toString().split("\t");
            if(strs[0].equals("false")){
                count1++;
                return;
            }
            for(int i=0;i<strs.length;i++){
                li.add(strs[i]);
            }
            MyData myData = new MyData();
            if(li.size()==2){
                myData.setList(li);
                count2++;
                context.write(new Text(li.get(li.size() - 1)), myData);
                return;
            }
            if(li.get(5).equals("failed")){
                count1++;
                return;
            }
            if (li.get(7).equals("http://blog.163.com/error_404")){
                count1++;
                return;
            }
                try {
                    String location=map.get(li.get(1));
                    while(location==null){
                        location=IPUtils.ip2site(li.get(1)).getData().get(0).getLocation();
                        map.put(li.get(1),location);
                    }

                    if(location.equals("保留地址")){
                        count1++;
                        return;
                    }

                    li.add(location);

                    myData.setList(li);

                    context.write(new Text(li.get(li.size() - 2)), myData);
                    count2++;
                } catch (Exception e) {
                    e.printStackTrace();
                }




         }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            System.out.println("Mapper 100% Reducer 0%");
            System.out.println("一共有多少条数据："+count);
            System.out.println("一共清除了多少条："+count1);
            System.out.println("一共发送了多少条："+count2);
        }
    }
    public static class MyReducer extends Reducer<Text,MyData,NullWritable,Text>{
        private StringBuffer sb;
        private static String browser;
        private List<String> list;
        private int count=0;
        private int count1=0;
        private int count2=0;
        private final String IE8="Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)";
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            sb=new StringBuffer();
            list=new ArrayList<String>();
        }

        @Override
        protected void reduce(Text key, Iterable<MyData> values, Context context) throws IOException, InterruptedException {
            count1++;
            count2 = 0;
            list.clear();
            for (Iterator<MyData> iterator = values.iterator(); iterator.hasNext(); ) {
                count++;
                count2++;
                sb.setLength(0);
                List<String> li = iterator.next().getList();
                if (li.size() == 2) {
                    browser = li.get(0);
                    if (key.toString().equals(IE8)) {
                        if (browser.equals("IE 8.0")) {
                            browser.concat("/IE 9.0");
                        } else {
                            browser.concat("/IE 8.0");
                        }
                    }

                } else {
                    for (int i = 0; i < li.size(); i++) {
                        sb.append(li.get(i));
                        sb.append("\t");
                    }
                    if (browser != null) {
                        sb.append(browser);
                        context.write(null, new Text(sb.toString()));
                    } else {
                        list.add(sb.toString());
                    }

                }

                System.out.println("单次有多少数据:" + count2);

            }
            for (int i = 0; i < list.size(); i++) {
                context.write(null, new Text(list.get(i).concat(browser)));
            }
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            System.out.println("接收了多少条数据"+count);
            System.out.println("一共运行了多少次Reducer"+count1);
            System.out.println("Mapper 100% Reducer 100%");
        }
    }


    public static class MyData extends SortedMapWritable  {
        private List<String> list=new ArrayList<String>();

        public MyData(){

        }
        public MyData(List<String> list){
            this.list=list;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public  void write(DataOutput out) throws IOException {
            for(int i=0;i<list.size();i++){
                out.writeUTF(this.list.get(i));
            }
        }

        @Override
        public  void readFields(DataInput in) throws IOException {
           this.list.clear();
           while (true){
               try {
                   this.list.add(in.readUTF());
               } catch (IOException e) {
                   return;
               }

           }

        }
    }

}
