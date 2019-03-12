package notes.java.override;

import java.util.Random;

public class A {

    static Random RANDOM = new Random();

    int id = RANDOM.nextInt(100);

    String name = "A";

    public String getName1() {
        return name;
    }

    public String getName2() {
        return name;
    }
}
