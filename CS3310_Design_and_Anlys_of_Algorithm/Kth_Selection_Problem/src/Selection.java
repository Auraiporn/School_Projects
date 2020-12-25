import java.util.*;
import java.util.Random;

public class Selection {
    public static void main (String[] args) {
    	
        int[] number_of_elements = {10, 50, 100, 250, 500, 1000, 1250, 1500, 2000, 2250, 2500, 3000, 3250, 3500, 4000, 4500, 5000, 5250, 5500, 6000};
        final int NUMBER_OF_TIMES = 40;
        int i = 0;
        while (i < number_of_elements.length) {
            int n = number_of_elements[i];
            int[] list_to_test_kth_0 = generateTheList (n); // Random list of numbers to test kth = 1 (minimum)
            int[] list_to_test_kth_1 = generateTheList (n); // Random list of numbers to test kth = n/4
            int[] list_to_test_kth_2 = generateTheList (n); // Random list of numbers to test kth = n/2
            int[] list_to_test_kth_3 = generateTheList (n); // Random list of numbers to test kth = 3*n/4
            int[] list_to_test_kth_4 = generateTheList (n); // Random list of numbers to test kth = n (maximum)
            int[] kth = {1, n / 4, n / 2, 3 * n / 4, n};
            System.out.println ("____________________________________________________________________________________________________\n");

            System.out.println ("Given Kth = 1, then test with the same list of numbers of size " + n + " for " + NUMBER_OF_TIMES + " times.");
            recordTime (list_to_test_kth_0, kth[0], NUMBER_OF_TIMES);
            System.out.println ("____________________________________________________________________________________________________\n");

            System.out.println ("Given Kth = n/4, then test with the same list of numbers of size " + n + " for " + NUMBER_OF_TIMES + " times.");
            recordTime (list_to_test_kth_1, kth[1], NUMBER_OF_TIMES);
            System.out.println ("____________________________________________________________________________________________________\n");

            System.out.println ("Given Kth = n/2, then test with the same list of numbers of size " + n + " for " + NUMBER_OF_TIMES + " times.");
            recordTime (list_to_test_kth_2, kth[2], NUMBER_OF_TIMES);
            System.out.println ("____________________________________________________________________________________________________\n");

            System.out.println ("Given Kth = 3*n/4, then test with the same list of numbers of size " + n + " for " + NUMBER_OF_TIMES + " times.");
            recordTime (list_to_test_kth_3, kth[3], NUMBER_OF_TIMES);
            System.out.println ("____________________________________________________________________________________________________\n");

            System.out.println ("Given Kth = n, then test with the same list of numbers of size " + n + " for " + NUMBER_OF_TIMES + " times.");
            recordTime (list_to_test_kth_4, kth[4], NUMBER_OF_TIMES);
            System.out.println ("____________________________________________________________________________________________________\n");
            
            i++;
        }
    }

    /**___________________________________________Calculation _______________________________________________________________________________*/
    
    /** Record the time taken of each algorithm
     * @param a an array a[]
     * @param kth the kth smallest element of a[]
     * @param times number of times to run a program
     * */
    static void recordTime (int[] a, int kth, int times){
        System.out.println ("The given unsorted list of numbers is: ");
        printArray (a);
        long [] time_taken_1 = new long [times];
        long [] time_taken_2 = new long [times] ;
        long [] time_taken_3 = new long [times];
        long [] time_taken_4 = new long [times];
        long start, end; 
        for(int i = 0; i < times; i++) {

            start = System.nanoTime();
            algorithm1(a, kth);
            end = System.nanoTime();

            time_taken_1[i] =  end - start;
            //System.out.println("\n\nTime taken of Algorithm #1 at trial #"+ (i+1) + " is: \t\t\t" + time_taken_1[i] );

            start = System.nanoTime();
            algorithm2_Iteratively (a,0,a.length-1, kth);
            end = System.nanoTime();

            time_taken_2[i] =  end - start;
            //System.out.println("Time taken of Algorithm #2 iteratively at trial #"+ (i+1) + " is: " + time_taken_2[i] );

            start = System.nanoTime();
            algorithm2_Recursively (a, 0, a.length-1, kth);
            end = System.nanoTime();

            time_taken_3[i] =  end - start;
            //System.out.println("Time taken of Algorithm #2 recursively at trial #"+ (i+1) + " is: " + time_taken_3[i] );

            start = System.nanoTime();
            algorithm_3 (a, 0, a.length-1, kth);
            end = System.nanoTime();

            time_taken_4[i] =  end - start;
            //System.out.println("Time taken of Algorithm #3 at trial #"+ (i+1) + " is: \t\t\t" + time_taken_4[i] );
        }
        System.out.println("\n\nKth smallest element of each algorithm is: ");
        System.out.println ("Algorithm #1: \t\t\t\t"+algorithm1 (a,kth));
        System.out.println ("Algorithm #2 Iteratively: \t"+algorithm2_Iteratively (a,0, a.length-1, kth));
        System.out.println ("Algorithm #2 Recursively: \t"+algorithm2_Recursively (a,0, a.length-1, kth));
        System.out.println ("Algorithm #3: \t\t\t\t"+ algorithm_3 (a,0,a.length-1,kth));

        System.out.println ("\nThe given sorted list of numbers is: ");
        Arrays.sort (a);
        printArray (a);

        long ave1 = average(time_taken_1);
        long ave2 = average(time_taken_2);
        long ave3 = average(time_taken_3);
        long ave4 = average(time_taken_4);

        System.out.println("\n\nAverage time of Algorithm #1: \t\t\t\t" + ave1);
        System.out.println("Average time of Algorithm #2 Iteratively: \t" + ave2);
        System.out.println("Average time of Algorithm #2 Recursively: \t" + ave3);
        System.out.println("Average time of Algorithm #3: \t\t\t\t" + ave4 + "\n");
    }
    
