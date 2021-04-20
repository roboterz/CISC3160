#print out the result of 1+2+3+4+...+N

.data
	message: .asciiz "Please enter a number N: "
.text

main:
	# print out the message
	li $v0, 4		# set the syscall as print string
	la $a0, message		# the message to be print
	syscall			# print
	
	#read the number from the input
	li $v0, 5		# set the syscall as read int
	syscall			# read
	move $t0, $v0		# let t0 to the save the number from the input
	
	li $t1, 0		# define t1 to save the result
	
Loop:
	ble $t0, 0, Exit	# if N<=0, jump to "Exit"
	add $t1, $t1, $t0	# t1 = t1 + N
	subi $t0, $t0, 1	# N--
	j Loop			# go back to "Loop"
Exit:
	li $v0, 1		# set the syscall as print int
	move $a0, $t1		# the result to be print
	syscall			# print