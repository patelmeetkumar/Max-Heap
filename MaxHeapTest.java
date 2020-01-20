/*
 * Name: Meetkumar Patel
 * CS 2400 Fall 2018 Project 4
 */



import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;

public class MaxHeapTest 
{
	public static void main(String[] args) 
	{
		final int MAX_RANGE = 250;
		final int NUMBERS_NEEDED = 100;
		final int SET_NUMBER = 20;
		int option;
		Scanner input = new Scanner(System.in);
		
		System.out.println("CS 2400 Fall 2018 Java Project 4 \n");
		
		System.out.println("Please select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100");
		System.out.print("Enter choice: ");
		option = input.nextInt();
		System.out.println();
		
		switch (option)
		{
			case 1:
				// 20 sets of 100 randomly generated integers each
				Random rand = new Random();
				@SuppressWarnings("unchecked") 
				MaxHeap<Integer>[] randomHeap = new MaxHeap[SET_NUMBER];
				int[] insertionSwaps = new int[SET_NUMBER];
				int[] smartSwaps = new int[SET_NUMBER];
				
				for (int i = 0; i < SET_NUMBER; i++)
				{
					Set <Integer> set = new LinkedHashSet<Integer>();
					while (set.size() < NUMBERS_NEEDED)
					{
					    Integer next = rand.nextInt(MAX_RANGE) + 1;
					    set.add(next);
					} // end while
					
					/*
					System.out.println("Random Set " + (i + 1));
					System.out.println("The random set contains the following:");
					for(int value : set) 
					{
						System.out.print(value + " ");
					} // end for
					System.out.println();
					System.out.println();
					*/
					
					randomHeap[i] = new MaxHeap<>(set.size());
					
					//System.out.println("Heap built using sequential insertions: ");
					sequentialAdd(randomHeap[i], set);		
					//displayHeap(randomHeaps[i]);
					insertionSwaps[i] = randomHeap[i].getInsertionSwap();
					//System.out.println("Number of swaps: " + insertionSwaps[i]);
					
					//System.out.println("Heap built using smart method: ");
					randomHeap[i] = smartAdd(set);
					//displayHeap(randomHeaps[i]);
					smartSwaps[i] = randomHeap[i].getSmartSwap();
					//System.out.println("Number of swaps: " + optimalSwaps[i]);
						
				} //end for
				
				System.out.println("Average swaps for series of insertions: " + findAverage(insertionSwaps));
				System.out.println("Average swaps for optimal method: " + findAverage(smartSwaps));
				
				break;
				
			case 2:
				// fixed integer values 1-100
				Set<Integer> set = new LinkedHashSet<Integer>();
				for(int i = 1; i <= NUMBERS_NEEDED; i++) 
				{
					set.add(i);
				} // end for
				
				/*
				System.out.println("The fixed set contains the following:");
				for(int value : set) 
				{
					System.out.print(value + " ");
				} // end for
				System.out.println();
				System.out.println();
				*/
				
				MaxHeap<Integer> fixedHeap = new MaxHeap<>(set.size());	
				
				System.out.print("Heap built using series of insertions: ");			
				sequentialAdd(fixedHeap, set);		
				//displayHeap(fixedHeap);
				displayHeap10(fixedHeap);
				
				int insertionSwap = fixedHeap.getInsertionSwap();
				System.out.println("Number of swaps: " + insertionSwap);
				
				for(int i = 0; i < 10; i++) 
				{
					fixedHeap.removeMax();
				} // end for
				System.out.print("Heap after 10 removals: ");
				//displayHeap(fixedHeap);
				displayHeap10(fixedHeap);
				System.out.println();
						
				
				System.out.print("Heap built using optimal method: ");
				fixedHeap = smartAdd(set);
				//displayHeap(fixedHeap);
				displayHeap10(fixedHeap);
				
				int optimalSwap = fixedHeap.getSmartSwap();
				System.out.println("Number of swaps: " + optimalSwap);
				
				for(int i = 0; i < 10; i++) 
				{
					fixedHeap.removeMax();
				} // end for
				System.out.print("Heap after 10 removals: ");
				//displayHeap(fixedHeap);
				displayHeap10(fixedHeap);
				
				break;
				
			default:
				System.out.println("Invalid request! Please enter 1 or 2.");
				
				break;
				
		} //end switch
		
		input.close();		
		
	} //end main

	
	private static void sequentialAdd(MaxHeap<Integer> aHeap, Set<Integer> set)
	{
		Object[] content = set.toArray();
		
		/*
		for(Object value : content) 
		{
			System.out.print(value + " ");
		} // end for
		*/
		
		for (int index = 0; index < content.length; index++) 
		{
			aHeap.add((Integer)content[index]);
		} // end for
		
	} // end sequentialAdd
	
	private static MaxHeap<Integer> smartAdd(Set<Integer> set)
	{
		Integer[] content = new Integer[set.size()];
		int i = 0;
		for(Integer val: set) 
		{
			content[i++] = val;
		} // end for
		
		/*
		for(Integer value : content) 
		{
			System.out.print(value + " ");
		} // end for
		*/
		
		return new MaxHeap<>(content);
	} // end smartAdd

/*	
	private static void displayHeap(MaxHeap<Integer> aHeap)
	{	
		Comparable<Integer>[] heapArray = aHeap.toArray();
		for (int index = 0; index < heapArray.length; index++) 
		{
			System.out.print(heapArray[index] + " ");
		} // end for
		System.out.println();
		
	} // end displayHeap
*/	
	
	private static void displayHeap10(MaxHeap<Integer> aHeap)
	{	
		Comparable<Integer>[] heapArray = aHeap.toArray();
		int length = heapArray.length;
		if(heapArray.length > 10) 
		{
			length = 10;
		} // end if
			
		for (int index = 0; index < length; index++) 
		{
			System.out.print(heapArray[index] + ",");
		} // end for
		System.out.println("...");
		
	} // end displayHeap10
	
	private static int findAverage(int[] data) 
	{

		int sum = 0;
		for (int i = 0; i < data.length; i++) 
		{
			sum += data[i];
		} // end for
		return (sum / data.length);
		
	} // end findAverage
	
	
	
}//end MaxHeapTest
