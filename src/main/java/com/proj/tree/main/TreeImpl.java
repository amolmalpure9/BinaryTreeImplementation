/*
 * ====================================================================
 * 
 * Author: Amol Malpure
 * Date: 07 Jun 20
 * 
 * About this implementation:
 *  
 * 1. Creating binary tree
 * 2. Diplay element in in-order traversal 
 * 3. Display element in pre-order traversal 
 * 4. Display element in post-order traversals  
 * 
 * ====================================================================
 * 
 * */

package com.proj.tree.main;

import java.util.Stack;

class Node<T> implements Comparable<Node> {

	private Node<T> leftNode;
	private Node<T> righNode;
	private T data;

	Node() {
		leftNode = null;
		righNode = null;
		data = null;
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNdoe) {
		this.leftNode = leftNdoe;
	}

	public Node<T> getRighNode() {
		return righNode;
	}

	public void setRighNode(Node<T> righNode) {
		this.righNode = righNode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int compareTo(Node o) {
		return this.getData().toString().compareTo(o.getData().toString());
	}

}

public class TreeImpl<T> {

	private Node<T> rootNode;
	private Stack<Node> displayStack = new Stack<Node>();
	private Stack<Node> pushedAgainStack = new Stack<Node>();

	public TreeImpl() {
		rootNode = null;
	}

	public void add(T data) throws Exception {

		if (null == data) {
			throw new Exception("Null is not a valid data");
		}
		if (rootNode == null) {
			rootNode = new Node();
			rootNode.setData(data);
		} else {
			Node<T> newNode = new Node();
			newNode.setData(data);
			if (newNode.compareTo(rootNode) < 0) {
				traverseLeftAndAdd(rootNode, newNode);
			} else {
				traverseRightAndAdd(rootNode, newNode);
			}

		}
	}

	private void traverseLeftAndAdd(Node<T> tempRootNode, Node<T> newNode) {
		if (null != tempRootNode.getLeftNode()) {
			tempRootNode = tempRootNode.getLeftNode();
			if (newNode.compareTo(tempRootNode) < 0) {
				traverseLeftAndAdd(tempRootNode, newNode);
			} else {
				traverseRightAndAdd(tempRootNode, newNode);
			}
		} else {
			tempRootNode.setLeftNode(newNode);
		}
	}

	private void traverseRightAndAdd(Node<T> tempRootNode, Node<T> newNode) {
		if (null != tempRootNode.getRighNode()) {
			tempRootNode = tempRootNode.getRighNode();
			if (newNode.compareTo(tempRootNode) < 0) {
				traverseLeftAndAdd(tempRootNode, newNode);
			} else {
				traverseRightAndAdd(tempRootNode, newNode);
			}
		} else {
			tempRootNode.setRighNode(newNode);
		}
	}

	public void inorderDisplay() {
		if (rootNode != null) {
			displayStack.push(rootNode);
			traverseInorderAndDisplay(rootNode);
		} else {
			System.err.println("There are no elements");
		}
		System.out.println();
	}

	public void preorderDisplay() {
		if (rootNode != null) {
			System.out.print(rootNode.getData() + " ");
			displayStack.push(rootNode);
			traversePreorderDisplay(rootNode);
		} else {
			System.err.println("There are no elements");
		}
		System.out.println();
	}

	public void postorderDisplay() {
		if (rootNode != null) {
			// System.out.print(rootNode.getData() + " ");
			displayStack.push(rootNode);
			traversePostorderAndDisplay(rootNode);
		} else {
			System.err.println("There are no elements");
		}
		System.out.println();

	}

	private void traversePreorderDisplay(Node<T> newRoot) {

		Node<T> tempNode = newRoot.getLeftNode();
		while (tempNode != null) {
			System.out.print(tempNode.getData() + " ");
			displayStack.push(tempNode);
			tempNode = tempNode.getLeftNode();
		}

		do {
			Node<T> nodeEle = displayStack.pop();
			// System.out.println(nodeEle.getData());
			if (null != nodeEle.getRighNode()) {
				System.out.print(nodeEle.getRighNode().getData() + " ");
				displayStack.push(nodeEle.getRighNode());
				traversePreorderDisplay(nodeEle.getRighNode());
			}
		} while (!displayStack.isEmpty());
	}

	private void traverseInorderAndDisplay(Node<T> rootNode) {
		Node<T> tempNode = rootNode.getLeftNode();
		while (tempNode != null) {
			displayStack.push(tempNode);
			tempNode = tempNode.getLeftNode();
		}

		do {
			Node<T> nodeEle = displayStack.pop();
			System.out.print(nodeEle.getData() + " ");
			if (null != nodeEle.getRighNode()) {
				displayStack.push(nodeEle.getRighNode());
				traverseInorderAndDisplay(nodeEle.getRighNode());
			}
		} while (displayStack.size() > 0);
	}

	private void traversePostorderAndDisplay(Node<T> rootNode) {
		Node<T> tempNode = rootNode.getLeftNode();
		while (tempNode != null) {
			displayStack.push(tempNode);
			tempNode = tempNode.getLeftNode();
		}

		do {
			Node<T> nodeEle = displayStack.pop();
			// System.out.print(nodeEle.getData() + " ");
			if (null != nodeEle.getRighNode()) {

				if (pushedAgainStack.size() > 0 && pushedAgainStack.contains(nodeEle)) {
					// displayStack.pop();
					pushedAgainStack.pop();
					System.out.print(nodeEle.getData() + " ");
				} else {
					// push the same node again
					displayStack.push(nodeEle);
					pushedAgainStack.push(nodeEle);
					displayStack.push(nodeEle.getRighNode());
					traversePostorderAndDisplay(nodeEle.getRighNode());
				}
			} else {
				System.out.print(nodeEle.getData() + " ");
			}
		} while (displayStack.size() > 0);
	}
}
