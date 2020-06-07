package com.proj.tree;

import com.proj.tree.main.TreeImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {

		TreeImpl<String> mytree = new TreeImpl<String>();
		mytree.add("RootElement");
		mytree.add("Left3");
		mytree.add("Left2");
		mytree.add("Left4");
		mytree.add("Rx3");
		mytree.add("Rx2");
		mytree.add("Rx4");

		System.out.println("=======================String INORDER TRAVERSAL======================");
		mytree.inorderDisplay();
		System.out.println("=======================String PRE-ORDER TRAVERSAL======================");
		mytree.preorderDisplay();
		System.out.println("=======================String POST-ORDER TRAVERSAL======================");
		mytree.postorderDisplay();

		System.out.println("------------------------------------------------------------------------");

		TreeImpl<Integer> intTree = new TreeImpl<Integer>();
		intTree.add(30);
		intTree.add(25);
		intTree.add(23);
		intTree.add(28);
		intTree.add(26);
		intTree.add(27);
		intTree.add(29);
		intTree.add(35);
		intTree.add(32);
		intTree.add(38);

		System.out.println("=======================INORDER TRAVERSAL======================");
		intTree.inorderDisplay();
		System.out.println("=======================PRE-ORDER TRAVERSAL======================");
		intTree.preorderDisplay();
		System.out.println("=======================POSTORDER TRAVERSAL======================");
		intTree.postorderDisplay();

	}
}
