/*******************************************************************
** Node.java
**
** @author Mitchell Ibarra
**
** This class is a user created Node object to hold a value and 
** pointers to a left and right Node
*******************************************************************
*/
package treesortgui;

/**
 *  treesortgui.Node
 *  @param <T extends Number> Value to be store in the Node
 */
public class Node<T extends Number> {
    
    /*===== member variables =====*/
    private T gKey;
    private Node<T> gLeftNode;
    private Node<T> gRightNode;
    
    
    /*===== constructor =====*/
    /**
     * Creates a new instance of <code>Node</code>
     */
    public Node(){
        gLeftNode = null;
        gRightNode = null;
    }
    
    /*===== protected methods =====*/
    protected void addKey(T aKey){
        gKey = aKey;
    }
    
    protected T getKey(){
        return gKey;
    }
    
    protected Node getLeftNode(){
        Node tLeftNode = gLeftNode;
        return tLeftNode;
    }
    
    protected void setLeftNode(Node aLeftNode){
        gLeftNode = aLeftNode;
    }
    
    protected Node getRightNode(){
        Node tRightNode = gRightNode;
        return tRightNode;
    }
    
    protected void setRightNode(Node aRightNode){
        gRightNode = aRightNode;
    }
}//end class Node
