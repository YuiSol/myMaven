package datageneration.data.day15_offlinepro.test;

/**
 * Created by tourbis on 2017/7/31.
 */
public class Test {
    public enum CallMethodID{
        WithSecond(13),WithMinute(12),WithHour(10),WithDay(5),WithMonth(2);
        private int id;
        CallMethodID(int id){
            this.id=id;
        }
        public int getId() {
            return id;
        }
    }
    public static void main(String[] args) {
        System.out.println(CallMethodID.WithSecond.getId());
    }
}
