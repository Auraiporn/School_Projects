import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; 
import java.io.*;

public class MaxHeap <T extends Comparable<T>> {
		
		private T [] heap;//Array of heap entries 
		private int lastIndex; // lastIndex (it tells number of heap entries) 
		private int swaps;
		private static final int DEFAULT_CAPACITY = 25; 
		private static final int MAX_CAPACITY = 10000; 
		
		public MaxHeap() {
			this(DEFAULT_CAPACITY);
		}

		public MaxHeap(int initialCapacity) {
			//Check if the initial capacity is too small
			if(initialCapacity < DEFAULT_CAPACITY)
				initialCapacity = DEFAULT_CAPACITY; 
			else // or is it too big
				checkCapacity(initialCapacity);
			
			@SuppressWarnings("unchecked")
			T [] tempHeap = (T[]) new Comparable[initialCapacity +1];
			heap = tempHeap;
			lastIndex = 0;
			swaps = 0;
		}
		
		// Suppose that n entries for our heap are given in an array of exactly
		// n locations. This method takes this array, copies it into data field heap, 
		// and uses reheap to create a heap
		public MaxHeap(T[] entries) {
			this(entries.length); // Call other constructor
			lastIndex = entries.length;
			// Copy given array to data field
			for (int index = 0; index < entries.length; index++)
				heap[index + 1] = entries[index];
			// Create heap
			for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
				reheap(rootIndex);
		} 

		private void checkCapacity(int capacity)
		{
			if(capacity > MAX_CAPACITY)
				throw new IllegalStateException("Capacity exceeds allowed maximum of " + MAX_CAPACITY);
		}

		public boolean isEmpty() {
			return lastIndex == 0; //@ index 0 of an array is unused location
		}
		
		public int getSize() {
			return lastIndex; 
		}
		
		//o(nlogn)
		public void add(T newEntry) {
			
			int newIndex = lastIndex + 1; // lastIndex + 1 is index of next available array location
			int parentIndex = newIndex / 2; // Index of parent of available location
			// Check if there is a parent which is to make sure the the heap is not empty
			while ( (parentIndex > 0) && ((Comparable) newEntry).compareTo(heap[parentIndex]) > 0) {
				heap[newIndex] = heap[parentIndex]; // Move parent to available location
				// Update indices 
				newIndex = parentIndex; 
				parentIndex = newIndex / 2;
				swaps++;
			} 
			heap[newIndex] = newEntry; // Place new entry in correct location
			lastIndex++;  
			ensureCapacity();
		}
		private void ensureCapacity() {
			if (lastIndex >= heap.length) {
				int newCapacity = 2 * (heap.length - 1);
				checkCapacity(newCapacity);
				heap = Arrays.copyOf(heap, newCapacity);
			} 
		} 
		
		
		public int getSwaps() {
			return this.swaps;
		}

		// Using reheap to create a max heap is an o(n) operation. 
		// Create a heap more efficiently by using the method reheap instead of the method add 
		public void reheap(int rootIndex) {
			boolean done = false;
			T orphan = heap[rootIndex];
			int leftChildIndex = 2 * rootIndex;
			while (!done && (leftChildIndex <= lastIndex) ) {
				int largerChildIndex = leftChildIndex; // Assume larger
				int rightChildIndex = leftChildIndex + 1;
				if ( (rightChildIndex <= lastIndex) && (heap[rightChildIndex]).compareTo(heap[largerChildIndex]) > 0) {
					largerChildIndex = rightChildIndex;
				} 
				if ((orphan).compareTo(heap[largerChildIndex]) < 0) {
					heap[rootIndex] = heap[largerChildIndex];
					rootIndex = largerChildIndex;
					leftChildIndex = 2 * rootIndex;
					swaps++;
				}
				else
					done = true;
				} 
			heap[rootIndex] = orphan;
		} 
		
		// o(nlog n)
		public T removeMax() { 
			T root = null;
			if (!isEmpty()) {
				root = heap[1]; // Return value
				heap[1] = heap[lastIndex]; // Form a semiheap
				lastIndex--; 
				reheap(1);
			} 
			return root; 
		} 
	
		public String toString(int length) {
			String out = "";
			for (int i = 1; i <= length; i++) {
				out += heap[i] + ",";
			}
			return out+"...";
		}
}
