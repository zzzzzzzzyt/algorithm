package com.atguigu.kruskal;

import java.util.Arrays;

public class KruskalAlgorithm
{
    private int edgeNum;// 边的条数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args)
    {
        //开始对生成树进行初始化
        char[] vertexs = {'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };

        //进行初始化 并进行查看
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs,matrix);
        //输出构建的
        //kruskalAlgorithm.print();
        //EData[] edges = kruskalAlgorithm.getEdges();
        //System.out.println("未排序"+Arrays.toString(edges));
        //System.out.println("-----------------------------------");
        //kruskalAlgorithm.sortEdges(edges);
        //System.out.println("排序后"+Arrays.toString(edges));

        kruskalAlgorithm.kruskal();

    }

    //构造器
    public KruskalAlgorithm(char[] vertexs,int[][] matrix)
    {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i=0;i<vlen;i++)
        {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i=0;i<vlen;i++)
        {
            for (int j=0;j<vlen;j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边  不会统计到同一条边
        for (int i=0;i<vlen;i++)
        {
            for (int j=i+1;j<vlen;j++)
            {
                if (this.matrix[i][j]!=INF)
                {
                    edgeNum++;
                }
            }
        }
    }

    //克鲁斯卡尔算法  将路按权重排序 然后根据路的起终点是否有同一个终点(也就是判断是不是回路) 进行 选择插入
    public void kruskal()
    {
        int index = 0;
        int[] ends = new int[vertexs.length];
        EData[] res = new EData[vertexs.length-1]; //最后的结果数组 最小生成树的边是结点数-1；
        //获得边的数组
        EData[] edges = getEdges();
        //将边进行排序
        sortEdges(edges);
        //接下来开始进行遍历
        for (int i = 0;i<edges.length;i++)
        {
            //判断是否构成回路
            EData edge = edges[i];
            int p1 = getPosition(edge.start);
            int p2 = getPosition(edge.end);
            int m = getEnds(ends,p1);
            int n = getEnds(ends,p2);
            if (m!= n)
            {
                res[index++] = edge;
                ends[m] = n; //发现不相等 然后将它们加入  使得start现在的终点变成了 end的终点
            }
        }
        for (int i = 0;i<res.length;i++)
        {
            System.out.println(res[i]);
        }
    }




    //显示邻接矩阵
    public void print()
    {
        for (int i=0;i<vertexs.length;i++)
        {
            for (int j=0;j<vertexs.length;j++)
            {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理 冒泡
    private void sortEdges(EData[] edges)
    {
        for (int i = 0;i<edges.length-1;i++)
        {
            for (int j = 0;j<edges.length-1-i;j++)
            {
                if (edges[j].weight>edges[j+1].weight)
                {
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }


    //返回ch顶点对应的下标,如果找不到,返回-1
    private int getPosition(char ch)
    {
        for (int i = 0;i<vertexs.length;i++)
        {
            if (vertexs[i]==ch)
            {
                return i;
            }
        }
        return -1;
    }

    //获取图中的边 放到EData[] 数组种,后面我们需要遍历该数组
    private EData[] getEdges()
    {
        int index = 0;
        //要有个长度啊
        EData[] edges = new EData[edgeNum];
        for (int i = 0;i<vertexs.length;i++)
        {
            for (int j = i+1;j<vertexs.length;j++)
            {
                if (matrix[i][j]!=INF)
                {
                    EData edge = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                    edges[index] = edge;
                    index++;
                }
            }
        }
        return edges;
    }


    // 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同 /依次找后面的结点 感觉好像是
    private int getEnds(int[] ends,int i)
    {
        while (ends[i]!=0)
        {
            i = ends[i];
        }
        return i;
    }

}

//创建一个类EData,它的对象实例就代表一条边
class EData
{
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//权值

    //构造器
    public EData(char start,char end,int weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    //重写toString方法 便于显示

    @Override
    public String toString()
    {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}