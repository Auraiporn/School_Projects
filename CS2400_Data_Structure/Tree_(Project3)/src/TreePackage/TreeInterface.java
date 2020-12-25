/**TreeInterface defines the methods' header of the operations in both BinaryTree and BinaryNode. */
package TreePackage; 
public interface TreeInterface <T> 
{
	/**Performs preorder traversal (root--> left --> right) of a subtree rooted at a given node. */
	public void pre_Order_Tree();
	
	/**Performs inorder traversal (left --> root --> right) of a subtree rooted at a given node. */
	public void in_Order_Tree();
	
	/** Performs postorder traversal (left --> right --> root) of a subtree rooted at a given node. */
	public void post_Order_Tree();
	
	/**Returns the number of nodes of a subtree rooted at a given node. */
	public int getNumberOfNodes_Tree();
	
	/**Returns the height of a subtree rooted at a given node. */
	public int getHeight_Tree();
	
	/**Returns the data of the left most node of a subtree rooted at a given node. */
	public T getLeftmostData_Tree();
	
	/**Returns the data of the right most node of a subtree rooted at a given node.*/
	public T getRightmostData_Tree();
	
}
