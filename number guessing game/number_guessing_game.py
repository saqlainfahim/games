# number guessing game
import random

lowest_num = 1
highest_num = 10

answer = random.randint(lowest_num, highest_num)

guesses = 0
is_running = True

while is_running:
    guess = input(f"enter your guess (between {lowest_num} to {highest_num}): ")

    if guess.isdigit():
        guess = int(guess)
        guesses += 1

        if guess < lowest_num or guess > highest_num:
            print("your input is out of range !!!")
        elif guess < answer:
            print("low !!!")
        elif guess > answer:
            print("high !!!")
        else:
            print(f"correct, the number was {answer}")
            is_running = False
    else:
        print("invalid guess")