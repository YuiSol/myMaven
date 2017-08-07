import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/7/28.
 *内部会进行局部统计
 * Mapper  -->  Reducer
 * 每一个ReduceThread线程都会生成一个文件
 * Reducer会根据hashPartioner去获取属于它自己的分区，然后把key相同的进行汇总
 * 每一个相同的key只会调用一次reduce（）
 * */
public class WordCountReducer extends Reducer<Phone,Text,Text,Text> {
    /**
     *
     * @param key 接收map的键
     * @param values 接收mapper的值
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Phone key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        int count1=0;
        int count2=0;
        while(iterator.hasNext()){
            context.write(iterator.next(),new Text(key.toString()));
        }

    }

}
