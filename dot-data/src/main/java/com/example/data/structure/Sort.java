package com.example.data.structure;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Evain on 2018/12/14.
 */
public class Sort {

    public static void main(String[] args) {
        System.out.println(f(4));
        int []a = {2,5,3,1,8,9,6};
        quickSortBySentry(a,0,6);
        for (int i = 0;i< 7;++i){
            System.out.print(a[i]);
        }
        HashMap<Integer,Integer> map = new LinkedHashMap<>();
        map.put(3,11);
        map.put(1,22);
        map.put(5,23);
        map.put(2,22);

        for (Map.Entry e: map.entrySet()) {
            System.out.println(e.getKey());
        }

    }
    public static int f(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return f(n-1) + f(n-2);
    }

    //冒泡排序 时间复杂度O（n……2）
    public static void bubbleSort(int [] items){
        if (items.length <= 1) return;
        for (int i = 0; i < items.length; i++) {
            boolean flag = false;
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j] > items[j+1]){
                    int tmp = items[j+1];
                    items[j+1] = items[j];
                    items[j] = tmp;
                    flag = true;
                }
            }
            if (!flag)break;//没有数据交换退出循环
        }
    }


    // 插入排序，a 表示数组，n 表示数组大小
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }

    //选择排序
    public static void selectionSort(int[] a,int n){
        if (n <= 1) return;
        for (int i = 0; i < n -1; i++) {
            int tmp = a[i];
            int x = 0;
            boolean flag = false;
            for (int j = i + 1; j < n; j++) {
                if (tmp > a[j]){
                    tmp = a[j];
                    x = j ;
                    flag = true;
                }
            }

            if (flag){
                a[x] = a[i];
                a[i] = tmp;
            }


        }
    }

    //归并排序
    public static void mergeSort(int[] a,int low,int high ){
        int middle = (low + high) / 2 ;
        if (low < high){
            mergeSort(a,low,middle);
            mergeSort(a,middle+1,high);
            merge(a,low,middle,high);
        }
    }

    public static void merge(int[] a,int low,int middle,int high){
        int[] tmp = new int[high - low + 1];
        int i = low;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && high >= j){
            if (a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else {
                tmp[k++] = a[j++];
            }
        }

        while (i <= middle){
            tmp[k++] = a[i++];
        }

        while (j <= high){
            tmp[k++] = a[j++];
        }

        for (int l = 0; l < tmp.length; l++) {
            a[l + low] = tmp[l];
        }
    }
    //快排
    public static void quickSort(int[] a, int low,int high){
        if (low == high)return;
       int p =  partition(a,low,high);
       quickSort(a,low,p);
       quickSort(a,p+1,high);
    }

    public static int partition(int[] a,int low,int high){
        int pivot = a[low];
        while (low < high){
            while (low < high && a[high] >= pivot){
                high--;
            }
            swap(a,low,high);
            while (low < high && a[low] <= pivot){
                low ++;
            }
            swap(a,low,high);

        }
        return low;
    }

    public static void swap(int[] a,int low,int high){
        int tmp = a[low];
        a[low] = a[high];
        a[high] = tmp;
    }


    //哨兵 快排优化
    private static void quickSortBySentry(int[] a, int head, int tail) {

        int low = head;
        int high = tail;
        int pivot = a[low];
        if (low < high) {

            while (low<high) {
                while (low < high && pivot <= a[high]) high--;
                a[low] = a[high];
                while (low < high && pivot >= a[low]) low++;
                a[high]=a[low];
            }
            a[low] = pivot;

            if(low>head+1) quickSortBySentry(a,head,low-1);
            if(high<tail-1) quickSortBySentry(a,high+1,tail);
        }

    }

    //二分查找
    public static int binarySearch(int[] a,int n,int value){

        return binarySearchInternally(a,0,n-1,value);
    }

    private static int binarySearchInternally(int[] a,int low,int high,int value){
        if (low > high) return -1;
        int mid = low + ((high - low)>>1);

        if (a[mid] == value){
            return mid;
        }else if (a[mid] > value){
            return binarySearchInternally(a,low,mid - 1,value);
        }else {
            return binarySearchInternally(a,mid + 1,high,value);
        }
    }

    //二分查找求算术平方根、保留小数点后6位
    private static double sqr(int x){
        float low = 0;
        float high = x;
        DecimalFormat df = new DecimalFormat("0.000000");
        while (low <= high){
            float mid = (low + high)/2;
            if ((mid + 0.0000001) * (mid + 0.0000001) < x){
                low = mid;
            }else  if ((mid - 0.0000001) * (mid - 0.0000001) >x){
                high = mid;
            }else {

                return Double.valueOf(df.format(mid));
            }
        }
        return -1;

    }

    public static int binaryCycleSearch(int[] nums,int target) {
        //二分法将数组分成两部分，要区分出哪部分是有序的
        //当nums[mid] > nums[mid-1] 时，前半部分有序
        //当nums[mid] < nums[mid + 1]时，后半部分有序
        int low = 0;
        int high = nums.length - 1;
        while (high>=low) {

            int mid = low + ((high-low) >> 1);
            if(nums[mid] == target) {
                return mid;
            }
            if(high == low) {
                return -1;
            }
            if(mid == 0) {
                low  = mid + 1;
                continue;
            } else if(mid == high) {
                high = mid -1;
                continue;
            }
            if(nums[mid] >=nums[low]) {
                //target落在前半部分有序队列中
                if(nums[mid] > target && (nums[mid-1] >= target && nums[low] <= target)) {
                    high = mid - 1;
                }
                //target未落在有序队列中（后半部分）
                else {
                    low = mid + 1;
                }
            } else if(nums[mid] < nums[high] ){
                //target落在后半部分有序队列中
                if(nums[mid] < target && (nums[high] >=target && nums[mid+1] <= target)) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }

        return -1;

    }
}
