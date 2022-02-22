package com.atguigu.dynamic;

public class KnapsackProblem
{
    public static void main(String[] args)
    {
        int[] w = {1,4,3}; //物品的重量
        int[] val = {1500,3000,2000}; //物品的价格
        int m = 4;//背包的容量
        int n = val.length; //物品的个数


        //动态规划问题 先创建二维数组 v[i][j] 表示前i个物品能装入容量为j的背包最大的价值
        int[][] v = new int[n+1][m+1];
        //记录该路径下商品存放了什么
        int[][] path = new int[n+1][m+1];


        //让 第一行置零  这边假如不置零的话 那么一开始的 往前复制可能 会报错 因为前面没有数值 但其实 java中数组的初始化都是0
        for (int i = 0; i < v[0].length; i++)
        {
            v[0][i] = 0;
        }
        //让 第一列置零
        for (int i = 0; i < v.length; i++)
        {
            v[i][0] = 0;
        }

        for (int i = 1;i <v.length;i++) //不处理第一行
        {
            for (int j = 1;j<v[i].length;j++) //不处理第一列
            {
                //一共有两种装入策略
                if (w[i-1]>j) //该商品的重量大于背包的容量
                {
                    //那就只能复制前面结点的重量了
                    v[i][j] = v[i-1][j];

                }else //该商品的重量小于等于背包的容量 所以换一种思路 进行操作
                {
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);  //要吗选取 比较上一个商品该重量时的最大 或者用上新商品再加上 背包容量减去新商品的重量 下上一个商品的最大效益
                    //为了记录商品存放到背包的情况 不能简单的使用函数进行求最大值 而应该使用 判断语句进行
                    if (v[i-1][j]>val[i-1]+v[i-1][j-w[i-1]])
                    {
                        v[i][j] = v[i-1][j];
                    }else
                    {
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]]; //这个表示每次就放了一个
                        path[i][j] = 1;//意思是在这里 把新品放了进去时价格最高
                    }
                }
            }
        }


        //输出看下 path数组
        for (int[] ints : path)
        {
            for (int anInt : ints)
            {
                System.out.print(anInt);
            }
            System.out.println();
        }


        //输出一下看下列表的情况
        for (int[] ints : v)
        {
            for (int i : ints)
            {
                System.out.print(i+" ");
            }
            System.out.println();
        }

        //输出查看背包里面商品放了什么
        int thing = v.length - 1;
        int weight = v[0].length -1;
        while (weight>0 && thing>0) //不考虑0
        {
            if (path[thing][weight]==1)
            {
                System.out.printf("第%d种商品放入了背包\n",thing);
                weight-=w[thing-1];  //代表放完了这个物品之后 背包的容量 还有多大
            }
            thing--;
        }



    }
}
