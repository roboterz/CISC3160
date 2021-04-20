#print out the result about base * times

.data
	base: .word 3
	times: .word 6
.text

main:
	lw $t0, base		# let t0 = base
	lw $t1, times		# let t1 = times
	li $t2, 0		# let t2 = 0, the result will be save in t2
Loop:
	ble $t1, 0, Exit	# if times(t1) less or equal than zero, jump to "Exit"
	add $t2, $t2, $t0	# t2 = t2 + base
	subi $t1, $t1, 1	# t1--
	j Loop			# go back to "Loop"
Exit:
	li $v0, 1		# set the syscall as print int
	move $a0, $t2		# the result to be print
	syscall			# pirnt
