from art import logo

alphabet = 'abcdefghijklmnopqrstuvwxyz'

def caesar_cipher(text, shift_amount, cipher_direction):
    end_text = ""
    if cipher_direction == "decode":
        shift_amount *= -1
    for char in text:
        if char in alphabet:
            position = alphabet.index(char)
            new_position = (position + shift_amount) % 26
            end_text += alphabet[new_position]
        else:
            end_text += char
    print(f"Here's the {cipher_direction}d result: {end_text}")

def main():
    while True:
        print(logo)
        while True:
            direction = input("Type 'encode' to encrypt, type 'decode' to decrypt: ")
            if direction in ["encode", "decode"]:
                break
            else:
                print("Please type 'encode' or 'decode'.")

        text = input("Type your message: ").lower()

        while True:
            try:
                shift = int(input("Type the shift number: "))
                break
            except ValueError:
                print("Please enter a valid number.")

        caesar_cipher(text, shift, direction)

        while True:
            answer = input("Type 'yes' if you want to go again. Otherwise type 'no': ").lower()
            if answer in ["yes", "no"]:
                break
            else:
                print("Please type 'yes' or 'no'.")

        if answer == "no":
            break 

if __name__ == "__main__":
    main()
