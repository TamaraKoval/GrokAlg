import java.util.Arrays;

public class Recursion {
    public static void main(String[] args) {
//        int length = 10;
//        int[] list = new int[length];
//        for (int i  = 0; i < length; i++) {
//            list[i] = new Random().nextInt(100);
//        }
//        System.out.println(Arrays.toString(list));
//        System.out.println(searchMax(list));

        int length = 100;
        int[] list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = i;
        }
        int index = binarySearch2(list, 0, length - 1, 99);
        System.out.println("Индекс: " + index);
    }

    public static int[] removeFirst(int[] list) {
        int[] current = new int[list.length - 1];
        System.arraycopy(list, 1, current, 0, list.length - 1);
        return current;
    }


    public static boolean canRemove(int[] list) {
        return list.length > 1;
    }

    public static int sum(int[] list) {
        int result;
        if (list.length == 0) {
            return 0;
        }
        if (!canRemove(list)) {
            return list[0];
        } else {
            int[] current = removeFirst(list);
            result = list[0];
            result += sum(current);
        }
        return result;
    }

    public static int myLength(int[] list) {
        int result = 0;
        if (list.length == 0) {
            return result;
        } else {
            if (canRemove(list)) {
                int[] current = removeFirst(list);
                System.out.println(Arrays.toString(current));
                result = 1 + myLength(current);
            } else {
                result = 1;
            }
        }
        return result;
    }

    public static int searchMax(int[] list) {
        int result = 0;
        if (list.length == 0) {
            return -1;
        } else {
            if (canRemove(list)) {
                int[] current = removeFirst(list);
                result = (list[0] > searchMax(current)) ? list[0] : searchMax(current);
            } else {
                result = list[0];
            }
        }
        return result;
    }

    public static int binarySearch1(int[] list, int number) {
        int steps = 0;
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = list[mid];
            if (guess == number) {
                System.out.println("Количество шагов: " + steps);
                return mid;
            } else if (guess > number) {
                high = mid - 1;
                steps++;
            } else {
                low = mid + 1;
                steps++;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] list, int left, int right, int number) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (number == list[mid]) {
            return mid;
        } else if (number < list[mid]) {
            return binarySearch2(list, left, mid - 1, number);
        } else {
            return binarySearch2(list, mid + 1, right, number);
        }
    }
}


