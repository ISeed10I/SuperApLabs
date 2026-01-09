package pkg;
import java.util.Scanner;
import java.util.Random;


public class Stack {
	/*  LAST IN FIRST OUT  */
	Node top;

	/* 
		Postcondition: The top will be null.
	*/
	public Stack() {
	
	}

	/*
		Insert a new Node on top of the stack
	*/
	public void push(int data){
		Node Current = top;
		while(true){
			if(Current.getNext()!= Null){
				Current = Current.getNext();
			}else{
				Current.setNext(data);
				break;
			}
		}
	}

	/*
		Removes the top node of the stack
	*/
	public int pop(){
		Node Current = top;
		while(true){
			if(Current.getNext()!= Null){
				Current = Current.getNext();
			}else{
				Current.setNext(Null);
				break;
			}
		}
	}

	}

	/*
		Returns the top value of the stack. Doesn't pop. 
	*/
	public int peek(){
		Node Current = top;
		while(true){
			if(Current.getNext()!= Null){
				Current = Current.getNext();
			}else{
				return Current.getNext().getData();
			}
		}
	}

	/*
		Checks if the stack is empty. 
	*/
	public boolean isEmpty(){
	if (top.getNext().getData() == Null){
		return true;
	}else{
		return false;
	}
	}
}
