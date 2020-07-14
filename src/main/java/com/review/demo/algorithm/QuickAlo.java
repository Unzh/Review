package com.review.demo.algorithm;

import java.util.Arrays;

/**
 * Title : QuickAlo.java
 * Package : com.review.demo.algorithm
 * Description : <p>
 * 快速排序算法
 * </p>
 * Create on : 2020/7/14
 */
public class QuickAlo {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {

        if (array == null || array.length <= 1 || left >= right) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (temp <= array[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[left]，则填坑
            if (left < right) {
                array[left] = array[right];
                ++left;
            }

            while (temp >= array[left] && left < right) {
                ++left;
            }
            // 当基准数小于了 arr[right]，则填坑
            if (left < right) {
                array[right] = array[left];
                --right;
            }
        }
        array[left] = temp;
        return left;
    }

/*    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        quickSort(array);

        System.out.println(Arrays.toString(array));
    }*/
}


