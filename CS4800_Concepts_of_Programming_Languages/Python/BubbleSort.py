#use bubble sort to swap elements if they are in wrong order
def bubbleSort(list):

    #go over through all array elements
    for i in range(len(list) - 1):

        #last i element is in place
        for j in range(0, len(list) - i - 1):

            #move through the array and swap if the element found is greater than the next one
            if list[j] > list[j + 1]:
                list[j], list[j + 1] = list[j + 1], list[j]

#taking multiple inputs at a time and type casting using list()function
numbers = list(map(int, input("Enter any numbers seperate by a space: ").split()))

#ask user to input a choice
user_input = ""

#check invalid input
while user_input not in ["A", "a", "D", "d"]:
    print("Would you like to sort in Ascending Order or Descending Order\nType 'A' or 'a' : Ascending 'D' or 'd' : Descending  ")
    user_input = str(input())
if user_input in ["A", "a", "D", "d"]:
    if user_input == "A" or user_input.lower() == "a":
        bubbleSort(numbers)
        print("Sorting numbers by Ascending Order: ", numbers)
        print("Good bye")
    if user_input == "D" or user_input.lower() == "d":
        bubbleSort(numbers)
        numbers.reverse()
        print("Sorting numbers by Descending Order: ", numbers)
        print("Good bye")