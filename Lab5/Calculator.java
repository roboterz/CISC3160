import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.lang.Math;


//*** class for function save and recall a number -----------------------
class Memory {

   double number;       //the number in the memory
   
   Memory() {
      number = 0;
   }
   
   // M+ Function
   public void add(double Number) {
      number += Number;
   }
   
   // M- Function
   public void minus(double Number) {
      number -= Number;
   }
   
   // MR Function, recall
   public double recall() {
      return number;
   }
   
   // MC Function, clear
   public void clear() {
      number = 0;
   }
   
   // MS Function, save
   public void save(double Number) {
      number = Number;   
   }
}



//*** class Calculator -------------------------------------------------------
public class Calculator{

   // define variable
   final int MAX_LENGTH = 16;
   double lastNumber;
   double currentNumber;
   boolean savedFlag;           // Flag if saved number to lastNumber
   boolean mathSignFlag;        // mathSign pressed
   int mathSign;
   Memory m;                    // memory for save and recall function
   
   // interface
   JFrame frame = new JFrame("Calculator");                 //Calculator Frame
   JPanel panel = new JPanel();                             //Panel
   JLabel lblScreen = new JLabel("0",  JLabel.RIGHT);      //Monitor
   JButton [] button = new JButton[28];                     //Buttons
   
   
   Calculator(){
      // initialize variable
      lastNumber = 0;
      currentNumber = 0;
      savedFlag = false;          
      mathSignFlag = false;   
      mathSign = 0;           
      m = new Memory();  
   
   
      // Create interface ---------------------------------------
      
         // Create frame
         frame.setSize(330, 400);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);  
         frame.add(panel);
         panel.setLayout(null);
         
         // Create Monitor
         lblScreen.setBounds(10,10,295,65);
         lblScreen.setFont(new Font("Arial", 0, 30));
         lblScreen.setOpaque(true);
         lblScreen.setBackground(Color.WHITE);
         lblScreen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
         panel.add(lblScreen);
         
         //the char on the buttons:
         /********
            index: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18  19 20 21 22 23 24 25 26 27
           symbol: 0 1 2 3 4 5 6 7 8 9 .  +- /  *  -  +  \/ %  1/x =  <- CE C  MC MR MS M+ M-
         ********/
         
