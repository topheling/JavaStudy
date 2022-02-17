package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //显示链表
        System.out.println("原链表情况~~");
        doubleLinkedList.list();

        //修改
//        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println("修改后链表情况~~");
//        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后链表情况~~");
        doubleLinkedList.list();

    }
}

//创建一个双向链表的类
class DoubleLinkedList{

    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }


    //添加节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后，跳出循环
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改。
    //1.根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode2 newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; //表示是否找到改节点
        while (true){
            if(temp == null){
                break; //已经遍历完
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            //找到了，进行修改
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            //没有找到
            System.out.printf("没有找到编号%d 的节点， 不能修改~", newHeroNode.no);
        }
    }

    //从双向链表中删除节点
    //对于双向链表，我们可以直接找到要删除的这个节点，自我删除即可
    public void delete(int no){
        //判断当前链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法删除");
        }
        HeroNode2 temp = head.next; //辅助遍历(指针)
        boolean flag = false; //标志是否找到待删除节点
        while (true){
            if(temp == null){ //已经到链表的最后
                break;
            }
            if(temp.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移
        }
        if(flag){
            //可以删除了
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            //判断是否到链表的最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点, 默认为null
    public HeroNode2 pre; //指向前一个节点, 默认为null
    //构造器
    public HeroNode2(int no, String name, String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}