    /** Compute the average
     * @param array an array to compute the average
     * @return the average of the given set of data
     * */
    static long average(long [] array) {
        long sum = 0;
        for(int i = 0; i< array.length; i++) {
            sum += array[i];
        }
        return sum / (long) array.length;
    }

    /**___________________________________________Generate Input list of size n elements_______________________________________________________________________________*/
    
    /** Generate random elements in the list
     * @param size the size of an array
     * @return an array of random numbers without duplicate value of size n
     * */
    static int[] generateTheList (int size) {
        int[] list = new int[size];
        int counter = 0;
        int number;
        Random random = new Random();
        while(counter < list.length){
            number = random.nextInt(10000);
            boolean duplicate = false;
            do{
                for(int i = 0; i < list.length; i++){
                    if(number == list[i]){
                        duplicate = true;
                        break;
                    }
                    else if( i == counter){
                        list[counter] = number;
                        counter++;
                        duplicate = true;
                        break;
                    }
                }
            }while(!duplicate);

        }
        return list;
    }
    
    /** Display an array
     * @param array an array to be displayed 
     * */
    static  void printArray(int[] array) {
        for(int i: array) {
            System.out.print(i + " ");
        }
    }

    /**___________________________________________________Select-kth 1  (Merge Select) ________________________________________________________________________________*/
    
    /** Find the kth smallest in a list of n elements
     * @param a an array a[]
     * @param k the kth smallest element of a[]
     * @return the kth smallest value of a[]
     * */
    static int algorithm1(int[] a, int k){
        mergeSort (a);
        return a[k-1];
    }
    
    /** Sort unsorted list of random numbers
     * @param array an array to be sorted
     * @return the result of the sorted array
     * */
    static int[] mergeSort(int[] array) {
        int n = array.length;
        int[] result = new int[n];
        if(n <=1) {
            return array;
        } else {
            int mid = n/2;
            int[] left = new int[mid];
            int[] right;
            if(n%2 == 0) {
                right = new int[mid];
            } else {
                right = new int[mid+1];
            }
            for(int i=0; i < mid; i++) {
                left[i] = array[i];
            }
            for(int j= 0; j<right.length;j++) {
                right[j] = array[mid+j];
            }
            left = mergeSort(left);
            right = mergeSort(right);
            result = merge(left, right);
        }
        return result;
    }
    
