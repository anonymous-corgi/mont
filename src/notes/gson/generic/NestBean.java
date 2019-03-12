package notes.gson.generic;

import com.google.gson.annotations.Expose;

public class NestBean {

    @Expose
    private final int id;
    @Expose
    private final String info;

    public NestBean(int id, String info) {
        this.id = id;
        this.info = info;
    }
}
