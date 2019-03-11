package notes.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

public class GsonTest {

    @Test
    public void test_ExposeAnnotation() {
        String name = "Mr. Bean";
        Bean bean = new Bean(name);

        Gson gson = new Gson();
        Gson gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = gson.toJson(new Bean("Testing"));
        String jsonExpose = gsonExpose.toJson(new Bean("Testing"));

        Assert.assertEquals("{\"name\":\"Testing\"}", json);
        Assert.assertEquals("{\"name\":\"Testing\"}", jsonExpose);

        Bean gBean = gson.fromJson(json, Bean.class);
        Bean gBeanExpose = gsonExpose.fromJson(json, Bean.class);

        Assert.assertNotNull(gBean.getName());
        Assert.assertNull(gBeanExpose.getName());
    }
}
