package base;

import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class AVLTree<E extends Comparable<? super E>> {
//	Constructor
	public AVLTree() {
		makeEmpty();
	}
	
//	Public Basic Information Methods 
	public void makeEmpty() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean contains(E target) {
		return contains(target, root);
	}
	
	public E findMin() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return findMin(root).element;
	}
	public E findMax() throws NoSuchElementException{
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return findMax(root).element;
	}
	
//	Private Member
	private AvlNode<E> root;

	private static class AvlNode<E>{
		E element;
		AvlNode<E> left;
		AvlNode<E> right;
		int height;
		public AvlNode(E d) {
			this(d, null, null);
		}
		public AvlNode(E e, AvlNode<E> lt, AvlNode<E> rt) {
			this.element = e;
			this.left = lt;
			this.right = rt;
			this.height = 0;
		}
	}
	
//	Public Operation Methods
	public void insert(E element) {
		root = insert(element, root);
	}
	
	public void remove(E element) {
		root = remove(element, root);
	}
	
	public void printTree() {
		if(isEmpty()) {
			System.out.print("There is nothing in the tree.");
		}else {
			printTree(root);
		}
	}
	
//	Private Basic Information Methods
	private boolean contains(E target, AvlNode<E> t) {
		return false;
	}
	private AvlNode<E> findMin(AvlNode<E> t) {
		if(t == null) {
			return null;
		}else if(t.left != null){
			return findMin(t.left);
		}else {
			return t;
		}
	}
	private AvlNode<E> findMax(AvlNode<E> t){
		if(t == null) {
			return null;
		}else if(t.right != null){
			return findMax(t.right);
		}else {
			return t;
		}
	}
	
	private int height( AvlNode<E> t ) {
		return t == null ? -1 : t.height;
	}

//	Private Operation Methods
//	t = insert(element, t) means whether element should be inserted into t. 
//	insert() will lead to the judgment of its child when insertion to t fails.
	private AvlNode<E> insert(E element, AvlNode<E> t) {
		if(t == null) {
			return new AvlNode<E>(element);
		}
		int compareResult = t.element.compareTo(element);
		
		if(compareResult < 0) {
			t.right = insert(element, t.right);
		}else if(compareResult > 0)
			t.left = insert(element, t.left);
		else
			;
		return balance(t);
	}
	
	private AvlNode<E> remove(E element, AvlNode<E> t){
		if(t == null) {
			return t;
		}
		
		int compareResult = t.element.compareTo(element);
		if(compareResult < 0) {
			t.left = remove(element, t.left);
		}else if(compareResult > 0){
			t.right = remove(element, t.right);
		}else if(t.left == null || t.right == null) {
			t = (t.right == null) ? t.left : t.right;
		}else {
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		}
		return t;
	}
//	Balance
	private static final int ALLOWED_IMBALANCE = 1;
	
	private AvlNode<E> balance(AvlNode<E> t){
		if(t == null) {
			return t;
		}
		if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if(height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			}else {
				t = doubleWithLeftChild(t);
			}
		}else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if(height(t.right.right) >= height(t.right.left)) {
				t = rotateWithRightChild(t);
			}else {
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
//	Single Rotate
	private AvlNode<E> rotateWithLeftChild(AvlNode<E> k2){
		AvlNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}
	private AvlNode<E> rotateWithRightChild(AvlNode<E> k2){
		AvlNode<E> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}
//	Double Rotate
	private AvlNode<E> doubleWithLeftChild(AvlNode<E> k3){
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
//		AvlNode<E> k2 = k3.left.right;
//		k3.left.right = k2.left;
//		k2.left = k3.left;
//		k3.left = k2.right;
//		k2.right = k3;
//		return k2;
	}
	private AvlNode<E> doubleWithRightChild(AvlNode<E> k3){
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	
//	Traversal
	private void printTree(AvlNode<E> t) {
		if(t != null) {
			printTree(t.left);
			System.out.print(t.element+", ");
			printTree(t.right);
		}
	}
	
//	Iterator
	public Iterator<E> iterator(){
		return new AvlTreeIterator<E>(root);
	}
	
	@SuppressWarnings("hiding")
	private class AvlTreeIterator<E> implements Iterator<E>{
		private Stack<E> stack;

	    public AvlTreeIterator(AvlNode<E> node) {
	      stack = new Stack<>();
	      infixTraversal(node);
	    }

	    private void infixTraversal(AvlNode<E> node) {
	      if (node.right != null) {
	    	  infixTraversal(node.right);
	      }
	      stack.push(node.element);
	      if (node.left != null) {
	    	  infixTraversal(node.left);
	      }
	    }

	    @Override
	    public boolean hasNext() {
	      return !stack.isEmpty();
	    }

	    @Override
	    public E next() {
	      return stack.pop();
	    }
	}
}