         // Create Button, order as left to right, top to bottom
         createButton(23, "MC", 10, 100, 55, 35);
         createButton(24, "MR", 70, 100, 55, 35);
         createButton(25, "MS", 130, 100, 55, 35);
         createButton(26, "M+", 190, 100, 55, 35);
         createButton(27, "M-", 250, 100, 55, 35);
         createButton(20, "\u232B", 10, 140, 55, 35);       //backspace
         createButton(21, "CE", 70, 140, 55, 35);
         createButton(22, "C", 130, 140, 55, 35);
         createButton(11, "\u00B1", 190, 140, 55, 35);      //negative or positive sign
         createButton(16, "\u221A", 250, 140, 55, 35);      //Square root
         createButton(7, "7", 10, 180, 55, 35);
         createButton(8, "8", 70, 180, 55, 35);
         createButton(9, "9", 130, 180, 55, 35);
         createButton(12, "/", 190, 180, 55, 35);
         createButton(17, "%", 250, 180, 55, 35);
         createButton(4, "4", 10, 220, 55, 35);
         createButton(5, "5", 70, 220, 55, 35);
         createButton(6, "6", 130, 220, 55, 35);
         createButton(13, "*", 190, 220, 55, 35);
         createButton(18, "\u215F\u2CAD", 250, 220, 55, 35);    // 1/x reciprocal
         createButton(1, "1", 10, 260, 55, 35);
         createButton(2, "2", 70, 260, 55, 35);
         createButton(3, "3", 130, 260, 55, 35);
         createButton(14, "-", 190, 260, 55, 35);
         createButton(19, "=", 250, 260, 55, 75);
         createButton(0, "0", 10, 300, 115, 35);
         createButton(10, ".", 130, 300, 55, 35);
         createButton(15, "+", 190, 300, 55, 35);
      //End Create interface-------------------------------------------------
   }
   
   
   // show calculator
   public void show( boolean value ){
      frame.setVisible(value);
   }
   
   
   // scientific notation format
   private String formatNumber(String str){
      
      int idx = str.indexOf("E");  // scientific notation

      // remove zero
      if (str.indexOf(".") > 0 && idx < 0 ){
         str = str.replaceAll("0+?$", "");
         str = str.replaceAll("[.]$", "");
      }

      // format scientific notation
      if (str.length() > MAX_LENGTH){
         if ( idx > 0 )
            str = str.substring(0, MAX_LENGTH - (str.length() - idx)- 1 ) + str.substring(idx);
         else
            str = str.substring(0, MAX_LENGTH);
      }
      
      return str;
   }
   
   
   // create button funtion
   private void createButton(int index, String buttonName, int left, int top, int wide, int height ){
      
      button[index] = new JButton(buttonName);
      button[index].setBounds(left, top, wide, height);
      panel.add(button[index]);
      button[index].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         
            /********
               index: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18  19 20 21 22 23 24 25 26 27
              symbol: 0 1 2 3 4 5 6 7 8 9 .  +- /  *  -  +  \/ %  1/x =  <- CE C  MC MR MS M+ M-
            ********/
            
            // function
            switch (index){
               // press the numbers button
               case 0:  // 0
               case 1:  // 1
               case 2:  // 2
               case 3:  // 3
               case 4:  // 4
               case 5:  // 5
               case 6:  // 6
               case 7:  // 7
               case 8:  // 8
               case 9:  // 9
               
                  if (savedFlag == true){                //if number was saved to lastNumber  
                     lblScreen.setText(buttonName);    //display the number on the screen of calculator
                     savedFlag = false;
                     
                  }else{
                     if ( lblScreen.getText().length() < MAX_LENGTH )
                        // if current number is not zero, append number to current number 
                        lblScreen.setText( (lblScreen.getText()== "0")? buttonName: (lblScreen.getText() + buttonName));
                  }
                  
                  currentNumber = Double.parseDouble(lblScreen.getText());
                  mathSignFlag = false;
                  break;
                  
               case 10:    // press "." button
                  if (savedFlag == true){                   //if no number buttons was pressed
                     lblScreen.setText("0" + buttonName);   //append the dot sign after Zero
                     savedFlag = false;
                     
                  }else                                     //if typing numbers
                     if ( lblScreen.getText().length() < MAX_LENGTH && lblScreen.getText().indexOf(".") < 0 )
                        lblScreen.setText( lblScreen.getText() + buttonName );      //append the dot sign on the right end of numbers
                        
                  currentNumber = Double.parseDouble(lblScreen.getText());
                  mathSignFlag = false;
                  break;
                  
               case 11:    // press +- negative number
                  currentNumber = currentNumber * -1 ;
                  if (currentNumber == 0){
                     currentNumber = 0;
                     lblScreen.setText("0");
                  }else 
                     lblScreen.setText( formatNumber(Double.toString(currentNumber)) );

                  mathSignFlag = false;
                  break;
               
                  // press +-*/ button:   mathSignFlag will be true until other buttons was pressed
               case 12:    // press / button
               case 13:    // press * button
               case 14:    // press - button
               case 15:    // press + button
                  if ( mathSign == 0 || mathSignFlag == true){      //if press calculating signs again
                     mathSign = index;                              //save the sign
                     lastNumber = currentNumber;                    //save the number which on the screen
                     savedFlag = true;                              //set up saved flag
                     
                  }else {                            //if first time press calculating signs
                     count(index);                                                          //count it
                     lblScreen.setText( formatNumber(Double.toString(currentNumber)) );     //put the result on the screen
                  }  
                  
                  mathSignFlag = true; 
                  break;
                  
               case 16:    // \/ press square root button
                  currentNumber = Math.sqrt( currentNumber );                           //count 
                  lblScreen.setText( formatNumber(Double.toString(currentNumber)));     //put the result on the screen
                  savedFlag = true;
                  mathSignFlag = false;
                  break;
                  
               case 17:    // %  press Percent sign button
                  currentNumber = currentNumber * 0.01;                                 //count
                  lblScreen.setText( formatNumber(Double.toString(currentNumber)));     //put the result on the screen
                  savedFlag = true;
                  mathSignFlag = false;
                  break;
                  
               case 18:    // 1/x  press reciprocal button
                  currentNumber = 1 / currentNumber;                                    //count
                  lblScreen.setText( formatNumber(Double.toString(currentNumber)) );    //put the result on the screen
                  savedFlag = true;
                  mathSignFlag = false;
                  break;
                  
               case 19:    // =  equal
                  count(0);                                                             //count
                  lblScreen.setText( formatNumber(Double.toString(currentNumber)) );    //put the result on the screen
                  savedFlag = true;
                  lastNumber = 0;
                  mathSignFlag = false;
                  break;
                  
               case 20:    // <-  Backspace
                  if ( savedFlag == false ){
                     if (lblScreen.getText().length() > 2){
                         
                        lblScreen.setText(lblScreen.getText().substring(0, lblScreen.getText().length()-1));
                        
                     }else if (lblScreen.getText().length() == 2){
                         
                        if (lblScreen.getText().indexOf("-") >= 0){
                           lblScreen.setText("0");
                           
                        }else{
                           lblScreen.setText(lblScreen.getText().substring(0, lblScreen.getText().length()-1));
                        }
                        
                     }else{
                        lblScreen.setText("0");
                     }
                     
                     currentNumber = Double.parseDouble(lblScreen.getText()); 
                  }
                  break;
               
               case 21:    // CE    clear currentNumber
                  currentNumber = 0;
                  lblScreen.setText("0");
                  savedFlag = false;
                  mathSignFlag = false;
                  break;
                  
               case 22:    // C     clear all
                  lastNumber = 0;
                  currentNumber = 0;
                  lblScreen.setText("0");
                  mathSign = 0;
                  savedFlag = false;
                  mathSignFlag = false;
                  break;
               
               case 23:    // MC
                  m.clear();
                  break;
                  
               case 24:    // MR
                  currentNumber = m.recall();
                  lblScreen.setText( formatNumber(Double.toString(currentNumber)) );
                  savedFlag = true;
                  mathSignFlag = false;
                  break;
                  
               case 25:    // MS
                  m.save( Double.parseDouble( lblScreen.getText() ) );
                  break;
                  
               case 26:    // M+
                  m.add( Double.parseDouble( lblScreen.getText() ) );
                  break;
                  
               case 27:    // M-
                  m.minus( Double.parseDouble( lblScreen.getText() ) );
                  break;
                  
               default :
            
            }
            
         }
      });
   }
   
   
   
   //calculation
   private void count(int MathSign){
      
      switch (mathSign){
         case 12:  // "/" division
            currentNumber = lastNumber / currentNumber;
            lastNumber = currentNumber;
            savedFlag = true;
            mathSign = MathSign;
            break;
            
         case 13:   // "*" multiply
            currentNumber = lastNumber * currentNumber;
            lastNumber = currentNumber;
            savedFlag = true;
            mathSign = MathSign;
            break;
            
         case 14:    // - subtract
            currentNumber = lastNumber - currentNumber;
            lastNumber = currentNumber;
            savedFlag = true;
            mathSign = MathSign;
            break;
            
         case 15:    // addition
            currentNumber = lastNumber + currentNumber;
            lastNumber = currentNumber;
            savedFlag = true;
            mathSign = MathSign;
            break;

         default:
            
      } 
   }

   
   
   
   
   //+++++++++++++++++++++  MAIN +++++++++++++++++
   public static void main(String[] args) {
   
      Calculator calc = new Calculator();       //create a calculator class
      
      calc.show(true);                          //show the calculator
   }
   

}

