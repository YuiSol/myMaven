package arrangement;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by YuiSol on 2017/7/29.
 */
public class WordCountReducer1 extends Reducer<Phone1,Text,Text,Phone1> {

    @Override
    protected void reduce(Phone1 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        System.out.println(key.getPhone());
        String name=null;
        int count1=0;
        int count2=0;
        String[] str=null;
        for(Iterator<Text> iterator = values.iterator();iterator.hasNext();){
            str = iterator.next().toString().split(",");
            count1+=Integer.parseInt(str[1]);
            count2+=Integer.parseInt(str[2]);
            if(name==null){
                name=str[0];
            }
        }

        context.write(new Text(name),new Phone1(name,count1,count2));
    }
}
