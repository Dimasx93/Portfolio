#Password generator project
#Start date: 21/05/2023
'''
The passwords generated will be 8 characters long and will have to include the following characters in any order:
2 uppercase letters from A to Z,
2 lowercase letters from a to z,
2 digits from 0 to 9,
2 punctuation signs such as !, ?, “, # etc.
'''
import random
#create a function to shuffle all the characters of a string
def shuffle(string):
    tempList = list(string)
    random.shuffle(tempList)
#return followed by an empty space'' as a separator between characters.join the new list
    return''.join(tempList)
#Generate 2 random uppercase letter between A-Z
upper_case1 = chr(random.randint(65,90))
upper_case2 = chr(random.randint(65,90))
#Generate 2 random lowercase letter between a-z
lower_case1 = chr(random.randint(97,122))
lower_case2 = chr(random.randint(97,122))
#Generate 2 random digit between 0-9
digit1 = chr(random.randint(48,57))
digit2 = chr(random.randint(48,57))
#Generate 2 random punctuation signs
punctuation_sign1 = chr(random.randint(33,47))
punctuation_sign2 = chr(random.randint(33,47))
#create var password
password = upper_case1+upper_case2+lower_case1+lower_case2+digit1+digit2+punctuation_sign1+punctuation_sign2
#Shuffle the result to mix characters
password = shuffle(password)
#print the final result
print(password)