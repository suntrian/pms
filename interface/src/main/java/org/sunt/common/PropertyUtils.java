package org.sunt.common;

public class PropertyUtils {

    private PropertyUtils() {
    }

    public static String snake(String name) {
        char[] chars = new char[(int) (name.length()*1.5)];
        int j = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                chars[j++] = '_';
                chars[j++] = Character.toLowerCase(c);
            } else {
                chars[j++] = c;
            }
        }
        return new String(chars, 0, j);
    }

    public static String camel(String name) {
        char[] chars = new char[name.length()];
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ('_' == c) {
                flag = true;
            } else if (flag){
                chars[j++] = Character.toUpperCase(c);
                flag = false;
            } else {
                chars[j++] = c;
            }
        }
        return new String(chars, 0, j);
    }
}
