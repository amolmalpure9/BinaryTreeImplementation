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

class Node<T extends Comparable<T>> {

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
}

public class TreeImpl<T extends Comparable<T>> {

	private int length = 0;
	private Node<T> rootNode;
	private Stack<Node> displayStack = new Stack<Node>();
	private Stack<Node> pushedAgainStack = new Stack<Node>();
	private Stack<Node> searchStack = new Stack<Node>();

	public TreeImpl() {
		rootNode = null;
	}

	public int getLength() {
		return length;
	}

	public void add(T data) throws Exception {

		if (null == data) {
			throw new Exception("Null is not a valid data");
		}
		if (rootNode == null) {
			length++;
			rootNode = new Node();
			rootNode.setData(data);
		} else {
			Node<T> newNode = new Node();
			newNode.setData(data);
			length++;
			if (newNode.getData().compareTo(rootNode.getData()) < 0) {
				traverseLeftAndAdd(rootNode, newNode);
			} else {
				traverseRightAndAdd(rootNode, newNode);
			}

		}
	}

	private void traverseLeftAndAdd(Node<T> tempRootNode, Node<T> newNode) {
		if (null != tempRootNode.getLeftNode()) {
			tempRootNode = tempRootNode.getLeftNode();
			if (newNode.getData().compareTo(tempRootNode.getData()) < 0) {
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
			if (newNode.getData().compareTo(tempRootNode.getData()) < 0) {
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

	public T search(T data) {

		return rootNode != null
				? (rootNode.getData().compareTo(data) == 0 ? rootNode.getData() : traverseTreeAndSearch(data, rootNode))
				: null;
	}

	private T traverseTreeAndSearch(T data, Node<T> newRootNode) {
		T nodeToReturn = null;

		Node<T> tempNode = newRootNode.getLeftNode();
		while (tempNode != null) {
			if (tempNode.getData().compareTo(data) == 0) {
				nodeToReturn = tempNode.getData();
				return nodeToReturn;
			}
			searchStack.push(tempNode);
			tempNode = tempNode.getLeftNode();
		}

		do {
			Node<T> nodeEle = searchStack.pop();
			// System.out.print(nodeEle.getData() + " ");
			if (null != nodeEle.getRighNode()) {
				if (null != nodeEle.getRighNode().getData() && nodeEle.getRighNode().getData().compareTo(data) == 0) {
					nodeToReturn = nodeEle.getRighNode().getData();
					return nodeToReturn;
				}
				searchStack.push(nodeEle.getRighNode());
				traverseTreeAndSearch(data, nodeEle.getRighNode());
			}
		} while (searchStack.size() > 0);

		return nodeToReturn;
	}
}
