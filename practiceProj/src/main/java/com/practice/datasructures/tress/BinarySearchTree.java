package com.practice.datasructures.tress;
public class BinarySearchTree implements Tree
{
	  
	 private Node root;
	  
		 public void add(int currentData){
		  if(root == null){
		   root = new Node();
		   root.data = currentData;
		   return;
		  }
		  add(currentData, root);
		 }
	  
		 private void add(int currentData, Node position)
		 {
			  if(currentData<position.data)
			  {
				   if(position.left==null){
				    position.left = new Node();
				    position.left.data = currentData;
				    position.left.parent = position;
				    return;
				   }
				   add(currentData, position.left);
			 }
			  else
			  {
				   if(position.right==null){
				    position.right = new Node();
				    position.right.data = currentData;
				    position.right.parent = position;
				    return;
				   }
				   add(currentData, position.right);
			  }
		 }
		  
		 public Node search(int searchData){
		  if(root == null){
		   return null;
		  }
		  return search(searchData, root);
		 }
	  
	 /*
  * O(log n) on average case
  */
	 private Node search(int searchData, Node node){
	  if(node.data == searchData){
	   return node;
	  }
	  if(searchData < node.data){
	   return search(searchData, node.left);
	  }else{
	   return search(searchData, node.right);
	  }
	 }
  
	 public void printOrdered(){
	  if(root == null){
	   return;
	  }
	  printOrdered(root);
	 }
  
 //DO A IN ORDER TRAVERSAL
 //VISIT LEFT
 //VISIT ROOT
 //VISIT RIGHT
	 public void printOrdered(Node node){
	  if(node.left != null){
	   printOrdered(node.left);
	  }
	  System.out.println(node.data);
	  if(node.right!=null){
	   printOrdered(node.right);
	  }
	 }
  
	 public void printValues(){
	  print(root);
	 }
  
	 private void print(Node node){
	  if(node == null){
	   return;
	  }else{
	   print(node.left);
	   print(node.right);
	  }
	 }
  
	 public static void main(String args[])
	 {
		  BinarySearchTree bTree = new BinarySearchTree();
		  
		  for(int i=0;i<10;i++)
		  {
			   int t = (int)(Math.random()*20);
			   System.out.println(t);
			   bTree.add(t);
		  }
		  
		  bTree.printValues();
		  
		  for(int i=0;i<10;i++)
		  {
		   int t = (int)(Math.random()*20);
		   System.out.println("For i="+t+": "+bTree.search(t));
		  }
		  System.out.println();
		  bTree.printOrdered();
	 }
}

