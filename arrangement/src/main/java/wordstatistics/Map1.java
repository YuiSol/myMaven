package wordstatistics;

import org.apache.hadoop.hdfs.util.EnumCounters;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuiSol on 2017/7/30.
 */
public class Map1 implements WritableComparable<Map1>{
    private String key;
    private int number;

    public Map1() {
    }

    public Map1(String key, int number) {

        this.key = key;
        this.number = number;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Map1{" +
                "key='" + key + '\'' +
                ", number=" + number +
                '}';
    }

    public int compareTo(Map1 o) {
        return this.key.hashCode()-o.hashCode();
    }

    public void write(DataOutput dataOutput) throws IOException {
              dataOutput.writeInt(this.getNumber());

            dataOutput.writeUTF(this.getKey());
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.setNumber(dataInput.readInt());
        this.setKey(dataInput.readUTF());
    }
}
