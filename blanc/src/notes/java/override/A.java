package notes.java.override;

public class A {

    String name = "A";

    public String getName1() {
        return name;
    }

    public String getName2() {
        return name;
    }

    public String getKey1() {
        return "A Key";
    }

    public String getKey2() {
        return getKey1();
    }
}
