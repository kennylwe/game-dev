extends Node2D

@onready var raycastright = $RayCast2D
@onready var raycastleft = $RayCast2D2

var randomdistance = randf() * 100

# Called when the node enters the scene tree for the first time.
func _physics_process(delta: float) -> void:
	if randomdistance < 100 && randomdistance > 50:
		position.x += 1
		randomdistance += 10
		
	if randomdistance <= 50 && randomdistance > 0:
		position.x -= 1
		randomdistance -= 10
	
	if randomdistance > 100 || randomdistance < 0:
		randomdistance = randf() * 100
	if raycastright.is_colliding():
		for x in range(10):
			position.x -= 1
	if raycastleft.is_colliding():
		for x in range(10):
			position.x += 1
