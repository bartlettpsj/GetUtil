package com.iqss;

import java.lang.reflect.Field;
import java.util.regex.*;

public class LodashGet {

    /**
     * Meant to mimic the behavior of the Lodash get() function. (https://lodash.com/docs/4.15.0#get)
     * <p>
     * Conventional Java 7 assignment of nested variable with null checking:
     * <p>
     * Object o = null;
     * if (a != null && a.b != null && a.b.c != null && a.b.c.d != null) {
     * o = a.b.c;
     * }
     * ...
     * <p>
     * With this method:
     * Object o = get(a, "b.c[1].d[0]");
     *
     * @param object object that contains indirect or direct reference to a variable we want to retrieve
     * @param path   the path of the variable within the object
     * @return
     */
    public static Object get(Object object, String path) {
        String[] arr = path.split("\\.");
        Pattern arrayPattern = Pattern.compile("(\\w+)\\[(\\d+)\\]");

        try {
            for (String property : arr) {
                Matcher matcher = arrayPattern.matcher(property);
                if (matcher.matches()) {
                    String fieldName = matcher.group(1);
                    int index = Integer.parseInt(matcher.group(2));

                    object = getObject(object, fieldName);

                    if (object instanceof Object[] objArray) {
                        object = (index >= 0 && index < objArray.length) ? objArray[index] : null;
                    } else if (object instanceof int[] intArray) {
                        object = (index >= 0 && index < intArray.length) ? intArray[index] : null;
                    } else {
                        return null;
                    }
                } else {
                    object = getObject(object, property);
                }
            }
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    /** Helper method to retrieve a field value from an object. */
    private static Object getObject(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (object == null) return null;

        Field f = object.getClass().getDeclaredField(fieldName);
        boolean b = f.canAccess(object);
        if (!b) f.setAccessible(true);
        object = f.get(object);
        if (!b) f.setAccessible(false);
        return object;
    }
}
