import java.util.Scanner;
import java.io.*;

public class Test <T extends Comparable<T>> {

	public static void main(String[] args) throws FileNotFoundException {
		

		PrintWriter outputFile = new PrintWriter("outputFile.txt"); // Write to the file 
		File myFile = new File ("data.txt");
		Scanner inputFile = new Scanner(myFile);
		int numbers = 0; 
		while(inputFile.hasNextInt()) 
		{
			numbers++;
			inputFile.nextInt();
		}
		int [] arrayOfNumbers = new int[numbers];
		
		Scanner inputFileArray = new Scanner(myFile);
		for(int i=0; i < arrayOfNumbers.length; i++)
		{
			arrayOfNumbers[i] = inputFileArray.nextInt();
		}
		inputFile.close();
	
		MaxHeap <Integer> maxheap_Sequential = new MaxHeap<>(arrayOfNumbers.length);
		for(int i=1; i < arrayOfNumbers.length+1; i++) {
			maxheap_Sequential.add(i);
		}
		System.out.println("=====================================================================");
		outputFile.println("=====================================================================");
		System.out.print("Heap built using sequential insertions: " + maxheap_Sequential.toString(10));
		outputFile.print("Heap built using sequential insertions: " + maxheap_Sequential.toString(10));
		
		System.out.println("\nNumber of swaps in the heap creation: " + maxheap_Sequential.getSwaps());
		outputFile.println("\nNumber of swaps in the heap creation: " + maxheap_Sequential.getSwaps());
		for(int i = 0 ; i < 10; i++) {
			maxheap_Sequential.removeMax();
		}
		System.out.print("Heap after 10 removals: " + maxheap_Sequential.toString(10));
		outputFile.print("Heap after 10 removals: " +  maxheap_Sequential.toString(10));
		System.out.println("\n");
		outputFile.println("\n");
				
		// Open the file 
		File file = new File ("data.txt");
		Scanner input = new Scanner(file);
		int size = 0; 
		while(input.hasNextInt()) 
		{
			size++;
			input.nextInt();
		}
		Integer [] arr = new Integer[size];
		Scanner inputArray = new Scanner(myFile);
		for(int i=0; i < arr.length; i++)
		{
			arr[i] = inputArray.nextInt();
		}
		input.close();
		
		MaxHeap<Integer> maxHeap_Optimal = new MaxHeap<Integer>(arr);
		outputFile.print("Heap built using optimal method: " +  maxHeap_Optimal.toString(10));
		System.out.print("Heap built using optimal method: " +  maxHeap_Optimal.toString(10));
		System.out.println("\nNumber of swaps in the heap creation: " +  maxHeap_Optimal.getSwaps() );
		outputFile.println("\nNumber of swaps in the heap creation: " +  maxHeap_Optimal.getSwaps() );
		for(int i = 0 ; i < 10; i++) {
			maxHeap_Optimal.removeMax();
		}
		System.out.print("Heap after 10 removals: " + maxHeap_Optimal.toString(10));
		outputFile.print("Heap after 10 removals: " + maxHeap_Optimal.toString(10));
		System.out.println("\n=====================================================================");
		outputFile.println("\n=====================================================================");
		
		outputFile.close();
			
		System.out.println("The current size of heap is: "+ maxHeap_Optimal.getSize() + " now.");
	
	}

}
