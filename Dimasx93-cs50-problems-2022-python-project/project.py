#04/05/2023 Start date
#CS50 Final Project CS50's Introduction to Programming with Python week 9
'''
Read in the CSV file using the csv module. DONE
Process the data in the CSV file to extract the birth dates of each employee. DONE
Calculate the employee's age, based on their birth date and the current date. DONE
Determine the number of years remaining until the employee reaches retirement age. DONE
Print out the number of years remaining for each employee, along with their name. DONE
'''
import sys
import csv
from datetime import datetime

def calculate_age(birth_date):
    today = datetime.today()
    age = today.year - birth_date.year - ((today.month, today.day) < (birth_date.month, birth_date.day))
    return age

def main():
    check_args()
    try:
        with open(sys.argv[1], 'r') as csvfile:
            reader = csv.DictReader(csvfile)
            birth_dates = {row['names']: row['date_of_birth'] for row in reader}

            # Call the check_name function with the birth_dates dictionary
            check_name(birth_dates)

    except FileNotFoundError:
        sys.exit("File not present")

def check_args():
    if len(sys.argv) < 2:
        sys.exit('Too few arguments')
    if len(sys.argv) > 2:
        sys.exit("Too many arguments")
    if '.csv' not in sys.argv[1]:
        sys.exit("Not a CSV file")

def check_name(birth_dates):
    # get the name from the user
    name = input("Enter a name: ")

    # check if the name is in the birth_dates dictionary
    if name in birth_dates:
        # calculate the age of the person
        birth_date_str = birth_dates[name]
        birth_date = datetime.strptime(birth_date_str, "%d/%m/%Y")
        age = calculate_age(birth_date)

        # calculate the number of years until retirement
        years_until_retirement = 65 - age

        if years_until_retirement > 0:
            print(f"{name} is {age} years old and has {years_until_retirement} years until retirement.")
        else:
            print(f"{name} is already retired.")
    else:
        print(f"{name} was not found in the csv file.")

if __name__ == "__main__":
    main()
