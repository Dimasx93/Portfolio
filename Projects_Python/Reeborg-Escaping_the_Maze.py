def turn_right():
    turn_left()
    turn_left()
    turn_left()
    
def jump():
    if front_is_clear():
        move()
    elif not wall_on_right():
        turn_right()
        move()
    elif front_is_clear() == False and right_is_clear() == False:
        turn_left()
while at_goal() == False:
    jump()