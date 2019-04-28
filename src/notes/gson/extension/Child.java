package notes.gson.extension;

public class Child extends Parent {

    private final String friend;

    public Child(String id, String name, String friend) {
        super(id, name);
        this.friend = friend;
    }
}
