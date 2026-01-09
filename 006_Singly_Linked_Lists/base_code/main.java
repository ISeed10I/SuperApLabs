import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
	    MySinglyLinkedList List  = new MySinglyLinkedList();
    	for(int x = 0; x<20;x++){
			
			int rando = (int)(Math.random() * 100);
			List.insert(x,rando);
		}
		  for(int x = 0; x<20;x++){
			
			int rando = (int)(Math.random() * 20);	
			List.insert(rando,-1);
		}
		
		System.out.println("/////////////Normal Print/////////////////////");
		List.printList();
		System.out.println("/////////////Backwards Print//////////////////");
		
		int last = 0;
		while (List.get(last+ 1) != -2) {
	    last++;
		}

		int count = 0;
		while (count < last) {
    	int val = List.get(last);   
    	List.remove(last);         
    	List.insert(count, val);
    	count++;
    	  
		}
		List.printList();

	
}
}
