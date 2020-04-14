package com.company;

import edu.princeton.cs.algs4.StdDraw;


public class sort {

    public static void main(String[] args) {
        // write your code here

        int[] target = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, -1};
        String[] test = new String[]{"2", "3", "4", "5"};
        sort_3 x = new sort_3();
        x.main();


    }
}

class sort_1 {  // 选择排序
    public static void sort(int[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            exchange(target, i, find(target, i));
            paint.show(target);
        }
    }

    public static void exchange(int[] target, int x1, int x2) {
        int temp = target[x1];
        target[x1] = target[x2];
        target[x2] = temp;
        //return target;
    }

    public static int find(int[] target, int x) {  //x是起始位置
        int temp = x;
        for (int i = x; i < target.length; i++) {
            if (target[temp] > target[i]) {
                temp = i;
            }
        }
        return temp;
    }

    public static int test_result(int[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i] > target[i + 1]) {
                System.out.println("false!!!!");
                return 0;
            }
        }
        System.out.println("success！！！");
        return 0;
    }

    public void main() {
        int[] target = new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1};
        sort(target);

    }

}

class sort_2 {  // 出列排序 只能看或交换最上面的牌，只能将最上面的牌放到最下面
    public static void sort(int[] target) {
        int times = max(target);
        for (int i = 0; i <= times; i++) {
            push(target);
            paint.show(target);
        }  // 将最大的数字推到最后一位
        for (int i = 2; i < target.length; i++) {
            int[] temp = new int[target.length - i + 1];
            System.arraycopy(target, 0, temp, 0, target.length - i + 1);
            times = max(temp);
            for (int x = 0; x < times; x++) {
                push(target);
                paint.show(target);
            }
            while (target[0] > target[1]) {
                exchange(target);
                paint.show(target);
                push(target);
                paint.show(target);
            }
            for (int x = 0; x < i; x++) {
                push(target);
                paint.show(target);
            }
        }
    }

    public static int max(int[] target) {
        int num = 0;
        int result = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] > result) {
                result = target[i];
                num = i;
            }
        }
        return num;
    }

    public static void exchange(int[] target) {
        int temp = target[0];
        target[0] = target[1];
        target[1] = temp;
    }

    public static void push(int[] target) {
        int[] temp = new int[]{target[0]};
        System.arraycopy(target, 1, target, 0, target.length - 1);
        System.arraycopy(temp, 0, target, target.length - 1, 1);

    }

    public static void main() {
        int[] target = new int[]{3, 5, 8, 7, 1, 6, 2, 4};
        sort(target);
    }

}

class paint {
    public static void show(int[] target) {
        StdDraw.setCanvasSize();
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(-1, target.length);
        StdDraw.setYscale(0, 15);
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < target.length; i++) {
            StdDraw.line(i, 0, i, target[i]);
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }

    public static void main() {

    }
}

class sort_3 {  // 归并算法，自下向上
    public static int[] sort_up(int[] target) {  // 自底向上归并排序，最后会出现一串0，因此如果存在非正数，需要对数组内所有的值都加上最小的复数的绝对值再加一
        int[][] data = divide(target);
        while (data.length != 1){
            data = merge(data);
        }
        return data[0];
    }

    public static int[][] divide(int[] target) {
        int[][] result = new int[(int) Math.ceil((float) target.length / 2)][2];
        for (int i = 0; i < result.length - 1; i++) {
            int[] temp = new int[2];
            temp[0] = Math.min(target[2 * i], target[2 * i + 1]);
            temp[1] = Math.max(target[2 * i], target[2 * i + 1]);
            result[i] = temp;
        }
        if (target.length % 2 == 0) {
            int i = result.length - 1;
            int[] temp = new int[2];
            temp[0] = Math.min(target[2 * i], target[2 * i + 1]);
            temp[1] = Math.max(target[2 * i], target[2 * i + 1]);
            result[i] = temp;
        } else {
            int i = result.length - 1;
            int[] temp = new int[]{target[target.length - 1]};
            result[i] = temp;
        }
        return result;
    }

    public static int[][] merge(int[][] data) {
        int[][] result = new int[(int) Math.ceil((float) data.length / 2)][data[0].length * 2];
        if (data.length % 2 == 0) {
            for (int i = 0; i < result.length; i++) {
                int[] temp = new int[result[0].length];
                int j = 0;
                int x = 0;
                int y = 0;
                while (j < result[0].length) {
                    temp[j] = Math.min(data[2 * i][x], data[2 * i + 1][y]);
                    if (temp[j] == data[2 * i][x]) {
                        if (x == data[2 * i].length - 1) {
                            j++;
                            while (y < data[2 * i + 1].length) {
                                temp[j] = data[2 * i + 1][y];
                                y++;
                                j++;
                            }
                            break;
                        }
                        x++;
                    } else {
                        if (y == data[2 * i + 1].length - 1) {
                            // all x
                            j++;
                            while (x < data[2 * i].length) {
                                temp[j] = data[2 * i][x];
                                x++;
                                j++;
                            }
                            break;
                        }
                        y++;
                    }
                    j++;
                }


                result[i] = temp;
                //for (int num : temp){System.out.print(num);}
            }
        }
        else{
            for (int i = 0; i < result.length-1; i++) {
                int[] temp = new int[result[0].length];
                int j = 0;
                int x = 0;
                int y = 0;
                while (j < result[0].length) {
                    temp[j] = Math.min(data[2 * i][x], data[2 * i + 1][y]);
                    if (temp[j] == data[2 * i][x]) {
                        if (x == data[2 * i].length - 1) {
                            j++;
                            while (y < data[2 * i + 1].length) {
                                temp[j] = data[2 * i + 1][y];
                                y++;
                                j++;
                            }
                            break;
                        }
                        x++;
                    } else {
                        if (y == data[2 * i + 1].length - 1) {
                            // all x
                            j++;
                            while (x < data[2 * i].length) {
                                temp[j] = data[2 * i][x];
                                x++;
                                j++;
                            }
                            break;
                        }
                        y++;
                    }
                    j++;
                }


                result[i] = temp;
                //for (int num : temp){System.out.print(num);}
            }
            int i = result.length -1;
            result[i] = data[data.length - 1];
        }

        return result;
    }

    public static void main() {
        int[] x = new int[]{7, 6, 5, 8, 10, 4, 3, 2, 1};
        int[] out = sort_up(x);
        for (int i : out) {
            System.out.print(i);
        }
    }

}

class sort_4{// 归并排序，自顶向下，递归

}