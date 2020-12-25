# The program illustrates a general sort method that can sort any type of data arrays/lists.
# For example, can sort a list of integers in ascending order, a list of integers in descending
# order, a list of doubles, a list of student objects (with names and scores) in ascending
# order of names, or in descending order of scores, …
# Then, it performs the test by using the following cases:
# (a) 4, 3, 7, 2, 1, 9 in ascending order
# (b) 4.5, 2.0, 9.0, 8.4, 7.2, 6.1, 20.5, 2.1 in descending order
# (c) John 40, Casey 45, Ben 47, Zadi 41, Kay 39, Tay 43 in ascending order of names
# (d) John 40, Casey 45, Ben 47, Zadi 41, Kay 39, Tay 43 in descending order of scores

list_integers = [4, 3, 7, 2, 1, 9]
list_doubles = [4.5, 2.0, 9.0, 8.4,7.2, 6.1, 20.5, 2.1]

print("case a: ")
print(sorted(list_integers))

print("\ncase b: ")
print(sorted(list_doubles, reverse=True))

class Person:
        def __init__(self, name, age):
                self.name = name
                self.age = age
        def __repr__(self):
                return repr((self.name, self.age))

person_objects = [
        Person('John'   , 40),
        Person('Casey'  , 45),
        Person('Ben'    , 47),
        Person('Zadi'   , 41),
        Person('Kay'    , 39),
        Person('Tay'    , 43),
]

print("\ncase c: ")
print(sorted(person_objects, key=lambda person: person.name))
print("\ncase d: ")
print(sorted(person_objects, key=lambda person: person.age, reverse = True))


