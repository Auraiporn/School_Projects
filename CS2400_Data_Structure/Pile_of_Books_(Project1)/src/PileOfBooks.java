import java.util.Arrays;
import java.util.EmptyStackException;

public class PileOfBooks <T> implements PileOfBooksInterface<T> 
{
	private T[] books;
	private int topIndex; 
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	//constructors 
	public PileOfBooks() 
	{
		this(DEFAULT_CAPACITY);
	}
	
	/*To indicate an empty stack, we have assigned -1 to topIndex as its initial value. 
	  It allows the add(T newEntry) method to just increment topIndex before using it when placing a new entry in the array of stack.*/
	public PileOfBooks (int initialCapacity)
	{
		integrityOK = false;
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked") // unchecked cast
		T[] tempBooks = (T[]) new Object[initialCapacity];
		books = tempBooks;
		topIndex = -1;
		integrityOK = true;
	}
	
	private void checkIntegrity()
	{
		if(!integrityOK)
			throw new SecurityException("PileOfBooks object is corrupt.");
	}
	
	public int getCurrentSize()
	{
		return topIndex+1; // The first index of a pile of books starts counting from "1".
	}
	
	public boolean isEmpty()
	{
		return topIndex < 0;
	}
	
	/* ensureCapacity() method doubles the size of an array after it becomes full*/
	private void ensureCapacity()
	{
		if(topIndex == books.length -1)
		{
			int newLength = 2* books.length;
			checkCapacity(newLength);
			books = Arrays.copyOf(books, newLength);
		}
	}
	/* Adding to the top. 
	 * The add(T newEntry) method checks whether the array has room for a new entry by calling the method ensureCapacity.
	 * EnsureCapacity resizes the array to make sure that a stack has room for a new entry.
	 * The add(T newEntry) method places the new entry immediately after the last occupied location in the array.
	 * Increment topIndex */
	public void add(T newEntry) 
	{
		checkIntegrity();
		ensureCapacity();
		
			books[topIndex +1] = newEntry; // Adding one because we only want to place a new entry on top of a pile of books.
			topIndex++;
	}
	
	/*Retrieving the top.
	 *DisplayContentOfTopBook() either returns the array entry at topIndex or throws an exception if the stack is empty.*/
	public T displayContentOfTopBook()
	{
		checkIntegrity();
		if(isEmpty())
			throw new EmptyStackException();
		else 
			return books[topIndex];
	}
	/* Removing the top. 
	 * Get the top entry in the stack.
	 * Then removes the top entry in the stack.
	 * Decrement topIndex
	 * return top */
	public T remove()
	{
		checkIntegrity();
		if(isEmpty())
			throw new EmptyStackException();
		else
		{
			T top = books[topIndex]; 
			books[topIndex] = null; 
			topIndex--;
			return top;
		}
	}
	
	public void clear()
	{
		while(!isEmpty())
			remove();
	}
	
	private void checkCapacity(int capacity)
	{
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Capacity exceeds allowed maximum of " + MAX_CAPACITY);
	}
	
	public String toString()
	{
		String result = "";
		for(int i = topIndex; i >= 0 ; i--) 
		{
			result+=books[i] + "\n" ;
			
		}
		return result;
	}
		
}
