package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChessboard
{
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    private static boolean[] visited;//标记棋盘是否被访问过
    //使用一个属性标记棋盘是否都被访问过了
    private static boolean finished;//如果是true则代表棋盘都被遍历了

    public static void main(String[] args)
    {
        System.out.println("骑士周游算法，开始运行~");
        X = 8;
        Y = 8;
        int row = 1; //马尔初始位置
        int column = 1;//马尔初始位置
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X*Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start)+"毫秒");
        //输出
        for (int[] rows : chessboard)
        {
            System.out.println(Arrays.toString(rows));
        }


    }


    /**
     * 完成骑士周游问题的算法
     * @param chessboard
     * @param row   马儿当前位置的行数 从0开始
     * @param column 列数 从0开始
     * @param step 这是第几步
     */
    public static void traversalChessboard(int[][] chessboard ,int row,int column,int step)
    {
        chessboard[row][column] = step; //代表这一步走的 然后标记进去
        visited[row*X+column] = true; //先判断它被访问过了
        ArrayList<Point> points = next(new Point(column, row));
        sort(points); //进行贪心算法的优化

        while (!points.isEmpty())
        {
            Point point = points.remove(0); //取出下个可以访问的位置进行访问
            if (!visited[point.y*X+point.x]) //说明还没有访问过
            {
                traversalChessboard(chessboard,point.y,point.x,step+1); //递归 调用
            }
        }
        //1、棋盘到当前 已经走不下去的位置里 还没走完   /
        //2、正在回溯    可能是这两种情况            / 还原成了还没调用这个的样子
        if (step<X*Y&&!finished)
        {
            chessboard[row][column] = 0;  //这步其实无所谓  但如果全失败了 那这一步还是有所谓的  要显示 全空 不然就乱
            visited[row*X+column] = false; //认为这还是 行不通的这一步  当没访问过
        }
        else
        {
            finished = true;
        }

    }

    //查看该结点能走那些步
    public static ArrayList<Point> next(Point curPoint)
    {
        //创建一个arrayList 用来保存当前点可以走的点
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        //判断第一个点是否能走 依次判断八个点 将点加入
        //5
        if ((p1.x= curPoint.x-2)>=0 &&((p1.y =curPoint.y-1)>=0))
        {
            points.add(new Point(p1));
        }
        //6
        if ((p1.x= curPoint.x-1)>=0 &&((p1.y =curPoint.y-2)>=0))
        {
            points.add(new Point(p1));
        }
        //7
        if ((p1.x= curPoint.x+1)<X &&((p1.y =curPoint.y-2)>=0))
        {
            points.add(new Point(p1));
        }

        //0
        if ((p1.x= curPoint.x+2)<X &&((p1.y =curPoint.y-1)>=0)) //因为这些都是局部的赋值  所以只会再局部有效果
        {
            points.add(new Point(p1));
        }
        //1
        if ((p1.x= curPoint.x+2)<X &&((p1.y =curPoint.y+1)<Y))
        {
            points.add(new Point(p1));
        }
        //2
        if ((p1.x= curPoint.x+1)<X  &&((p1.y =curPoint.y+2)<Y))
        {
            points.add(new Point(p1));
        }
        //3
        if ((p1.x= curPoint.x-1)>=0 &&((p1.y =curPoint.y+2)<Y))
        {
            points.add(new Point(p1));
        }
        //4
        if ((p1.x= curPoint.x-2)>=0 &&((p1.y =curPoint.y+1)<Y))
        {
            points.add(new Point(p1));
        }
        return points;
    }


    //根据当前这一步的所有下一步他们的选择位置 进行非递减排序
    public static void sort(ArrayList<Point> ps)
    {
        ps.sort(new Comparator<Point>()
        {
            @Override
            public int compare(Point o1, Point o2)
            {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return count1-count2;
            }
        });
    }
}
