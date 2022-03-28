package notes.gson.extension;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class ExtensionTest {

    @Test
    public void testExtensionField() {
        Parent child  = new Child("321", "Tom", "Kate");

        String json1 = new Gson().toJson(child);
        Assert.assertEquals("{\"friend\":\"Kate\",\"id\":\"321\",\"name\":\"Tom\"}", json1);

        String json2 = new Gson().toJson(child, Parent.class);
        Assert.assertEquals("{\"id\":\"321\",\"name\":\"Tom\"}", json2);
    }
}
