#Calculate the Body Mass Index
.data
	msg_weight: 	.asciiz	"Please enter your weight(lb): "
	msg_height:	.asciiz	"Please enter your height(in): "
	msg_BMI:	.asciiz	"Your Body Mass Index is: "
	conversion_factor:	.float 703
.text

main:
	# print out the message to get the weight
	li $v0, 4		# set the syscall as print string
	la $a0, msg_weight	# the message to be print
	syscall			# print
	
	#read the weight from the input
	li $v0, 5		# set the syscall as read int
	syscall			# read
	mtc1 $v0, $f0		# let f0 as the weight
	cvt.s.w $f0, $f0	# covert to float
	
	# print out the message to get the height
	li $v0, 4		# set the syscall as print string
	la $a0, msg_height	# the message to be print
	syscall			# print
	
	#read the height from the input
	li $v0, 5		# set the syscall as read int
	syscall			# read
	mtc1 $v0, $f1		# let f1 as the height
	cvt.s.w $f1, $f1	# covert to float

	# Calculate BMI
	lwc1 $f2, conversion_factor
	mul.s $f0, $f0, $f2	# f0 = weight * 703
	mul.s $f1, $f1, $f1	# f1 = height * height
	div.s $f2,$f0, $f1	# f2 = (weight*703) / {height * height}
		
	# print out the message of BMI
	li $v0, 4		# set the syscall as print string
	la $a0, msg_BMI		# the message to be print
	syscall			# print
	
	# print out the number of BMI
	li $v0, 2		# set the syscall as print float
	mov.s $f12, $f2		# load the result ready to print
	syscall			# print
	
	li $v0, 10
	syscall
