package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class map {
    public static void main(String[] args) {
        no_direction_map.main();

    }
}


class no_direction_map {
    public static void find_road(map_node start, map_node end) { // 根据起点终点找出所有可以到达的路径
        to_next(start, end);
        System.out.println('\n');
        to_next_short(new map_node[]{start}, end);


    }

    public static void to_next_short(map_node[] now, map_node end) {  // 广度优先
        ArrayList<map_node> temp = new ArrayList<>();

        for (map_node mapNode : now) {
            mapNode.x.add(mapNode.val);
            if (mapNode.val == end.val) {
                System.out.println(mapNode.x.toString());
                return;
            }
            temp.addAll(Arrays.asList(mapNode.next));
        }
        for (com.company.map_node map_node : now) {
            for (map_node n : map_node.next) {
                n.x.add(map_node.val);
            }
        }

        map_node[] nextstep = new map_node[temp.size()];
        for (
                int i = 0; i < temp.size(); i++) {
            nextstep[i] = temp.get(i);
        }

        to_next_short(nextstep, end);

    }

    public static void to_next(map_node now, map_node end) {  //深度优先
        now.x.add(now.val);
        if (now.val == end.val) {
            System.out.println(now.x.toString());
        } else {
            for (map_node i : now.next) {
                int judge = 0;
                for (int node : now.x) {
                    if (node == i.val) {
                        judge = 1;
                        break;
                    }
                }
                if (judge == 0) {
                    i.x = now.x;
                    to_next(i.clone(), end);
                }
            }
        }


    }

    public static void main() {
        map_node zero = new map_node(0);
        map_node one = new map_node(1);
        map_node two = new map_node(2);
        map_node three = new map_node(3);
        map_node four = new map_node(4);
        map_node five = new map_node(5);

        map_node[] forzero = new map_node[]{one, two, five};
        map_node[] forone = new map_node[]{zero, two, three};
        map_node[] fortwo = new map_node[]{zero, one, three, four};
        map_node[] forthree = new map_node[]{one, two, four, five};
        map_node[] forfour = new map_node[]{two, three};
        map_node[] forfive = new map_node[]{zero, three};

        zero.add(forzero);
        one.add(forone);
        two.add(fortwo);
        three.add(forthree);
        four.add(forfour);
        five.add(forfive);

        find_road(zero, two);
    }


}

class map_node implements Cloneable {
    int val = 0;  // 点的名字
    map_node[] next = new map_node[0];  //可以去的下一个点
    ArrayList<Integer> x = new ArrayList<>(); // 已经去过的点

    map_node(int val) {
        this.val = val;
    }

    @Override
    public map_node clone() {
        try {
            map_node newone = (map_node) super.clone();
            newone.x = (ArrayList<Integer>) x.clone();
            return newone;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    public void add_node(map_node target) {
        map_node[] new_map = new map_node[this.next.length + 1];
        System.arraycopy(this.next, 0, new_map, 0, this.next.length);
        map_node[] other = new map_node[]{target};
        System.arraycopy(other, 0, new_map, this.next.length, 1);
        this.next = new_map;
    }

    public void add(map_node[] target) {
        for (map_node i : target) {
            this.add_node(i);
        }
    }
}


