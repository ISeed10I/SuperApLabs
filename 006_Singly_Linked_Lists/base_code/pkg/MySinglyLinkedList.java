package pkg;
import java.util.Scanner;
import java.util.Random;


public class MySinglyLinkedList {
	Node head;

	/* 
		Postcondition: The head will be null 
	*/
	public MySinglyLinkedList() {
	
	}

	/* 
		Receives an integer position, searches through the SinglyLinkedList for the position and returns the data at that positon
	   	If the position doesn't exist, it returns -1
	*/ 
	public int get(int pos){
		Node Current = head;
		int index = 0;
		
		while(Current != null ){
			if(index == pos){
				return Current.getData();
			}
			Current = Current.next;
			index++;
		}
		return -2;

	}

	/*
		Insert a new Node at the given position with the data given
	*/
	public void insert(int pos, int data){
    	Node Current = head;
	    int index = 0;
    	Node Sample = new Node(data);

		 if (pos == 0) {
        	Sample.setNext(head);
    		head = Sample;
        	return;
    	}
		pos = pos - 1;
    	while(Current != null ){
        	if(index == pos ){
            	if(Current.getNext() != null){
                	Sample.setNext(Current.getNext());
                	Current.setNext(Sample);
            	} else {
                	Current.setNext(Sample);
            	}
            	return;
        	}
        	Current = Current.next;
        	index++;
    }
}

	/*
		Remove the node at the given position
		If no position exists, don't change the list
	*/
	public void remove(int pos){
		Node Current = head;
		int index = 0;
		if(pos ==  0 ){
			head = head.getNext();
			return;
		}
		
		while(Current != null ){
			if(index == pos - 1){
				if(Current.getNext() != null){
					Current.setNext(Current.getNext().getNext());
				}else{
				Current.setNext(null);
				}
				
			}
			Current = Current.next;
			index++;
		}
	}

	/*
		Print all data values in the LinkedList 
	*/
	public void printList(){
		Node Current = head;
		int index = 0;
		while(Current != null ){
			System.out.println((index+ 1)+".{ "+Current.getData()+" }");
			Current = Current.next;
			index++;
		}
	

	}
}
