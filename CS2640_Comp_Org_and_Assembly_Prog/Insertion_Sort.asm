#  Author:      	Auraiporn Auksorn
#  Date:        	06/17/2020
#  Description: 	This program will prompt the user to input 10 numbers, and the program will ask the user to choose 
#	        	how to sort the numbers, in descending order or ascending order.
#	        	Then, it will print out sorted numbers according to the order that the user has chosen.
 
	#_________________________________________________MACRO________________________________________________________________#
	
	.macro print_str (%str) 					# This macro use to print the string
	.data
		myLabel: .asciiz %str
			 .text
				li 	$v0, 4				# set up print string syscall 
				la 	$a0, myLabel				# argument to print string
				syscall					# tell the OS to do the syscall 
	.end_macro
	.macro exit_program    						# This macro terminates a program
		li 	$v0, 10						# set up exit syscall --> number 10 is the number for exit syscall 
		syscall							# tell the OS to do the syscall 
	.end_macro
	.macro print_int %d
		li 	$v0, 1						# Print integer
		move	$a0, $s0
		syscall							# tell the OS to do the syscall 
	.end_macro
	
	#______________________________________________________________________________________________________________________#

.data	
	array: 		.space 	40					# An array of word to store integers; 1 word = 4 bytes --> 10 words = 40 bytes
	userInput: 	.space 	20					# To get the user input (prompt for the size)
	length: 	.word 	10					# Set the defaut size of the array
