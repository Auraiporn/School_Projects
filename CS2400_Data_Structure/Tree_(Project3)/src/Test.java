import TreePackage.BinaryTree;
import TreePackage.BinaryTreeInterface;
import TreePackage.BinaryNode;

public class Test <T extends BinaryTreeInterface <T>>  {

	public static <T> void main(String[] args) {
		
		//Constructing a tree to test a BinaryTree class
		BinaryTreeInterface<String> hTree = new BinaryTree<>();
		hTree.setTree("H", null, null);
		
		BinaryTreeInterface<String> gTree = new BinaryTree<>();
		gTree.setTree("G", null, null);
		
		BinaryTreeInterface<String> emptyTree = new BinaryTree<>();
		
		BinaryTreeInterface<String> fTree = new BinaryTree<>();
		fTree.setTree("F", null, null);
		
		/*BinaryTreeInterface<String> emptyTree1 = new BinaryTree<>();
		BinaryTreeInterface<String> emptyTree2 = new BinaryTree<>();
		BinaryTreeInterface<String> emptyTree3 = new BinaryTree<>();
		BinaryTreeInterface<String> emptyTree4 = new BinaryTree<>();*/
		BinaryTreeInterface<String> eTree = new BinaryTree<>();
		eTree.setTree("E", gTree, hTree);
		
		BinaryTreeInterface<String> dTree = new BinaryTree<>();
		dTree.setTree("D", fTree, emptyTree);
		
		/*BinaryTreeInterface<String> emptyTree5 = new BinaryTree<>();
		BinaryTreeInterface<String> emptyTree6 = new BinaryTree<>();*/
				
		//Subtree rooted at C
		BinaryTreeInterface<String> cTree = new BinaryTree<>();
		cTree.setTree("C", dTree, eTree);
		
		BinaryTreeInterface<String> bTree = new BinaryTree<>();
		bTree.setTree("B", null, null);
		
		//Tree rooted at A 
		BinaryTreeInterface<String> aTree = new BinaryTree<>();
		aTree.setTree("A", bTree, cTree);
				
		System.out.println("The preorder traversal of a binary tree is: ");
		aTree.pre_Order_Tree();
		System.out.println("-----------------------------------------------------");
		System.out.println();
				
		System.out.println("The inorder traversal of a binary tree is: ");
		aTree.in_Order_Tree();
		System.out.println("-----------------------------------------------------");
		System.out.println();
				
		System.out.println("The postorder traversal of a binary tree is: ");
		aTree.post_Order_Tree();
		System.out.println("-----------------------------------------------------");
		
		System.out.println();
		System.out.println("The left most node of the tree is: " + aTree.getLeftmostData_Tree());
		System.out.println("The right most node of the tree is: " + aTree.getRightmostData_Tree());
		System.out.println("Tree has " + aTree.getNumberOfNodes_Tree() + " nodes.");
		System.out.println("Height of tree is " + aTree.getHeight_Tree() + ".");  
		System.out.println("-----------------------------------------------------");
		
	}
}
