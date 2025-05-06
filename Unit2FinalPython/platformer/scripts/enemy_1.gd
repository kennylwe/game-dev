extends CharacterBody2D

@onready var raycastright = $RayCast2D
@onready var raycastleft = $RayCast2D2
@onready var animatedsprite = $AnimatedSprite2D
const SPEED = 3000.0


func _ready() -> void:
	velocity.x = 50


func _physics_process(delta: float) -> void:
	# Add the gravity.
	if not is_on_floor():
		velocity.y += 1400 * delta
	else:
		velocity.y = 0
		

	
	if raycastright.is_colliding():
		velocity.x = -SPEED * delta
		animatedsprite.flip_h = true
		
	if raycastleft.is_colliding():
		velocity.x = SPEED * delta
		animatedsprite.flip_h = false
	
	move_and_slide()