.text
	main:
	
	#_________________________________________________Insertion Sort________________________________________________________#
	
	# 		void insertionSort(int array[], int size) {
     	# 		for (int step = 1; step < size; step++) {
	# 		int key = array[step];
    	# 		int j = step - 1;
	#    		// For descending order, change key<array[j] to key>array[j].
	#    			while (key < array[j] && j >= 0) {
	#     				array[j + 1] = array[j];
	#     				--j;
	#   			}
	#   			array[j + 1] = key;
	# 			}
	#		}
	
	#_________________________________________________Variables Declaration	_________________________________________________#
	
	# 		
	# 	base address of Array in 				$t0
	#	size of Array in 					$t1
	#	step in							$t2
	# 	j in 							$t3
	# 	key in 						 	$t4
	# 	value of each index in Array[index] in 			$t5
	# 	calculate the address of each index in Array[index] in 	$t6
	#	address of Array[index] = base address of Array + index*(element size)
	
	#_______________________________________________Prompt 10 Numbers from a User___________________________________________#
	
	print_str("Please enter 10 numbers to be sorted\n")
	jal 	initialize_for_loop					# Jump to program statement at initialize_for_loop and 		
	li 	$t4, 1000000000						# Load immediate 1000000000 to $t4; use this part to handle exception 
	get_numbers:
		bge	$t2, $t1, end_get_numbers			# Exit the loop, if ($t2 > $t1); in other words, it means while ( i < length).
		print_str("Please enter #")				# Use .macro to print the string
		li	$v0, 1						# Load 1 into $v0; 1 is to print integer
		addi	$a0, $t2, 1					# $a0 = $t2+1; $a0 = i + 1 --> Keep track of a current entered number, starting from 1
		syscall							# Tell the OS to do the syscall 
		print_str(":   ")					# Use .macro to print the string
		jal 	read_int					# Jump to program statement "read_int"
		slt 	$t3, $v0, $t4					# Check for invalid input
		beqz 	$t3, invalid_numbers	
		sw	$v0, 0($t0)					# Store numbers entered in an array
		addi	$t0, $t0, 4					# Increment array pointer by 4.
		addi	$t2, $t2, 1					# Increment the loop counter by 1; i++
		j	get_numbers					# Go back to the looping process
	end_get_numbers:
		print_str("\nThe initial capacity is 10.\n\n          But!!!!!\n\nIf you continue this program, you can CUSTOMIZE the SIZE of your array!")
		j 	print_entered_numbers				# Jump unconditionaly to the 'print_entered_numbers'
	
	#_____________________________________________Print the Numbers Entered__________________________________________________#

	print_entered_numbers:
		jal 	initialize_for_loop				# Copy program counter (return address) to register $ra (return address register)
		print_str("\n\nThe array of UNSORTED numbers is: ")	
		jal	print						# Call the procedure "print"
		jal 	how_to_sort					# Call the proocedure "how_to_sort"
 
 	#________________________________________Insertion Sort in Descending Order______________________________________________#
  	
  	descending_order:
  	  	jal 	initialize_for_loop				# Call "initialize_for_loop"
	for_loop:
		slt	$t3, $t2, $t1 			        	# if($t2 < $t1), $t3 = 1, else $t3 = 0
		beqz	$t3, end_for_loop				# brach to end_for_loop since $t3= 0
		addi	$t3, $t2, -1					# $t3 = $t2 - 1; j = step - 1
		sll 	$t6, $t2, 2 					# Multiply the offset by 4; step*4 ; 1 word = 4 bytes
		add 	$t6, $t0, $t6					# $t6 = $t0 + $t6; Array[step]
		lw 	$t4, 0($t6)					# $t4 = 0($t6);  key = Array[step];
	while_loop: 
		blt 	$t3, 0, end_while_loop				# if($t3>0); if(j > 0)?; (if $t3<0) --> exit the loop
		sll 	$t6, $t3, 2					# Multiply the offset(j) by 4
		add 	$t6, $t0, $t6					# $t6 = $t0 + $t6; Array[j]
		lw 	$t5, 0($t6)					# $t5 = 0($t6) ; $t5 = Array[j]
		bge 	$t5, $t4, end_while_loop			# if(Array[j] > key), end while loop, but if(Array[j] < key) continue sorting in descending order
		sw 	$t5, 4($t6)					# $t5 = 4($t6); Array[j+1] = Array[j]
		addi 	$t3, $t3, -1					# --j; Decrement the inner for loop; smaller value are moving up.
		b 	while_loop					# Branch to while loop again
	end_while_loop:
		sll 	$t6, $t3, 2 					# Multiply the offset by 4
		add 	$t6, $t0, $t6					# Array[j]
		sw 	$t4, 4($t6)					# key = Array[j+1]
		addi 	$t2, $t2, 1					# Increment the loop; step++
		b 	for_loop					# Branch to for loop again
	end_for_loop:
		print_str("\nThis is the array in DESCENDING SORTED order:   ")
		j 	display_sorted_array				# Jump to display_sorted_array
		
	#____________________________________________Insertion Sort in Ascending Order___________________________________________#
  	
  	ascending_order:
		jal 	initialize_for_loop				# Call "initialize_for_loop"
	for_loop1:
		slt	$t3, $t2, $t1 			       		# If($t2 < $t1), $t3 = 1, else $t3 = 0
		beqz	$t3, end_for_loop1				# Brach to end_for_loop since $t3= 0
		addi	$t3, $t2, -1					# $t3 = $t2 - 1; j = step - 1
		sll 	$t6, $t2, 2 					# Multiply the offset by 4; step*4 ; 1 word = 4 bytes
		add 	$t6, $t0, $t6					# $t6 = $t0 + $t6; Array[step]
		lw 	$t4, 0($t6)					# $t4 = 0($t6);  key = Array[step];
	while_loop1: 
		blt 	$t3, 0, end_while_loop1				# if($t3>0); if(j > 0)?; (if $t3<0) --> exit the loop
		sll 	$t6, $t3, 2					# Multiply the offset(j) by 4
		add 	$t6, $t0, $t6					# $t6 = $t0 + $t6; Array[j]
		lw 	$t5, 0($t6)					# $t5 = 0($t6) ; $t5 = Array[j]
		blt 	$t5, $t4, end_while_loop1			# if(Array[j] > key), end while loop, but if(Array[j] < key) continue sorting in ascending order
		sw 	$t5, 4($t6)					# $t5 = 4($t6); Array[j+1] = Array[j]
		addi 	$t3, $t3, -1					# --j; Decrement the inner for loop; smaller value are moving up.
		b 	while_loop1					# Branch to while loop again
	end_while_loop1:
		sll 	$t6, $t3, 2 					# Multiply the offset by 4
		add 	$t6, $t0, $t6					# Array[j]
		sw 	$t4, 4($t6)					# key = Array[j+1]
		addi 	$t2, $t2, 1					# Increment the loop; step++
		b 	for_loop1					# Branch to for loop again
	end_for_loop1:
		print_str("\nThis is the array in ASCENDING SORTED order:   ")
		j	display_sorted_array				# Jump to display_sorted_array
		
	#_________________________________________________Display Sorted Array__________________________________________________#
	
	display_sorted_array: 
		jal 	initialize_for_loop				# Call "initialize_for_loop"
		jal	print						# Call the "print" function
		print_str("\n\n                        Done!!!!                        \n\n")
		jal	continue
		
	#______________________________________Ask a User to Resize the Capacity of the Array___________________________________#
	
	resize_array:
	 	print_str("\nThe default capacity was 10. The maximun capacity is also 10.\n")
		print_str("Now, it is your turn to enter the new capacity of array:   ")
		jal 	read_int					# Call the procedure "read_int"
		move 	$s0, $v0					# Store the value of $v0 in $s0
		jal 	initialize_for_loop				# Call the procedure "initialize_for_loop"
		slt 	$t4, $s0, $t1					# If(user input > length)
		beqz 	$t4, invalid					# Print out the invalid message
		sge 	$t4, $s0, -1					# If(user input < -1); the size of array cannot be a negative number 
		beqz 	$t4, invalid					# Print out the invalid message 
		sne 	$t4, $s0, $zero 				# If(user input = 0)
		beqz 	$t4, exception0					# Print out the exception message		
	resize:
		bge 	$t2, $s0, end_resize				# If(user input > length), end resize the array
		print_str("Please enter #")				# Use .macro to print the string
		li	$v0, 1						# Load 1 into $v0; 1 is to print integer
		addi	$a0, $t2, 1					# $a0 = $t2+1; $a0 = i + 1 --> Keep track of a current entered number, starting from 1
		syscall							# Tell the OS to do the syscall 
		print_str(":   ")					# Use .macro to print the string
		jal read_int						# Jump to program statement "read_int"
		sw	$v0, 0($t0)					# Store numbers entered in an array
		addi	$t0, $t0, 4					# Increment array pointer by 4.
		addi	$t2, $t2, 1					# Increment the loop counter by 1; i++
		j	resize						# Jump unconditionally to resize 
	end_resize:
		print_str("\nThere are ")
		print_int %$s0						# Use .macro to print an integer
		print_str(" numbers added in a resizable array. Now, ")
		print_str("\n\nThe array of UNSORTED numbers is: ")
	print_resize:	
		la 	$t0, array					# Load address of Array to $t0
		li 	$t2, 0 						# Loop counter
	print1:
		bge	$t2, $s0, end_print1				# If (loop counter > user input), then end print
		li	$v0, 1						# 1 is used to print an integer
		lw	$a0, 0($t0)					# $a0 = 0($t0)
		syscall							# Tell the OS to do the syscall 
		print_str(" ")
		addi	$t0, $t0, 4					# Increment the index of an array
		addi	$t2, $t2, 1					# Increment the loop counter
		j	print1						# Jump back to the loop to print
	end_print1: 
		print_str("\n")
		jal 	how_to_sort		
	invalid: 
		print_str("\n           Invalid!!!!\n\nPlease enter the size of a new array in the range:(0 < Capacity of the array < 10)\n")
		j	resize_array					# Jump unconditionally to resize_array
	exception0:
		print_str("\n	        Invalid!!!!\nThere is no new element added!\n\nPlease enter the size of a new array in the range:(0 < Capacity of the array < 10)\n ")
		j 	resize_array					# Jump unconditionally to resize_array
		
	#_______________________________________________________Functions________________________________________________________#
	
	# Print the invalid message of the numbers 
	invalid_numbers: 
		print_str("A number is out of range!\n")  		# Print invalid message when the user enters an invalid number to the array 
		print_str("Please enter a number less than 10 digits (i.e.,1000000000)\n")
		jr 	$ra 						# Jump to return address in $ra (stored by jal instruction)
		
	#_________________________________________________________________________________________________________________________#	
	
	# Ask the user to choose how to sort the numbers i.e., in ascending order or descending order
	how_to_sort:
		print_str("\nHow would you like to print the sorted numbers?\nType '0' Descending order or '1' Ascending order:   ")
		jal 	read_int 					# Call "read_int" function
		move 	$s0, $v0 					# Set $s0 = $v0
		li	$t2, 1						# Load immediate 1 to $t2 
		beq 	$s0, $t2, ascending_order			# Check if (the user input = 1), then go to print in ascending order
		beq	$s0, $zero, descending_order			# Check if (the user input = 0), then go to print in descending order
		sne  	$t3, $s0, 1 	 				# If(user input != 1), print invalid message
		j 	invalid_num					# Jump unconditionally to invalid_num
		sne 	$t3, $s0, $zero 				# If(user input != 0), print invalid message
		j 	invalid_num					# Jump unconditionally to invalid_num
	invalid_num: 
		print_str("\n           Invalid!!! \n\nPlease enter only '1' Ascending order or '0' Descending order\n")
		j 	how_to_sort					# Jump unconditionally to how_to_sort
		jr 	$ra						# Jump to return address in $ra (stored by jal instruction)
	
	#_________________________________________________________________________________________________________________________#
	
	# Read integer
	read_int:
		li	$v0, 5						# 5 = read_int syscall.
		syscall							# Tell the OS to do the syscall 
		jr 	$ra						# Jump to return address in $ra (stored by jal instruction)
	
	#_________________________________________________________________________________________________________________________#
		
	# Initialization of for loop
	initialize_for_loop:	
		la 	$t0, array					# Load address of Array to $t0
		lw 	$t1, length					# St1 = size
		li 	$t2, 0 						# Loop counter
		jr	$ra						# Jump to return address in $ra (stored by jal instruction)
	
	#_________________________________________________________________________________________________________________________#
	
	# Print the array
	print:
		bge	$t2, $t1, end_print				# If (loop counter > length), then end print
		li	$v0, 1						# 1 is used to print an integer
		lw	$a0, 0($t0)					# $a0 = 0($t0)
		syscall							# Tell the OS to do the syscall 
		print_str(" ")
		addi	$t0, $t0, 4					# Increment the index of an array
		addi	$t2, $t2, 1					# Increment the loop counter
		j	print						# Jump back to the loop to print
	end_print: 
		print_str("\n")
		jr 	$ra						# Jump to return address in $ra (stored by jal instruction)
	
	#_________________________________________________________________________________________________________________________#
	
	# Check if the user would like to continue the program
	continue:	
		print_str("\nDo you want to continue?\nType '1' yes or Type '0' no:     ")
		jal 	read_int					# Call the procedure "read_int"
		move 	$s0, $v0					# Set $s0 = $v0
		li 	$t2, 1						# Load immediate 1; use this to check the condition
		beq 	$t2, $s0, resize_array				# Check if (the user input = 1), then continue the program
		beq 	$s0, $zero, exit				# Check if (the user input = 0), then exit the program
		sne  	$t3, $s0, 1 	 				# Check if (the user input != 1)
		j 	invalid_number					# Print invalid message
		sne 	$t3, $s0, $zero 				# Check if (the user input != 0)
		j 	invalid_number					# Print invalid message
	invalid_number: 
		print_str("\n           Invalid!!! \n\nPlease enter only '1' Yes or '0' No\n")
		j 	continue					# Jump to continue
		jr 	$ra
	
	#_________________________________________________________________________________________________________________________#
	
	# Use .macro to exit the program
	exit:
		print_str("\n\n                      Bye bye!!!\n\n                  See you later!!!!!\n")
		exit_program	
							
		
		
