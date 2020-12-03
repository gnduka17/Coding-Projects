from pyCreate2 import create2
from p_controller import PController
import math
import numpy as np
import odometry
import pd_controller2
import pid_controller
import matplotlib
# if on the robot, don't use X backend
matplotlib.use('Agg')
import matplotlib.pyplot as plt

class Run:
    def __init__(self, factory):
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.sonar = factory.create_sonar()
        self.servo = factory.create_servo()
        self.odometry = odometry.Odometry()
        # self.pidTheta = pd_controller2.PDController(500, 100, -200, 200, is_angle=True)
        self.pidTheta = pid_controller.PIDController(300, 5, 50, [-10, 10], [-200, 200], is_angle=True)
        self.pidDistance = pid_controller.PIDController(1000, 0, 50, [0, 0], [-200, 200], is_angle=False)
        #self.pdObstacle = pd_controller2.PDController(1000, 100, -75, 75)
        self.pdObstacle = PController(500, -500, 500, 100)
            #self.pdObstacle = pd_controller2.PDController(1000, 100, -75, 75, is_angle=False)
    def run(self):
        self.create.start()
        self.create.safe()
        self.servo.go_to(0)
        self.time.sleep(2)
        prev_error = 0
            #request sensors
        self.create.start_stream([
            create2.Sensor.LeftEncoderCounts,
            create2.Sensor.RightEncoderCounts,
        ])
        # goal_x = 0.5
        # goal_y = -0.5
        base_speed = 200
        object_distance = 0.9

        waypoints = [
            [2.0, 0.0],
            [3.0, 2.0],
            [2.5, 2.0],
            [0.0, 1.5],
            [0.0, 0.0]
        ]
        result = np.empty((0,5))
        end_time = self.time.time() + 10
        currentWaypoint = 0
        changeWaypoint = False
        while self.time.time() < end_time:
            while changeWaypoint == False:
                print(f"waypoint: {waypoints[currentWaypoint]}")
                goal_x = waypoints[currentWaypoint][0]
                goal_y = waypoints[currentWaypoint][1]
                state = self.create.update()

                print("Going to waypoint")
                print("odometry x:", self.odometry.x)
                print("odometry y:", self.odometry.y)

                if state is not None:
                    self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    new_row = [self.time.time(), math.degrees(self.odometry.theta), math.degrees(goal_theta), self.odometry.x, self.odometry.y]
                    result = np.vstack([result, new_row])

                    output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
                    sondistance = self.sonar.get_distance()
                    duhend = self.time.time() + 7
                    if sondistance <=1.3:
                        print("should get in here")
                        print("beginning timeeeeee bout to enter while loop", self.time.time())
                        print("beginning timeeeeee bout to enter while loop end time", (self.time.time()+3))
                        print("DISTANCE: ", sondistance)
                        while self.time.time() < duhend:
                            sondistance = self.sonar.get_distance()
                            print("NEWWWWWWW DISTANCEEEEEE", sondistance)
                            if sondistance is not None:
                                if sondistance > 1.3:
                                    print("I BROKE OUT THE LOOPPPPPPPPPPPPPPP")
                                    break
                                error = object_distance - sondistance
                                left_wheel_speed, right_wheel_speed = self.pdObstacle.update(error)
                                self.create.drive_direct(right_wheel_speed, left_wheel_speed)
                                prev_error = error
                                self.time.sleep(0.01)
                            print("theeeeeeeeee timeeeeeeeeeeeeeeee",self.time.time())
                        print("OUTTTT THE WHILE LOOOOPP TIME----------------------------", self.time.time())
                    else:
                        distance = math.sqrt(math.pow(goal_x - self.odometry.x, 2) + math.pow(goal_y - self.odometry.y, 2))
                        output_distance = self.pidDistance.update(0, distance, self.time.time())
                        self.create.drive_direct(int(output_theta + output_distance), int(-output_theta + output_distance))
                        print(f"output: {output_distance}\n")
                        if output_distance < 5 and currentWaypoint == 4:
                            print("finished")
                            break
                        elif output_distance < 5:
                            print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~changing waypoint!")
                            currentWaypoint += 1
                    
                                
