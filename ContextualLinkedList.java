//Part of the LinkedList Project
//Cameron Gonzalez

import java.util.ListIterator;

public interface ContextualLinkedList<E>
{
	public int size();
	public boolean isEmpty();
	public boolean contains(Object o);
	public ListIterator<E> listIterator(boolean atHead);
}