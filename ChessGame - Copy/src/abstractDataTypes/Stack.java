package abstractDataTypes;

public class Stack{

    // Static variables
    public static int numberOfStacks = 0;

    // Attributes
    public LinkedList stack;

    // Constructor
    public Stack(){
        this.stack = new LinkedList();
        numberOfStacks++;
    }

    // String format of the Stack.
    public String toString(){
        String output = "";
        for (int i = 0; i<stack.size(); i++){
            output += stack.get(i) + "\n";

        }
        return output;

    }

    // Returns true if you check if the Stack is Empty
    public boolean isEmpty(){

        if (this.stack.size() == 0 ){
            return true;
        }
        return false;

    }

    // Push: adds a value onto the top of the stack, "last in first out format (LIFO)"" .
    public void push (int item){
        this.stack.append(item); // .add()
    }

    //  Pop: Removes the top value.
    public int pop(){
        int top = stack.get(0);
        stack.remove(0);
        return top;
    }

}
