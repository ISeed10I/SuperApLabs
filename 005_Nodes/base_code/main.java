import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		/*
			Create an ArrayList of 100 Nodes
			Store random integers in each of them
			Print out all of the values
		*/
		ArrayList<Node> MyNodes = new ArrayList<>();
        System.out.println("Creating array...");
				int cap = 100;
			for(int x = 0; x<cap;x++){
			
			int rando = (int)(Math.random() * 100);	
			Node Sample = new Node(rando);
			MyNodes.add(Sample);
		}
		System.out.println("Creation Done");
		System.out.println("Printing array out...");
		System.out.println("--------------------------");
		for(int x = 0; x<cap;x++){
			System.out.println((x+1)+". "+ MyNodes.get(x).getData());
		}
		System.out.println("--------------------------");
		System.out.println("Printing Array is Done.");

	}
}
