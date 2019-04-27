package notes.java.override;

public class B extends A {

    String name = "B";

    @Override
    public String getName1() {
        return super.getName1();
    }

    @Override
    public String getName2() {
        return this.name;
    }

    @Override
    public String getKey1() {
        return "B Key";
    }

    @Override
    public String getKey2() {
        return super.getKey2();
    }
}
