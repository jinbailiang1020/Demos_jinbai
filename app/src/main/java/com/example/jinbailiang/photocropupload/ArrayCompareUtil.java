package com.example.jinbailiang.photocropupload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by jinbailiang on 2016/6/16.
 */
public class ArrayCompareUtil {

    /**
     * 数组内容必须是同一个Object类型
     *
     * @param array 数组
     * @return 排序成功？
     */
    public static boolean compareNameSortArray(Object array[]) {
        if (array.length < 2) {
            return false;//无数据排序
        }
        List<File> list = new ArrayList<>();
        int k = 0;
        for (int n = 0; n < array.length; n++) { //去除不是一类的数组元素
            if (array[n] instanceof File) {
                list.add((File) array[n]);
            }
        }
        array = list.toArray();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {

                String fileName01 = ((File) array[i]).getName();
                String fileName02 = ((File) array[j]).getName();
                fileName01 = filterUnNumber(fileName01);
                fileName02 = filterUnNumber(fileName02);
                Long longCompare = Long.parseLong(fileName01) - Long.parseLong(fileName02);
                if (longCompare <= 0) {
                    Object file = array[i];
                    array[i] = array[j];
                    array[j] = file;
                }
            }
        }
        return true;
    }


    private static String filterUnNumber(String str) {
        // 只允数字
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        java.util.regex.Matcher m = p.matcher(str);
//替换与模式匹配的所有字符（即非数字的字符将被""替换）
        return m.replaceAll("").trim();

    }
}
