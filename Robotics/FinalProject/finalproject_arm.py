import pyCreate2
# from pyCreate2 import kuka_lbr4p_vrep
import math
import odometry
import pid_controller
import lab8_map
import particle_filter

import numpy as np


class Run:
    def __init__(self, factory):
        """Constructor.
        Args:
            factory (factory.FactoryCreate)
        """
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.servo = factory.create_servo()
        self.sonar = factory.create_sonar()
        self.arm = factory.create_kuka_lbr4p()
        self.virtual_create = factory.create_virtual_create()
        # self.virtual_create = factory.create_virtual_create("192.168.1.XXX")
        self.odometry = odometry.Odometry()
        self.map = lab8_map.Map("lab8_map.json")

        # TODO identify good PID controller gains
        self.pidTheta = pid_controller.PIDController(200, 0, 100, [-10, 10], [-50, 50], is_angle=True)
        # TODO identify good particle filter parameters
        self.pf = particle_filter.ParticleFilter(self.map, 1000, 0.06, 0.15, 0.2)

        self.joint_angles = np.zeros(7)

    def sleep(self, time_in_sec):
        """Sleeps for the specified amount of time while keeping odometry up-to-date
        Args:
            time_in_sec (float): time to sleep in seconds
        """
        start = self.time.time()
        while True:
            state = self.create.update()
            if state is not None:
                self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
            t = self.time.time()
            if start + time_in_sec <= t:
                break

    def go_to_angle(self, goal_theta):
        old_x = self.odometry.x
        old_y = self.odometry.y
        old_theta = self.odometry.theta
        while math.fabs(math.atan2(
            math.sin(goal_theta - self.odometry.theta),
            math.cos(goal_theta - self.odometry.theta))) > 0.05:
            output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
            self.create.drive_direct(int(+output_theta), int(-output_theta))
            self.sleep(0.01)
        self.create.drive_direct(0, 0)
        self.pf.move_by(self.odometry.x - old_x, self.odometry.y - old_y, self.odometry.theta - old_theta)

    def forward(self):
        old_x = self.odometry.x
        old_y = self.odometry.y
        old_theta = self.odometry.theta
        base_speed = 300
        distance = 0.5
        goal_x = self.odometry.x + math.cos(self.odometry.theta) * distance
        goal_y = self.odometry.y + math.sin(self.odometry.theta) * distance
        while True:
            goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
            output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
            self.create.drive_direct(int(base_speed+output_theta), int(base_speed-output_theta))

            # stop if close enough to goal
            distance = math.sqrt(math.pow(goal_x - self.odometry.x, 2) + math.pow(goal_y - self.odometry.y, 2))
            if distance < 0.05:
                self.create.drive_direct(0, 0)
                break
            self.sleep(0.01)
        self.pf.move_by(self.odometry.x - old_x, self.odometry.y - old_y, self.odometry.theta - old_theta)

    def visualize(self):
        x, y, theta = self.pf.get_estimate()
        self.virtual_create.set_pose((x, y, 0.1), theta)
        data = []
        for particle in self.pf._particles:
            data.extend([particle.x, particle.y, 0.1, particle.theta])
        self.virtual_create.set_point_cloud(data)
        

    def inverse_kinematics(self, x_i, z_i):
        L1 = 0.4 # estimated using V-REP (joint2 - joint4)
        L2 = 0.39 # estimated using V-REP (joint4 - joint6)
        # Corrections for our coordinate system
        z = z_i - 0.3105
        x = -x_i
        # compute inverse kinematics
        r = math.sqrt(x*x + z*z)
        alpha = math.acos((L1*L1 + L2*L2 - r*r) / (2*L1*L2))
        theta2 = math.pi - alpha

        beta = math.acos((r*r + L1*L1 - L2*L2) / (2*L1*r))
        theta1 = math.atan2(x, z) - beta
        if theta2 < -math.pi / 2.0 or theta2 > math.pi / 2.0 or theta1 < -math.pi / 2.0 or theta1 > math.pi / 2.0:
           theta2 = math.pi + alpha
           theta1 = math.atan2(x, z) + beta
        if theta2 < -math.pi / 2.0 or theta2 > math.pi / 2.0 or theta1 < -math.pi / 2.0 or theta1 > math.pi / 2.0:
           print("Not possible")
           return
        
        if (theta1 < 0):
            self.slowMove(1,0,theta1,-0.01)
        else:
            self.slowMove(1,0,theta1,0.01)
        
        if (theta2 <  0):
            self.slowMove(3,0,theta2,-0.01)
        else:
            self.slowMove(3,0,theta2,0.01)
