package pkg;
import java.util.Scanner;
import java.util.Random;


public class BaseClass {
	int findnum;
	public BaseClass() {
		
		
	}
	public void Randomize(int array[]){
		for(int x = 0; x< array.length;x++){
    int randomNumber = (int)(Math.random() * 200000);
    array[x] = randomNumber;
		}
	int randomNumber = (int)(Math.random() * 200000);
    findnum = randomNumber;
	}
	
	public boolean Search(int array[]){
		boolean tof = false;
		for(int x = 0; x< array.length;x++){
		if(array[x]== findnum){
			tof = true;
		}else{
			tof = false;
		}
		
		}
		return tof;
	}
	
	public void BubbleSort(int array[],int n){
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
			System.out.println(array[x]);
		}
	}
	
	public void InsertSort(int array[],int n){
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
	public void SelectionSort(int array[],int n){
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
