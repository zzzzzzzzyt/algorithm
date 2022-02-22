package com.atguigu.binarysearchnorecursion;

//二分查找算法 非递归实现
public class BinarySearchNoRecur
{
    public static void main(String[] args)
    {
        int[] arr = {1,3,8,10,11,67,100};
        System.out.println("查到的目标下标为"+binarySearch(arr, -100));
    }

    /**
     * 使用非递归进行查找 最好就是循环查找
     * @param arr 要查找的数组
     * @param target 要查找的目标
     * @return 查到的下标
     */
    public static int binarySearch(int[] arr,int target)
    {
        int left = 0;
        int right = arr.length-1;


        while (left<=right)
        {
            int mid = (left+right)/2;

            if (target>arr[mid])
            {
                left = mid +1; //需要向右边查找
            }else if (target<arr[mid])
            {
                right = mid -1; //需要向左边查找
            }else
            {
                return mid;
            }
        }
        //这代表while循环中也没找到
        return -1;
    }
}
