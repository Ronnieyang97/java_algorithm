package com.company;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args){
        ArrayList<Integer> x = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }};
        Integer[] y = x.toArray(new Integer[0]);
        System.out.println(Arrays.toString(y));

    }
}
