package notes.gson.expose;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

public class GsonTest {

    @Test
    public void test_ExposeAnnotation() {
        String id = "9527";
        String name = "Mr. Bean";
        Bean bean = new Bean(id, name);

        Gson gson = new Gson();
        Gson gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = gson.toJson(bean);
        String jsonExpose = gsonExpose.toJson(bean);

        Assert.assertEquals("{\"id\":\"9527\",\"name\":\"Mr. Bean\"}", json);
        Assert.assertEquals("{\"id\":\"9527\",\"name\":\"Mr. Bean\"}", jsonExpose);

        Bean gBean = gson.fromJson(json, Bean.class);
        Bean gBeanExpose = gsonExpose.fromJson(json, Bean.class);

        Assert.assertNotNull(gBean.getName());
        Assert.assertNull(gBeanExpose.getName());
    }

    @Test
    public void test_Null() {
        String id = null;
        String name = "Mr. Bean";
        Bean bean = new Bean(id, name);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = gson.toJson(bean);

        Assert.assertEquals("{\"name\":\"Mr. Bean\"}", json);
    }
}
