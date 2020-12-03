from pyCreate2 import create2
from particle import Particle
#from particle_filter import ParticleFilter
import lab8_map
import math
import odometry
import pid_controller
import numpy as np
import particle_filterold


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
        # Add the IP-address of your computer here if you run on the robot
        self.virtual_create = factory.create_virtual_create()
        self.map = lab8_map.Map("lab8_map.json")
        self.odometry = odometry.Odometry()
        self.pidTheta = pid_controller.PIDController(200, 0, 100, [-10, 10], [-50, 50], is_angle=True)
        self.particlesList = []
        self.data=[]
        self.p = particle_filterold.ParticleFilter(1000,self.map)
#        self.pf = particle_filter.ParticleFilter(self.map, 1000,)

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
            t = self.time.time()
            if start + time_in_sec <= t:
               break

    def run(self):
        self.create.start()
        self.create.safe()
        # request sensors
        self.create.start_stream([
          create2.Sensor.LeftEncoderCounts,
          create2.Sensor.RightEncoderCounts,
          ])
        distanceList = []
        xdist = [] 
        
        
        
        # This is an example on how to visualize the pose of our estimated position
        # where our estimate is that the robot is at (x,y,z)=(0.5,0.5,0.1) with heading pi
        self.virtual_create.set_pose((0.5, 0.5, 0.1), 0)

        # This is an example on how to show particles
        # the format is x,y,z,theta,x,y,z,theta,...
        count = 0
        xdist = np.random.uniform(0,3,1000)
        ydist = np.random.uniform(0,3,1000)
        thetadist = np.random.uniform(0,2*math.pi,1000)
        while count<1000:
            self.data.append(xdist[count])
            self.data.append(ydist[count])
            self.data.append(0.1)
            self.data.append(thetadist[count])
            self.particlesList.append(Particle(xdist[count],ydist[count], thetadist[count], math.log(1.0/1000)))
            count=count+1
        #data = [0.5, 0.5, 0.1, math.pi/2, 1.5, 1, 0.1, 0]
        self.virtual_create.set_point_cloud(self.data)
#        self.create.

        
        
       
        
        
        

        # This is an example on how to estimate the distance to a wall for the given
        # map, assuming the robot is at (0, 0) and has heading math.pi
        print(self.map.closest_distance((0.5,0.5), 0))

        # This is an example on how to detect that a button was pressed in V-REP
        while True:
            b = self.virtual_create.get_last_button()
            if b == self.virtual_create.Button.MoveForward:
                self.moveForward()
#                Movement()
#                self.create.drive_direct(100,100)
#                self.sleep(0.5)
                #self.create.drive_direct(0,0)
                print("Forward pressed!")
            elif b == self.virtual_create.Button.TurnLeft:
                self.turn_left()
#                self.create.drive_direct(100,-100)
#                self.sleep(0.05)
                #self.create.drive_direct(0,0)
                print("Turn Left pressed!")
            elif b == self.virtual_create.Button.TurnRight:
                self.turn_right()
#                self.create.drive_direct(-100,100)
#                self.sleep(0.05)
                #self.create.drive_direct(0,0)
                print("Turn Right pressed!")
            elif b == self.virtual_create.Button.Sense:
                dist = self.sonar.get_distance()
                self.particlesList = self.p.Sensing(self.particlesList,0,dist)
                self.data = []
                for particle in self.particlesList:
                    self.data.append(particle.x)
                    self.data.append(particle.y)
                    self.data.append(0.1)
                    self.data.append(particle.theta)
                        
                        #            self.particlesList = self.data
                self.virtual_create.set_point_cloud(self.data)
#                distanceList.append(self.sonar.get_distance())
#                print(distanceList[len(distanceList)-1])
                #sensing function
                print("Sense pressed!")

            self.time.sleep(0.01)

    def moveForward(self):
#        t_end = self.time.time() + 1
#        while self.time.time() < t_end:
            oldx = self.odometry.x
            oldy = self.odometry.y
            oldang = self.odometry.theta
            print("OLD ANGLE BEFORE FORWARD OF ROBOT::", oldang)
            gotox = self.odometry.x + math.cos(self.odometry.theta) * 0.5
            gotoy = self.odometry.y + math.sin(self.odometry.theta) * 0.5
            while True:
                goal_theta = math.atan2(gotoy - self.odometry.y, gotox - self.odometry.x)
                output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
#                print("output angle is::::::" , output_theta)
                self.create.drive_direct(int(100+output_theta), int(100-output_theta))
                distance = math.sqrt(math.pow(gotox - self.odometry.x, 2) + math.pow(gotoy - self.odometry.y, 2))
                if distance < 0.05:
                    self.create.drive_direct(0, 0)
                    break
                self.sleep(0.01)
            
#            self.create.drive_direct(int(0.1 * 1000.0), int(0.1 * 1000.0))
#            self.sleep(3)
        #find x distance
#        state = self.create.update()
#        if state is not None:
#            self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
#    print("[{},{},{}]".format(self.odometry.x, self.odometry.y,

