package com.atguigu.kmp;

public class KMPAlgorithm
{
    public static void main(String[] args)
    {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
    }

    /**
     *
     * @param str1
     * @param str2
     * @param kmpNext 部分匹配表
     * @return -1就是没匹配到 不然就是匹配到了
     */
    public static int kmpSearch(String str1,String str2,int[] kmpNext)
    {
        for (int i = 0 , j =0 ;i<str1.length();i++)
        {
            while (j>0 && str1.charAt(i) != str2.charAt(j))
            {
                j = kmpNext[j-1];  //这里就和之前一样 进行回退 其实
            }

            //进行遍历
            if (str1.charAt(i)==str2.charAt(j))
            {
                j++;
            }
            if (j==str2.length())// 代表全部匹配上了
            {
                return i - j + 1;
            }
        }
        return -1;
    }



    //寻找到一个字符串的部分值匹配表   kmp算法最重要的就是 next数组  所以一定要好好理解  理解了一晚上  真的还是有点难度的
    public static int[] kmpNext(String dest)
    {
        //用于储存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1,j=0;i<dest.length();i++)
        {
            while (j>0 && dest.charAt(i) != dest.charAt(j))
            {
                j = next[j-1];  //这是kmp算法的核心   门牌号 很形象 根据底下的 找到门牌号
            }

            //匹配到了 就加一
            if (dest.charAt(i) == dest.charAt(j))
            {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
