import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.lang.Math;


// class for function save and recall a number
class Memory {

   double number;
   
   Memory() {
      number = 0;
   }
   
   // M+
   public void add(double Number) {
      number += Number;
   }
   
   // M-
   public void minus(double Number) {
      number -= Number;
   }
   
   // MR
   public double recall() {
      return number;
   }
   
   // MC
   public void clear() {
      number = 0;
   }
   
   // MS
   public void save(double Number) {
      number = Number;   
   }
}


// class Calculator 
public class Calculator{

   // define variable
   final int MAX_LENGTH = 16;
   double lastNumber;
   double currentNumber;
   boolean saved;          // saved number to lastNumber
   boolean mathSignFlag;   // mathSign pressed
   int mathSign;
   Memory memory;          // memory for save and recall function
   
   // interface
   JFrame frame = new JFrame("Calculator");
   JPanel panel = new JPanel();
   JLabel label = new JLabel("0",  JLabel.RIGHT);
   JButton [] button = new JButton[28];
   
   
   Calculator(){
      // initialize variable
      lastNumber = 0;
      currentNumber = 0;
      saved = false;          
      mathSignFlag = false;   
      mathSign = 0;           
      memory = new Memory();  
   
      // interface ---------------------------------------
      
      // window
      frame.setSize(330, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);  
      frame.add(panel);

      panel.setLayout(null);
      
      // display text label
      label.setBounds(10,10,295,65);
      label.setFont(new Font("Arial", 0, 30));
      label.setOpaque(true);
      label.setBackground(Color.WHITE);
      label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      panel.add(label);
      
      /********
         index: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18  19 20 21 22 23 24 25 26 27
        symbol: 0 1 2 3 4 5 6 7 8 9 .  +- /  *  -  +  \/ %  1/x =  <- CE C  MC MR MS M+ M-
      ********/
      
      // Button
      createButton(23, "MC", 10, 100, 55, 35);
      createButton(24, "MR", 70, 100, 55, 35);
      createButton(25, "MS", 130, 100, 55, 35);
      createButton(26, "M+", 190, 100, 55, 35);
      createButton(27, "M-", 250, 100, 55, 35);
      createButton(20, "\u232B", 10, 140, 55, 35);
      createButton(21, "CE", 70, 140, 55, 35);
      createButton(22, "C", 130, 140, 55, 35);
      createButton(11, "\u00B1", 190, 140, 55, 35);
      createButton(16, "\u221A", 250, 140, 55, 35);
      createButton(7, "7", 10, 180, 55, 35);
      createButton(8, "8", 70, 180, 55, 35);
      createButton(9, "9", 130, 180, 55, 35);
      createButton(12, "/", 190, 180, 55, 35);
      createButton(17, "%", 250, 180, 55, 35);
      createButton(4, "4", 10, 220, 55, 35);
      createButton(5, "5", 70, 220, 55, 35);
      createButton(6, "6", 130, 220, 55, 35);
      createButton(13, "*", 190, 220, 55, 35);
      createButton(18, "\u215F\u2CAD", 250, 220, 55, 35);
      createButton(1, "1", 10, 260, 55, 35);
      createButton(2, "2", 70, 260, 55, 35);
      createButton(3, "3", 130, 260, 55, 35);
      createButton(14, "-", 190, 260, 55, 35);
      createButton(19, "=", 250, 260, 55, 75);
      createButton(0, "0", 10, 300, 115, 35);
      createButton(10, ".", 130, 300, 55, 35);
      createButton(15, "+", 190, 300, 55, 35);

   }
   
   // show
   public void show( boolean value ){
      frame.setVisible(value);
   }
   
