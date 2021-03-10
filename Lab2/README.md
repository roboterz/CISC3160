# Design a mini programming language


Goal: Identify the input mathematical formula, calculate and get the result.

- This mini programming language should be able to recognize the addition, subtraction, multiplication, and division arithmetic operators + - * /, as well as the operator precedence ().
- Operation object： 0123456789
- The operation level in parentheses is the highest, followed by the multiplication and division signs, and the lowest is the addition and subtraction signs.

## Template

(1+3)*5/4 
5
2+6/3+9*5 
49
L 5 5 5 5 5 
> 25
Q 4 4 4 4 4
> 20
R 0 4 4 4 0
> 12
T
> 57
