package notes.java.reflect;

public class Child extends Parent {

    private String hack = "Hack me";

    public Child(String id, String name) {
        super(id, name);
    }

    public String getHack() {
        return hack;
    }
}
