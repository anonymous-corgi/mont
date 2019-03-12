package notes.gson.expose;

import com.google.gson.annotations.Expose;

public class Bean {

    @Expose
    private final String id;
    @Expose(serialize = true, deserialize = false)
    private final String name;

    public Bean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
