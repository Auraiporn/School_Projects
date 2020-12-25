package TreePackage;
import TreePackage.BinaryTreeInterface;

public class BinaryTree <T> implements BinaryTreeInterface <T> {
	
	private BinaryNode<T> root;

	public BinaryTree()
	{
		root = null;
	}
	
	public BinaryTree(T rootData)
	{
		root = new BinaryNode<> (rootData);
	}
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		initializeTree(rootData, leftTree, rightTree);
	}
	
	public void setTree(T rootData, BinaryTreeInterface <T> leftTree, BinaryTreeInterface <T> rightTree)
	{
		initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	private void initializeTree(T rootData, BinaryTree <T> leftTree, BinaryTree <T> rightTree)
	{
		root = new BinaryNode<>(rootData);
		if(leftTree != null)
			root.setLeftChild(leftTree.root);
		if(rightTree != null)
			root.setRightChild(rightTree.root);
	}
	
	public void setRootData(T rootData)
	{
		root.setData(rootData);
	}
	
	public T getRootData()
	{
		return root.getData();
	}
	
	protected void setRootNode(BinaryNode<T> rootNode)
	{
		root = rootNode;
	}
	
	protected BinaryNode<T> getRootNode()
	{
		return root;
	}
	
	//Methods for BINARY TREE 
	public boolean isEmpty()
	{
		return root == null;
	}
	
	public void clear()
	{
		root = null;
	}
	
	/** Make a public method to call a private because we want to hide detail from clients. */
	
	//• Pre_Order_BTree
	public void pre_Order_Tree()
	{
		 pre_Order_Tree(root);
	}
	private void pre_Order_Tree(BinaryNode<T> node)
	{
		if(node !=null)
		{
			System.out.println(node.getData());
			 pre_Order_Tree(node.getLeftChild());
			 pre_Order_Tree(node.getRightChild());
		}
	}
	
	//• In_Order_BTree
	public void in_Order_Tree()
	{
		in_Order_Tree(root);
	} 
	private void in_Order_Tree(BinaryNode<T> node)
	{
		if (node != null)
		{
			in_Order_Tree(node.getLeftChild());
			System.out.println(node.getData());
			in_Order_Tree(node.getRightChild());
		} 
	} 	
	
	//• Post_Order_BTree
	public void post_Order_Tree()
	{
		post_Order_Tree(root);
	}
	private void post_Order_Tree(BinaryNode<T> node)
	{
		if(node != null)
		{
			post_Order_Tree(node.getLeftChild());
			post_Order_Tree(node.getRightChild());
			System.out.println(node.getData());
		}
	}
	
	//• GetNumberOfNodes_BTree
	public int getNumberOfNodes_Tree()
	{
		int numberOfNodes = 0;
		if (root != null)
		numberOfNodes = root.getNumberOfNodes_Tree();
		return numberOfNodes;
	} 
	
	//• GetHeight_BTree
	public int getHeight_Tree()
	{
		int height = 0;
		if (root != null)
			height = root.getHeight_Tree();
		return height;
	} 
	
	//• GetLeftmostData_BTree
	public T getLeftmostData_Tree()
	{
		return getLeftmostData_Tree(root);
	}
	private T getLeftmostData_Tree(BinaryNode<T> node)
	{
		if (node.getLeftChild() == null)
			return node.getData();
		else
			return node.getLeftChild().getLeftmostData_Tree( );
	}
			
	//• GetRightmostData_BTree
	public T getRightmostData_Tree()
	{
		return getRightmostData_Tree(root);
	}
	private T getRightmostData_Tree(BinaryNode<T> node)
	{
		if (node.getRightChild() == null)
			return node.getData();
		else
			return node.getRightChild().getRightmostData_Tree( );
	}

}
