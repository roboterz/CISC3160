# Design a mini programming language


Goal: Identify the input mathematical formula, calculate and get the result.

- This mini programming language should be able to recognize the addition, subtraction, multiplication, and division arithmetic operators + - * /, as well as the operator precedence ().
- Operation object： 0123456789
- The operation level in parentheses is the highest, followed by the multiplication and division signs, and the lowest is the addition and subtraction signs.

## Template

(1+3)*5/4 

>5

2+6/3+9*5 

>49




# Reflection
Jiongjun Liu

will work with John Ngan

The language I designed is as described above. It is a language that can understand mathematical formulas and calculate the results.

I use flex to design a mini programing language.

flex includes lex and yacc. I have discussed them in class, so I am familiar with them. No other tools have been touched. 

I am using the windows version, and after installing flex, I also need to install Dev-C++. Since the Windows version is relatively easy to install, there are no major problems.

But to figure out how to use lex and yacc, I spent a lot of time to figure it out. Find information on the Internet, watch tutorials, and watch how others use it on YouTube. All resources basically come from the Internet.
