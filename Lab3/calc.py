

import ply.lex as lex
import ply.yacc as yacc


#token
tokens = ('NUMBER','PLUS','MINUS','TIMES','DIVIDE','LPAREN','RPAREN',)

t_PLUS    = r'\+'
t_MINUS   = r'-'
t_TIMES   = r'\*'
t_DIVIDE  = r'/'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'

#input number
def t_NUMBER(n):
    r'\d+'
    n.value = int(n.value)
    return n

def t_newline(n):
    r'\n+'
    n.lexer.lineno += n.value.count("\n")

#error message
def t_error(n):
    print(f"{n.value[0]!r}")
    n.lexer.skip(1)
    
#lex
lex.lex()

# precedence rules
precedence = (('left','PLUS','MINUS'),('left','TIMES','DIVIDE'),('right','UMINUS'),)

#variables names
names = { }


#output
def p_statement_expr(n):
    'statement : expression'
    print(n[1])

#Addition, subtraction, multiplication and division
def p_expression_binop(n):
    '''expression : expression PLUS expression
                  | expression MINUS expression
                  | expression TIMES expression
                  | expression DIVIDE expression'''
    if n[2] == '+'  :
        n[0] = n[1] + n[3]
    elif n[2] == '-': 
        n[0] = n[1] - n[3]
    elif n[2] == '*': 
        n[0] = n[1] * n[3]
    elif n[2] == '/': 
        n[0] = n[1] / n[3]

#negative number
def p_expression_uminus(n):
    'expression : MINUS expression %prec UMINUS'
    n[0] = -n[2]

#parentheses
def p_expression_group(n):
    'expression : LPAREN expression RPAREN'
    n[0] = n[2]

#number
def p_expression_number(n):
    'expression : NUMBER'
    n[0] = n[1]

#yacc
yacc.yacc()

while True:
    try:
        n = input('> ')
    except EOFError:
        break
    yacc.parse(n)