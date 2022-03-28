package notes.gson.generic;

import org.junit.Test;

public class GenericTest {

    @Test
    public void test_generic() {

        String id = "9527";
        String name = "Mr. Bean";
        GBean<NestBean> bean = new GBean<>(id, name);
        bean.setObj(new NestBean(13, "This is the nested Obj Info"));

    }
}
