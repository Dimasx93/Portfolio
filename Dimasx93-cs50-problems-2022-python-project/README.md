***Title: Retirement Calculator***
#### Video Demo:  <https://youtu.be/FFTFLssVJS4>
#### Description:
  Project structure :
 - project.py
 - test_project.py
 - birthdays.csv
 - README.md
 - requirements.txt
## **Installing Libraries**
there is a a requirements.txt file that has all the libraries used.

## __Usage__

```python project.py birthdays.csv```

## __Explanation__

I created a file that requires a csv file(birthdays.csv) using the sys library and expects only 1 csv file
using **def check_args**, it opens the csv file with DictReader, stores in a dict the result(birth_dates).
After we call **def check_name** ask user for an input (name,surname of a person) and if it is on the list,
we proceed to extract the birthdate and using **def calculate_age** we take today's date and subtract it
with the birthdate.
Finally we return the age and check if is over or less than 65 (my chosen retirement age)
and print how much that person has to work in years, or if is alreadt retired.

## __Functioning__

the project.py contains 4 functions including the main function.

### __calculate_age()__ __function__ :

This function takes today's date and subtracts the birth date of the subject, also if today's month and day are smaller than the birthdate,
it subtract an extra year I.E.(17 May < 17June so if a person was born on 17/06/2000 he/she will be 22 years old and not 23)

### __main()__ __function__ :

Here besides calling check_args() function we start with a try and except situation, where we __try__ to open sys.argv[1] read it
and save it into a var called reader, with csv.DictReader, after we store the result into a dict called __birth_dates__
and in the end we call the check_name function.

### __check_args()__ __function__ :

This fucntion is pretty simple, we simply check the len of sys.argv so we are sure that we have not only project.py to run, but also a CSV file.

### __check_name()__ __function__ :

Here we ask the user for an input(name,surname) and we use an if loop to check that the name var is in the csv file, if it is,
we access the person's birthdate and format it in Day/Month/Year and we call calculate_age function to get the person's age, after that,
we simply check if this person is older or younger than 65 years old and print name, age and if this person is already retired
or how much more he/she needs to work till retirement.

### Author : Stefano Di Mauro.            ### Date: 17/05/2023        ### Location: Vilnius, Lithuania.