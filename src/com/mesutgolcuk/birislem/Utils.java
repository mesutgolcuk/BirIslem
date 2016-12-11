package com.mesutgolcuk.birislem;

import java.util.Random;

public class Utils {
	/**
	 * Chooses random operator.
	 * @return operator
	 */
	public static String chooseRandomOperator(){
		Random random = new Random();
		int index = random.nextInt(Constants.operators.length);
		return Constants.operators[index];
		
	}
	/**
	 * Sorts nodes according to their score metric with bubble sort
	 * @param nodes
	 * @param scores
	 */
	public static void sort(Node[] nodes,float[]scores){
		 float temp;
		 Node tempNode;
	     int i, j;
	 
	     for (i=1; i<scores.length; i++)
	     {
	         for (j=0; j<scores.length-i; j++)
	         {
	             if(scores[j] > scores[j+1])
	             {
	            	 temp = scores [j];
	                 tempNode = nodes[j];
	                 scores [j] = scores [j+1];
	                 scores [j+1] = temp;
	                 nodes [j] = nodes [j+1];
	                 nodes [j+1] = tempNode;
	             }
	         }
	     }
		
	}
	
}
