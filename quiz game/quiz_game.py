# quiz game

questions = ("What is the closest planet to the Sun?",
             "Which planet is known as the Red Planet",
             "What is the name of the galaxy that contains our Solar System?",
             "Which planet has the most moons?",
             "What is the largest planet in our Solar System?")

options = (("a. Earth", "b. Mars", "c. Venus", "d. Mercury", "e. Jupiter"),
           ("a. Venus", "b. Mars", "c. Jupiter", "d. Saturn", "e. Neptune"),
           ("a. Andromeda Galaxy", "b. Whirlpool Galaxy", "c. Milky Way Galaxy", "d. Sombrero Galaxy",
            "e. Triangulum Galaxy"),
           ("a. Earth", "b. Mars", "c. Jupiter", "d. Saturn", "e. Uranus"),
           ("a. Earth", "b. Mars", "c. Venus", "d. Saturn", "e. Jupiter"))

answers = ("d", "b", "c", "c", "e")
option_num = 0
guesses = []
score = 0

for question in questions:
    print("----------------------------------------------------------------------")
    print(question)
    for option in options[option_num]:
        print(option)

    guess = input("Enter (a, b, c, d, e): ").lower()
    guesses.append(guess)

    if guess == answers[option_num]:
        print("Correct!")
        score += 1
    else:
        print()
        print("Incorrect!")
        print(f"{answers[option_num]} is the correct ans.")

    option_num += 1
print("----------------------------------------------------------------------")
print()
print("--------------------------------RESULT--------------------------------")

print("Answers: ", end=" ")
for answer in answers:
    print(answer, end=" ")
print()

print("Guesses: ", end=" ")
for guess in guesses:
    print(guess, end=" ")
print()

score = int(score / len(questions) * 100)
print(f"Your score is {score}%")