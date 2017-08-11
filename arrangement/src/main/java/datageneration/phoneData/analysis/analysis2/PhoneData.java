package datageneration.phoneData.analysis.analysis2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YuiSol on 2017/8/1.
 */
public  class PhoneData implements WritableComparable<PhoneData> {
    private String location;
    private Date date;
    private String type;
    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public PhoneData(String type, String date, String location) {
        this.type = type;
        try {
            this.date = sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.location = location;
    }

    public PhoneData() {
    }

    @Override
    public String toString() {
        return "PhoneData{" +
                "location='" + location + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int compareTo(PhoneData o) {
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(this.getDate());
        ca2.setTime(o.getDate());
        return ca1.get(Calendar.YEAR)==ca2.get(Calendar.YEAR)?
                ca1.get(Calendar.MONTH)-ca2.get(Calendar.MONTH):
                ca1.get(Calendar.YEAR)-ca2.get(Calendar.YEAR);
    }

    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(this.getType());
        dataOutput.writeUTF(this.getLocation());
        dataOutput.writeUTF(sf.format(this.getDate()));
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.setType(dataInput.readUTF());
        this.setLocation(dataInput.readUTF());
        try {
            this.setDate(sf.parse(dataInput.readUTF()));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}