package algorithm.base;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JavaIO {

    public interface TxtEditor {

        String read(String filePath) throws IOException;

        default boolean write(String filePath, String[] content) throws IOException {
            return write(filePath, content, true);
        }

        boolean write(String filePath, String[] content, boolean append) throws IOException;
    }

    // FileInputStream and FileOutputStream are 'Byte Based'.
    public static class FileStream implements TxtEditor {

        @Override
        public String read(String filePath) throws IOException {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            try (FileInputStream stream = new FileInputStream(file)) {
                int length;
                byte[] bytes = new byte[1024];
                while ((length = stream.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, length, StandardCharsets.UTF_8));
                }
            }
            return sb.toString();
        }

        @Override
        public boolean write(String filePath, String[] content, boolean append) throws IOException {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            try (FileOutputStream stream = new FileOutputStream(file, append)) {
                int count = 0;
                for (String line : content) {
                    if (count++ != 0) {
                        stream.write('\n');
                    }
                    stream.write(line.getBytes(StandardCharsets.UTF_8));
                    stream.flush();
                }
            }
            return true;
        }
    }

    // BufferedInputStream and BufferedOutputStream are 'Byte Based'.
    public static class BufferedFileStream implements TxtEditor {

        @Override
        public String read(String filePath) throws IOException {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            try (FileInputStream stream = new FileInputStream(file)) {
                try (BufferedInputStream bufferStream = new BufferedInputStream(stream)) {
                    int length;
                    byte[] bytes = new byte[1024];
                    while ((length = bufferStream.read(bytes)) != -1) {
                        sb.append(new String(bytes, 0, length, StandardCharsets.UTF_8));
                    }
                }
            }
            return sb.toString();
        }

        @Override
        public boolean write(String filePath, String[] content, boolean append) throws IOException {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            try (FileOutputStream stream = new FileOutputStream(file, append)) {
                try (BufferedOutputStream bufferStream = new BufferedOutputStream(stream)) {
                    int count = 0;
                    for (String line : content) {
                        if (count++ != 0) {
                            stream.write('\n');
                        }
                        bufferStream.write(line.getBytes(StandardCharsets.UTF_8));
                        bufferStream.flush();
                    }
                }
            }
            return true;
        }
    }

    // FileReader and FileWriter are 'Character Based'
    public static class ReaderWriter implements TxtEditor {

        @Override
        public String read(String filePath) throws IOException {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            try (FileReader reader = new FileReader(file)) {
                int length;
                char[] chars = new char[1024];
                while ((length = reader.read(chars)) != -1) {
                    sb.append(chars, 0, length);
                }
            }
            return sb.toString();
        }

        @Override
        public boolean write(String filePath, String[] content, boolean append) throws IOException {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            try (FileWriter writer = new FileWriter(file, append)) {
                int count = 0;
                for (String line : content) {
                    if (count++ != 0) {
                        writer.write('\n');
                    }
                    writer.write(line);
                    writer.flush();
                }
            }
            return true;
        }
    }

    // BufferedReader and BufferedWriter are 'Character Based'
    public static class BufferReaderWriter implements TxtEditor {

        @Override
        public String read(String filePath) throws IOException {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            try (FileReader reader = new FileReader(file)) {
                try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                    String line;
                    int count = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (count++ != 0) {
                            sb.append('\n');
                        }
                        sb.append(line);
                    }
                }
            }
            return sb.toString();
        }

        @Override
        public boolean write(String filePath, String[] content, boolean append) throws IOException {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            try (FileWriter writer = new FileWriter(file, append)) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    int count = 0;
                    for (String line : content) {
                        if (count++ != 0) {
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.write(line);
                        bufferedWriter.flush();
                    }
                }
            }
            return true;
        }
    }

    // Scanner and PrintWriter are 'Character Based'
    public static class ScannerPrintWriter implements TxtEditor {

        @Override
        public String read(String filePath) throws IOException {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            try (FileInputStream stream = new FileInputStream(file)) {
                try (Scanner scanner = new Scanner(stream)) {
                    int count = 0;
                    while (scanner.hasNext()) {
                        if (count++ != 0) {
                            sb.append('\n');
                        }
                        sb.append(scanner.nextLine());
                    }
                }
            }
            return sb.toString();
        }

        @Override
        public boolean write(String filePath, String[] content, boolean append) throws IOException {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            try (FileOutputStream stream = new FileOutputStream(file, append)) {
                try (PrintWriter printWriter = new PrintWriter(stream)) {
                    int count = 0;
                    for (String line : content) {
                        if (count++ != 0) {
                            printWriter.write('\n');
                        }
                        printWriter.write(line);
                        printWriter.flush();
                    }
                }
            }

            return true;
        }
    }

    private static TxtEditor getEditor() {
        return new BufferedFileStream();
    }

    @Test
    public void testcase1() throws IOException {
        test(new FileStream());
    }

    @Test
    public void testcase2() throws IOException {
        test(new BufferedFileStream());
    }

    @Test
    public void testcase3() throws IOException {
        test(new ReaderWriter());
    }

    @Test
    public void testcase4() throws IOException {
        test(new BufferReaderWriter());
    }

    @Test
    public void testcase5() throws IOException {
        test(new ScannerPrintWriter());
    }

    private void test(TxtEditor editor) throws IOException {
        final String[][] SOURCES = new String[][]{{""}, {"Hello"}, {" World"}, {"Hello\nWorld"}, {"Hello", "World!"}};
        final boolean[] APPEND = new boolean[]{false, true, true, false, false};
        final String[] EXPECTED = new String[]{"", "Hello", "Hello World", "Hello\nWorld", "Hello\nWorld!"};
        String testFilepath = "/Users/jingzeh/Documents/script/test.txt";
        String content;
        for (int i = 0; i < 5; i++) {
            editor.write(testFilepath, SOURCES[i], APPEND[i]);
            content = editor.read(testFilepath);
            assertThat(content, equalTo(EXPECTED[i]));
        }
    }

    public static void main(String[] args) {
        final String filepath = "/Users/jingzeh/Documents/script/test.txt";
        TxtEditor editor = getEditor();
        try {
            editor.write(filepath, new String[]{"Hello World!"}, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String doc;
        try {
            doc = editor.read(filepath);
            System.out.println("---   File Starts   ---");
            System.out.println(doc);
            System.out.println("---    File Ends    ---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
