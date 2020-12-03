from pyCreate2 import create2
import lab10_map
import random
import math
import odometry
import pid_controller


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

# for node in self.children:
#     nodeList.append(node)
# return self.children


class Run:
    def __init__(self, factory):
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.map = lab10_map.Map("lab10.png")
        self.nodeList = []
        start = Node(270, 308, None)
        self.nodeList.append(start)
        self.odometry = odometry.Odometry()
        self.pidTheta = pid_controller.PIDController(300, 5, 50, [-10, 10], [-200, 200], is_angle=True)
        self.pidDistance = pid_controller.PIDController(1000, 0, 50, [0, 0], [-200, 200], is_angle=False)
    
    def run(self):
        # This is an example on how to check if a certain point in the given map is an obstacle
        # Each pixel corresponds to 1cm
        self.create.start()
        self.create.safe()
        
        # request sensors
        self.create.start_stream([
          create2.Sensor.LeftEncoderCounts,
          create2.Sensor.RightEncoderCounts,
        ])
        print(self.map.has_obstacle(50, 60))
          
        # This is an example on how to draw a line
        # self.map.draw_line((0,0), (self.map.width, self.map.height), (255, 0, 0))
        # self.map.draw_line((0,self.map.height), (self.map.width, 0), (0, 255, 0))
        # self.map.save("lab10_rrt.png")
      
        goal_x = 60
        goal_y = 120
        move_dis = 10
      
      #generate x and y in map
      
        for _ in range(10000):
            temp_x = random.randrange(0, 300)
            temp_y = random.randrange(0, 300)
          
            min_dis = 99999
            min_node = None
            for node in self.nodeList:
                x_diff = temp_x - node.x
                y_diff = temp_y - node.y
                distance = math.sqrt(x_diff**2 + y_diff**2)
                if distance < min_dis:
                  min_dis = distance
                  min_node = node
              
            goal_theta = math.atan2(temp_y - min_node.y, temp_x - min_node.x)
              
            new_x = min_node.x + move_dis * math.cos(goal_theta)
            new_y = min_node.y + move_dis * math.sin(goal_theta)
              
              #print(str(new_x) + ", " + str(new_y))
              #new_x = min_node.x + move_dis
              #new_y = min_node.y + move_dis
              
            if not self.map.has_obstacle(new_x, new_y):
              new_node = Node(new_x, new_y, min_node)
              self.nodeList.append(new_node)
              self.map.draw_line((min_node.x, min_node.y), (new_x, new_y), (255, 0, 0))
                      #print("entered")
                      # else:
                      #     print("else")
                      
            if abs(new_x - goal_x) < 5 and abs(new_y - goal_y) < 5:
                  break

                    #for node in self.nodeList:
                    #print(str(node.x) + ", " + str(node.y))
        parents = []
        node_curr = self.nodeList[len(self.nodeList) - 1]
        while node_curr != self.nodeList[0]:
            self.map.draw_line((node_curr.x, node_curr.y), (node_curr.parent.x, node_curr.parent.y),(0, 255, 0))
            node_curr = node_curr.parent
            parents.insert(0, node_curr)
                               
                               
        self.map.save("lab10_rrtTest.png")
                                   
       #min_node.addChild(new_node)
        base_speed = 200
       #move robot
#        for node in parents:
#            print(str(node.x) + ", " + str(node.y))
        for node in parents:
            
#            end_time = self.time.time() + 3
#            while self.time.time() < end_time:
            node.x=node.x/100
            node.y=node.y/100
            print("nodex: ", node.x, "nodeY: ", node.y)
            while True:
                state = self.create.update()
                if state is not None:
                   self.odometry.update(state.leftEncoderCounts, state.rightEncoderCounts)
                   goal_theta = math.atan2(node.y - self.odometry.y, node.x - self.odometry.x)
                   theta = math.atan2(math.sin(self.odometry.theta), math.cos(self.odometry.theta))
                   print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
#                   new_row = [self.time.time(), math.degrees(self.odometry.theta), math.degrees(goal_theta), self.odometry.x, self.odometry.y]
                              # result = np.vstack([result, new_row])
                                      
                   output_theta = self.pidTheta.update(self.odometry.theta, goal_theta, self.time.time())
                  
                   # base version:
                   self.create.drive_direct(int(base_speed+output_theta), int(base_speed-output_theta))
#                       print("Odometry x: ", Odometry.)
                  # improved version 1: stop if close enough to goal
                   distance = math.sqrt(math.pow(node.x - self.odometry.x, 2) + math.pow(node.y - self.odometry.y, 2))
                   print("nodex-od: ", node.x - self.odometry.x )
                   print("nodey-od: ", node.y - self.odometry.y )
#                   distance = distance/100
                   print("Distance: ", distance)
                   if distance < 0.1:
                       print("arrived")
                       break
#                   distance = math.sqrt(math.pow(node.x - self.odometry.x, 2) + math.pow(node.y - self.odometry.y, 2))
#                   output_distance = self.pidDistance.update(0, distance, self.time.time())
#                   self.create.drive_direct(int(output_theta + output_distance), int(-output_theta + output_distance))
#                   # improved version 2: fuse with velocity controller
#
#                   if distance < 0.1:
#                       break
#
#                   self.create.drive_direct(int(output_theta + output_distance), int(-output_theta + output_distance))
