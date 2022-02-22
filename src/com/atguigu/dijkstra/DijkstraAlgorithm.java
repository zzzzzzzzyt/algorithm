package com.atguigu.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm
{
    public static void main(String[] args)
    {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        //创建Graph对象
        Graph graph = new Graph(vertex,matrix);
        //看看邻接矩阵是否ok
        graph.showGraph();
        graph.dsj(6);

    }
}

class Graph
{
    private char[] vertexs;
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //设置一个访问顶点集合

    public Graph(char[] vertexs,int[][] matrix)
    {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    //显示邻接矩阵图
    public void showGraph()
    {
        for(int[] link : matrix)
        {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dsj(int index)
    {
        vv = new VisitedVertex(vertexs.length, index); //对我们要访问的顶点先进行初始化
        update(index);
        for (int i = 1;i<vertexs.length;i++)
        {
            index = vv.updateArr();
            update(index);
        }
        vv.check();
    }

    //更新 index为下标顶点 到周围的距离和 周围顶点的前驱结点
    private void update(int index)
    {
        int len = 0;
        //遍历邻接矩阵的matrix行
        for (int j = 0 ; j < matrix[index].length ;j++)
        {
            //len表示 ：出发顶点到index顶点的距离+index顶点到j顶点的距离
            len = vv.getDis(index) + matrix[index][j]; //vv这里的dis还是原来顶点到所有其他结点的距离的
            if (!vv.isVisited(j) && len<vv.getDis(j))
            {
                vv.updateDis(j,len);//进行更新距离
                vv.updatePre(j,index); //更新前驱结点
            }
        }
    }

}

//已访问顶点集合
class VisitedVertex
{
    //记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标,会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离,比如G为出发顶点就会记录G到其他顶点的距离 会动态更新 求的最短距离 就会存放到dis
    public int[] dis;

    //构造器

    /**
     *
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标 比如G就是6
     */
    public VisitedVertex(int length,int index)
    {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1; //设置已经自身已经被访问
        this.dis[index] = 0; //到自身的距离 为0
    }

    //判断index为下标的顶点是否被访问过
    public boolean isVisited(int index)
    {
        return already_arr[index] == 1;
    }

    //更新距离 出发顶点到index顶点的距离
    public void updateDis(int index,int len)
    {
        dis[index] = len;
    }

    //更新顶点的前驱结点
    public void updatePre(int index,int pre)
    {
        pre_visited[index] = pre;
    }

    //返回出发顶点到index结点的距离
    public int getDis(int index)
    {
        return dis[index];
    }

    //返回一个新的结点 比如G访问完了 ，就是A做新的访问结点了
    public int updateArr()
    {
        int min = 65536;
        int index = 0;
        for (int i = 0 ;i<already_arr.length;i++)
        {
            if (already_arr[i]==0 && dis[i] < min)
            {
                index = i;
                min = dis[i];
            }
        }
        already_arr[index] = 1;//设置成已经访问过了
        return index;
    }


    //打印所有的数组
    public void check()
    {
        for (int i = 0;i<already_arr.length;i++)
        {
            System.out.print(already_arr[i]+" ");
        }
        System.out.println();
        for (int i = 0;i<already_arr.length;i++)
        {
            System.out.print(pre_visited[i]+" ");
        }
        System.out.println();
        for (int i = 0;i<already_arr.length;i++)
        {
            System.out.print(dis[i]+" ");
        }
    }

}