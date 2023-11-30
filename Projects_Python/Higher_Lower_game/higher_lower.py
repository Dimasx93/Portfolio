from art import logo, vs
from game_data import data
from random import choice

score = 0
option1 = choice(data)
option2 = choice(data)
while True:
  print(logo)
  print(f"Compare A: {option1['name']}, a {option1['description']}, from {option1['country']}" + vs + f" Against B: {option2['name']}, a {option2['description']}, from {option2['country']}")
  guess = input("Who has more followers? Type 'A' or 'B': ").lower()
  if guess == "a":
    if option1["follower_count"] > option2["follower_count"]:
      score += 1
      print(f"Correct! Your score is {score}.")
      option1 = option2
      option2 = choice(data)
    else:
      print(f"Wrong! Your score is {score}.")
      break
  elif guess == "b":
    if option1["follower_count"] < option2["follower_count"]:
      score += 1
      print(f"Correct! Your score is {score}.")
      option1 = option2
      option2 = choice(data)
    else:
      print(f"Wrong! Your score is {score}.")
      break
  else:
    print("Invalid input. Please try again. Type 'A' or 'B'.") 
