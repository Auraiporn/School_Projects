import java.util.Vector;
import java.util.EmptyStackException;


public final class VectorStack <T> implements StackInterface <T> {
	
	private Vector <T> stack;
	private boolean integrityOK;
	private static int DEFAULT_CAPACITY = 50;
	private static int MAX_CAPACITY = 10000;
	
	public VectorStack()
	{
		this(DEFAULT_CAPACITY);
	}

	public VectorStack(int initialCapacity)
	{
		integrityOK = false;
		checkCapacity(initialCapacity);
		stack = new Vector<>(initialCapacity);
		integrityOK = true; 
	}
	
	private void checkIntegrity()
	{
		if(!integrityOK)
			throw new SecurityException("VectorStack object is corrupt.");
	}
	
	private void checkCapacity(int capacity)
	{
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Capacity exceeds allowed maximum of " + MAX_CAPACITY);
	}

	/*Use Vector's method add to add an entry to the end of the vector.*/
	public void push(T newEntry)
	{
		checkIntegrity();
		stack.add(newEntry);
	}
	
	/*Remove the top by using Vector's method remove.
	 The argument of this method is the index of the last entry in the vector, since that entry is at the top of the stack.
	The index is less than the vector's current size.   */
	public T pop()
	{
		checkIntegrity();
		if(isEmpty())
			throw new EmptyStackException();
		else 
			return stack.remove(stack.size()-1);
	}
	
	/*Retrieve the stack's top entry by using Vector's method lastElement. */
	public T peek()
	{
		checkIntegrity();
		if(isEmpty())
			throw new EmptyStackException();
		else 
			return stack.lastElement();	
	}

	public boolean isEmpty()
	{
		checkIntegrity();
		return stack.isEmpty();
	}
	
	public void clear()
	{
		checkIntegrity();
		stack.clear();
	}
	
}