#
#                            if distance<=1.3:
#                                while self.time.time() < end_time:
#                                    distance = self.sonar.get_distance()
#                                    if distance is not None:
#                                        output = self.pdObstacle.update(distance, object_distance, self.time.time())
#                                        self.create.drive_direct(int(base_speed - output), int(base_speed + output))
#                                    if distance<=1.3:
#                                        print("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY--------------------------------------")
#
#                                    else:
#                                        break
#
#                            else:
#                                break


                    # base version:
                    # self.create.drive_direct(int(base_speed+output_theta), int(base_speed-output_theta))

                    # improved version 1: stop if close enough to goal
                    # distance = math.sqrt(math.pow(goal_x - self.odometry.x, 2) + math.pow(goal_y - self.odometry.y, 2))
                    # if distance < 0.1:
                    #     break

                    # improved version 2: fuse with velocity controller
                    
               
#                    sonar_distance = self.sonar.get_distance()
#                    print(f"sonar reading: {sonar_distance}")
#
#                    if sonar_distance is not None:
#                        if sonar_distance <= object_distance:
#                            while True:
#                                sonar_distance = self.sonar.get_distance()
#
#                                output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())
#                                self.create.drive_direct(int(base_speed - output), int(base_speed + output))
#                                self.time.sleep(0.01)


                    # if sonar_distance is not None:
                    #     self.servo.go_to(0)
                    #     while sonar_distance <= object_distance:
                    #         print("Avoiding obstacles")
                    #         print(f"sonar reading: {sonar_distance}")
                    #         #output = self.p_controller.update(distance, goal_distance)
                    #         output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())

                    #         # self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    #         # goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    #         # theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    #         # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    #         self.create.drive_direct(int(base_speed - output), int(-base_speed + output))
                    #         self.time.sleep(0.01)
                    #         sonar_distance = self.sonar.get_distance()   
                        
                    #     self.servo.go_to(60)         
                    #     while sonar_distance <= object_distance:
                    #         print("Avoiding obstacles")
                    #         print(f"sonar reading: {sonar_distance}")
                    #         #output = self.p_controller.update(distance, goal_distance)
                    #         output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())

                    #         # self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    #         # goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    #         # theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    #         # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    #         self.create.drive_direct(int(base_speed - output), int(-base_speed + output))
                    #         self.time.sleep(0.01)
                    #         sonar_distance = self.sonar.get_distance() 

                    #     self.servo.go_to(0)
                    #     while sonar_distance <= object_distance:
                    #         print("Avoiding obstacles")
                    #         print(f"sonar reading: {sonar_distance}")
                    #         #output = self.p_controller.update(distance, goal_distance)
                    #         output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())

                    #         # self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    #         # goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    #         # theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    #         # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    #         self.create.drive_direct(int(base_speed - output), int(-base_speed + output))
                    #         self.time.sleep(0.01)
                    #         sonar_distance = self.sonar.get_distance()   

                    #     self.servo.go_to(-60)         
                    #     while sonar_distance <= object_distance:
                    #         print("Avoiding obstacles")
                    #         print(f"sonar reading: {sonar_distance}")
                    #         #output = self.p_controller.update(distance, goal_distance)
                    #         output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())

                    #         # self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    #         # goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    #         # theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    #         # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    #         self.create.drive_direct(int(base_speed - output), int(-base_speed + output))
                    #         self.time.sleep(0.01)
                    #         sonar_distance = self.sonar.get_distance()  

                    #     self.servo.go_to(0)
                    #     while sonar_distance <= object_distance:
                    #         print("Avoiding obstacles")
                    #         print(f"sonar reading: {sonar_distance}")
                    #         #output = self.p_controller.update(distance, goal_distance)
                    #         output = self.pdObstacle.update(sonar_distance, object_distance, self.time.time())

                    #         # self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    #         # goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    #         # theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    #         # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                    #         self.create.drive_direct(int(base_speed - output), int(-base_speed + output))
                    #         self.time.sleep(0.01)
                    #         sonar_distance = self.sonar.get_distance()   
    
    


        plt.figure()
        plt.plot(result[:,0], result[:,1])
        plt.plot(result[:,0], result[:,2])
        plt.savefig("angle.png")

        plt.figure()
        plt.plot(result[:,3], result[:,4])
        plt.savefig("position.png")
