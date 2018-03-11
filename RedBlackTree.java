package sjsu.moon.cs146.project3;


public class RedBlackTree<Key extends Comparable<Key>> 
{	
		RedBlackTree.Node<Key> root;
		RedBlackTree.Node<Key> empty;
		public static class Node<Key extends Comparable<Key>> 
		{ //changed to static 
			  Key key;  		  
			  Node<Key> parent;
			  Node<Key> leftChild;
			  Node<Key> rightChild;
			  int color;
			  public Node()
			  {
				  key = null;
				  leftChild = null;
				  rightChild = null;
				  parent = null;
				  color = 0;
			  }			  
			  public Node(Key data){
				  this.key = data;
				  leftChild = null;
				  rightChild = null;
				  parent = null;
			  }		
			  
			  public int compareTo(Node<Key> n){ 	//this < that  <0
			 		return key.compareTo(n.key);  	//this > that  >0
			  }
			  public int compareTo(Key n)
			  {
				  return key.compareTo(n);
			  }
			  
			  public boolean isLeaf(){
				  if (this.leftChild == null && this.rightChild == null){
					  return true;
				  }
				  return false;
			  }
		}
		 public boolean isLeaf(RedBlackTree.Node<Key> n)
		 {
			  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
			  if (n.equals(root)) return false;
			  if (n.leftChild == null && n.rightChild == null){
				  return true;
			  }
			  return false;
		 } 	
		public interface Visitor<Key extends Comparable<Key>> 
		{
			/**
			This method is called at each node.
			@param n the visited node
			*/
			void visit(Node<Key> n);  
		}
		
		public void visit(Node<Key> n)
		{
			System.out.println(n.key);
		}
			
		public void printTree()
		{  //preorder: visit, go left, go right
			RedBlackTree.Node<Key> currentNode = root;	
			printTree(currentNode);
		}
		
