// javac DLL.java
// java DLL

import java.util.*;

// B254
// To print out the address, you may use
// Integer.toHexString(node.hashCode())

@SuppressWarnings({ "hiding", "unused" })
class DLLNode<T> {
	private T info;
    private DLLNode<T> next, prev;
    
    // add copy constructor here
    public DLLNode(DLLNode<T> copy) {
    	info = copy.info;
    	DLLNode<T> node = this;
    	
    	while((copy = copy.getNext()) != null) {
    		DLLNode<T> cloneNode = new DLLNode<T>(copy.getData());
    		cloneNode.setPrev(node);
    		node.setNext(cloneNode);
    		node = cloneNode;
    	}
    }
    
    public DLLNode(T info) {
        this.info = info;
        next = null;  // link to the next node
        prev = null;  // link to the previous node
    }
    
    public void setNext(DLLNode<T> next) {
        this.next = next;
    }
    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }
    
    public DLLNode<T> getNext() {
        return this.next;
    }
    
    public DLLNode<T> getPrev() {
        return this.prev;
    }
    
    public T getData() {return this.info;}
}