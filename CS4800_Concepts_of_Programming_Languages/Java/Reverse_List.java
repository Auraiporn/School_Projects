/**
 * This class illustrates the functions to reverse a list of integers. 
 */
public class Reverse_List {
	public static void main(String [] args){
		
		 int[] a = {6, 7, 8, 9, 10, 11};
		 System.out.println ("The original list is: ");
		 printArray (a);
		 System.out.println ("\nThe reverse list using tail recursion is: ");
		 rev_tail_recursion (a, 0, a.length-1);
		 printArray (a);
		 
		 System.out.println("\n----------------------------------------------");
		 int[] b = {1, 2, 3, 4, 5, 6};
		 System.out.println ("The original list is: ");
		 printArray (b);
		 System.out.println ("\nThe reverse list using iterative method is: ");
		 rev_iterative(b);
		 printArray(b);
	}
	
	static int[] rev_iterative (int [] a){
		if(a.length==0 || a.length < 2){
			return a;
		}
		int n = a.length;
		for(int i=0; i<n;i++){
			swap (a, i, n-1);
			n = n -1;
		}
		return a;
	}
		 		 
	static int[] rev_tail_recursion (int[]a, int i, int n){
		if(i<n){
			swap (a,i,n);
			rev_tail_recursion (a, i+1, n-1);
		}
		return a;
	}

	static void swap (int [] a,int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	static void printArray(int[] array) {
		for(int i: array) {
		System.out.print(i + " ");
		}
	}

}
