package com.atguigu.kmp;

//暴力匹配算法
public class ViolenceMatch
{
    public static void main(String[] args)
    {
        String str1 = "wocsdasaonimalzjsjsjdaskjd";
        String str2 = "nimalz";
        System.out.println(violenceMatch(str1,str2));
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2)
    {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int lenStr1 = s1.length;
        int lenStr2 = s2.length;
        int i = 0;
        int j = 0;
        while (i<lenStr1&&j<lenStr2) //就是不断的匹配
        {
            if (s1[i]==s2[j])
            {
                i++;
                j++;
            }else {
                i = i - j + 1; //进行回溯 重新进行匹配
                j = 0;
            }

        }

        if (j==lenStr2)
        {
            return i-j;
        }
        else {
            return -1;
        }
    }
}
