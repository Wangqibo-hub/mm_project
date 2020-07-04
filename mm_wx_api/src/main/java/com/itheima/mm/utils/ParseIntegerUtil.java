package com.itheima.mm.utils;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-24 17:19
 */
public class ParseIntegerUtil {

    public static Integer parseObjectToInteger(Object obj){
        Integer number = null;
        if (obj instanceof String) {
            number = Integer.valueOf((String) obj);
        } else {
            number = (Integer) obj;
        }
        return number;
    }
}
