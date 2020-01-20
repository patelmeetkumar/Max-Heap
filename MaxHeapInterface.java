/*
 * Name: Meetkumar Patel
 * CS 2400 Fall 2018 Project 4
 */



public interface MaxHeapInterface <T extends Comparable <? super T>>
{
	public void add(T newEntry);
	public T removeMax();
	public T getMax();
	public boolean isEmpty();
	public int getSize();
	public void clear();

} // end MaxHeapInterface
