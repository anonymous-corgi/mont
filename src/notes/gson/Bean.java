package notes.gson;

import com.google.gson.annotations.Expose;

public class Bean {

    @Expose(serialize = true, deserialize = false)
    private final String name;

    public Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
