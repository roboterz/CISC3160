# Learning MIPS Assembly Language


  In the most recent course, assembler is used to write programs. I have never used assembly language, but I have always been interested in it. Take this opportunity to study how to write assembly programs. Assembler is a language that directly manipulates machine code, and its working method is very different from the usual high-level programming languages.
  
  First of all, there are no custom variables in assembly, and the variables that can be used are limited registers. For example, $t0 to $t9. There are also some special-purpose registers. From [MIPS Reference Sheet](https://github.com/roboterz/CISC3160/blob/main/Lab4/MIPSReference.pdf), we see that the syntax is very simple, that is, the variable is followed by the instruction. The number of instructions is also very small, mainly for instructions such as loading values, displacements, additions, and jumps.
  
  Only understand these, there is no way to write assembler, even a simple "Hello world". Because assembly essentially manipulates how the machine works, not like a usual high-level language, you don’t need to care about how it works, just write the algorithm. The computer is essentially a binary adder. To write an assembly, you need to understand how the machine works.
  
First compare a piece of assembly and C language code, let x increase by 1 in a loop until x equals 10:

C Language:
```
main{
	int x=1;
	while (x < 10 ){
		x++;
	}
}
```
[MIPS](https://github.com/roboterz/CISC3160/blob/main/Lab4/Lab4_1.asm):
```
data
	X: .word 1
.text
main:
	lw $t1, X	# let t1 = X
	li $t0, 10	# let t0 = 10
Loop:
	bge $t1, $t0, Exit	  # if X(t1) great or equal to 10(t1), jump to label "Exit".
	addi $t1, $t1, 1	  # X++
	j Loop			  # go back to label "Loop"
Exit:
```

It can be seen that assembly language has no structured syntax, loops and conditional branches. Loops and conditional branches can only be realized by jumping to the sticky note. The structured syntax of the C language is very different.


Even simple multiplication and division cannot be done in one sentence. For example, for the multiplication and division in this [assembly code for calculating the BMI](https://github.com/roboterz/CISC3160/blob/main/Lab4/Lab4_4%20_Int.asm), the result of the division calculation is stored in the low register, and the remainder is stored in the high register. Because the computer central processing unit manufactured at the beginning did not have a floating-point operation unit, all operations are integers. But I ask the result to keep one decimal place, what should I do?
```
# Calculate BMI
	li $t2, 703		# conversion factor
	mult $t0, $t2		# weight * 703
	mflo $t0		# put the result to t0
	mult $t1, $t1		# height * height
	mflo $t1		# put the result to t1
	div $t0, $t1		# (weight*703) / (height*height)
	mflo $t2		# Quotient
	mfhi $t3		# remainder
	
	#Calculate the first decimal place
	li $t0, 100		# t0 as temp
	mult $t3, $t0		# remainder * 10
	mflo, $t0		# save the result on t0
	div $t0, $t1		# first decimal place = remainder * 10 / divisor
	mflo $t3		# save the first decimal place on t3
```
As you can see from the code, I used the remainder to be multiplied by 10 and then divided by the divisor. The quotient obtained is the value of the first decimal place. This is no longer a question of grammar, it is a mathematical principle, how to find the decimal of the quotient. Because the assembly is very simple, we need to learn the principles of complex calculations and learn how to use simple calculations to solve complex problems. This is the focus of assembly. To learn to use assembly, you must learn machine principles, not assembly syntax.

Later, the central processing unit had a floating-point unit. As the machine is different, we need to modify the corresponding assembly statement. Example of division using floating point operations: [Calculate BMI code line32-36](https://github.com/roboterz/CISC3160/blob/main/Lab4/Lab4_4_float.asm)

```
	# Calculate BMI
	lwc1 $f2, conversion_factor	# conversion factor is 703
	mul.s $f0, $f0, $f2	# f0 = weight * 703
	mul.s $f1, $f1, $f1	# f1 = height * height
	div.s $f2,$f0, $f1	# f2 = (weight*703) / {height * height}
```
