# rock, paper, scissors game
import random

options = ("rock", "paper", "scissor")
running = True

while running:
    player_choice = None
    computer_choice = random.choice(options)

    while player_choice not in options:
        player_choice = input("enter a choice(rock, paper or scissor): ")

        print(f"player: {palyer_choice}")
        print(f"computer: {computer_choice}")

        if player_choice == computer_choice:
            print("draw")
        elif player_choice == "rock" and computer_choice == "scissor":
            print("player win")
        elif player_choice == "paper" and computer_choice == "rock":
            print("player win")
        elif player_choice == "scissor" and computer_choice == "paper":
            print("player win")
        else:
            print("computer win")

        if not input("play again? (y/n)").lower() == "y":
            running = False

print("game over")