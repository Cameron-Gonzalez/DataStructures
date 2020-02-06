//Part of the LinkedList Project
//Cameron Gonzalez
//Help from: Dr. Hong

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements ContextualLinkedList<E>
{
	//creating private Node objects for head and tail, and set size to 0 (this is our own default constructor)
	private Node<E> head = null; //set to null initially because it is empty
	private Node<E> tail = null; //set to null initially because it is empty
	private int size = 0;
	
	//creating a private static for Nodes, which are used for node operations
	private static class Node<E>
	{
		private E data; //holds data of a node
		private Node<E> next; //references the node after the current node
		private Node<E> prev; //references the node previous to the current node
		
		private Node(E dataItem) //private Node constructor which initializes data of a node
		{
			data = dataItem; //sets data to parameter dataItem
			next = null; //has no link next
			prev = null; //has no link previous
		}
		
		private Node(Node<E> prev, E dataItem, Node<E> next) //private Node constructor which initializes data and links
		{
			this.data = dataItem; //sets data to parameter dataItem
			this.next = next; //sets next
			this.prev = prev; //sets prev
		}
	}
	
	//private class that implements the ListIterator interface
	private class MyListItr implements ListIterator<E>
	{
		private Node<E> lastNodeReturned; //reference to last node returned
		private Node<E> nextNode; //reference to next node
		private int nextIndex = 0; //starts at head index (0)
		
		//public constructor that makes a new listIterator that implements some ListIterator methods
		public MyListItr(boolean atHead)
		{
			lastNodeReturned = null; //previous is null (at head)
			if(atHead) //if specified at head, it will set the nextNode to head, and starting index at 0
			{
				nextNode = head;
				nextIndex = 0;
			}
			else //if specified at tail (false) it will set the nextNode to null, and starting index at size, so it is at the tail
			{
				nextNode = null;
				nextIndex = size;
			}
			//works also when the list is empty
		}
		
		//return true if call to next will not throw an exception
		public boolean hasNext()
		{
			return nextNode != null; //fastest check; or nextIndex < size
		}
		
		//returns the next item in the list
		public E next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			lastNodeReturned = nextNode;
			nextNode = nextNode.next;
			nextIndex++;
			return lastNodeReturned.data;
		}
		
		//return true if call to previous will not throw an exception
		public boolean hasPrevious()
		{
			return nextIndex > 0; //or prev != null
		}
		
		//return the previous item in the list
		//throws NoSuchElementException if there is no such object
		public E previous()
		{
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			if(nextNode == null) //Iterator is past the last element
			{
				nextNode = tail;
			}
			else
			{
				nextNode = nextNode.prev;
			}
			lastNodeReturned = nextNode;
			nextIndex--;
			return lastNodeReturned.data;
		}
		
		public void add(E e)
		{
			if(null == e)
			{
				throw new IllegalArgumentException();
			}
			
			lastNodeReturned = null;
			if(nextNode == null)
			{
				appendElem(e);
			}
			else
			{
				insertElemBefore(e, nextNode);
			}
			nextIndex++;
		}
		
		public int nextIndex() 
		{
			if(!hasNext())
			{
				return size;
			}
			else
			{
				return nextIndex;
			}
		}

		public int previousIndex() 
		{
			if(!hasPrevious())
			{
				return -1;
			}
			else
			{
				return nextIndex-1;
			}
		}

		public void remove() 
		{
			if(null == lastNodeReturned)
			{
				throw new IllegalStateException();
			}
			
			Node<E> lastNext = lastNodeReturned.next;
			removeNode(lastNodeReturned);
			
			//due to an earlier previous() call
			if(nextNode == lastNodeReturned) 
			{
				nextNode = lastNext;
			}
			//due to an earlier next() call
			else 
			{
				nextIndex--;
			}
			
			lastNodeReturned = null;
		}

		public void set(E e) 
		{
			if(null == e) //element must not be null
			{
				throw new IllegalArgumentException();
			}
			if(null == lastNodeReturned) //checks to see if add() or remove() has been used because they both set lastNodeReturned to null
			{
				throw new IllegalStateException();
			}
			lastNodeReturned.data = e;
			//don't need to reset lastNodeReturned to null because the node is still the same, just the data changed
		}
	}
	
	private void appendElem(E e)
	{
		if(e == null)
		{
			throw new IllegalArgumentException();
		}
		
		Node<E> newNode = new Node<>(tail, e, null);
		if(tail == null)
		{
			head = newNode;
		}
		else
		{
			tail.next = newNode;
		}
		tail = newNode;
		size++;
	}
	
	private void insertElemBefore(E e, Node<E> aNode)
	{
		assert aNode != null;
		
		Node<E> pp = aNode.prev;
		Node<E> newNode = new Node<>(pp, e, aNode);
		
		aNode.prev = newNode;
		if(pp == null)
		{
			head = newNode;
		}
		else
		{
			pp.next = newNode;
		}
		size++;
	}
	
	private E removeNode(Node<E> aNode) 
	{
        assert aNode != null;
        E e = aNode.data;
        Node<E> nn = aNode.next;
        Node<E> pp = aNode.prev;

        // removing head
        if (pp == null)
        {   
            head = nn;
        } 
        // not head
        else 
        {            
            pp.next = nn;
        }

        // removing tail
        if (nn == null) 
        {   
            tail = pp;
        } 
        // not tail
        else 
        {            
            nn.prev = pp;
        }
        
        size--;
        return e;
    }
	
	@Override
	//returns a ListIterator, but must create an instance of a private class of ListIterator
	public ListIterator<E> listIterator(boolean atHead)
	{
		return new MyListItr(atHead); //ListIterator<>(); would not work. You cannot create an instance of an interface
	}
	
	//returns size
	public int size() 
	{
		return size;
	}
	
	//checks to see if the list is empty
	public boolean isEmpty() 
	{
		return size == 0; //fastest check; if it is 0, it returns true, else it returns false; could also type out checks
	}

	//checks if list contains this particular Object o by conducting a linear search
	public boolean contains(Object o) 
	{
		Node<E> current = head; //creating a temporary node that runs through the list by starting at head
		while(current != null) //if current is not empty, check if the data of current equals o
		{
			if (current.data.equals(o)) //not '==' because '==' checks to see if it points to the same object
			{
				return true; //if it does, return true
			}
			current = current.next; //else, step to the next node
		}
		return false; //if none matched, returns false
	}
}