#
#        self.arm.go_to(1, theta1)
#        self.time.sleep(6)
#        self.arm.go_to(3, theta2)
#        self.time.sleep(6)

        print("Go to [{},{}], IK: [{} deg, {} deg]".format(x_i, z_i, math.degrees(theta1), math.degrees(theta2)))
           
    def slowMove(self, arm, prev, next, incr):
    
        for x in np.arange(prev, next, incr):
            self.arm.go_to(arm, x )
            self.time.sleep(0.05)
        
    def findCup(self):
        #Find cup
#        self.arm.go_to(0, -0.05 )
        self.time.sleep(2)
        self.arm.go_to(1, math.pi/2 )
        self.time.sleep(2)
        self.arm.go_to(3, math.pi/10)
        self.time.sleep(2)
        self.arm.go_to(5, -math.pi/8 + 0.05 )
        self.time.sleep(2)
    
    def grabCup(self):
        #grab cup
        self.arm.close_gripper()
        self.time.sleep(10)
#        self.slowMove(0,-0.05,0,0.01)
        self.slowMove(1,math.pi/2,0,-0.01)
        self.slowMove(3,math.pi/10,0,-0.01)
        self.slowMove(5,-math.pi/8 + 0.05, 0, 0.01)
        
        #adjust arm
#        self.slowMove(0, 0,-math.pi/2, -0.01 )
#
#        self.arm.close_gripper()
#        self.time.sleep(6)
    
    def moveToShelf(self, num):
        xVal = 0
        yVal = 0
        armNum = 0
        degrees = 0
        
        if (num == 3):
            #adjust arm

            self.slowMove(5,0,-math.pi/4 - 0.1, -0.01)
            self.slowMove(1, 0, -math.pi/6 + .2, -0.01)

            
        else:
        
            if (num == 0):
                xVal = -0.75
                yVal = 0.40
                armNum = 0
                degrees = -math.pi + .8

            elif (num == 1):
                xVal = -0.55
                yVal = 0.60
                armNum = 0
                degrees = -math.pi + .8
                
            elif (num == 2):
                xVal = -0.35
                yVal = 0.80
                armNum = 0
                degrees = -math.pi +  0.6

            #move arm to shelf
            self.inverse_kinematics(xVal, yVal)
            self.time.sleep(10)

           
            self.slowMove(armNum,0,degrees,-0.01)
    
        #release cup
        self.arm.open_gripper()
        self.time.sleep(6)
        
    def run(self):
    
       
        

        self.create.start()
        self.create.safe()

        self.create.drive_direct(0, 0)

    
        self.arm.open_gripper()
        self.findCup()
       

        # request sensors
        self.create.start_stream([
            pyCreate2.Sensor.LeftEncoderCounts,
            pyCreate2.Sensor.RightEncoderCounts,
        ])
        self.virtual_create.enable_buttons()
        
        self.forward()
        self.go_to_angle(self.odometry.theta + math.pi / 2)
        self.forward()
        self.forward()
        self.forward()
        self.forward()
        self.create.drive_direct(-25, -25)
        self.time.sleep(.5)
        self.go_to_angle(self.odometry.theta + math.pi / 2)
        
    
        while True:
            b = self.virtual_create.get_last_button()
            if b == self.virtual_create.Button.MoveForward:
                self.forward()
#                self.visualize()
            elif b == self.virtual_create.Button.TurnLeft:
                self.go_to_angle(self.odometry.theta + math.pi / 2)
#                self.visualize()
            elif b == self.virtual_create.Button.TurnRight:
                self.go_to_angle(self.odometry.theta - math.pi / 2)
#                self.visualize()
            elif b == self.virtual_create.Button.Sense:
               
                self.create.stop()
                self.grabCup()
                self.moveToShelf(0)
#                distance = self.sonar.get_distance()
#                print(distance)
#                self.pf.measure(distance, 0)
#                self.visualize()

#            self.time.sleep(0.01)
