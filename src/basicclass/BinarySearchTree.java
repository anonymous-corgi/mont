package basicclass;

public class BinarySearchTree<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	
	private static class BinaryNode<E>{
		E data;
		BinaryNode<E> left;
		BinaryNode<E> right;
		
		public BinaryNode(E d) {
			this(d, null, null);
		}
		public BinaryNode(E d, BinaryNode<E> lt, BinaryNode<E> rt) {
			this.data = d;
			this.left = lt;
			this.right = rt;
		}
	}
	
	public BinarySearchTree() {
		makeEmpty();
	}
	
	public void makeEmpty() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean contains(E target) {
		return contains(target, root);
	}
	
	
	public E findMin() {
		return findMin(root).data;
	}
	public E findMax() {
		return findMax(root).data;
	}
	
//	Undone
	public void insert(E data) {
		root = insert(data, root);
	}
	
	public void remove(E data) {
		root = remove(data, root);
	}
	
	public void printTree() {
		if(isEmpty()) {
			System.out.print("There is nothing in the tree.");
		}else {
			printTree(root);
		}
	}
	
//	Private.
	private boolean contains(E target, BinaryNode<E> t) {
		return false;
	}
	private BinaryNode<E> findMin(BinaryNode<E> t){
		if(t == null) {
			return null;
		}else if(t.left != null){
			return findMin(t.left);
		}else {
			return t;
		}
	}
	private BinaryNode<E> findMax(BinaryNode<E> t){
		if(t == null) {
			return null;
		}else if(t.right != null){
			return findMax(t.right);
		}else {
			return t;
		}
	}
//	t = insert(data, t) means whether data should be inserted into t. 
//	insert() will lead to the judgment of its child when insertion to t fails.
	private BinaryNode<E> insert(E data, BinaryNode<E> t) {
		if(t == null) {
			return new BinaryNode<E>(data);
		}
		int compareResult = t.data.compareTo(data);
		
		if(compareResult < 0) {
			t.right = insert(data, t.right);
		}else if(compareResult > 0){
			t.left = insert(data, t.left);
		}

		return t;
	}
	
	private BinaryNode<E> remove(E data, BinaryNode<E> t){
		if(t == null) {
			return t;
		}
		
		int compareResult = t.data.compareTo(data);
		if(compareResult < 0) {
			t.left = remove(data, t.left);
		}else if(compareResult > 0){
			t.right = remove(data, t.right);
		}else if(t.left == null || t.right == null) {
			t = (t.right == null) ? t.left : t.right;
		}else {
			t.data = findMin(t.right).data;
			t.right = remove(t.data, t.right);
		}
		return t;
	}
	
	private void printTree(BinaryNode<E> t) {
		if(t != null) {
			printTree(t.left);
			System.out.print(t.data+", ");
			printTree(t.right);
		}
	}
	
}
