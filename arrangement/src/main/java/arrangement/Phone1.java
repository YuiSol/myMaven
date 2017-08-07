package arrangement;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by YuiSol on 2017/7/29.
 */
public class Phone1 implements WritableComparable<Phone1> {
    private String phone;
    private int upData;
    private int downData;
    public Phone1() {
    }

    public Phone1(String phone, int upData, int downData) {

        this.phone = phone;
        this.upData = upData;
        this.downData = downData;
    }

    public int compareTo(Phone1 phone) {
        return this.getUpData()==phone.getUpData()?phone.getDownData()-this.getDownData():phone.getUpData()-this.getUpData();
    }
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(upData);
        dataOutput.writeInt(downData);
        dataOutput.writeUTF(phone);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.setUpData(dataInput.readInt());
        this.setDownData(dataInput.readInt());
        this.setPhone(dataInput.readUTF());
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUpData() {
        return upData;
    }

    public void setUpData(int upData) {
        this.upData = upData;
    }

    public int getDownData() {
        return downData;
    }

    public void setDownData(int downData) {
        this.downData = downData;
    }

    @Override
    public String toString() {

            return  this.getTrim(upData)+this.getTrim(downData);

    }

    private String getTrim(int number){
        String str=String.valueOf(number);
        StringBuffer sb=new StringBuffer(str);
        for(int i=0;i<8-sb.length();i++){
            sb.append(" ");
        }
        return sb.toString();
    }
}
