package algorithm.interview.twilio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Hosts {
  
  public void host(String filename) {

    Map<String, Integer> count = new HashMap<>();
    
    try {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str = null;
        while ((str = br.readLine()) != null) {
            String host = str.substring(0, str.indexOf(" "));
            count.put(host, count.getOrDefault(host, 0) + 1);
        }
        
        br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    try {
        File file = new File("records_" + filename);
        file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
  }

  public static void main(String[] args) {

  }

}
