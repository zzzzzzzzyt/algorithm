package com.atguigu.floyd;

import java.util.Arrays;

public class FloydAlgorithm
{
    public static void main(String[] args)
    {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N =65535;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};

        //创建一个图对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
        graph.floyd();
        graph.show();
    }
}

//创建图
class Graph
{
    private char[] vertex;//顶点数组
    private int[][] dis;//各个顶点到目标点的最短距离
    private int[][] pre;//到达目标点的前驱结点矩阵

    //构造器
    public Graph(int length,int[][] matrix,char[] vertex)
    {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组进行初始化
        for (int i = 0;i<length;i++)
        {
            Arrays.fill(pre[i],i); //存放的是前驱结点的下标 而不是'A' 'B'

        }
    }


    //显示pre 和 dis 数组
    public void show()
    {
        //距离数组
        for (int[] link : dis)
        {
            System.out.println(Arrays.toString(link));
        }

        //前驱结点数组
        for (int[] link:pre)
        {
            System.out.println(Arrays.toString(link));
        }
    }


    //弗洛伊得算法
    public void floyd()
    {
        int len = 0;
        //分为三层遍历 第一层中间顶点 第二层出发顶点 第三层终点
        //中间顶点
        for (int k= 0 ;k< vertex.length;k++)
        {
            //出发顶点
            for (int j = 0;j< vertex.length;j++)
            {
                //终点
                for (int i = 0;i< vertex.length;i++)
                {
                    len = (dis[j][k]+dis[k][i]);
                    //如果len小于该两点的最小距离的话 那就进行更改 距离结点数组 和前驱结点数组
                    if (dis[j][i]>len)
                    {
                        //则进行更改
                        dis[j][i] = len;
                        dis[i][j] = len;
                        pre[i][j] = k;
                        pre[j][i] = k;
                    }
                }
            }
        }
    }
}