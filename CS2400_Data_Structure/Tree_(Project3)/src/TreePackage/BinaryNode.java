package TreePackage;
import TreePackage.BinaryTreeInterface;

public class BinaryNode<T> implements BinaryTreeInterface <T> {
	
	private T data;
	private BinaryNode<T> leftChild, rightChild;

	public BinaryNode()
	{
		this(null); 
	} 
	
	public BinaryNode(T dataPortion)
	{
		this(dataPortion, null, null); // Call next constructor
	} 
	
	public String toString()
    {
       return data.toString();
    }
	 
	public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild)
	{
		data = dataPortion;
		leftChild = newLeftChild;
		rightChild = newRightChild;
	} 
	
	/** Retrieves the data portion of this node.
		@return The object in the data portion of the node. */
	public T getData()
	{
		return data;
	} 
	
	/** Sets the data portion of this node.
		@param newData The data object. */
	public void setData(T newData)
	{
		data = newData;
	} 
	
	/** Retrieves the left child of this node.
		@return A reference to this node's left child. */
	public BinaryNode<T> getLeftChild()
	{
		return leftChild;
	} 
	
	/** Retrieves the right child of this node.
		@return A reference to this node's right child. */
	public BinaryNode<T> getRightChild()
	{
		return rightChild;
	} 
	
	/** Sets this node's left child to a given node.
		@param newLeftChild A node that will be the left child. */
	public void setLeftChild(BinaryNode<T> newLeftChild)
	{
		leftChild = newLeftChild;
	} 
	
	/** Sets this node's right child to a given node.
		@param newRightChild A node that will be the right child. */
	public void setRightChild(BinaryNode<T> newRightChild)
	{
		rightChild = newRightChild;
	} 
		
	/** Detects whether this node has a left child.
		@return True if the node has a left child. */
	public boolean hasLeftChild()
	{
		return leftChild != null;
	} 
	
	/** Detects whether this node is a leaf.
		@return True if the node is a leaf. */
	public boolean isLeaf()
	{
		return (leftChild == null) && (rightChild == null);
	} 
	
	/**This method is inside the BinaryNode, so we don't need to use mutators and accessors */
	//• Pre_Order_BNode
	public void pre_Order_Tree() {
		//root --> left --> right
		// print out root
		System.out.println(data);
		
		//print left --> this is a stopping condition
		if(leftChild!=null) 
		{
			leftChild.pre_Order_Tree();
		}
		
		//print right --> this is a stopping condition
		if(rightChild!=null) 
		{
			rightChild.pre_Order_Tree();
		}
	}
	
	//• In_Order_BNode
	public void in_Order_Tree()
	{
		if(leftChild!=null) 
		{
			leftChild.in_Order_Tree();
		}
		
		System.out.println(data);
		
		if(rightChild!=null)
		{
			rightChild.in_Order_Tree();
		}
	}
	
	///• Post_Order_BNode
	public void post_Order_Tree()
	{
		if(leftChild!=null)
		{
			leftChild.post_Order_Tree();
		}
		
		if(rightChild!=null)
		{
			rightChild.post_Order_Tree();
		}
		
		System.out.println(data);
	}
	
	//• GetNumberOfNodes_BNode
	/** Counts the nodes in the subtree rooted at this node.
	@return The number of nodes in the subtree rooted at this node. */
	public int getNumberOfNodes_Tree()
	{
		int leftNumber = 0;
		int rightNumber = 0;
		if (leftChild != null)
			leftNumber = leftChild.getNumberOfNodes_Tree();
		if (rightChild != null)
			rightNumber = rightChild.getNumberOfNodes_Tree();
		return 1 + leftNumber + rightNumber;
	} 
	
	//• GetHeight_BNode
	public int getHeight_Tree()
	{
		return getHeight_Tree(this); // Call private getHeight
	} 
	private int getHeight_Tree(BinaryNode<T> node)
	{
		int height = 0;
		if(node!=null)
		{
			height = 1 + Math.max(getHeight_Tree(node.leftChild),getHeight_Tree(node.rightChild));
		}
		return height;
	}
	
	//• GetLeftmostData_BNode
	public T getLeftmostData_Tree()
	{
		if (leftChild == null)
			return data;
		else
			return leftChild.getLeftmostData_Tree();
	}
		
	//• GetRightmostData_BNode
	public T getRightmostData_Tree()
	{
		if (rightChild == null)
			return data;
		else
			return rightChild.getRightmostData_Tree();
	}

	@Override
	public void setRootData(T rootData) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		// TODO Auto-generated method stub
	}
} 

