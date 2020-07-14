package com.review.demo.algorithm;

import java.util.Arrays;

/**
 * Title : ChooseAlo.java
 * Package : com.review.demo.algorithm
 * Description : <p>
 * 选择排序算法
 * </p>
 * Create on : 2020/7/14
 */
public class ChooseAlo {

    public static void choosingSort(int[] arrays) {

        if (arrays == null || arrays.length == 0) {
            return;
        }

        for (int i = 0; i < arrays.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }

            //交换位置
            if (i != minIndex) {
                swap(arrays, i, minIndex);
            }
        }
    }

    /**
     * 交换位置
     *
     * @param array
     * @param srcIndex
     * @param tarIndex
     */
    private static void swap(int[] array, int srcIndex, int tarIndex) {
        int temp = array[tarIndex];
        array[tarIndex] = array[srcIndex];
        array[srcIndex] = temp;
    }

 /*   public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        choosingSort(array);

        System.out.println(Arrays.toString(array));
    }*/
}