   // format number
   private String formatNumber(String str){
      
      int idx = str.indexOf("E");  // scientific notation

      // remove zero
      if (str.indexOf(".") > 0 && idx < 0 ){
         str = str.replaceAll("0+?$", "");
         str = str.replaceAll("[.]$", "");
      }

      // format scientific notation
      if (str.length() > MAX_LENGTH)
         if ( idx > 0 )
            str = str.substring(0, MAX_LENGTH - (str.length() - idx)- 1 ) + str.substring(idx);
         else
            str = str.substring(0, MAX_LENGTH);
            
      return str;
   }
   
   
   // create button
   private void createButton(int index, String Name, int left, int top, int weight, int high ){
      
      button[index] = new JButton(Name);
      button[index].setBounds(left, top, weight, high);
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
               // number
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
                  if (saved){             
                     label.setText(Name);    //new number
                     saved = false;
                  }else{
                     if ( label.getText().length() < MAX_LENGTH )
                        // if current number is not zero, append number to current number 
                        label.setText( (label.getText()== "0")? Name: (label.getText() + Name));
                  }
                  currentNumber = Double.parseDouble(label.getText());
                  mathSignFlag = false;
                  break;
                  
               case 10:    // "."
                  if (saved){
                     label.setText("0" + Name);
                     saved = false;
                  }else
                     if ( label.getText().length() < MAX_LENGTH && label.getText().indexOf(".") < 0 )
                        label.setText( label.getText() + Name );
                        
                  currentNumber = Double.parseDouble(label.getText());
                  mathSignFlag = false;
                  break;
                  
               case 11:    // +- negative number
                  currentNumber = currentNumber * -1 ;
                  if (currentNumber == 0){
                     currentNumber = 0;
                     label.setText("0");
                  }else 
                     label.setText( formatNumber(Double.toString(currentNumber)) );

                  mathSignFlag = false;
                  break;
                  
               case 12:    // /
               case 13:    // *
               case 14:    // -
               case 15:    // +     
                  if ( mathSign == 0 || mathSignFlag == true){
                     mathSign = index;
                     lastNumber = currentNumber;
                     saved = true;
                  }else {
                     count(index);
                     label.setText( formatNumber(Double.toString(currentNumber)) );
                  }  
                  mathSignFlag = true; 
                  break;
                  
               case 16:    // \/ square root
                  currentNumber = Math.sqrt( currentNumber );
                  label.setText( formatNumber(Double.toString(currentNumber)));
                  saved = true;
                  mathSignFlag = false;
                  break;
                  
               case 17:    // %  percent
                  currentNumber = currentNumber * 0.01;
                  label.setText( formatNumber(Double.toString(currentNumber)));
                  saved = true;
                  mathSignFlag = false;
                  break;
                  
               case 18:    // 1/x   denominator
                  currentNumber = 1 / currentNumber;
                  label.setText( formatNumber(Double.toString(currentNumber)) );
                  saved = true;
                  mathSignFlag = false;
                  break;
                  
               case 19:    // =  equal
                  count(0);
                  label.setText( formatNumber(Double.toString(currentNumber)) );
                  saved = true;
                  lastNumber = 0;
                  mathSignFlag = false;
                  break;
                  
               case 20:    // <-  Backspace
                  if ( saved == false ){
                     if (label.getText().length() > 2)
                        label.setText(label.getText().substring(0, label.getText().length()-1));
                     else if (label.getText().length() == 2)
                        if (label.getText().indexOf("-") >= 0)
                           label.setText("0");
                        else
                           label.setText(label.getText().substring(0, label.getText().length()-1));
                     else
                        label.setText("0");
                     
                     currentNumber = Double.parseDouble(label.getText()); 
                  }
                  break;
               
               case 21:    // CE    clear currentNumber
                  currentNumber = 0;
                  label.setText("0");
                  saved = false;
                  mathSignFlag = false;
                  break;
               case 22:    // C     clear all
                  lastNumber = 0;
                  currentNumber = 0;
                  label.setText("0");
                  mathSign = 0;
                  saved = false;
                  mathSignFlag = false;
                  break;
               
               case 23:    // MC
                  memory.clear();
                  break;
               case 24:    // MR
                  currentNumber = memory.recall();
                  label.setText( formatNumber(Double.toString(currentNumber)) );
                  saved = true;
                  mathSignFlag = false;
                  break;
               case 25:    // MS
                  memory.save( Double.parseDouble( label.getText() ) );
                  break;
               case 26:    // M+
                  memory.add( Double.parseDouble( label.getText() ) );
                  break;
               case 27:    // M-
                  memory.minus( Double.parseDouble( label.getText() ) );
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
            saved = true;
            mathSign = MathSign;
            break;
            
         case 13:   // "*" multiply
            currentNumber = lastNumber * currentNumber;
            lastNumber = currentNumber;
            saved = true;
            mathSign = MathSign;
            break;
            
         case 14:    // - subtract
            currentNumber = lastNumber - currentNumber;
            lastNumber = currentNumber;
            saved = true;
            mathSign = MathSign;
            break;
            
         case 15:    // addition
            currentNumber = lastNumber + currentNumber;
            lastNumber = currentNumber;
            saved = true;
            mathSign = MathSign;
            break;

         default:
            
      } 
   }

   
   
   //+++++++++++++++++++++  MAIN +++++++++++++++++
   public static void main(String[] args) {
   
      Calculator calc = new Calculator();
      
      calc.show(true);
   }
   

}

