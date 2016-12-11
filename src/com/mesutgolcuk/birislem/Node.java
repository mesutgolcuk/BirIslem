package com.mesutgolcuk.birislem;

public class Node {
	String value; // value of node operator or number
	Node left; // left child
	Node right; // right child
	boolean isOperator; // is is operator or number
	
	public Node(String value) {
		super();
		this.value = value;
		isOperator = isItOperator();
		left = null;
		right = null;
	}
	
	public Node(String value, Node left, Node right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * is it operator o node
	 * @return
	 */
	private boolean isItOperator() {
		if( value.equalsIgnoreCase("*") 
				|| value.equalsIgnoreCase("/") 
				|| value.equalsIgnoreCase("+")
				|| value.equalsIgnoreCase("-")
				){
			return true;
		}
		else{
			return false;
		}
		
	}


	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public boolean isOperator() {
		return isOperator;
	}


	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}

	
}
