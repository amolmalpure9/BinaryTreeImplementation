package com.proj.tree;

import com.proj.tree.bean.Person;
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

		System.out.print("Inorder  \t->\t");
		mytree.inorderDisplay();
		System.out.print("Pre-order\t->\t");
		mytree.preorderDisplay();
		System.out.print("Postorder\t->\t");
		mytree.postorderDisplay();
		System.out.println("No of elements in tree\t" + mytree.getLength());
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

		System.out.print("Inorder  \t->\t");
		intTree.inorderDisplay();
		System.out.print("Pre-order\t->\t");
		intTree.preorderDisplay();
		System.out.print("Postorder\t->\t");
		intTree.postorderDisplay();
		System.out.println("No of elements in tree\t" + intTree.getLength());
		TreeImpl<Person> personTree = new TreeImpl<Person>();
		personTree.add(new Person("RootElement", "Nasik"));
		personTree.add(new Person("Left3", "Mumbai"));
		personTree.add(new Person("Left2", "Pune"));
		personTree.add(new Person("Left4", "London"));
		personTree.add(new Person("Rx3", "Harrow"));
		personTree.add(new Person("Rx2", "Milan"));
		personTree.add(new Person("Rx4", "Glasgow"));

		System.out.println("------------------------------------------------------------------------");
		System.out.print("Inorder  \t->\t");
		personTree.inorderDisplay();
		System.out.print("Pre-order\t->\t");
		personTree.preorderDisplay();
		System.out.print("Postorder\t->\t");
		personTree.postorderDisplay();
		System.out.println("No of elements in tree\t" + personTree.getLength());

		Person p = personTree.search(new Person("gfg", "Nasik"));
		System.out.println(p != null ? "Element \"" + p.getName() + "\" is present in the tree" : "Element not found");
	}
}
