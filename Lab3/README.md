# Design a mini programming language


Goal: Identify the input mathematical formula, calculate and get the result.

- This mini programming language should be able to recognize the addition, subtraction, multiplication, and division arithmetic operators + - * /, as well as the operator precedence ().
- Operation object： 0123456789
- The operation level in parentheses is the highest, followed by the multiplication and division signs, and the lowest is the addition and subtraction signs.

## Template

>(1+3)*5/4 

5.0

>2+6/3+9*5 

49.0




# Reflection
Jiongjun Liu

will work with John Ngan

The language I designed is as described above. It is a language that can understand mathematical formulas and calculate the results.

I use lex and yacc to design a mini programing language.

I have discussed them in class, so I am familiar with them. I used the lex and yacc toolkit ply in python. Before use lex and yacc in python. Need to install ply, the installation command is as follows:
pip install ply

I am using the windows. Since the Windows version is relatively easy to install, there are no major problems.

But to figure out how to use lex and yacc, I spent a lot of time to figure it out. Find information on the Internet, watch tutorials, and watch how others use it on YouTube. All resources basically come from the Internet.

The ply package provides some interface functions. As long as you use the corresponding interface functions, you can easily process statements. First use lex to process the input sentence, divide it into various takons, and then use yacc to construct the grammar. p_expression_binop establishes the calculation method. p_expression_group handles high-priority characters. There are many interfaces in ply that can handle other information. You can also handle error messages easily. I still need time to find out how to use the other interfaces it provides.

