package notes.gson.enumTest;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

    @Test
    public void test_enum_custom1() {
        Gson gson = new Gson();

        Assert.assertEquals("\"MONDAY\"", gson.toJson(Day.MONDAY, Day.class));
        Assert.assertEquals("\"2\"", gson.toJson(Day.TUESDAY, Day.class));
        Assert.assertNotEquals("2", gson.toJson(Day.TUESDAY, Day.class));
        Assert.assertEquals("\"Three\"", gson.toJson(Day.WEDNESDAY, Day.class));

        Assert.assertEquals(Day.MONDAY, gson.fromJson("MONDAY", Day.class));
        Assert.assertEquals(Day.TUESDAY, gson.fromJson("2", Day.class));
        Assert.assertEquals(Day.TUESDAY, gson.fromJson("\"2\"", Day.class));
        Assert.assertEquals(Day.WEDNESDAY, gson.fromJson("Three", Day.class));
        Assert.assertEquals(Day.WEDNESDAY, gson.fromJson("\"Three\"", Day.class));
    }

    @Test
    public void test_enum_custom2() {
        Gson gson = new Gson();

        Assert.assertEquals("{\"day\":\"MONDAY\"}", gson.toJson(new Bean(Day.MONDAY), Bean.class));
        Assert.assertEquals("{\"day\":\"2\"}", gson.toJson(new Bean(Day.TUESDAY), Bean.class));
        Assert.assertEquals("{\"day\":\"Three\"}", gson.toJson(new Bean(Day.WEDNESDAY), Bean.class));

        Assert.assertEquals(Day.MONDAY, gson.fromJson("{\"day\":\"MONDAY\"}", Bean.class).day);
        Assert.assertEquals(Day.TUESDAY, gson.fromJson("{\"day\":2}", Bean.class).day);
        Assert.assertEquals(Day.TUESDAY, gson.fromJson("{\"day\":\"2\"}", Bean.class).day);
        Assert.assertEquals(Day.WEDNESDAY, gson.fromJson("{\"day\":\"Three\"}", Bean.class).day);
    }
}
