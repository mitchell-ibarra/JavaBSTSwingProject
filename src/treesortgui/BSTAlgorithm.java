/*******************************************************************
** BSTAlgorithm.java
**
** @author Mitchell Ibarra
**
** This class is meant to provide an example of a binary sort tree
** algorithm. 
*******************************************************************
*/
package treesortgui;

import java.util.ArrayList;

/**
 *  treesortgui.BSTAlgorithm
 */
public class BSTAlgorithm {
    
    /*===== member variables =====*/
    private static Node<Integer> gRootNode;
    private static ArrayList<Integer> gSortedList = new ArrayList<>();
    private static ArrayList<Integer> gCopyList = new ArrayList<>();
    
    
    /*===== constructor =====*/
    /**
     * Creates a new instance of <code>BSTAlgorithm</code>
     * @param aList ArrayList<Integer> The list of integers to sort
     */
    public BSTAlgorithm(ArrayList<Integer> aList){
        gRootNode = null;
        deepCopyArrayList(aList);
    }
    
    /*===== private methods =====*/
    /**
     * TODO
     * @param aList Arraylist<Integer> The list to make a deep copy of
     */
    private void deepCopyArrayList(ArrayList<Integer> aList){
//        for(Integer tInt : aList){
//            gCopyList.add(new Integer(tInt));
//        }
        gCopyList = new ArrayList(aList);
    }
    
    /**
     * TODO
     * @param aRoot
     * @param aKey
     * @return 
     */
    private static Node<Integer> insertRecord(Node<Integer> aRoot, Integer aKey){
        Node<Integer> tNodeRoot = aRoot;
        
        if(tNodeRoot == null){
            tNodeRoot = new Node();
            tNodeRoot.addKey(aKey);
        }
        else{
            if(aKey < tNodeRoot.getKey()){
                Node tLeftNode = insertRecord(tNodeRoot.getLeftNode(), aKey);
                tNodeRoot.setLeftNode(tLeftNode);
            }
            else if(aKey >= tNodeRoot.getKey()){
                Node tRightNode = insertRecord(tNodeRoot.getRightNode(), aKey);
                tNodeRoot.setRightNode(tRightNode);
            }
        }
        return tNodeRoot;
    }
    
    /**
     * TODO
     * @param aKey 
     */
    private static void insertKey(int aKey){
        gRootNode = insertRecord(gRootNode, aKey);
    }
    
    /**
     * TODO
     * @param aRoot
     * @return 
     */
    private static ArrayList<Integer> returnInOrder(Node<Integer> aRoot){
        if(aRoot != null){
            returnInOrder(aRoot.getLeftNode());
            gCopyList.add(aRoot.getKey());
            returnInOrder(aRoot.getRightNode());
        }
        return gCopyList;
    }
    
    /*===== protected methods =====*/
    /**
     * TODO
     * @return 
     */
    protected static ArrayList<Integer> BSTAlgorithm(){
        for(Integer tKey : gCopyList){
            insertKey(tKey);
        }
        gCopyList.clear();
        gCopyList = returnInOrder(gRootNode);
        return gCopyList;
    }
    
    
    /*===== public methods =====*/
    /**
     * TODO
     * @param aArgs 
     */
    public static void main(String[] aArgs)
    {
        ArrayList<Integer> tList = new ArrayList<>();
        tList.add(3);
        tList.add(65);
        tList.add(5);
        tList.add(455);
        tList.add(451);
        tList.add(98);
        
        for(int tInt : tList){
            System.out.println("Unsorted list: " + tInt);
        }
        
        BSTAlgorithm tBST = new BSTAlgorithm(tList);
        tList = BSTAlgorithm();
        
        for(int tInt : tList){
            System.out.println("Sorted list: " + tInt);
        }
    }
}//end class BSTAlgorithmz
