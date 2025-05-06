extends CharacterBody2D


@onready var COYOTETIME = $CoyoteTimer
@onready var double_tap_timer = $Doubletaptimer
@onready var dashtimer = $Dashtimer
@onready var dashcooldown = $DashCooldowntimer
@onready var animatedsprite = $AnimatedSprite2D
var SPEED = 4000.0
const JUMP_VELOCITY = -350.0
var can_coyotejump = false
var falltime = 10
var last_action_pressed = ""
var dash_speed = 22000.0
var move_speed: float = SPEED
var can_dash = true
const gravity = 1400.0
var nograv = false
var gravity_depressor = [1, 0]


func _physics_process(delta: float) -> void:
	# Add the gravity.
	
	if not is_on_floor() and not nograv:
		velocity.y += gravity * gravity_depressor[0] * gravity_depressor[1] * delta
		if velocity.y > 10:
			if gravity_depressor[0] > 0.6:
				gravity_depressor[0] -= 0.015
		elif velocity.y < -200:
			if gravity_depressor[1] < 1.6:
				gravity_depressor[1] += 0.015
			
		else:
			gravity_depressor[0] = 1
			gravity_depressor[1] = 1
		falltime -= 5
	else:
		falltime = 10
	
	if nograv:
		velocity.y = 0
	# Handle jump.
	if Input.is_action_just_pressed("ui_accept"):
		if is_on_floor() || can_coyotejump:
			velocity.y = JUMP_VELOCITY
			can_coyotejump = false
	
	


	# Get the input direction and handle the movement/deceleration.
	# As good practice, you should replace UI actions with custom gameplay actions.
	var direction = Input.get_axis("ui_left", "ui_right")
	if direction:
		velocity.x = direction * move_speed * delta
		_check_for_double_tap("ui_left")
		_check_for_double_tap("ui_right")
	else:
		velocity.x = 0
	if direction > 0:
		animatedsprite.flip_h = false
	elif direction < 0:
		animatedsprite.flip_h = true
		
		
	if is_on_floor():
		if direction == 0:
			animatedsprite.play("idle")
		else:
			animatedsprite.play("walking")
	else:
		animatedsprite.play("jump")
	

	if !is_on_floor() && falltime > 0 && falltime <= 5 && velocity.y >= 0:
		COYOTETIME.start()
		can_coyotejump = true
		print("hi")
		
	if Input.is_action_just_pressed("quit"):
		get_tree().quit()
		
	move_and_slide()
	
	





# Literally same code for both "move_left" and "move_right", why not make them into one function
func _check_for_double_tap(action_string: String) -> void:
	if Input.is_action_just_pressed(action_string):
		if last_action_pressed == action_string && can_dash : # If double-tapped
			move_speed = dash_speed
			dashtimer.start()
			nograv = true
			can_dash = false
		else: 
			last_action_pressed = action_string
			double_tap_timer.start()


func _on_coyote_timer_timeout():
	can_coyotejump = false


func _on_doubletaptimer_timeout() -> void:
	last_action_pressed = ""


func _on_dashtimer_timeout() -> void:
	move_speed = SPEED
	nograv = false
	dashcooldown.start()
	


func _on_dash_cooldowntimer_timeout() -> void:
	can_dash = true
