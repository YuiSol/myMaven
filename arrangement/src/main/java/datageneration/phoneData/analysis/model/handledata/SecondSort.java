package datageneration.phoneData.analysis.model.handledata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by tourbis on 2017/8/1.
 */
public class SecondSort extends WritableComparator {
    public SecondSort(){
        super(IntWritable.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return b.compareTo(a);
    }
}
