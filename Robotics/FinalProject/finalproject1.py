import pyCreate2
import math
import odometry
import pid_controller
import lab8_map
import particle_filter
import rrt
import lab10_map
import numpy as np


class Node:
    
    def __init__(self, x, y, parent):
        self.x = x
        self.y = y
        self.parent = parent
        self.children = []
    def addChild(self, child):
        self.children.append(child)
    def iterate(self):
        nodeList = [self]
        while nodeList:
            temp = nodeList.pop()
            for node in temp.children:
                nodeList.append(node)

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
        self.mapRRT = lab10_map.Map("finalproject_map2_config_new.png")
        self.mapParticle = lab8_map.Map("finalproject_map2.json")
        # self.mapRRT = lab10_map.Map("configuration_space.png")
        # self.mapParticle = lab8_map.Map("lab8_map.json")

        # TODO identify good PID controller gains
        # self.pidTheta = pid_controller.PIDController(200, 0, 100, [-10, 10], [-50, 50], is_angle=True)
        # TODO identify good particle filter parameters
        self.pf = particle_filter.ParticleFilter(self.mapParticle, 1000, 0.06, 0.15, 0.2)

        self.joint_angles = np.zeros(7)

        self.rrt = rrt.RRT(self.mapRRT)

    
        
        self.nodeList = []
        start = Node(270, 300, None)
        self.nodeList.append(start)
        # self.pidTheta = pid_controller.PIDController(200, 5, 50, [-10, 10], [-200, 200], is_angle=True)
        # self.pidTheta = pid_controller.PIDController(200, 5, 50, [-10, 10], [-200, 200], is_angle=True)
        self.pidTheta = pid_controller.PIDController(300, 5, 50, [-10, 10], [-200, 200], is_angle=True)
        self.pidDistance = pid_controller.PIDController(1000, 0, 50, [0, 0], [-200, 200], is_angle=False)





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
        base_speed = 100
        distance = 0.15
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
        # print("THETA IN VISUALIZE FUNC: ", theta)
        # theta=theta/2
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
        self.arm.open_gripper()
        self.time.sleep(4)
        self.slowMove(1, 0, math.pi/2, 0.01)
        self.slowMove(3, 0, math.pi/10, 0.01)
        self.slowMove(5, 0, -math.pi/8 + 0.04, -0.01 )
        self.time.sleep(2)
    
    def grabCup(self):
        #grab cup
        self.arm.close_gripper()
        self.time.sleep(10)
        self.slowMove(1,math.pi/2,0,-0.01)
        self.slowMove(5,-math.pi/8 + 0.04, 0, 0.01)
        self.slowMove(3,math.pi/10,0,-0.01)
       
        
     
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
     
        print("theta: ", self.odometry.theta)

        # x_goal = self.rrt.nearest_neighbor((56, 138.5))
        self.rrt.build((268, 254.5), 4000, 10)
        x_goal = self.rrt.nearest_neighbor((65.5, 137.5))
        # print("goal: ", x_goal)
        path = self.rrt.shortest_path(x_goal)

        for v in self.rrt.T:
            for u in v.neighbors:
                self.mapRRT.draw_line((v.state[0], v.state[1]), (u.state[0], u.state[1]), (0, 0, 0))
        for idx in range(0, len(path) - 1):
            self.mapRRT.draw_line((path[idx].state[0], path[idx].state[1]),
                               (path[idx + 1].state[0], path[idx + 1].state[1]), (0, 255, 0))

        self.mapRRT.save("project_rrtnew1.png")

        self.create.start()
        self.create.safe()

        self.create.drive_direct(0, 0)

        # request sensors
        self.create.start_stream([
            pyCreate2.Sensor.LeftEncoderCounts,
            pyCreate2.Sensor.RightEncoderCounts,
        ])

        self.odometry.x = 2.6799
        self.odometry.y = 0.4547
        self.odometry.theta = math.pi/2
        base_speed = 100
        count = 0
        # perhaps add this back in??? needs testing
        sondistance = self.sonar.get_distance()
        self.pf.measure(sondistance, 0)
        self.visualize()
    

        for p in path:

            old_x =self.odometry.x
            old_y = self.odometry.y
            old_theta = self.odometry.theta
            goal_x = p.state[0] / 100.0
            goal_y = 3.0-p.state[1]/100.0
            # print(goal_x, goal_y)
            while True:
                state = self.create.update()
                if state is not None:
                    # print("odometry xs forst old: ", old_x, self.odometry.x)
                    self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                    goal_theta = math.atan2(goal_y - self.odometry.y, goal_x - self.odometry.x)
                    theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                    output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
                    self.create.drive_direct(int(base_speed + output_theta), int(base_speed - output_theta))
                    # print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))

                    distance = math.sqrt(math.pow(goal_x - self.odometry.x, 2) + math.pow(goal_y - self.odometry.y, 2))
                    # print("odometry xs forst old: ", old_x, self.odometry.x)
                    if distance < 0.075:
                        if count%3==0:
                            sondistance = self.sonar.get_distance()
                            # print(distance)
                            self.pf.measure(sondistance, 0)
                            self.visualize()
                        break
            self.pf.move_by(self.odometry.x - old_x, self.odometry.y - old_y, self.odometry.theta - old_theta)
            # print("self.odometry.thetha: new? ", self.odometry.theta)
            self.visualize()
            count+=1

        self.create.drive_direct(0,0)
        curAngle = self.odometry.theta
        goToAng = (math.pi-curAngle)+curAngle
        self.go_to_angle(goToAng)

        #change distance in forward function
        self.forward()
#        # self.time.sleep(.0001)
        self.create.drive_direct(0,0)
#        print("angle:",self.odometry.theta )
#
        
        #place cup functions
        self.findCup()
        self.grabCup()
        self.moveToShelf(0)
        self.arm.open_gripper()
        self.time.sleep(10)
       
       
