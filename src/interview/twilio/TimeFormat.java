package interview.twilio;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TimeFormat {
  
  private final String[] NUMBER = {"01", "02", "03", "04", "05", "06", "07", "08", "09", 
                                   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", 
                                   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                                   "30", "31"};
  
  private final String[] DAY = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", 
                                "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", 
                                "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", 
                                "30th", "31st"};
  
  private final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
  
  private final Map<String, String> DAY_MAP;
  private final Map<String, String> MONTH_MAP;
  
  public TimeFormat() {
    this.DAY_MAP = new HashMap<>();
    this.MONTH_MAP = new HashMap<>();
    for (int i = 0; i < DAY.length; i++) {
      this.DAY_MAP.put(DAY[i], NUMBER[i]);
    }
    for (int i = 0; i < MONTH.length; i++) {
      this.MONTH_MAP.put(MONTH[i], NUMBER[i]);
    }
  }
  
  public String formateTime(String time) {
    String[] times = time.split(" ");
    if (times.length != 3) { return ""; }
    String neoMonth = MONTH_MAP.get(times[1]); 
    String neoDay = DAY_MAP.get(times[0]); 
    return times[2] + "-" + neoMonth + "-" + neoDay;
  }
  
  @Test
  public void testFormateTime1() {
    String time = "1st Mar 1984";
    String res = "1984-03-01";
    assertEquals(res, formateTime(time));
  }
  
  @Test
  public void testFormateTime2() {
    String time = "2nd Feb 2013";
    String res = "2013-02-02";
    assertEquals(res, formateTime(time));
  }
  
  @Test
  public void testFormateTime3() {
    String time = "4th Apr 1900";
    String res = "1900-04-04";
    assertEquals(res, formateTime(time));
  }

  public static void main(String[] args) {
//    String time = "";
//    TimeFormat one = new TimeFormat();
//    System.out.println();
  }

}
