package ru.tarasov.main;

public class Block2_5 {

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст или null");
        }
        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }

    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст или null");
        }
        int min = arr[0];
        for (int val : arr) {
            if (val < min) min = val;
        }
        return min;
    }

    public static int sum(int[] arr) {
        if (arr == null) return 0;
        int s = 0;
        for (int val : arr) {
            s += val;
        }
        return s;
    }

    public static double average(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пуст или null");
        }
        return (double) sum(arr) / arr.length;
    }

    public static int[] reverse(int[] arr) {
        if (arr == null) return null;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[arr.length - 1 - i];
        }
        return res;
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null) return null;
        int[] res = arr.clone();
        for (int i = 0; i < res.length - 1; i++) {
            for (int j = 0; j < res.length - 1 - i; j++) {
                if (res[j] > res[j + 1]) {
                    int tmp = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = tmp;
                }
            }
        }
        return res;
    }

    public static int findIndex(int[] arr, int value) {
        if (arr == null) return -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    public static int countPositive(int[] arr) {
        if (arr == null) return 0;
        int count = 0;
        for (int val : arr) {
            if (val > 0) count++;
        }
        return count;
    }
}