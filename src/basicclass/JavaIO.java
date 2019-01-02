package basicclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

public class JavaIO {

  interface TxtEditable {

    String read(String filepath);

    boolean write(String filepath, String[] content);
  }

  public static class Buffer implements TxtEditable {

    @Override
    public String read(String filepath) {
      StringBuilder sb = new StringBuilder();
      try {
        File file = new File(filepath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String str = null;
        sb.append("---File Starts---\n");
        while ((str = br.readLine()) != null) {
          sb.append(str + "\n");
        }
        sb.append("\n---File Ends---");

        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return sb.toString();
    }

    @Override
    public boolean write(String filepath, String[] content) {
      boolean isSuccessful = false;
      try {
        File file = new File(filepath);
        if (!file.exists()) {
          file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(writer);

        for (String str : content) {
          bw.write(str);
          bw.newLine();
          bw.flush();
        }
        bw.close();
        isSuccessful = true;
      } catch (IOException e) {
        e.printStackTrace();
      }

      return isSuccessful;
    }

  }

  public static class FileStream implements TxtEditable {

    @Override
    public String read(String filepath) {
      StringBuilder sb = new StringBuilder();
      try {
        File file = new File(filepath);
        FileInputStream stream = new FileInputStream(file);

        byte[] bytes = new byte[1024];
        int length = 0;

        sb.append("---File Starts---\n");
        while ((length = stream.read(bytes)) != -1) {
          sb.append(new String(bytes, 0, length));
        }
        sb.append("\n---File Ends---");

        stream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return sb.toString();
    }

    @Override
    public boolean write(String filepath, String[] content) {
      // TODO Auto-generated method stub
      return false;
    }

  }

  public static class ScannerPrintWriter implements TxtEditable {

    @Override
    public String read(String filepath) {
      StringBuilder sb = new StringBuilder();

      try {
        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        sb.append("---File Starts---\n");
        while (sc.hasNext()) {
          sb.append(sc.nextLine() + "\n");
        }
        sb.append("\n---File Ends---");

        sc.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      return sb.toString();
    }

    @Override
    public boolean write(String filepath, String[] content) {
      boolean isSuccessful = false;
      try {
        File file = new File(filepath);
        if (!file.exists()) {
          file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(file);

        for (String str : content) {
          pw.write(str + "\n");
          pw.flush();
        }
        pw.close();

        isSuccessful = true;
      } catch (IOException e) {
        e.printStackTrace();
      }

      return isSuccessful;
    }

  }
}
