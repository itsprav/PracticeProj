/**
 * Copyright 2009 - Nitman Software Pvt. Ltd. All Rights Reserved. 
 * This software is the proprietary information of Nitman Software Pvt. Ltd. 
 * Use is subject to license terms.
 */
package com.practice.datasructures.tress;

import java.util.ArrayList;
import java.util.List;

/**
 * This was a solution for online test question of celigo
 * 
 * @author PraveenK
 * @since  Jun 19, 2012
 */
public class TraverseNonBinaryTree {
	
	public static void main(String[] args) {
		TraverseNonBinaryTree t = new TraverseNonBinaryTree();
		Node node = t.createTestTree();
		List<String> lst = t.traverseTreeReturnListContainingAllNodeLabels(node);
		for (String str : lst) {
			System.out.println(str);
		}
	}
	
	/**
	 * Creates a sample Tree, used to test the Tree Traversal
	 * @return
	 */
	private Node createTestTree(){
		Node node = new Node("1");
		node.addChild(new Node("2").addChild(new Node("5").addChild(new Node("8"))));
		node.addChild(new Node("3"));
		node.addChild(new Node("4").addChild(new Node("6")).addChild(new Node("7").addChild(new Node("9"))));
		return node;
	}
	
	/**
	 * Traverses the full tree recursively
	 * @param n
	 * @return list of all nodes of tree
	 */
	public List<Node> traverseTreeReturnListContainingAllNodes(Node root){
		List<Node> nodesLst = new ArrayList<>();
		traverseTreeReturnListContainingAllNodes(root, nodesLst);
		return nodesLst;		
	}
	
	/**
	 * Recursive function that adds all nodes into <code>nodesLst</code> 
	 * @param n
	 * @param nodesLst
	 * @return list of all nodes of under <param>n</param>
	 */
	public List<Node> traverseTreeReturnListContainingAllNodes(Node n, List<Node> nodesLst){
		if(n!=null){
			nodesLst.add(n);
			if(!n.isLeafNode()){
				for (Node node : n.getChildren()) {
					traverseTreeReturnListContainingAllNodes(node, nodesLst);
				}
			}
		}
		return nodesLst;		
	}
	
	/**
	 * Traverses the full tree recursively
	 * @param n
	 * @return list of all labels of tree with root node as root
	 */
	public List<String> traverseTreeReturnListContainingAllNodeLabels(Node root){
		List<String> nodesLst = new ArrayList<>();
		traverseTreeReturnListContainingAllNodeLabels(root, nodesLst);
		return nodesLst;		
	}
	
	/**
	 * Recursive function that adds all nodes into <code>nodesLst</code> 
	 * @param n
	 * @param nodesLst
	 * @return list of all labels under node <param>n</param>
	 */
	public List<String> traverseTreeReturnListContainingAllNodeLabels(Node n, List<String> nodesLst){
		if(n!=null){
			nodesLst.add(n.getLabel());
			if(!n.isLeafNode()){
				for (Node node : n.getChildren()) {
					traverseTreeReturnListContainingAllNodeLabels(node, nodesLst);
				}
			}
		}
		return nodesLst;		
	}
	

	class Node {
		private String label;
		private List<Node> children;
		
		public Node(String label, List<Node> children){
			this.label=label;
			this.children=children;
		}
		
		public Node(String label){
			this.label=label;
			this.children=new ArrayList<>();
		}
		
		public Node addChild(Node node){
			getChildren().add(node);
			return this;
		}
		
		public boolean isLeafNode(){
			return this.children.isEmpty();
		}
		
		/**
		 * @return the lebel
		 */
		public String getLabel() {
			return label;
		}
		/**
		 * @param lebel the lebel to set
		 */
		public void setLabel(String lebel) {
			this.label = lebel;
		}
		/**
		 * @return the children
		 */
		public List<Node> getChildren() {
			return children;
		}
		/**
		 * @param children the children to set
		 */
		public void setChildren(List<Node> children) {
			this.children = children;
		}
	}

}