#            self.create.drive_direct(0,0)
            print("BEFORE MOVEMENT forward FUNC:::", self.particlesList[0].theta,self.particlesList[1].theta,self.particlesList[2].theta,self.particlesList[3].theta,self.particlesList[4].theta,self.particlesList[5].theta )
            self.particlesList = self.p.Movement(self.odometry.x-oldx, self.odometry.y-oldy, self.odometry.theta-oldang,self.particlesList)
            print("AFTER MOVEMENT forward FUNC:::", self.particlesList[0].theta,self.particlesList[1].theta,self.particlesList[2].theta,self.particlesList[3].theta,self.particlesList[4].theta,self.particlesList[5].theta)
            x, y, theta = self.p.Estimation(self.particlesList)
            self.virtual_create.set_pose((x, y, 0.1), theta)
            self.data = []
            for particle in self.particlesList:
                self.data.append(particle.x)
                self.data.append(particle.y)
                self.data.append(0.1)
                self.data.append(particle.theta)
            
#            self.particlesList = self.data
            self.virtual_create.set_point_cloud(self.data)

    def turn_left(self):
        oldx = self.odometry.x
        oldy = self.odometry.y
        oldang = self.odometry.theta
        goal = self.odometry.theta + (math.pi / 2)
        #        t_end = self.time.time() + 1
        #        while self.time.time() < t_end:
        #            self.create.drive_direct(int(-100), int(100))
        #            self.time.sleep(2)
        #            self.create.drive_direct(0,0)
        #        def go_to_angle(self, goal_theta):
        while math.fabs(math.atan2(math.sin(goal - self.odometry.theta), math.cos(goal - self.odometry.theta))) > 0.05:
#            print("hello")
            #                print("Go TO: " + str(goal_theta) + " " + str(self.odometry.theta))
            output_theta = self.pidTheta.update(self.odometry.theta, goal, self.time.time())
            print("OUTPUT THETAAAAAAAAAAAA::",output_theta)
            self.create.drive_direct(int(+output_theta), int(-output_theta))
            self.sleep(0.01)
        self.create.drive_direct(0, 0)
        print("BEFORE MOVEMENT LEFT FUNC:::", self.particlesList[0].theta,self.particlesList[1].theta,self.particlesList[2].theta,self.particlesList[3].theta,self.particlesList[4].theta,self.particlesList[5].theta)
        self.particlesList = self.p.Movement(self.odometry.x-oldx, self.odometry.y-oldy, output_theta-oldang, self.particlesList)
        print("AFTER MOVEMENT LEFT FUNC:::", self.particlesList[0].theta,self.particlesList[1].theta,self.particlesList[2].theta,self.particlesList[3].theta,self.particlesList[4].theta,self.particlesList[5].theta)
        x, y, theta = self.p.Estimation(self.particlesList)
        self.virtual_create.set_pose((x, y, 0.1), theta)
        self.data = []
        for particle in self.particlesList:
            self.data.append(particle.x)
            self.data.append(particle.y)
            self.data.append(0.1)
            self.data.append(particle.theta)
        
        #            self.particlesList = self.data
        self.virtual_create.set_point_cloud(self.data)
#        t_end = self.time.time() + 1
#        while self.time.time() < t_end:
#            self.create.drive_direct(int(0.1* 1000.0), int(-0.1 * 1000.0))
#            self.sleep(2)
#            self.create.drive_direct(0,0)
#            self.data = self.p.Movement(self.odometry.x, self.odometry.y, self.odometry.theta,self.particlesList)
#            #
#            self.virtual_create.set_point_cloud(self.data)

    def turn_right(self):
        oldx = self.odometry.x
        oldy = self.odometry.y
        oldang = self.odometry.theta
        goal = self.odometry.theta - (math.pi / 2)
#        t_end = self.time.time() + 1
#        while self.time.time() < t_end:
#            self.create.drive_direct(int(-100), int(100))
#            self.time.sleep(2)
#            self.create.drive_direct(0,0)
#        def go_to_angle(self, goal_theta):
        while math.fabs(math.atan2(math.sin(goal - self.odometry.theta), math.cos(goal - self.odometry.theta))) > 0.05:
            print("hello")
#                print("Go TO: " + str(goal_theta) + " " + str(self.odometry.theta))
            output_theta = self.pidTheta.update(self.odometry.theta, goal, self.time.time())
            self.create.drive_direct(int(+output_theta), int(-output_theta))
            self.sleep(0.01)
        self.create.drive_direct(0, 0)
        self.particlesList = self.p.Movement(self.odometry.x-oldx, self.odometry.y-oldy, output_theta-oldang, self.particlesList)
#        x, y, theta = self.pf.get_estimate()
#        self.virtual_create.set_pose((x, y, 0.1), theta)
        x, y, theta = self.p.Estimation(self.particlesList)
        self.virtual_create.set_pose((x, y, 0.1), theta)
        self.data = []
        for particle in self.particlesList:
            self.data.append(particle.x)
            self.data.append(particle.y)
            self.data.append(0.1)
            self.data.append(particle.theta)
                
                #            self.particlesList = self.data
        self.virtual_create.set_point_cloud(self.data)
