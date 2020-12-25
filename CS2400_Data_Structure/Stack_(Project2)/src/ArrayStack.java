import java.util.EmptyStackException;
import java.util.Arrays;

public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack; // Array of stack entries
	private int topIndex; // Index of top entry
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack()
	{
		this(DEFAULT_CAPACITY);
	} 
	
	public ArrayStack(int initialCapacity)
	{
		integrityOK = false;
		checkCapacity(initialCapacity);
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[])new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		integrityOK = true;
	}
	
	private void checkIntegrity()
	{
		if(!integrityOK)
			throw new SecurityException("ArrayStack object is corrupt.");
	}
	
	private void checkCapacity(int capacity)
	{
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Capacity exceeds allowed maximum of " + MAX_CAPACITY);
	}
	
	/*Check whether the array has room for a new entry by calling ensureCapacity(). 
	 ensureCapacity() resizes the array to avoid a stack that has no room. 
	 Place a new entry after the last occupied location in the array.
	 Each push is o(1) unless resizing the array.*/
	public void push(T newEntry)
	{
		checkIntegrity();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	} 
	
	// o(n) operation since it performs an operation copyOf array.
	private void ensureCapacity()
	{
		if (topIndex >= stack.length - 1) 
		{
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		} 
	} 
	
	/*Retrieve the top is o(1) operation.*/
	public T peek()
	{
		checkIntegrity();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return stack[topIndex];
	} 
	
	/*Retrieve the top and then remove it. T pop() is o(1) operation.*/
	public T pop()
	{
		checkIntegrity();
		if (isEmpty())
			throw new EmptyStackException();
		else
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		} 
	} 
	
	public void clear()
	{
		checkIntegrity();
		while (topIndex > -1)
		{
			stack[topIndex] = null;
			topIndex--;
		}
	}
	
	public boolean isEmpty()
	{
		return topIndex < 0;
	} 
	
		
	
	
}