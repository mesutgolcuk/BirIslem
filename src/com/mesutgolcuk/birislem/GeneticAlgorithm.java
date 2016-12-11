package com.mesutgolcuk.birislem;

import java.util.Random;
/**
 * Genetic Algorithm to find a solution.
 * @author mesut gölcük
 *
 */
public class GeneticAlgorithm {
	
	private final static int population = 35; // population size
	private final static int epoch = 1000000; // iteration
	private final static int mutation_rate = 5; // mutation % chance rate

	private String []numbers; // the numbers that require to produce result
	private int result; // target
	Random random; // random
	Tree tree; 
	Node resultRoot; // root of solution tree
	Node []roots; // roots of population
	float []survivals; // survival scores of population
	 
	public GeneticAlgorithm(String[] numbers, int result) {
		this.numbers = numbers;
		this.result = result;
		random = new Random();

	}
	
	/**
	 * Static method for get a solution from specific numbers and result
	 * @param numbers
	 * @param result
	 * @return
	 */
	public static String produceResult(String numbers[],int result){
		GeneticAlgorithm ga = new GeneticAlgorithm(numbers, result);
		long start = System.currentTimeMillis();
		String s = ga.getSolution();
		long stop = System.currentTimeMillis();
		System.out.println("Found in "+(stop-start)+" ms");
		if(s!=null)
			return s;
		else
			return "Sonuç Bulunamadý";
	}
	
	/**
	 * find a solution 
	 * @return solution if there is no then return null
	 */
	public String getSolution(){
		
		tree = new Tree(numbers);
		
		roots = new Node[population];
		survivals = new float[population];
		
		// create random population
		for(int i=0;i<population;i++){
			roots[i] = tree.produceTree();
			survivals[i] = fitness(roots[i]);
		}
		
		int i=0;
		// produce new generations until reach to he solution or
		// 						   until reach to max iteration
		while( !isFound(roots,survivals) && i<epoch ){
			// sort population according to their fitness
			Utils.sort(roots, survivals);
			
//			for(int j=0;j<population;j++){
//				
//				System.out.println(print(roots[j]));
//				System.out.println(fitness(roots[j]));
//				System.out.println("--------------");
//			}
			
			// give opportunity to best %50 of population to crossover with each other
			for(int j=0;j<roots.length/2;j+=2){
				if(random.nextBoolean())
					crossover(roots[j],roots[j+1]);
			}
			// give opportunity to mutate all population
			for(int j=0;j<roots.length;j++){
				roots[j] = mutation(roots[j]);
			}
			// calculate new fitnesses
			for(int j=0;j<population;j++){
				survivals[j] = fitness(roots[j]);
			}
			
			System.out.println("Generation: "+i);
			// new generation
			i++;
		}
		// if solution found
		if( i<epoch){
			System.out.println(print(resultRoot));
			System.out.println(score(resultRoot));
			System.out.println("Found in Generation: "+(i-1));
		}
		else{
			System.out.println("Bulamadý");
		}

		return print(resultRoot);
		
	}

	/**
	 * is solution in the population if it is then put it to resultRoot
	 * @param roots population roots
	 * @param survivals fitness scores
	 * @return it is on population or not
	 */
	private boolean isFound(Node[] roots,float[]survivals) {
		for(int i=0;i<survivals.length;i++){
			if( survivals[i] == 0){
				resultRoot = roots[i];
				return true;
			}
		}
		return false;
	}
	
	/**
	 * find result of a tree
	 * @param node root of tree
	 * @return result
	 */
	public float score(Node node){
		
		if( node.isOperator() ){
			switch(node.getValue()){
				case "+":
					return score(node.left)+score(node.right);
				case "-":
					return score(node.left)-score(node.right);
				case "*":
					return score(node.left)*score(node.right);
				case "/":
					return score(node.left)/score(node.right);
				default:
					System.out.println("Default");
					return -1;
			}
		}
		else{
			return Float.parseFloat(node.value);
		}
		
	}
	/**
	 * closeness of a member of population to result
	 * @param node
	 * @return
	 */
	public float fitness(Node node){	
		return Math.abs(result-score(node));
	}
	/**
	 * Crossovers roots and their 1 2nd level operator
	 * @param node1
	 * @param node2
	 */
	public void crossover(Node node1, Node node2){
		String tmp = node1.getValue();
		node1.setValue(node2.getValue());
		node2.setValue(tmp);
		
		Node current = node1.getLeft();
		if( current.isOperator() ){
			Node current2 = node2.getLeft();
			if( current2.isOperator()){
				tmp = current.getValue();
				current.setValue(current2.getValue());
				current2.setValue(tmp);
			}
			else{
				current2 = node2.getRight();
				tmp = current.getValue();
				current.setValue(current2.getValue());
				current2.setValue(tmp);
			}
		}
		else{
			current = node1.getRight();
			Node current2 = node2.getLeft();
			if( current2.isOperator()){
				tmp = current.getValue();
				current.setValue(current2.getValue());
				current2.setValue(tmp);
			}
			else{
				current2 = node2.getRight();
				tmp = current.getValue();
				current.setValue(current2.getValue());
				current2.setValue(tmp);
			}
		}
	}
	/**
	 * mutates one point of a tree then create a new subtree for that.
	 * @param node
	 * @return mutated root of tree
	 */
	public Node mutation( Node node ){
		if( node != null ){
			if( node.isOperator() ){
				int rate = random.nextInt(100);
				if( rate < mutation_rate ){
					node = tree.produceSubTree(node);
				}
			}
			mutation(node.getLeft());
			mutation(node.getRight());
		}
		return node;
	}
	
	/**
	 * generates string for tree calculations
	 * @param node
	 * @return
	 */
	public String print(Node node){
		if( node != null){
			if( node.isOperator() ){
				return "("+print(node.getLeft())+node.value+print(node.getRight())+")";
			}
			else{
				
				return node.getValue();
				
			}
		}
		else{
			return null;
		}

	}


}
