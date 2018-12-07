import javafx.scene.Node;

@SuppressWarnings("unused")
public class DLL<T> {
    
	private DLLNode<String> head;
	private DLLNode<String> last;
	
    // add your code here
	// Insert in the front
    public void insertInFront(DLL<String> linkedList, DLLNode<String> node) {
		// make next of the new node as head and previous as null
        // move the head to point to the new node
        node.setNext(linkedList.head);
        node.setPrev(null);

        // change previous of head to the new node
        if(head != null) {
            // if not empty
            linkedList.head.setPrev(node);
        }

        // move the head to point to the new node
        linkedList.head = node;
    }

    // Insert in the end
	public void insertInEnd(DLL<String> linkedList, DLLNode<String> node) {
        // save the head for traversal
        last = head;
        
        // insert at the end
        node.setNext(null);

        // if the list is empty
        if (head == null) {
            node.setPrev(null);
            head = node;
            return;
        }

        // otherwise, traverse till the end
        while (last.getNext() != null) {
            last = last.getNext(); //return the next pointer
        }

        // point the next of the last node to the new node
        last.setNext(node);

        // point the previous to the last node
        node.setPrev(last);
    }

    // Insert in the interior
	public void insertInInterior(DLLNode<String> prev, DLLNode<String> node) {
        if (node == null) {
            System.out.println("Previous node cannot be null.");
            return;
        } else {
        	// point the next to the prev's next
            node.setNext(prev.getNext());
            
            // point the previous to the previous
            node.setPrev(prev);
            
            // point the prev's next to the new node
            prev.setNext(node);

            // change the next node's previous to the new node
            if(node.getNext() != null) {
                DLLNode<String> tmp = node.getNext();
                tmp.setPrev(node);
            }
        }
    }

    // delete the front
    public void deleteFront(DLLNode<String> node, DLLNode<String> head) {
        if (head == node) {
        	node.setNext(node);
            head = node.getNext();
            return;
        }
    }   

    // delete the last node
    public void deleteLast(DLLNode<T> node) {
        if (node.getNext() == null) {
            DLLNode<T> tmp = node.getPrev();
            tmp.setNext(null);
            return;
        }
    }

    // delete an interior node
    public void deleteInterior(DLLNode<String> node) {
        if (node.getNext() != null) {
            DLLNode<String> tmp1 = node.getNext();
            DLLNode<String> tmp2 = node.getPrev();
            tmp1.setPrev(node.getPrev());
            tmp2.setNext(node.getNext());
            return;
        }
    }
	private static void Traverse(DLLNode<String> head) {
		DLLNode<String> tmp = head;
		while(tmp.getNext() != null) {
			System.out.print(tmp.getData() + "(" + Integer.toHexString(tmp.hashCode()) + ") ");
			tmp = tmp.getNext();
		}
		System.out.println(tmp.getData() + " (" + Integer.toHexString(tmp.hashCode()) + ") ");
	}
	
	private static DLL<String> Reverse(DLL<String> dupHead) {
		DLLNode<String> prev = null;
		DLLNode<String> current = dupHead.head;
		DLLNode<String> next = null;
		
		while(current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}
		dupHead.head = prev;
		return dupHead;
		/*
		DLLNode<String> last = null;
		DLLNode<String> current = dupHead;
		DLLNode<String> tmp = null;
		
		while(current != null) {
			tmp = current.getNext();
			current.setNext(current.getPrev());
			current.setPrev(tmp);
			current = current.getPrev();
		}
		tmp = dupHead;
		dupHead = last;
		last = tmp;
		dupHead.setPrev(null);
		last.setNext(null);
		return tmp;*/
	}
	
    private static DLL<String> Duplicate(DLL<String> dllList) {
    	DLL<String> newDLL = new DLL<String>();
    	DLLNode<String> currentNode = dllList.head;
    	
    	while(currentNode.getNext() != null) {
    		DLLNode<String> newNode = new DLLNode<String>(currentNode.getData());
    		newDLL.insertInEnd(newDLL, newNode);
    		currentNode = currentNode.getNext();
    	}
    	DLLNode<String> anotherNewNode = new DLLNode<String>(currentNode.getData());
    	newDLL.insertInEnd(newDLL, anotherNewNode);
		return newDLL;
	}
    
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
        
        DLLNode<String> node1, node2, node3, node4, node5, node6;
        
        // allocate nodes
        node1 = new DLLNode<String>("A");
        node2 = new DLLNode<String>("B");
        node3 = new DLLNode<String>("C");
        node4 = new DLLNode<String>("D");
        node5 = new DLLNode<String>("E");
        node6 = new DLLNode<String>("F");
        
        // A B C
        // Insert A to the front
        DLL<String> linkedList = new DLL<String>();
        linkedList.insertInFront(linkedList, node1);
        //Integer.toHexString(linkedList.head.hashCode());
        // Insert B to the end
        linkedList.insertInEnd(linkedList, node2);
        // Insert C to the end
        linkedList.insertInEnd(linkedList, node3);
        
   		// A B D C
        // Insert D between B and C
        linkedList.insertInInterior(node2, node4);
    
    	// A B D C E F
        // Insert E to the end
        linkedList.insertInEnd(linkedList, node5);
        // Insert F to the end
        linkedList.insertInEnd(linkedList, node6);
    	// A D C E F
        //  Delete B
        linkedList.deleteInterior(node2);
        
        
        System.out.println("Original List");
        Traverse(linkedList.head);
        
        System.out.println("Duplicated List");
        DLL<String> dupHead = Duplicate(linkedList);
        Traverse(dupHead.head);
        
        System.out.println("Reserved List");
//        linkedList.head = Reverse(linkedList.head);
//        Traverse(linkedList.head);
        dupHead = Reverse(dupHead);
        Traverse(dupHead.head);
        
        System.out.println("Original List");
        Traverse(linkedList.head);
        System.out.println();
        
        // BONUS POINTS
        @SuppressWarnings({"unchecked", "null"})
        DLLNode<String> newHead = new DLLNode(linkedList.head.getNext());
        System.out.println("Copy Constructor");
        Traverse(newHead);
    }
}
