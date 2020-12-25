import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface <T> {
	
	//The first node references the stack's top entry.
	private Node topNode; 
	
	public LinkedStack()
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
	
	/*Push operation is independent of other entries--> o(1)*/
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry , topNode);
		topNode = newNode;	
	} 
	
	/*topNode will reference what was the second node in the chain, 
	 so the original first node will no longer be referenced/ deallocated. It is o(1) operation.*/
	public T pop()
	{
		T top = peek(); // peek() will check whether the chain is empty or not
		topNode = topNode.getNextNode();
		return top;
	} 
	
	/*Get the top entry in the stack by accessing the data portion of the first node in the chain. 
	It is o(1) operation.*/
	public T peek()
	{
		if(isEmpty())
			throw new EmptyStackException();
		else
			return topNode.getData();
	} 
	

	public boolean isEmpty()
	{
		return topNode == null;
	}	
	
	public void clear()
	{
		topNode = null;
	} 	

}
