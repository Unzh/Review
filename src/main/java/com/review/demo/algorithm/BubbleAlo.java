package com.review.demo.algorithm;

import java.util.Arrays;

/**
 * Title : BubbleAlo.java
 * Package : com.review.demo.algorithm
 * Description : <p>
 * 冒泡排序算法
 * </p>
 * Create on : 2020/7/14
 */
public class BubbleAlo {

    public static void bubbleSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        //外循环控制次数
        for (int i = 0; i < array.length; i++) {
            //内循环控制比较
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 前面的数大于后面的数就进行交换
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }

        }

    }

    /*public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        bubbleSort(array);

        System.out.println(Arrays.toString(array));
    }*/
}
