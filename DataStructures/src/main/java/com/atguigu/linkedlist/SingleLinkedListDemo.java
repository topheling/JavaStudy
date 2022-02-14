package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //显示链表
        System.out.println("原链表情况~~");
        singleLinkedList.list();

//        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//
//        //修改后显示链表
//        System.out.println("修改后的链表情况：");
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        System.out.println("删除后的链表情况：");
//        singleLinkedList.list();
//
//        //测试一下 求单链表中有效节点的个数
//        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));
//
//        //测试是否得到倒数第k个节点
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 0);
//        System.out.println("res=" + res);

//        System.out.println("反转单链表~~");
//        receiveList(singleLinkedList.getHead());
//        singleLinkedList.list();

        System.out.println("测试逆序打印单链表,没有改变链表的结构~~");
        reversePrint(singleLinkedList.getHead());
    }

    //从尾到头打印单链表
    //思路：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印
        while (stack.size()>0){
            System.out.println(stack.peek());//stack的特点是先进后出
        }
    }

    //单链表的反转
    public static void receiveList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助变量，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //next节点：指向当前节点[cur]的下一个节点
        HeroNode next = null;
        //空的链表头
        HeroNode receiveHead = new HeroNode(0, "", "");
        while (cur != null){
            next = cur.next;//先保存当前节点的下一个节点，后面需要
            cur.next = receiveHead.next;
            receiveHead.next = cur; //将cur连接到新的链表上
            cur = next; //后移
        }
        head.next = receiveHead.next;

    }



    //查找单链表中倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if(head.next == null){
            return null; //没有找到
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //先做一个index的校验
        if(index <=0 || index > size){
            return null;
        }
        HeroNode cur = head.next;
        //第二次遍历
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){ //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        //没有统计头节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }
}

//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
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
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种方式添加英雄，根据排名将英雄添加到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        //通过
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){ //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){ //说明希望添加的heroNode的编号已存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag 的值
        if(flag){
            System.out.printf("准备插入的英雄的编号%d已存在了，不能加入\n", heroNode.no);
        }else {
            //插入到链表中，temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改。
    //说明
    //1.根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
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

    //删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除节点的前一个节点
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移
        }
        if(flag){
            //可以删除了
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
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
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickName){
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