package com.company;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class search {
    public static void main(String[] args){
        my_dict x = new my_dict();
        x.main();
    }
}


class my_dict{
    public static void main(){
        ST<String, Integer> dict;  // 声明dict的形式
        dict = new ST<String, Integer>();
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++){
            String key = scan.nextLine();
            dict.put(key, i);
        }

        for (String s : dict.keys()){
            StdOut.print(s);
        }

    }

}