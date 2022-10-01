package com.review.demo.interview;
import java.util.*;

/**
 * 小Q是篮球训练队的教练，篮球队新加入了N名队员，第i名队员的篮球水平值为ai。
 * 小Q现在要把他们按照以下的要求分为A队和B队进行训练:
 * 1、A队的队员水平值之和严格大于B队的队员水平值之和
 * 2、对于A队中的任意一名队员，如果把他分配到B队，A队的水平值之和就会严格小于B队的水平值之和。
 * 3、每个队员必须要加入一个队伍
 * 小Q现在想知道有多少种方案可以按照以上要求完成分队。
 */
public class Question5 {

    public static void main(String[] args){
        int n;
        int sum = 0;
        long ans = 0;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }
        Arrays.sort(a);
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = sum; j >= a[i]; j--) {
                dp[j] += dp[j - a[i]];
                //A>B && A-a[i]<B+a[i]
                if (j > sum - j && j - a[i] < sum - j + a[i]) {
                    ans += dp[j - a[i]];
                }
            }
        }
        System.out.println(ans);
    }
}
