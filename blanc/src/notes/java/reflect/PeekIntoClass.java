package notes.java.reflect;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PeekIntoClass {

    public static void main(String[] args) {
        Object child = new Child("321", "Tom");

        Class childClass = child.getClass();
        Class parentClass = childClass.getSuperclass();

        printClass(childClass);

        System.out.println(((Child) child).getHack());
        try {
            Field fieldToChange = child.getClass().getDeclaredFields()[0];
            fieldToChange.setAccessible(true);
            fieldToChange.set(child, "You are hacked");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(((Child) child).getHack());

        String json = "{\"id\":\"521\",\"name\":\"Tom\"}";
        Child child1 = new Gson().fromJson(json, Child.class);
        System.out.println(new Gson().toJson(child));
        System.out.println(new Gson().toJson(child1));
    }

    public static void printClass(Class clazz) {
        System.out.println(clazz.getName());

        Constructor[] constructors = clazz.getConstructors();
        System.out.println("\tConstructor:");
        System.out.println("\t" + toString(constructors));
        System.out.println();

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("\tField:");
        System.out.println("\t" + toString(fields));
        System.out.println();

        Method[] methods = clazz.getMethods();
        System.out.println("\tMethod:");
        System.out.println("\t" + toString(methods));
        System.out.println();

        Constructor[] constructors2 = clazz.getDeclaredConstructors();
        System.out.println("\tConstructor:");
        System.out.println("\t" + toString(constructors2));
        System.out.println();
    }

    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(",\n\t ");
        }
    }
}
