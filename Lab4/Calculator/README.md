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
   panel.setLayout(null);
```

Add other components on the form：
```
      // display text label
      label.setBounds(10,10,295,65);
      label.setFont(new Font("Arial", 0, 30));
      label.setOpaque(true);
      label.setBackground(Color.WHITE);
      label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      panel.add(label);
      
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
      ...
```
