package com.nju.training_college.util;

public class MemberUtil {
    private static final int[] gate = {100, 500, 1000, 2000, 5000, 10000};

    private static final int[] discount = {100, 90, 80, 70, 60, 50};

    public static int setLevel(int consumption) {
        for (int i = 0; i < gate.length; i++) {
            if (consumption < gate[i]){
                return i;
            }
        }
        return gate.length;
    }

    public static int getDiscount(int level) {
        return discount[level];
    }
}