    /** Merge two arrays
     * @param left an array from the left part of array 
     * @param right an array from the right part of array 
     * @return the result array after merging two arrays 
     * */
    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftPointer = 0, rightPointer = 0, resultPointer = 0;
        while(leftPointer < left.length || rightPointer < right.length) {
            if(leftPointer < left.length && rightPointer < right.length) {      //check if left and right array have elements in them
                if(left[leftPointer] < right[rightPointer]) {
                    result[resultPointer++] = left[leftPointer++];
                } else {
                    result[resultPointer++] = right[rightPointer++];
                }
            } else if (leftPointer < left.length) {    // if the values are in the left array
                result[resultPointer++] =  left[leftPointer++];
            } else {
                result[resultPointer++] = right[rightPointer++];      // if the values are in the right array
            }
        }
        return result;
    }
    /**___________________________________________________Methods that are used in Select-kth 2, 3, and 4 _________________________________________________________________*/
    
    /** Swap two elements in an array a[]
     * @param a an array a[]
     * @param i an element to swap
     * @param j another element to swap
     * */
    static void swap(int[]a,int i, int j)    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    /** Partition an array a
     * @param a an array a[]
     * @param l the initial index
     * @param r the end index (the last index or size of a[])
     * */
    static int partition(int a[], int l, int r) {
        int x = a[r], i = l;
        for (int j = l; j <= r - 1; j++)        {
            if (a[j] <= x)            {
                swap( a,i, j);
                i++;
            }
        }
        swap(a,i, r);
        return i;
    }
    /**___________________________________________________Select-kth 2 & Select-kth 3  (Quick Select) ____________________________________________________________________*/
    
    /** Find the kth smallest in a list of n elements by using “partition” (from Quick Sort) procedure repeatedly until we find the kth smallest element.
     * @param a an array a[]
     * @param m the initial index
     * @param j the end index (the last index or size of a[])
     * @param k the kth smallest element of a[]
     * @return the kth smallest value of a[]
     * */
    static int algorithm2_Iteratively(int[] a, int m, int j, int k) {
        if (m==j) { // if left == right --> return a[left]
            return a[m];
        }
        while(m<=j) {
            int pivotPosition = partition (a, m, j); // Select a pivot position between left and right
            if (k-1 == pivotPosition) {
                return a[pivotPosition];
            } else if (k-1 < pivotPosition) {
                j = pivotPosition - 1;
            } else {
                m = pivotPosition + 1;
            }
        }
        return  Integer.MAX_VALUE;
    }
    
    /** Find the kth smallest in a list of n elements by using “partition” (from Quick Sort) procedure repeatedly until we find the kth smallest element.
    * @param a an array a[]
    * @param m the initial index
    * @param j the end index (the last index or size of a[])
    * @param k the kth smallest element of a[]
    * @return the kth smallest value of a[]
    * */
    static int algorithm2_Recursively(int arr[], int m, int j, int k){
        if (k > 0 && k <= j - m + 1){
            int pos = partition(arr, m, j);
            if (pos-m == k-1) {
                return arr[pos];
            } else if (pos-m > k-1) {
                return algorithm2_Recursively (arr, m, pos - 1, k);
            } else {
                return algorithm2_Recursively (arr, pos + 1, j, k - pos + m - 1);
            }
        }
        return Integer.MAX_VALUE;
    }

    /**___________________________________________________Select-kth 4  (Median of Medians) ________________________________________________________________________________*/
    
    /** Find median of a[]; in this case only finding the median when a.length = 5
     * @param a an array a[] of size 5
     * @param i the initial index (fromIndex)
     * @param n the end index (the last index or size of a[])
     * @return the middle element of a[]
     * */
    static int findMedian(int a[], int i, int n){
        if(i <= n) {
            Arrays.sort(a, i, n);
        }else {
            Arrays.sort(a, n, i);
        }
        return a[n/2];
    }
    
    /** Find the kth smallest in a list of n elements by finding the median of medians of a[]; in this case only finding the median when a.length = 5
     * @param a an array a[]
     * @param m the initial index
     * @param j the end index (the last index or size of a[])
     * @param k the kth smallest element of a[]
     * @return the kth smallest value of a[]
     * */
    static int algorithm_3(int a[], int m, int j, int k){
        // Check if k is smaller than # of elements in array
        if (k > 0 && k <= j - m + 1){
            int n = j - m + 1;
            int i, median[] = new int[(n + 4) / 5]; // Divide the a[] into n/r of size 5
            for (i = 0; i < n/5; i++) { // Calculate the median of each group and store the values in median[]
                median[i] = findMedian(a, m + i * 5, 5);
            }
            if (i*5 < n){
                median[i] = findMedian(a, m + i * 5, n % 5);
                i++;
            }
            int mm = (i == 1)? median[i - 1]: algorithm_3 (median, 0, i - 1, i / 2); // Calculate median of medians by using recursive call
            int pivotPosition = partition(a, m, j); // Partition a[] and get pivot position element in sorted array
            if (k-1 == pivotPosition-m){
                return a[pivotPosition];
            } else if (k-1 < pivotPosition-m ) {
                return algorithm_3 (a, m, pivotPosition - 1, k);  // Recursive to the left --> A(1,..., pivotPosition - 1)
            } else {
                return algorithm_3 (a, pivotPosition + 1, j, k - pivotPosition + m - 1);  // Recursive to the right --> A(pivotPosition + 1, ..., n)
            }
        }
        return Integer.MAX_VALUE;  // If k > # of elements in a[]
    }
}

