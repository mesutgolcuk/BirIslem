package com.mesutgolcuk.birislem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tree {
	Node root; // root of tree
	List<String> numberList; // numbers are in the tree as list
	Random random;
	int operatorCount; // how many operator left 
	String []numbers; // numbers as array
	
	public Tree(String []numbers) {
		super();
		this.numbers = numbers;
	}
	
	/**
	 * produces a random tree
	 * @return root of tree
	 */
	public Node produceTree(){
		operatorCount = 4;
		/**
		 * produce a new random tree until it is a tree with 5 operator
		 */
		while( operatorCount != 0){
			random = new Random();
			operatorCount = 4;
			// to shuffle convert it to list format
			numberList = new ArrayList<>(Arrays.asList(numbers));
			Collections.shuffle(numberList);
			// create a operator root
			root = new Node(Utils.chooseRandomOperator());
			// set random left child
			root.setLeft(getRandomNode());
			// if left child isn't operator make right child operator.
			if(!root.getLeft().isOperator()){
				root.setRight(new Node(Utils.chooseRandomOperator()));
				operatorCount--;
			}
			else{
				root.setRight(getRandomNode());
			}
			// after this point build a tree recursively without concerning it is operator or not
			// if it doesn't have all numbers in it then try to create new one in while loop
			buildTree(root);
		}
		return root;

	}
	/**
	 * for given root with childs builds a tree with numbers and given operators
	 * @param parent
	 */
	public void buildTree(Node parent){
		if(parent != null){
			Node current = parent.getLeft();
			// if left child operator set random children then call itselft again
			if(current.isOperator()){
				current.setLeft(getRandomNode());
				current.setRight(getRandomNode());
				buildTree(current);
			}
			current = parent.getRight();
			// if right also operator set random children then call itself again
			if(current.isOperator()){
				current.setLeft(getRandomNode());
				current.setRight(getRandomNode());
				buildTree(current);
			}
		}
	}
	/**
	 * gets random node 
	 * @return
	 */
	public Node getRandomNode(){
		Node node;
		// if we still need an operator and random is operator then node's value is operator
		if( random.nextBoolean() && operatorCount != 0 ){
			node = new Node(Utils.chooseRandomOperator());
			operatorCount--;
		}
		// otherwise it is number
		else{
			node = new Node(chooseRandomNumber());
		}
		return node;
	}
	/**
	 * choose number from shuffled list
	 * @return number
	 */
	public String chooseRandomNumber(){
		String str = numberList.get(0);
		numberList.remove(0);
		return str;
	}
	/**
	 * for a given root finds all numbers below that node
	 * @param node
	 */
	public void findSubNumbers(Node node){
		Node current = node.getLeft();
		if(current.isOperator()){
			findSubNumbers(current);
		}
		else{
			numberList.add(current.getValue());
		}
		current = node.getRight();
		if(current.isOperator()){
			findSubNumbers(current);
		}
		else{
			numberList.add(current.getValue());
		}
		
	}
	/**
	 * produce a subtree
	 * @param node
	 * @return
	 */
	public Node produceSubTree(Node node){
		numberList.clear();
		findSubNumbers(node);
		ArrayList<String> al = new ArrayList<>();
		// gets all numbers below node
		al.addAll(numberList);
		// init operator count according to number of numbers
		int count = numberList.size()-2;
		operatorCount = count;
		while( operatorCount != 0){
			random = new Random();
			numberList = new ArrayList<>();
			numberList.addAll(al);
			Collections.shuffle(numberList);
			operatorCount = count;
			
			node = new Node(Utils.chooseRandomOperator());
			node.setLeft(getRandomNode());
			node.setRight(getRandomNode());
			
			buildTree(node);
			
		}
		return node;
		
	}
	
}
