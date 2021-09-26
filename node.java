// A class to store a binary tree node
class node
{
	int data;
	node left = null, right = null;

	node(int data) {
		this.data = data;
	}
}

class Main
{
	// Wrapper over `node` class
	static class nodeWrapper
	{
		public node node;

		nodeWrapper(node node) {
			this.node = node;
		}
	}

	// Function to check if a given node is present in a binary tree or not
	public static boolean isnodePresent(node root, node node)
	{
		// base case
		if (root == null) {
			return false;
		}

		// if the node is found, return true
		if (root == node) {
			return true;
		}

		// return true if a given node is found in the left or right subtree
		return isnodePresent(root.left, node) || isnodePresent(root.right, node);
	}

	// Function to find the lowest common ancestor of given nodes `x` and `y`, where
	// both `x` and `y` are present in the binary tree.
	// The function returns true if `x` or `y` is found in a subtree rooted at the root
	// `lca` �> stores `LCA(x, y)`
	public static boolean findLCA(node root, nodeWrapper lca, node x, node y)
	{
		// base case 1: return false if the tree is empty
		if (root == null) {
			return false;
		}

		// base case 2: return true if either `x` or `y` is found
		if (root == x || root == y)
		{
			// set lca to the current node
			lca.node = root;
			return true;
		}

		// recursively check if `x` or `y` exists in the left subtree
		boolean left = findLCA(root.left, lca, x, y);

		// recursively check if `x` or `y` exists in the right subtree
		boolean right = findLCA(root.right, lca, x, y);

		// if `x` is found in one subtree and `y` is found in the other subtree,
		// update lca to the current node
		if (left && right) {
			lca.node = root;
		}

		// return true if `x` or `y` is found in either left or right subtree
		return left || right;
	}

	// Function to find the lowest common ancestor of nodes `x` and `y`
	public static void findLCA(node root, node x, node y)
	{
		// `lca` stores the lowest common ancestor
		node lca = null;

		// Wrap the `lca` node, so its reference can be changed inside the
		// `findLCA()` method
		nodeWrapper LCA = new nodeWrapper(lca);

		// call LCA procedure only if both `x` and `y` are present in the tree
		if (isnodePresent(root, y) && isnodePresent(root, x))
		{
			findLCA(root, LCA, x, y);
			lca = LCA.node;
		}

		// if LCA exists, print it
		if (lca != null) {
			System.out.println("LCA is " + lca.data);
		}
		else {
			System.out.print("LCA does not exist\n");
		}
	}

	public static void main(String[] args)
	{
		/* Construct the following tree
              1
            /   \
           /     \
          2       3
           \     / \
            4   5   6
               / \
              7   8
		 */

		node root = new node(1);
		root.left = new node(2);
		root.right = new node(3);
		root.left.right = new node(4);
		root.right.left = new node(5);
		root.right.right = new node(6);
		root.right.left.left = new node(7);
		root.right.right.right = new node(8);

		findLCA(root, root.right.left.left, root.right.right);
		findLCA(root, root.right.left.left, new node(10));
		findLCA(root, root.right.left.left, root.right.left.left);
		findLCA(root, root.right.left.left, root.right.left);
		findLCA(root, root.left, root.right.left);
	}
}

