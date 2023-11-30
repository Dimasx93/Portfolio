#Number Guessing Game Project
from random import randint

from art import logo

EASY_LEVEL_TURNS = 10
HARD_LEVEL_TURNS = 5

#Make function to set difficulty.
def set_difficulty():
  difficulty = input("Choose a difficulty. Type 'easy' or 'hard': ")
  game_over = False
  while not game_over:
    if difficulty == "easy":
      return EASY_LEVEL_TURNS
      game_over = True
    elif difficulty == 'hard':
      return HARD_LEVEL_TURNS
      game_over = True
    else:
      user_input = input("Wrong input, select 'easy' or 'hard': ")
      if user_input == "easy":
        return EASY_LEVEL_TURNS
        game_over = True
      elif user_input == "hard":
        return HARD_LEVEL_TURNS
        game_over = True
#Function to check user's guess against actual answer.
def check_answer(guess, answer, turns):
  if guess > answer:
    print("Too high.")
    return turns - 1
  elif guess < answer:
    print("Too low.")
    return turns - 1
  else:
    print(f"You got it! The answer was {answer}.")

def game():
  print(logo)
    #Choosing a random number between 1 and 100.
  print("Welcome to the Number Guessing Game!")
  print("I'm thinking of a number between 1 and 100.")
  answer = randint(1, 100)
  print(f"Pssst, the correct answer is {answer}") 

  turns = set_difficulty()
  #Repeat the guessing functionality if they get it wrong.
  guess = 0
  while guess != answer:
    print(f"You have {turns} attempts remaining to guess the number.")

    #Let the user guess a number.
    guess = int(input("Make a guess: "))

    #Track the number of turns and reduce by 1 if they get it wrong.
    turns = check_answer(guess, answer, turns)
    if turns == 0:
      print("You've run out of guesses, you lose.")
      return
    elif guess != answer:
      print("Guess again.")

game()