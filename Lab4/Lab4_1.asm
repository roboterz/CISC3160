.data
	X: .word 1
.text

main:
	lw $t1, X	# let t1 = X
	li $t0, 10	# let t0 = 10
Loop:
	bge $t1, $t0, Exit	# if X(t1) great or equal to 10(t1), jump to label "Exit".
	addi $t1, $t1, 1	# X++
	j Loop			# go back to label "Loop"
Exit:
