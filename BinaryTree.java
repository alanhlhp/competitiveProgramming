import java.util.*;

class TreeNode{
	int key;
	TreeNode left;
	TreeNode right;

	TreeNode(int key){
		this.key = key;
	}
}

public class BinaryTree{ //Binary Tree class

	TreeNode root;

	BinaryTree(int key){
		root = new TreeNode(key);
	}
	
	public static void main(String[] args){
		BinaryTree tree = new BinaryTree(5);

		tree.root.left = new TreeNode(4);
		tree.root.right = new TreeNode(8);

		tree.root.left.left = new TreeNode(11);
		tree.root.right.left = new TreeNode(13);
		tree.root.right.right = new TreeNode(4);

		tree.root.left.left.left = new TreeNode(7);
		tree.root.left.left.right = new TreeNode(2);
		tree.root.right.right.left = new TreeNode(5);
		tree.root.right.right.right = new TreeNode(1);

		//System.out.println(pathSumBoolean(tree.root, 22));
		//System.out.println("Minimum depth: "+minCount(tree.root));

		//System.out.println("** Tree leaves **");
		//print(findLeaves(tree.root));

		//System.out.println("** Tree paths **");
		//print(pathFinder(tree.root, 22));

		//System.out.println("** Min Tree path from scratch **");
		
		//System.out.println(minPathFinder(tree.root));

		//ZIGZAG
		//tree.print(tree.zigzagLevelOrder(tree.root));

		//SYMETRIC
		System.out.println(tree.isSymetric(tree.root));
	}

	public int isSymetric(TreeNode a){
		if(a==null)
			return 1;

		return isSymetric(a.right, a.left);
	}

	public int isSymetric(TreeNode r, TreeNode l){
		if(r==null && l==null)
			return 1;
		else if(r==null || l==null)
			return 0;

		if(r.key!=l.key)
			return 0;
		if(isSymetric(l.left, r.right) == 0)
			return 0;
		if(isSymetric(l.right, r.left) == 0)
			return 0;

		return 1;
	}

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
	  
	    dfs(res, a, 0);
	    
	    return res;
	}
	
	public void dfs(ArrayList<ArrayList<Integer>> res, TreeNode a, int level){
	    if(a==null)
	    	return;

	    if(res.size()<=level){
	    	res.add(new ArrayList<>());
	    }
	    if(level%2==0){
	    	res.get(level).add(a.key);
	    } else{
	    	res.get(level).add(0, a.key);
	    }

	    dfs(res, a.left, level+1);
	    dfs(res, a.right, level+1);
   
	}

	public static int minPathFinder(TreeNode root){
		int result = root.key;
		int leftSum = Integer.MAX_VALUE;
		int rightSum = Integer.MAX_VALUE;

		if(root == null)
			return result;

		if(root.right==null && root.left==null)
			return result;
		else{

			if(root.left != null){
				leftSum = minPathFinder(root.left);
			}
			if(root.right != null){
				rightSum = minPathFinder(root.right);
			}

			if(leftSum < rightSum)
				result = leftSum; 
			else
				result = rightSum;
		}

		return result;
	}

	public static List<List<Integer>> pathFinder(TreeNode root, int sum){
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if(root == null)
			return result;

		ArrayList<Integer> l = new ArrayList<>();
		l.add(root.key);
		dfs(root, sum-root.key, result, l);

		return result;
	}

	public static void dfs(TreeNode t, int sum, List<List<Integer>> result, ArrayList<Integer> l){
		if(t.left == null && t.right==null && sum==0){
			ArrayList<Integer> temp = new ArrayList<>();
			temp.addAll(l);
			result.add(temp);
		}

		if(t.left != null){
			l.add(t.left.key);
			dfs(t.left, sum-t.left.key, result, l);
			l.remove(l.size()-1);
		}
		if(t.right != null){
			l.add(t.right.key);
			dfs(t.right, sum-t.right.key, result, l);
			l.remove(l.size()-1);
		}

	}

	//Collect all the leaves from a binary tree starting from left to right until root
	public static List<List<Integer>> findLeaves(TreeNode root){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		leavesDfs(result, root);
		return result;

	}

	public static int leavesDfs(List<List<Integer>> list, TreeNode root){
		if(root == null)
			return -1;

		int left = leavesDfs(list, root.left);
		int right = leavesDfs(list, root.right);
		int curr = Math.max(left, right)+1;

		//System.out.println("current: "+curr+" left: "+left+" right: "+right);

		if(list.size() <= curr)
			list.add(new ArrayList<Integer>());

		list.get(curr).add(root.key);

		return curr;
	}

	public static void print(ArrayList<ArrayList<Integer>> list){

		for(ArrayList<Integer> l : list){
			for(Integer i : l){
				System.out.print(i+" ");
			}
			System.out.println(" ");
		}

	}


	//Find the minimun depth of a binary tree
	public static int minCount(TreeNode root){
		if(root == null)
			return 0;

		LinkedList<TreeNode> nodes = new LinkedList();
		LinkedList<Integer> count = new LinkedList();

		nodes.add(root);
		count.add(1);

		while(!nodes.isEmpty()){
			TreeNode crr = nodes.poll();
			int s = count.poll();

			if(crr.left == null && crr.right == null)
				return s;

			if(crr.left != null){
				nodes.add(crr.left);
				count.add(s+1);
			}
			if(crr.right != null){
				nodes.add(crr.right);
				count.add(s+1);
			}
		}

		return 0;

	}

	public static boolean pathSumBoolean(TreeNode root, int sum){
		if(root == null)
			return false;

		LinkedList<TreeNode> nodes = new LinkedList();
		LinkedList<Integer> values = new LinkedList();

		nodes.add(root);
		values.add(root.key);

		while(!nodes.isEmpty()){
			TreeNode current = nodes.poll();
			int sumValue = values.poll();

			if(current.left==null && current.right==null && sumValue == sum)
				return true;

			if(current.left != null){
				nodes.add(current.left);
				values.add(sumValue + current.left.key);
			}

			if(current.right != null){
				nodes.add(current.right);
				values.add(sumValue + current.right.key);
			}

		}

		return false;
	}

	public static TreeNode builTree(int[] inorder, int[] postorder){
		int inStart = 0;
		int inEnd = inorder.length-1;
		int postStart = 0;
		int postEnd = postorder.length-1;

		return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
	}

	public static TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
		if(inStart > inEnd || postStart > postEnd)
			return null;

		int rootValue = postorder[postEnd];
		TreeNode root = new TreeNode(rootValue);

		int k =0;
		for(int i=0; i<inorder.length; i++){
			if(inorder[i] == rootValue){
				k = i;
				break;
			}
		}

		root.left = buildTree(inorder, inStart, k - 1, postorder, postStart, postStart + k - (inStart +1));

		root.right = buildTree(inorder, k + 1, inEnd, postorder, postStart + k - inStart, postEnd-1);

		return root;
	}
}

