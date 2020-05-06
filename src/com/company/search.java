package com.company;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class search {
    public static void main(String[] args) {
    }
}


class my_dict {
    public static void main() {
        ST<String, Integer> dict;  // 声明dict的形式
        dict = new ST<String, Integer>();
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String key = scan.nextLine();
            dict.put(key, i);
        }

        for (String s : dict.keys()) {
            StdOut.print(s);
        }

    }

}

class BST { //二叉查找树
    public static int addone(node root, int val) {
        node std = root;
        while (true) {
            if (std.val >= val) {
                if (std.left == null) {
                    std.insert(new node(val));
                    return 0;
                } else {
                    std = std.left;
                }
            } else {
                if (std.right == null) {
                    std.insert(new node(val));
                    return 0;
                } else {
                    std = std.right;
                }
            }
        }
    }

    public static void addsome(node root, int[] vals) {
        for (int i : vals) {
            addone(root, i);
        }
    }

    public static void rebuild(node root) {
        int[] target = getnum(root);
        node newroot = build(target);
        System.out.println(newroot.left.right.val);

    }

    public static int[] getnum(node target) {
        if ((target.left == null) && (target.right == null)) {
            return new int[]{target.val};
        } else if (target.left == null) {
            int[] temp = getnum(target.right);
            int len = temp.length + 1;
            int[] result = new int[len];
            System.arraycopy(new int[]{target.val}, 0, result, 0, 1);
            System.arraycopy(temp, 0, result, 1, len - 1);
            return result;
        } else if (target.right == null) {
            int[] temp = getnum(target.left);
            int len = temp.length + 1;
            int[] result = new int[len];
            System.arraycopy(new int[]{target.val}, 0, result, len - 1, 1);
            System.arraycopy(temp, 0, result, 0, len - 1);
            return result;
        } else {
            int[] tempr = getnum(target.right);
            int[] templ = getnum(target.left);
            int[] result = new int[templ.length + 1 + tempr.length];
            System.arraycopy(templ, 0, result, 0, templ.length);
            System.arraycopy(new int[]{target.val}, 0, result, templ.length, 1);
            System.arraycopy(tempr, 0, result, templ.length + 1, tempr.length);
            return result;
        }
    }


    public static node build(int[] nums) {
        if (nums.length == 1) {
            return new node(nums[0]);
        } else {
            int mid = (int) nums.length / 2;
            node result = new node(nums[mid]);
            if (mid > 0) {
                int[] left = new int[mid];
                System.arraycopy(nums, 0, left, 0, mid);
                result.insert(build(left));
            }
            if (nums.length - mid - 1 > 0) {
                int[] right = new int[nums.length - mid - 1];
                System.arraycopy(nums, mid + 1, right, 0, nums.length - mid - 1);
                result.insert(build(right));
            }
            return result;
        }
    }


    public static void main() {
        node root = new node(1);
        addone(root, 3);
        addone(root, 7);
        addone(root, 6);
        addsome(root, new int[]{2, 5, 4, 8});

        rebuild(root);
    }

}

class RBT {  // 红黑树
    // 写不出来
}

class node {
    int val;
    node father = null;
    node left = null;
    node right = null;
    boolean color;  // 黑为false, 红为true

    node(int val) {
        this.val = val;
    }

    node(int val, boolean color) {
        this.val = val;
        this.color = color;
    }

    public void insert(node son) {  // 二叉树插入
        if (son.val > val) {
            right = son;
        } else {
            left = son;
        }
        son.father = this;
    }
}