		public void printTree(RedBlackTree.Node<Key> node){
			System.out.print(node.key);
			if (node.isLeaf()){
				return;
			}
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		// place a new node in the RB tree with data the parameter and color it red. 
		public void addNode(Key data)
		{  
			RedBlackTree.Node<Key> temp = root;
			RedBlackTree.Node<Key> tempParent = empty;
			RedBlackTree.Node<Key> insert = new RedBlackTree.Node<Key>(data);
			if (root == null) 
			{
				root = new RedBlackTree.Node<Key>(data);
				root.color = 1;	
				empty = new RedBlackTree.Node("");	//very crucial
				root.parent = empty;
				root.leftChild = empty;
				root.rightChild = empty; 
				empty.color = 1;
			} 
			else
			{
				while (temp != empty) 
				{
					tempParent = temp;
					if (temp.compareTo(data) > 0)
						temp = temp.leftChild;
					else
						temp = temp.rightChild;
				}
				if(tempParent.compareTo(insert) > 0)
					tempParent.leftChild = insert;
				else
					tempParent.rightChild = insert;
				insert.parent = tempParent; 		 
				insert.leftChild = empty;
				insert.rightChild = empty; 
				fixTree(insert);
			}
		}			

		public void insert(Key data){
			addNode(data);	
		}
		
		public RedBlackTree.Node<Key> lookup(Key k)
		{
			RedBlackTree.Node<Key> temp = root;
			while(temp.compareTo(k)!= 0 && temp.compareTo(empty) != 0 )
			{
				if(temp.compareTo(k)>0)
					temp = temp.leftChild;
				else
					temp = temp.rightChild;			
			}
			return temp;
		}
	
		
		public RedBlackTree.Node<Key> getSibling(RedBlackTree.Node<Key> n)
		{
			if(n.parent == empty)
				return empty;
			else
			{
				if(n == n.parent.leftChild)
					return n.parent.rightChild;
				return n.parent.leftChild;
			}
		}
		
		
		public RedBlackTree.Node<Key> getAunt(RedBlackTree.Node<Key> n)
		{
			if(n.parent == empty || getGrandparent(n) == empty)
				return empty;
			else
			{
				if(n.parent == getGrandparent(n).leftChild)
					return getGrandparent(n).rightChild;
				else return getGrandparent(n).leftChild;
			}
		}
		
		public RedBlackTree.Node<Key> getGrandparent(RedBlackTree.Node<Key> n)
		{
			return n.parent.parent;
		}
		
		public void rotateLeft(RedBlackTree.Node<Key> current)
		{
			RedBlackTree.Node<Key> newRightChild = current.rightChild;
			current.rightChild = newRightChild.leftChild;
			if(newRightChild.leftChild != empty)
				newRightChild.leftChild.parent = current;
			newRightChild.parent = current.parent;
			if(current.parent == empty)
				root = newRightChild;
			else if(current == current.parent.leftChild)
				current.parent.leftChild = newRightChild;
			else // current is right child of the parent
				current.parent.rightChild = newRightChild;
			newRightChild.leftChild = current; // current becomes leftChild of the newRightChild
			current.parent = newRightChild;
	    }
		
		
		public void rotateRight(RedBlackTree.Node<Key> current)
		{
			RedBlackTree.Node<Key> newLeftChild = current.leftChild;
			current.leftChild = newLeftChild.rightChild;
			if(newLeftChild.rightChild != empty)
				newLeftChild.rightChild.parent = current;
			newLeftChild.parent = current.parent;
			if(current.parent == empty)
				root = newLeftChild;
			else if(current == current.parent.leftChild)
				current.parent.leftChild = newLeftChild;
			else // current is right child of the parent
				current.parent.rightChild = newLeftChild;
			newLeftChild.rightChild = current; // current becomes rightChild of the newLeftChild
			current.parent = newLeftChild;
		}
		
		public void fixTree(RedBlackTree.Node<Key> current) 
		{
			if(current == root)
			{
				current.color = 1;
			}
			if(current.parent != empty && current.parent.color == 1)
			{
			}
			if(current.parent != empty && getGrandparent(current) != empty)
			{
				// if current node is red and parent node is red
				if(current.color == 0 && current.parent.color == 0)
				{
					// 1. if aunt is either empty or black
					if(getAunt(current) == empty || getAunt(current).color == 1)
					{
						if(current == current.parent.rightChild && current.parent == getGrandparent(current).leftChild)
						{
							rotateLeft(current.parent);
							fixTree(current.parent);
						}
						else if(current == current.parent.leftChild && current.parent == getGrandparent(current).rightChild)
						{
							rotateRight(current.parent);
							fixTree(current.parent);
						}
						else if(current == current.parent.leftChild && current.parent == getGrandparent(current).leftChild)
						{
							current.parent.color = 1; // make current parent's color black
							getGrandparent(current).color = 0; // make grandparent's color red
							rotateRight(getGrandparent(current));
						}
						else if(current == current.parent.rightChild && current.parent == getGrandparent(current).rightChild)
						{
							current.parent.color = 1; // make current parent's color black
							getGrandparent(current).color = 0; // make grandparent's color red
							rotateLeft(getGrandparent(current));
						}
					}
					else if(getAunt(current) != empty || getAunt(current).color == 0)
					{
						current.parent.color = 1;
						getAunt(current).color = 1;
						getGrandparent(current).color = 0;
						fixTree(getGrandparent(current));
					}	
				}
			}
			root.color = 1; 
		}
		
		public boolean isEmpty(RedBlackTree.Node<Key> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		 
		public boolean isLeftChild(RedBlackTree.Node<Key> parent, RedBlackTree.Node<Key> child)
		{
			if (child.compareTo(parent) < 0 ) {//child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit(Visitor<Key> v) {
		   	preOrderVisit(root, v);
		}
		 
		 
		private void preOrderVisit(RedBlackTree.Node<Key> n, Visitor<Key> v) {
		  	if (n == null) {
		  		return;
		  	}
		  	v.visit(n);
		  	preOrderVisit(n.leftChild, v);
		  	preOrderVisit(n.rightChild, v);
		}	
	}
