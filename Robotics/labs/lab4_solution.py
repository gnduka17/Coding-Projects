import p_controller
import pd_controller


class Run:
    def __init__(self, factory):
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.sonar = factory.create_sonar()
        self.servo = factory.create_servo()
        #self.p_controller = p_controller.PController(1000, -75, 75)
        self.pd_controller = pd_controller.PDController(1000, 100, -75, 75)


    def run(self):
        self.create.start()
        self.create.safe()

        self.servo.go_to(70)
        self.time.sleep(2)

        goal_distance = 0.5
        base_speed = 100

        while True:
            distance = self.sonar.get_distance()
            if distance is not None:
                print(distance)
                #output = self.p_controller.update(distance, goal_distance)
                output = self.pd_controller.update(distance, goal_distance, self.time.time())
                self.create.drive_direct(int(base_speed - output), int(base_speed + output))
                self.time.sleep(0.01)




#while true - while on going -----------> while im going to waypoint destination
#get distance sonar distance t
#then if distance not none and distance is less than 1.3m
#go to waypoint......if distance of servo is less than 1.3 meters then drive direct until distance is less than 1.3

#while fully got around abstacle
#go to next way point
#
#distance = self.sonar.get_distance()
#if distance <=1.3:
#    while self.time.time() < end_time:
#        distance = self.sonar.get_distance()
#        if distance is not None:
#            output = self.pd_controller.update(distance, goal_distance, self.time.time())
#            self.create.drive_direct(int(base_speed - output), int(base_speed + output))
#        if distance<=1.3:
#            while self.time.time() < end_time:
#                distance = self.sonar.get_distance()
#                if distance is not None:
#                    output = self.pd_controller.update(distance, goal_distance, self.time.time())
#                    self.create.drive_direct(int(base_speed - output), int(base_speed + output))
#                if distance<=1.3:
#                
#                print("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY--------------------------------------")
#        
#                else:
#                    break
#        
#        else:
#            break
#





