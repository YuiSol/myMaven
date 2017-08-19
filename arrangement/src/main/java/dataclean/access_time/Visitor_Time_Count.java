package dataclean.access_time;

import datageneration.data.order.util.DateUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
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
 * Created by YuiSol on 2017/8/17.
 * 每个用户每天访问深度总访问时长
 * 需要字段ip，请求页面，跳转页面，时间（年月日） 时间（时分秒）
 */
public class Visitor_Time_Count {
    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(Visitor_Time_Count.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(UserVisitor.class);
        job.setMapOutputValueClass(UserVisitor.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path("D:\\buffer\\log\\visition.log"));
        FileSystem fileSystem = FileSystem.newInstance(config);
        Path path = new Path("D:\\4b\\离线业务项目实战数据\\数据分析");
        if(fileSystem.exists(path)){
            fileSystem.delete(path,true);
        }
        FileOutputFormat.setOutputPath(job,path);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class MyMapper extends Mapper<LongWritable,Text,UserVisitor,UserVisitor>{
        private static UserVisitor user;
        private StringBuffer sb;
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            System.out.println("mapper 0% reducer 0%");
            if(user==null){
                user=new UserVisitor();
            }
            if(sb==null){
                sb=new StringBuffer();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
           String[] strs=value.toString().split("\t");
            sb.setLength(0);
            user.setAddress(strs[1]);
            sb.append(strs[strs.length-2]).append("-").append(strs[strs.length-1]).append("-").append(strs[3]);
            user.setDate(sb.toString());
            sb.setLength(0);
            sb.append(strs[4]).append(":").append(strs[5]).append(":").append(strs[6]);
            user.setTime(sb.toString());
            sb.setLength(0);
            user.setReferer(strs[10]);
            user.setRequest(strs[7]);
            context.write(user,user);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            System.out.println("mapper 100% reducer 0%");
        }
    }
    //每个用户每天访问深度总访问时长
    public static class MyReducer extends Reducer<UserVisitor,UserVisitor,NullWritable,Text> {
        private StringBuffer sb;
        private List<String> timecount;
        private List<Integer> timeavg;
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            if(sb==null){
                sb=new StringBuffer();
            }
            if(timecount==null){
                timecount=new ArrayList<String>();
            }
            if(timeavg==null){
                timeavg=new ArrayList<Integer>();
            }
        }
        //用户:XX  时间:XX    访问深度:XX  总时长:XX
        //用户:XX  时间:XX    访问深度:XX  总时长:XX
        //========================================
        @Override
        protected void reduce(UserVisitor key, Iterable<UserVisitor> values, Context context) throws IOException, InterruptedException {
            int avgtime=0;
            sb.setLength(0);
            timecount.clear();
            sb.append("用户:").append(key.getAddress()).append("\t")
                    .append("时间:").append(key.getDate()).append("\t");
            for(Iterator<UserVisitor> iterator = values.iterator();iterator.hasNext();){
                UserVisitor next = iterator.next();
                timecount.add(next.getTime());
            }
            Collections.sort(timecount, new MyCompartor());

           if(timecount.size()<=0) return;
            else if (timecount.size()==1) avgtime=this.getAvgTime();
            else{
               for(int i=0;i<timecount.size()-1;i++){
                   avgtime+= DateUtil.getTimecount(timecount.get(i),timecount.get(i+1));
               }
               avgtime+=this.getAvgTime();
           }
            timeavg.add(avgtime/timecount.size());
            sb.append("访问深度:").append(timecount.size()).append("\t")
                .append("总时长：").append(avgtime).append("s");
            context.write(null, new Text(sb.toString()));
            context.write(null,new Text("===================================="));
        }
        public int getAvgTime(){
            if(this.timeavg.size()==0){
                return 6000;
            }else {
                int count=0;
                for(int i=0;i<this.timeavg.size();i++){
                    count+=timeavg.get(i);
                }
                return count/this.timeavg.size();
            }
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            System.out.println("mapper 100% reducer 100%");
        }

    }
    public static class UserVisitor implements WritableComparable<UserVisitor> {
        private String address;
        private String request;
        private String referer;
        private String date;
        private String time;

        public UserVisitor(String address, String request, String referer, String time, String date) {
            this.address = address;
            this.request = request;
            this.referer = referer;
            this.time = time;
            this.date = date;
        }

        public UserVisitor() {
        }

        @Override
        public String toString() {
            return "UserVisitor{" +
                    "address='" + address + '\'' +

                    ", request='" + request + '\'' +
                    ", referer='" + referer + '\'' +
                    ", date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }



        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int compareTo(UserVisitor o) {
            return this.date.equals(o.getDate())
                    ?this.address.compareTo(o.getAddress())
                    :this.date.compareTo(o.getDate());
        }

        public void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeUTF(this.address);
            dataOutput.writeUTF(this.date);
            dataOutput.writeUTF(this.referer);
            dataOutput.writeUTF(this.request);
            dataOutput.writeUTF(this.time);
        }

        public void readFields(DataInput dataInput) throws IOException {
            this.address=dataInput.readUTF();
            this.date=dataInput.readUTF();
            this.referer=dataInput.readUTF();
            this.request=dataInput.readUTF();
            this.time=dataInput.readUTF();
        }
    }
    public static class MyCompartor implements Comparator<String>{
        public int compare(String o1, String o2) {
            String[] split1 = o1.split(":");
            String[] split2 = o2.split(":");
            int hour = split1[0].compareTo(split2[0]);
            if(hour!=0) return hour;
            int minute=split1[1].compareTo(split2[1]);
            if(minute!=0) return minute;
            return split1[2].compareTo(split2[2]);
        }
    }
}
