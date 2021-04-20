#Calculate the Body Mass Index
.data
	msg_weight: 	.asciiz	"Please enter your weight(lb): "
	msg_height:	.asciiz	"Please enter your height(in): "
	msg_BMI:	.asciiz	"Your Body Mass Index is: "
	dot:		.ascii "."
.text

main:
	# print out the message to get the weight
	li $v0, 4		# set the syscall as print string
	la $a0, msg_weight	# the message to be print
	syscall			# print
	
	#read the weight from the input
	li $v0, 5		# set the syscall as read int
	syscall			# read
	move $t0, $v0		# let t0 as the weight
	
	# print out the message to get the height
	li $v0, 4		# set the syscall as print string
	la $a0, msg_height	# the message to be print
	syscall			# print
	
	#read the height from the input
	li $v0, 5		# set the syscall as read int
	syscall			# read
	move $t1, $v0		# let t1 as the height

	# Calculate BMI
	li $t2, 703		#
	mult $t0, $t2		# t0 = weight * 703
	mflo $t0
	mult $t1, $t1		# t1 = height * height
	mflo $t1
	div $t0, $t1		# t2 = (weight*703) / (height*height)
	mflo $t2		# Quotient
	mfhi $t3		# remainder
	
	#Calculate the first decimal place
	li $t0, 100		# t0 as temp
	mult $t3, $t0		# remainder * 10
	mflo, $t0		#
	div $t0, $t1		# first decimal place = remainder * 10 / divisor
	mflo $t3		# first decimal place
	
	# print out the message of BMI
	li $v0, 4		# set the syscall as print string
	la $a0, msg_BMI		# the message to be print
	syscall			# print
	
	# print out the integer part of BMI
	li $v0, 1		# set the syscall as print float
	move $a0, $t2		# load the result ready to print
	syscall			# print
	
	# print out Decimal point "."
	li $v0, 4		# set the syscall as print string
	la $a0, dot		# the message to be print
	syscall			# print
	
	# print out the decimal part of BMI
	li $v0, 1		# set the syscall as print float
	move $a0, $t3		# load the result ready to print
	syscall			# print
