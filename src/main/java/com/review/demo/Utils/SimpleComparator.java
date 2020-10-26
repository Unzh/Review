package com.review.demo.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * 自定义排序
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/10/26
 */
public class SimpleComparator {

    private static void compare2(){
        //这里的顺序，是我自己定义的一个List<String>
        String[] regulation = {"buke","jams","rose","lua"};
        final List<String> regulationOrder = Arrays.asList(regulation);
        String[] ordered = {"rose","jams","lua","rose","buke","jams","rose","buke","jams"};
        List<String> orderedList = Arrays.asList(ordered);
        Collections.sort(orderedList, new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                int io1 = regulationOrder.indexOf(o1);
                int io2 = regulationOrder.indexOf(o2);
                return io1 - io2;
            }
        });
        System.out.println(orderedList);
    }

    public static void main(String[] args) {
        SimpleComparator.compare2();
    }
}
