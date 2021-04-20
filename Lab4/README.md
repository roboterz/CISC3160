# Learning MIPS Assembly Language


  In the most recent course, assembler is used to write programs. I have never used assembly language, but I have always been interested in it. Take this opportunity to study how to write assembly programs. Assembler is a language that directly manipulates machine code, and its working method is very different from the usual high-level programming languages.
  
  First of all, there are no custom variables in assembly, and the variables that can be used are limited registers. For example, $t0 to $t9. There are also some special-purpose registers. The syntax is very simple, that is, the variable is followed by the instruction. The number of instructions is also very small, mainly for instructions such as loading values, displacements, additions, and jumps.
  
  Only understand these, there is no way to write assembler, even a simple "Hello world". Because assembly essentially manipulates how the machine works, not like a usual high-level language, you don’t need to care about how it works, just write the algorithm. The computer is essentially a binary adder. To write an assembly, you need to understand how the machine works.
  
First compare a piece of assembly and C language code, let x increase by 1 in a loop until x equals 10:

C Language:
main{
	int x=1;
	while (x < 10 ){
		x++;
	}
}

MIPS:
data
	X: .word 1
.text
main:
	lw $t1, X	# let t1 = X
	li $t0, 10	# let t0 = 10
Loop:
	bge $t1, $t0, Exit	  # if X(t1) great or equal to 10(t1), jump to label "Exit".
	addi $t1, $t1, 1	    # X++
	j Loop			          # go back to label "Loop"
Exit:
