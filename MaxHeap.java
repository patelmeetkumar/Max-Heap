
/*
 * Name: Meetkumar Patel
 * CS 2400 Fall 2018 Project 4
 */

import java.util.Arrays;

/*
 *  Implementation of the ADT maxheap by using an array.
*/

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> 
{
	private T[] heap; // Array of heap entries; ignore heap[0]
	private int lastIndex; // Index of last entry and number of entries
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	private int insertionSwap;
	private int smartSwap;

	public MaxHeap() 
	{
		this(DEFAULT_CAPACITY); // Call following constructor
		
	} // end default constructor

	public MaxHeap(int initialCapacity) 
	{
		if (initialCapacity < DEFAULT_CAPACITY) 
		{
			initialCapacity = DEFAULT_CAPACITY;
		} // end if
		else 
		{
			checkCapacity(initialCapacity);
		} // end else

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1]; // index begins at 1
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
		insertionSwap = 0;
		smartSwap = 0;
		
	} // end constructor

	public MaxHeap(T[] entries) 
	{
		this(entries.length); // Call previous constructor
		lastIndex = entries.length;
		assert initialized = true;

		// Copy given array to data field
		for (int index = 0; index < entries.length; index++) 
		{
			heap[index + 1] = entries[index];
		} // end for

		// Create heap
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--) 
		{
			reheap(rootIndex);
		} // end for
		
	} // end constructor
	
	// Throws an exception if this object is not initialized.
	private void checkInitialization() 
	{
		if (!initialized) 
		{
			throw new SecurityException("MaxHeap object is not initialized properly.");
		} // end if
			
	} // end checkInitialization

	// Throws an exception if the client requests a capacity that is beyond capable.
	private void checkCapacity(int capacity) 
	{
		if (capacity > MAX_CAPACITY) 
		{
			throw new IllegalStateException("Attempt to create a maxheap " + 
											"whose capacity exceeds permitted maximum.");
		} // end if
			
	} // end checkCapacity

	public void add(T newEntry) 
	{
		checkInitialization(); // Ensure initialization of data fields
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0) // swapping occurs
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			insertionSwap++;
		} // end while

		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
		
	} // end add

	public T removeMax() 
	{
		checkInitialization(); // Ensure initialization of data fields
		T root = null;

		if (!isEmpty()) 
		{
			root = heap[1]; // Return value
			heap[1] = heap[lastIndex]; // Replace with lastIndex
			lastIndex--; // Decrease size
			reheap(1); // Transform to a heap
		} // end if

		return root;
		
	} // end removeMax

	public T getMax() 
	{
		checkInitialization();
		T root = null;
		
		if (!isEmpty()) 
		{
			root = heap[1];
		} // end if
			
		return root;
		
	} // end getMax

	public boolean isEmpty() 
	{
		return lastIndex < 1;
	} // end isEmpty

	public int getSize() 
	{
		return lastIndex;
	} // end getSize

	public void clear() 
	{
		checkInitialization();
		
		while (lastIndex > -1) 
		{
			heap[lastIndex] = null;
			lastIndex--;
		} // end while
		
		lastIndex = 0;
		
	} // end clear

	public T[] toArray() 
	{
		checkInitialization();

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[lastIndex]; // Unchecked cast
		for (int index = 0; index < lastIndex; index++) 
		{
			result[index] = heap[index + 1];
		} // end for

		return result;
		
	} // end toArray

	public int getInsertionSwap() 
	{
		return insertionSwap;
		
	} // end getInsertionSwap

	public int getSmartSwap() 
	{
		return smartSwap;
		
	} // end getSmartSwap

	private void reheap(int rootIndex) 
	{
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;

		while (!done && (leftChildIndex <= lastIndex)) // it's a complete binary tree
		{
			int largerChildIndex = leftChildIndex; // Assume larger
			int rightChildIndex = leftChildIndex + 1; // array implementation and complete binary tree

			if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) 
			{
				largerChildIndex = rightChildIndex;
			} // end if

			if (orphan.compareTo(heap[largerChildIndex]) < 0) 
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
				smartSwap++;
			} // end if
			else 
			{
				done = true;
			} // end else
				
		} // end while

		heap[rootIndex] = orphan;
		
	} // end reheap

	private void ensureCapacity() 
	{
		int capacity = heap.length - 1;
		
		if (lastIndex >= capacity) 
		{
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			heap = Arrays.copyOf(heap, newCapacity + 1);
		} // end if
		
	} // end ensureCapacity
	
	

} // end MaxHeap
