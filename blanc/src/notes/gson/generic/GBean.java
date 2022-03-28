package notes.gson.generic;

import com.google.gson.annotations.Expose;

public class GBean<T> {

    @Expose
    private final String id;
    @Expose(serialize = true, deserialize = false)
    private final String name;
    @Expose
    private T obj;

    public GBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setObj(T t) {
        this.obj = t;
    }
}
