import java.util.EmptyStackException;
public class PileOfBooksLinkedNode <T> implements PileOfBooksInterface<T>  {

	private Node topNode;
		
	public PileOfBooksLinkedNode()
	{
		topNode = null;
	}
	
	class Node 
	{
		private T data; //Entry in the bag
		private Node next; // Link to next node
		
		private Node(T dataPortion)
		{
			this(dataPortion, null);
		}
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		}
		private T getData()
		{
			return data;
		}
		private void setData(T newData)
		{
			data = newData;
		}
		private Node getNextNode()
		{
			return next;
		}
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
			
	}
		
	public boolean isEmpty()
	{
		return topNode == null;
	}
	
	/* Adding to the top. 
	 * Add an entry onto the stack by first allocating a new node that references the stack’s existing chain.
	 * This reference is in topNode, the head reference to the chain. 
	 * Set topNode to reference the new node. */
	public void add(T newEntry) 
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode; 
	}
	
	/*Display the top of a pile of books.
	 * Get the top entry by accessing a data portion.
	 * If a pile of books is empty, it will throw an exception. */
	public T displayContentOfTopBook()
	{
		if(isEmpty())
			throw new EmptyStackException();
		else 
			return topNode.getData();
	}
	
	/* Removing the top. 
	 * Remove the top entry in a pile of books by setting topNode to the reference in the first node.
	 * topNode references what was the second node in the chain. 
	 * First node will no longer be referenced, so it will be deallocated.
	 * Return top entry before it is removed */
	public T remove()
	{
		T top = displayContentOfTopBook();
		topNode = topNode.getNextNode();
		return top;
	}
	
	public void clear()
	{
		topNode = null;
	}
	
	public int getCurrentSize()
	{
		Node temp = topNode;
		int count = 0;
		while(temp != null)
		{
			count++;
			temp = temp.next;
		}
		return count;
	}
}
