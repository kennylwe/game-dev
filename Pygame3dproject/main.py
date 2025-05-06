

import pygame, sys, random
pygame.init()

vector = pygame.math.Vector2
clock = pygame.time.Clock()

scrn_x = 1200
scrn_y = 800


screen = pygame.display.set_mode((scrn_x, scrn_y))
all_sprites = pygame.sprite.Group()






class Car(pygame.sprite.Sprite):
    def __init__(self, x, y, image, *groups):
        super().__init__(*groups)

        self.start_ticks=pygame.time.get_ticks() #starter tick

        self.image1 = pygame.image.load(image)
        self.image_base = pygame.transform.scale(self.image1, (75, 75))
        self.rect = self.image_base.get_rect()
        self.angle = 0
        self.driftmeter = 0


        self.position = vector(x + 400, y + 400)
        self.velocity = vector(0, 0)
        self.acceleration = vector(0, 0)
        self.camera = vector(400, 400)

        self.ACCELERATIONRATE = 1.5
        self.FRICTION = 0.14
    

    def rotate(self):
        self.TURNRATE = 2.5
        if self.angle >= 360:
            self.angle = 0
        elif self.angle <= 0:
            self.angle = 360
        if self.acceleration.x > 0:
            if self.acceleration.y == 0:
                if self.angle >= 180:
                    self.angle += self.TURNRATE
                elif self.angle < 180:
                    self.angle -= self.TURNRATE
            else:
                if self.angle >= 180 and self.angle < 315:
                    self.angle += self.TURNRATE
                if self.angle < 180 and self.angle > 45:
                    self.angle -= self.TURNRATE
        
        elif self.acceleration.x < 0:
            if self.acceleration.y == 0:
                if self.angle >= 0 and self.angle < 180:
                    self.angle += self.TURNRATE
                elif self.angle < 360 and self.angle > 180:
                    self.angle -= self.TURNRATE
            else:
                if self.angle < 135 and self.angle >= 0:
                    self.angle += self.TURNRATE
                if self.angle > 225 and self.angle <= 360:
                    self.angle -= self.TURNRATE


        if self.acceleration.y < 0:
            if self.acceleration.x == 0:
                if self.angle <= 270 and self.angle > 90:
                    self.angle -= self.TURNRATE
                if self.angle > 270 or self.angle < 90:
                    self.angle += self.TURNRATE
            else:
                if self.angle < 270 and self.angle > 135:
                    self.angle -= self.TURNRATE
                if self.angle >= 270 or self.angle < 45:
                    self.angle += self.TURNRATE
        elif self.acceleration.y > 0:
            if self.acceleration.x == 0:
                if self.angle >= 90 and self.angle < 270:
                    self.angle += self.TURNRATE
                if self.angle > 270 or self.angle < 90:
                    self.angle -= self.TURNRATE
            else:
                if self.angle > 90 and self.angle < 225:
                    self.angle += self.TURNRATE
                if self.angle <= 90 or self.angle > 315:
                    self.angle -= self.TURNRATE

    def update(self):
        self.acceleration = vector(0, 0)
        self.facing = vector(0, 0)
        
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT]:
            self.acceleration.x = -1 * self.ACCELERATIONRATE

            self.facing.x = -1


         
        if keys[pygame.K_RIGHT]:
            self.acceleration.x = 1 * self.ACCELERATIONRATE

            self.facing.x = 1

        if keys[pygame.K_UP]:
            self.acceleration.y = -1 * self.ACCELERATIONRATE

            self.facing.y = -1
        
        if keys[pygame.K_DOWN]:
            self.acceleration.y = 1 * self.ACCELERATIONRATE
        
            self.facing.y = 1
        
        
        


        if keys[pygame.K_LSHIFT]:

            #self.seconds=(pygame.time.get_ticks()-self.start_ticks)/1000
            '''if self.seconds > 2:


                self.acceleration = 70 * self.facing
                
                self.start_ticks = pygame.time.get_ticks() #starter tick
                '''
            self.drifting = True
            
        else:
            self.drifting = False

                
            
        

        self.rotate()
        

        
        self.image = pygame.transform.rotate(self.image_base, self.angle - 90)
    

        if self.drifting:
            self.driftmeter += 1
            self.slow = 0.5
            
        else:
            if self.driftmeter < 10:
                pass
            elif self.driftmeter < 50:
                self.acceleration += self.acceleration * 10
            elif self.driftmeter < 200:
                self.acceleration += self.acceleration * 25
            self.driftmeter = 0
            self.slow = 1

        

                    
            

        self.acceleration *= self.FRICTION
        self.acceleration -= self.velocity * 0.03
        self.velocity += self.acceleration

        self.position += self.velocity + 0.2 * self.acceleration * self.slow

        self.camera += self.velocity + 0.2 * self.acceleration * self.slow 

        self.rect = self.image.get_rect(center = self.position)

        print(self.position)

        

class Background(pygame.sprite.Sprite):
    def __init__(self, x, y, scale, image, *group):
        super().__init__(group)
        self.image = pygame.image.load(image)

        self.image = pygame.transform.smoothscale(self.image.convert_alpha(), scale)
        self.rect = self.image.get_rect(center=(x, y))



class Walls(pygame.sprite.Sprite):
    def __init__(self, x, y, scale, image, *group):
        super().__init__(group)
        self.image = pygame.image.load(image)

        self.image = pygame.transform.smoothscale(self.image.convert_alpha(), scale)
        self.rect = self.image.get_rect(center = (x + 100, y - 100))

        self.start_ticks = pygame.time.get_ticks() #starter tick
    
    def update(self):
        if self.rect.colliderect(player.rect):
            self.seconds = (pygame.time.get_ticks()-self.start_ticks)/1000
            if self.seconds > 0.2:

                player.acceleration *= -1
                player.velocity *= -1

                self.start_ticks = pygame.time.get_ticks() #starter tick







player = Car(scrn_x/2, scrn_y / 2, "orangecar.png", all_sprites)

walls = pygame.sprite.Group()

wall_data = [
    (-20, -100, (100, 100)),
    (100, 100, (100, 100)),
    (500, 300, (50, 50)),
    (500, 500, (150, 150)),
    (1000, 1000, (100, 100)),
    (250, 500, (80, 80)),
]

for x, y, scale in wall_data:
    wall = Walls(x, y, scale, "block.png", walls)
    walls.add(wall)



'''
walls = pygame.sprite.Group()
for rect in ((100, 170, 90, 20), (200, 100, 20, 140), (400, 60, 150, 100), (300, 470, 150, 100)):
    walls.add(object(*rect))
all_sprites.add(walls)
'''
background = pygame.sprite.Group()

track = Background(0, 0, (2000, 2000), "racetrack.png", background)
track2 = Background(2000, 2000, (5000, 5000), "racetrack2.png", background)



all_sprites.add(player)


while True:

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()





    screen.fill((255, 255, 255))




    all_sprites.update()
    walls.update()

    
    


    screen.blit(track.image, track.rect.center - player.camera)
    screen.blit(track2.image, track2.rect.center - player.camera)
    
    for block in walls:

        screen.blit(block.image, block.rect.center - player.camera)

    for sprite in all_sprites:

        screen.blit(sprite.image, sprite.rect.center - player.camera)


    
    #screen.blit(player.image, player.rect.bottomleft)

    
    
    pygame.display.update()



    # set framerate
    
    clock.tick(60)