/*******************************************************************
** BSTGUI.java
**
** @author Mitchell Ibarra
**
** This class serves as the user interface which takes user input 
** to add unordered integers to a binary sort tree and demonstrate 
** how the sorting algorithms functions. 
*******************************************************************
*/
package treesortgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;

/**
 *  treesortgui.BSTGUI
 */
public class BSTGUI extends JFrame {
    
    /*===== member variables =====*/
    private static final int MAX_NODES = 7;
    private static JButton gAddKeyBtn; // TODO maybe local var
    private static JPanel gInputPanel;
    private static JTextField gKeyInputTF;
    private int gIndex = 0;
    private int gStoredIndex = 0;
    private int gLowIndex = 0;
    private int gHighIndex = MAX_NODES - 1;
    private int gRootValue; 
    private boolean gSorted = false;
    private StringBuilder gNumberList = new StringBuilder();
    private JPanel gDrawPanel;
    private JButton gNextBtn; 
    private JLabel gNodeOne;
    private JLabel gNodeTwo;
    private JLabel gNodeThree;
    private JLabel gNodeFour;
    private JLabel gNodeFive;
    private JLabel gNodeSix;
    private JLabel gNodeSeven;
    private JLabel gSortedNodeOne;
    private JLabel gSortedNodeTwo;
    private JLabel gSortedNodeThree;
    private JLabel gSortedNodeFour;
    private JLabel gSortedNodeFive;
    private JLabel gSortedNodeSix;
    private JLabel gSortedNodeSeven;
    private JButton gResetBtn;
    private JButton gStartAlgorithmBtn;
    private JTextArea gTextArea;
    private JScrollPane gScrollPane;
    private JLabel gScrollLabel;
    private ArrayList<Integer> gUnsortedValues = new ArrayList<>(MAX_NODES);
    private ArrayList<Integer> gKeyValues = new ArrayList<>(MAX_NODES);
    private ArrayList<JLabel> gNodeLabels = new ArrayList<>();
    private ArrayList<JLabel> gSortedNodeLabels = new ArrayList();
    private BSTAlgorithm gBST;
    
    
    /*===== constructor =====*/
    /**
     * Creates a new instance of <code>BSTGUI</code>
     */
    public BSTGUI(){
        initComponents();
    }
    
    
    /*===== private methods =====*/
    private void initComponents(){
        this.setBounds(475, 200, 900, 500);
        this.setPreferredSize(new Dimension(350, 350));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Border tBevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        
        // Setting up draw panel 
        gDrawPanel = new JPanel();
        gDrawPanel.setBorder(tBevelBorder);
        gDrawPanel.setForeground(Color.BLACK);
        
        // Setting up input panel
        gInputPanel = new JPanel();
        gInputPanel.setBorder(tBevelBorder);
        
        // Creating text area
        gTextArea = new JTextArea(10, 3);
        gTextArea.setEditable(false);
        
        // Create scroll pane for text area 
        gScrollPane = new JScrollPane(gTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gScrollPane.setSize(250, 100);
        gScrollLabel = new JLabel("Values Entered", SwingConstants.CENTER);
        gScrollLabel.setFont(new Font("Liveration Serif", 0, 15));
        
        // Creating node labels with text placement 
        //TODO make sure these are the right nodes that I need
        gNodeOne = new JLabel("", SwingConstants.CENTER);
        gNodeTwo = new JLabel("", SwingConstants.CENTER);
        gNodeThree = new JLabel("", SwingConstants.CENTER);
        gNodeFour = new JLabel("", SwingConstants.CENTER);
        gNodeFive = new JLabel("", SwingConstants.CENTER);
        gNodeSix = new JLabel("", SwingConstants.CENTER);
        gNodeSeven = new JLabel("", SwingConstants.CENTER);
        
        // Set the borders for the nodes
        gNodeOne.setBorder(tBevelBorder);
        gNodeTwo.setBorder(tBevelBorder);
        gNodeThree.setBorder(tBevelBorder);
        gNodeFour.setBorder(tBevelBorder);
        gNodeFive.setBorder(tBevelBorder);
        gNodeSix.setBorder(tBevelBorder);
        gNodeSeven.setBorder(tBevelBorder);
        
        // Adding nodes to arraylist to set nodes as values are entered
        // TODO could probably add nodes earlier and set the text and border for each
        gNodeLabels.add(gNodeOne);
        gNodeLabels.add(gNodeTwo);
        gNodeLabels.add(gNodeThree);
        gNodeLabels.add(gNodeFour);
        gNodeLabels.add(gNodeFive);
        gNodeLabels.add(gNodeSix);
        gNodeLabels.add(gNodeSeven);
        
        // Creating sorted nodes labels and text placement
        // TODO same as above put into list first and then set defaults
        gSortedNodeOne = new JLabel("", SwingConstants.CENTER);
        gSortedNodeTwo = new JLabel("", SwingConstants.CENTER);
        gSortedNodeThree = new JLabel("", SwingConstants.CENTER);
        gSortedNodeFour = new JLabel("", SwingConstants.CENTER);
        gSortedNodeFive = new JLabel("", SwingConstants.CENTER);
        gSortedNodeSix = new JLabel("", SwingConstants.CENTER);
        gSortedNodeSeven = new JLabel("", SwingConstants.CENTER);
        
        gSortedNodeOne.setBorder(tBevelBorder);
        gSortedNodeTwo.setBorder(tBevelBorder);
        gSortedNodeThree.setBorder(tBevelBorder);
        gSortedNodeFour.setBorder(tBevelBorder);
        gSortedNodeFive.setBorder(tBevelBorder);
        gSortedNodeSix.setBorder(tBevelBorder);
        gSortedNodeSeven.setBorder(tBevelBorder);
        
        gSortedNodeLabels.add(gSortedNodeOne);
        gSortedNodeLabels.add(gSortedNodeTwo);
        gSortedNodeLabels.add(gSortedNodeThree);
        gSortedNodeLabels.add(gSortedNodeFour);
        gSortedNodeLabels.add(gSortedNodeFive);
        gSortedNodeLabels.add(gSortedNodeSix);
        gSortedNodeLabels.add(gSortedNodeSeven);
        
        //TODO
        for(JLabel tNode : gNodeLabels){
            tNode.setSize(40, 25);
        }
        
        // TODO create font once and use it wherever
        JLabel tTitleLabel= new JLabel("Binary Search Tree");
        tTitleLabel.setFont(new Font("Liberation Serif", 0, 18));
        
        JLabel tEntryLabel = new JLabel("Enter 7 values to Tree");
        tEntryLabel.setFont(new Font("Liberation Serif", 0, 14));
        
        NumberFormat tFormat = NumberFormat.getIntegerInstance();
        NumberFormatter tFormatter = new NumberFormatter(tFormat);
        tFormatter.setAllowsInvalid(true);
        tFormatter.setMinimum(0);
        tFormatter.setMaximum(999);
        gKeyInputTF = new JFormattedTextField(tFormat);
        
        // Grouping for Add button
        gAddKeyBtn = new JButton("Add");
        this.getRootPane().setDefaultButton(gAddKeyBtn);
        gAddKeyBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent aEvent){
                Integer tKeyInput = null;
                try{
                    tKeyInput = Integer.parseInt(gKeyInputTF.getText());
                }
                catch(Exception aEx){
                    JOptionPane.showMessageDialog(null, "Valid entries are 0-999");
                }
                
                // If there is a valid entry, add it to the BST
                if(tKeyInput != null){
                    gKeyValues.add(tKeyInput);
                    
                    // append values to list
                    gNumberList.append(tKeyInput.toString());
                    gTextArea.setText(gNumberList.toString());
                    gNumberList.append("\n");
                    
                    // enable/disable buttons when max key values reached
                    if(gKeyValues.size() == MAX_NODES){
                        for(Integer tValue : gKeyValues){
                            gUnsortedValues.add(new Integer(tValue));
                        }
                        
                        gRootValue = gUnsortedValues.get(0);
                        
                        gStartAlgorithmBtn.setEnabled(true);
                        gKeyInputTF.setEnabled(false);
                        gAddKeyBtn.setEnabled(false);
                    }
                }
                gKeyInputTF.setText("");
            }
        });
        
        // Grouping for Start button
        gStartAlgorithmBtn = new JButton("Start");
        gStartAlgorithmBtn.setEnabled(false);
        gStartAlgorithmBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent aEvent){
                // Sort the values
                gBST = new BSTAlgorithm(gKeyValues);
                for(Integer tInt : gKeyValues)
                {
                    System.out.println("second unsorted : " + tInt);
                }
                gKeyValues = gBST.BSTAlgorithm();
                System.out.println("");
                for(Integer tInt : gKeyValues)
                {
                    System.out.println("sorted: " + tInt);
                }
                // Tree level 1
                gNodeOne.setText(gUnsortedValues.get(0).toString());
                gNodeOne.setVisible(true);
                
                // Tree level 2
                if(gUnsortedValues.get(1) < gRootValue){
                    gNodeTwo.setLocation(270, 35);
                    gNodeTwo.setText(gUnsortedValues.get(1).toString());
                    gNodeTwo.setVisible(true);
                }
                else{
                    gNodeTwo.setLocation(330, 35);
                    gNodeTwo.setText(gUnsortedValues.get(1).toString());
                    gNodeTwo.setVisible(true);
                }
                
                // Tree level 3
                if(gUnsortedValues.get(2) < gRootValue){
                    if(gUnsortedValues.get(2) < Integer.parseInt(gNodeTwo.getText())){
                        gNodeThree.setLocation(240, 70);
                        gNodeThree.setText(gUnsortedValues.get(2).toString());
                        gNodeThree.setVisible(true);
                    }
                    else{
                        gNodeThree.setLocation(360, 70);
                        gNodeThree.setText(gUnsortedValues.get(2).toString());
                        gNodeThree.setVisible(true);
                    }
                }
                else{
                    if(gUnsortedValues.get(2) < Integer.parseInt(gNodeTwo.getText())){
                        gNodeThree.setLocation(240, 70);
                        gNodeThree.setText(gUnsortedValues.get(2).toString());
                        gNodeThree.setVisible(true);
                    }
                    else{
                        gNodeThree.setLocation(360, 70);
                        gNodeThree.setText(gUnsortedValues.get(2).toString());
                        gNodeThree.setVisible(true);
                    }
                }
                
                // Tree level 4
                if(gUnsortedValues.get(3) < gRootValue){
                    if(gUnsortedValues.get(3) < Integer.parseInt(gNodeThree.getText())){
                        gNodeFour.setLocation(310, 105);
                        gNodeFour.setText(gUnsortedValues.get(3).toString());
                        gNodeFour.setVisible(true);
                    }
                    else{
                        gNodeFour.setLocation(390, 105);
                        gNodeFour.setText(gUnsortedValues.get(3).toString());
                        gNodeFour.setVisible(true);
                    }
                }
                else{
                    if(gUnsortedValues.get(3) < Integer.parseInt(gNodeTwo.getText())){
                        gNodeFour.setLocation(210, 105);
                        gNodeFour.setText(gUnsortedValues.get(3).toString());
                        gNodeFour.setVisible(true);
                    }
                    else{
                        gNodeFour.setLocation(390, 105);
                        gNodeFour.setText(gUnsortedValues.get(3).toString());
                        gNodeFour.setVisible(true);
                    }
                }
                
                // Tree level 5
                if(gUnsortedValues.get(4) < gRootValue){
                    if(gUnsortedValues.get(4) < Integer.parseInt(gNodeFour.getText())){
                        gNodeFive.setLocation(180, 140);
                        gNodeFive.setText(gUnsortedValues.get(4).toString());
                        gNodeFive.setVisible(true);
                    }
                    else{
                        gNodeFive.setLocation(420, 140);
                        gNodeFive.setText(gUnsortedValues.get(4).toString());
                        gNodeFive.setVisible(true);
                    }
                }
                else{
                    if(gUnsortedValues.get(4) < Integer.parseInt(gNodeTwo.getText())){
                        gNodeFive.setLocation(180, 140);
                        gNodeFive.setText(gUnsortedValues.get(4).toString());
                        gNodeFive.setVisible(true);
                    }
                    else{
                        gNodeFive.setLocation(420, 140);
                        gNodeFive.setText(gUnsortedValues.get(4).toString());
                        gNodeFive.setVisible(true);
                    }
                }
                
                // Tree level 6
                if(gUnsortedValues.get(5) < gRootValue){
                    if(gUnsortedValues.get(5) < Integer.parseInt(gNodeFive.getText())){
                        gNodeSix.setLocation(150, 175);
                        gNodeSix.setText(gUnsortedValues.get(5).toString());
                        gNodeSix.setVisible(true);
                    }
                    else{
                        gNodeSix.setLocation(450, 175);
                        gNodeSix.setText(gUnsortedValues.get(5).toString());
                        gNodeSix.setVisible(true);
                    }
                }
                else{
                    if(gUnsortedValues.get(5) < Integer.parseInt(gNodeFive.getText())){
                        gNodeSix.setLocation(150, 175);
                        gNodeSix.setText(gUnsortedValues.get(5).toString());
                        gNodeSix.setVisible(true);
                    }
                    else{
                        gNodeSix.setLocation(450, 175);
                        gNodeSix.setText(gUnsortedValues.get(5).toString());
                        gNodeSix.setVisible(true);
                    }
                }
                
                // Tree level 7
                if(gUnsortedValues.get(6) < gRootValue){
                    if(gUnsortedValues.get(6) < Integer.parseInt(gNodeSix.getText())){
                        gNodeSeven.setLocation(120, 175);
                        gNodeSeven.setText(gUnsortedValues.get(6).toString());
                        gNodeSeven.setVisible(true);
                    }
                    else{
                        gNodeSeven.setLocation(480, 175);
                        gNodeSeven.setText(gUnsortedValues.get(6).toString());
                        gNodeSeven.setVisible(true);
                    }
                }
                else{
                    if(gUnsortedValues.get(6) < Integer.parseInt(gNodeFive.getText())){
                        gNodeSeven.setLocation(120, 175);
                        gNodeSeven.setText(gUnsortedValues.get(6).toString());
                        gNodeSeven.setVisible(true);
                    }
                    else{
                        gNodeSeven.setLocation(480, 175);
                        gNodeSeven.setText(gUnsortedValues.get(6).toString());
                        gNodeSeven.setVisible(true);
                    }
                }
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        System.out.println("Running start button");
                        validate();
                    }
                });
                
                gSorted = true;
                gStartAlgorithmBtn.setEnabled(false);
                gNextBtn.setEnabled(true);
            }
        });
        
        // Grouping for next button
        gNextBtn = new JButton("Next");
        gNextBtn.setEnabled(false);
        gIndex = 0;
        gNextBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent aEvent){
                if(!gKeyValues.isEmpty()){
                    gSortedNodeLabels.get(0).setVisible(true);
                    gSortedNodeLabels.get(0).setText(gKeyValues.get(0).toString());
                    gKeyValues.remove(0);
                    gSortedNodeLabels.remove(0);
                }
                gAddKeyBtn.setEnabled(false);
            }
        });
                
        // Grouping for reset button
        gResetBtn = new JButton("Reset");
        gResetBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent aEvent){
                // Reset all values TODO can put in own method
                gKeyValues.clear();
                gTextArea.setText("");
                gKeyInputTF.setEnabled(true);
                gKeyInputTF.setText("");
                gStartAlgorithmBtn.setEnabled(false);
                gNextBtn.setEnabled(false);
                gAddKeyBtn.setEnabled(true);
                gNodeOne.setText("");
                gNodeOne.setBackground(null);
                gNodeTwo.setText("");
                gNodeTwo.setBackground(null);
                gNodeThree.setText("");
                gNodeThree.setBackground(null);
                gNodeFour.setText("");
                gNodeFour.setBackground(null);
                gNodeFive.setText("");
                gNodeFive.setBackground(null);
                gNodeSix.setText("");
                gNodeSix.setBackground(null);
                gNodeSeven.setText("");
                gNodeSeven.setBackground(null);
            }
        });
        
        // Input panel group layout
        GroupLayout tLayout = new GroupLayout(gInputPanel);
        gInputPanel.setLayout(tLayout);
        tLayout.setHorizontalGroup(
            tLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.LEADING, tLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(tLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(tEntryLabel)
                    .addGroup(tLayout.createSequentialGroup()
                        .addComponent(gKeyInputTF, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gAddKeyBtn))
                    .addGroup(tLayout.createSequentialGroup()
                        .addComponent(gStartAlgorithmBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gNextBtn))
                    .addComponent(gResetBtn)
                    .addComponent(gScrollLabel, 10, 15, 112)
                    .addGap(100, 100, 100)
                    .addComponent(gScrollPane, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTitleLabel))
                .addGap(42, 42, 42)
                .addContainerGap())
        );
        
        tLayout.setVerticalGroup(
            tLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tTitleLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(tEntryLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gAddKeyBtn)
                    .addComponent(gKeyInputTF, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gNextBtn)
                    .addComponent(gStartAlgorithmBtn))
                .addGap(18, 18, 18)
                .addComponent(gResetBtn)
                .addComponent(gScrollLabel, 10, 15, 50)
                .addComponent(gScrollPane, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE))
        );
        
        // Draw panel null layout
        gDrawPanel.setLayout(null);
        
        gNodeOne.setLocation(300, 0);
        gNodeOne.setVisible(false);
        gDrawPanel.add(gNodeOne);
        gNodeTwo.setVisible(false);
        gDrawPanel.add(gNodeTwo);
        gNodeThree.setVisible(false);
        gDrawPanel.add(gNodeThree);
        gNodeFour.setVisible(false);
        gDrawPanel.add(gNodeFour);
        gNodeFive.setVisible(false);
        gDrawPanel.add(gNodeFive);
        gNodeSix.setVisible(false);
        gDrawPanel.add(gNodeSix);
        gNodeSeven.setVisible(false);
        gDrawPanel.add(gNodeSeven);
        
        gSortedNodeOne.setSize(40, 25);
        gSortedNodeOne.setLocation(130, 300);
        gSortedNodeOne.setVisible(false);
        gDrawPanel.add(gSortedNodeOne);
        gSortedNodeTwo.setSize(40, 25);
        gSortedNodeTwo.setLocation(180, 300);
        gSortedNodeTwo.setVisible(false);
        gDrawPanel.add(gSortedNodeTwo);
        gSortedNodeThree.setSize(40, 25);
        gSortedNodeThree.setLocation(230, 300);
        gSortedNodeThree.setVisible(false);
        gDrawPanel.add(gSortedNodeThree);
        gSortedNodeFour.setSize(40, 25);
        gSortedNodeFour.setLocation(280, 300);
        gSortedNodeFour.setVisible(false);
        gDrawPanel.add(gSortedNodeFour);
        gSortedNodeFive.setSize(40, 25);
        gSortedNodeFive.setLocation(330, 300);
        gSortedNodeFive.setVisible(false);
        gDrawPanel.add(gSortedNodeFive);
        gSortedNodeSix.setSize(40, 25);
        gSortedNodeSix.setLocation(380, 300);
        gSortedNodeSix.setVisible(false);
        gDrawPanel.add(gSortedNodeSix);
        gSortedNodeSeven.setSize(40, 25);
        gSortedNodeSeven.setLocation(430, 300);
        gSortedNodeSeven.setVisible(false);
        gDrawPanel.add(gSortedNodeSeven);
        
        // Place both panels in group layout frame
        GroupLayout tGroupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(tGroupLayout);
        tGroupLayout.setHorizontalGroup(
            tGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tGroupLayout.createSequentialGroup()
                .addComponent(gInputPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(gDrawPanel, 1000, 1000, 1000))
        );
        
        tGroupLayout.setVerticalGroup(
            tGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(gInputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gDrawPanel, 1000, 1000, 1000)
        );
    }   
    
    
    /*===== public methods =====*/
    /**
     * Start binary sort tree gui
     * @param aArgs String[] Command line arguments
     */
    public static void main(String[] aArgs){
        // Create and display the form
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                BSTGUI tGUI = new BSTGUI();
//                tGUI.setSize(1000, 500);
//                tGUI.setLocationRelativeTo(null);
                tGUI.setVisible(true);
            }
        });
    }
}//end class BSTGUI