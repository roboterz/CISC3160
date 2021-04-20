# Calculator created with java

![Calculator](https://github.com/roboterz/CISC3160/blob/main/Lab4/Calculator/Calculator.png)

This calculator interface uses swing components. Swing is a GUI toolkit designed for Java, which is part of the basic Java classes. Swing includes graphical user interface functions, and its components include: text boxes, text fields, buttons, tables, lists... and so on. They are written in pure Java, so they can run across platforms like Java itself.

To create a framework with 330 wide and 400 height, the code is as follows: ([Calculator Code line57-78](https://github.com/roboterz/CISC3160/blob/main/Lab4/Calculator/Calculator.java))
```
   // interface
   JFrame frame = new JFrame("Calculator");
   JPanel panel = new JPanel();
   JLabel label = new JLabel("0",  JLabel.RIGHT);
   JButton [] button = new JButton[28];
   
    // Calculator frame
    frame.setSize(330, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);  
    frame.add(panel);
```
