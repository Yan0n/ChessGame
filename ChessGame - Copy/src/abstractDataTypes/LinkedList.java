package abstractDataTypes;

class LinkedList{

    // Attributes
    public Node head;

    // Constructor
    public LinkedList(){
        this.head = null;
    }

    // returns the size of the Linked List
    public int size(){

        Node temp = this.head;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.next;

        }

        return count;

    }

    // removes data at the specified index.
    public void remove(int position) {
        if (head == null) {
            // The list is empty, nothing to remove
            return;
        }
        if (position == 0) {
            head = head.next;
            return;
        }
        Node temp = head;
        for (int i = 0; i < position - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        // Check if position is valid
        if (temp.next == null) {
            // The given position is beyond the end of the list
            System.out.println("Invalid position");
            return;
        }
        temp.next = temp.next.next;
    }

    //  Returns data at the specified position.
    public int get(int position){

        Node current = this.head;
        int count = 0;
        if (position == 0){
            return current.data;
        }
        else{
            while (count < position-1){
                current = current.next;
                count ++;

            }
            return current.next.data;
        }
    }

    // Adds data to the tail-end of the Linked List.
    public void append(int value){
        Node node = new Node(value);
        node.next = this.head;
        this.head = node;
    }

}