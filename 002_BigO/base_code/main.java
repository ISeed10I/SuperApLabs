import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	int findnum;
	public static void main(String args[]) {
		int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		int [] nums = new int[100];
		Stopwatch s = new Stopwatch();
		main obj = new main();
		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for(int i : times){
			System.out.println("Interval: " + i);
			nums = new int[i];
			obj.Randomize(nums);
			s.start();	
			//  Put your method between these two comments
			
			obj.InsertSort(nums,nums.length);
			//obj.Search(nums);
			//
			s.stop();
			System.out.println("Duration: " + Stopwatch.readable(s.read()));
			System.out.println("");
		}
	}

	public  void Randomize(int array[]){
		for(int x = 0; x< array.length;x++){
    int randomNumber = (int)(Math.random() * 200000);
    array[x] = randomNumber;
    if(x==array.length-1){
    	System.out.println("-----"+randomNumber);
    }
		}
	int randomNumber = (int)(Math.random() * 200000);
    findnum = randomNumber;
	}
	
	public  boolean Search(int array[]){
		boolean tof = false;
		for(int x = 0; x< array.length;x++){
		if(array[x]== findnum){
			tof = true;
		}else{
			tof = false;
		}
		
		}
		
							if(tof ==true){
									System.out.println(tof+"Found number{"+findnum+"} within the array");
							}else{
								System.out.println(tof+"Could not find number{"+findnum+"}");

							}
		return tof;

		
		}

	
	
	public  void BubbleSort(int array[],int n){
		int i , j;
		for(i=0;i<n-1;i++){
			for(j=0;j<n-i-1;j++){
				if(array[j]>array[j+1]){
					int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
       
				}
			}
		}
		for(int x = 0; x< array.length;x++){
			//System.out.println(array[x]);
		}
	}
	
	public  void InsertSort(int array[],int n){
		int i,key,j;
		for(i=1;i<n;i++){
			key=array[i];
			j=i-1;
			while(j>=0 && array[j]>key){
				array[j+1]= array[j];
				j=j-1;
			}
			array[j+1]=key;
		}
		
	}
	public  void SelectionSort(int array[],int n){
	int i,j,min;
	for(i=0;i<n-1;i++){
		min = i;
		for(j=i+1;j<n;j++){
			if(array[j]<array[min]){
				min = j;
			int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
			}

		}
	}
		
	}
}

