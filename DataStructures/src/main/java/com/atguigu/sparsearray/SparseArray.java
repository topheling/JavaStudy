package com.atguigu.sparsearray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0:表示没有棋子，1表示黑子，2表示篮子
        int chessArr1[][] = new int[11][11];
        chessArr1[0][0] = 7;
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        for(int[] row : chessArr1){
            for(int dada : row){
                System.out.printf("%d\t", dada);
            }
            System.out.println();
        }

        //二维数组 转 稀疏数组的思路
        //1. 遍历原始的二维数组，得到有效数据的个数sum
        int sum = 0;
        for(int i = 0; i<chessArr1.length; i++){
            for(int j = 0; j<chessArr1[i].length; j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("得到有效数据的个数sum=" + sum);
        //2. 根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
        int sparseArr[][] = new int[sum+1][3];
        //3. 将二维数组的有效数据存入到稀疏数组
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0; //用于记录是第几个非0数据
        for(int i = 0; i<chessArr1.length; i++){
            for(int j = 0; j<chessArr1[i].length; j++){
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~");
        for(int i=0; i<sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组存入磁盘
        try {
            FileWriter fw = new FileWriter("D:\\map.data");
            for(int i=0; i<sparseArr.length; i++){
                String line = sparseArr[i][0] + "\t" + sparseArr[i][1] + "\t" + sparseArr[i][2];
                fw.write(line);
                fw.write("\r\n");
            }
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        //从磁盘中读取稀疏数组
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\map.data"));
            String line;
            //将读取到的数据写入到list集合中
            List<String> list1 = new ArrayList<String>();
            while((line=br.readLine())!=null){
                list1.add(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        //稀疏数组转原始的二维数组的思路
        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如chessArr2=int[11][11]
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 再读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
        for(int i=1; i<sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        System.out.println("得到的原始的二维数组为~~~~~");
        //输出原始的二维数组
        for(int[] row : chessArr2){
            for(int dada : row){
                System.out.printf("%d\t", dada);
            }
            System.out.println();
        }
    }
}
