package abstractDataTypes;

// Class Node, the basic node formatting that Class LinkedList uses
class Node{

    // Attributes

    int data;
    Node next;

    // constructor

    public Node(int data){
        this.data = data;
        this.next = null;

    }

    // Methods

    public String toString(){
        return this.data + " --> " + this.next;

    }

}