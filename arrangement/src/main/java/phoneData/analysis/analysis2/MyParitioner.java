package phoneData.analysis.analysis2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import phoneData.rondomtodata.model.Phone;

/**
 * Created by YuiSol on 2017/8/1.
 */
public class MyParitioner extends Partitioner<Text,PhoneData> {

    @Override
    public int getPartition(Text text,PhoneData phoneData, int i) {
        for(int j=1;j<=Call_Location.CALL_LOCATION.length;j++){
            if(Call_Location.CALL_LOCATION[j-1].equals(phoneData.getLocation())){

                return j;
            }
        }
        return 0;
    }
}
