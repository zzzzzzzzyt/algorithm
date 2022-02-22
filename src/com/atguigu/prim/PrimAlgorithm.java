package com.atguigu.prim;

import java.util.Arrays;

//普里姆算法
public class PrimAlgorithm
{
    public static void main(String[] args)
    {
        //创建图的前期工作
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertex = data.length;
        //邻接矩阵的关系 使用二维数组表示  10000这个大数表示的是两个点 不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };

        //创建图对象
        MGraph graph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertex,data,weight);
        minTree.showGraph(graph);
        minTree.prim(graph,0);
    }
}

//创建最小生成树 村庄的图
class MinTree
{

    //创建图的邻接矩阵

    /**
     *
     * @param graph 图对象
     * @param vertexs 节点个数
     * @param data 节点名称
     * @param weight  邻接矩阵 //邻接矩阵就是顶之间相邻关系的矩阵
     */
    public void createGraph(MGraph graph,int vertexs,char[] data,int[][] weight)
    {
        int i,j;
        for (i = 0;i<vertexs;i++)
        {
            graph.data[i] = data[i];
            for (j = 0;j<vertexs;j++)
            {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph)
    {
        for (int[] link : graph.weight)
        {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     *  prim算法获取 最小生成树
     * @param graph
     * @param v v是代表从哪个结点开始
     */
    public void prim(MGraph graph,int v)
    {
        //判断每个结点是否被访问过
        int[] isVisted = new int[graph.vertexs];
        isVisted[v] = 1; //1代表被访问过
        int h1 = -1;
        int h2 = -1;
        for (int k = 1;k<graph.vertexs;k++) //普里目算法 只需要求出vertex-1边
        {
            int minWeight = 10000;//为了找到最小路径设置的
            for (int i = 0 ; i<graph.vertexs;i++) //分别进行两次循环 外面这层代表的是已经访问过的结点
            {
                for (int j = 0; j<graph.vertexs ;j++) //里面这层代表的没有访问过的结点
                {
                    if (isVisted[i]==1 && isVisted[j]==0 && graph.weight[i][j]<minWeight)
                    {
                        minWeight  = graph.weight[i][j];
                        h1 = i;
                        h2 = j;  //记录每个点的位置
                    }
                }
            }
            isVisted[h2] = 1;
            System.out.println("<"+graph.data[h1]+","+graph.data[h2]+":"+graph.weight[h1][h2]+">");
        }

    }

}


//图类
class MGraph
{
    int vertexs;//顶点个数
    char[] data;//存放结点个数
    int[][] weight;//存放边 就是邻接边

    public MGraph(int vertexs)
    {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}
