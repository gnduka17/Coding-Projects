"""
Sample Solution for Lab3
Use "run.py [--sim] lab3_solution" to execute
"""

from pyCreate2 import create2
import math
import odometry
import matplotlib.pyplot as plt

listX = []
listY = []
listXTrue = []
listYTrue = []
class Run:
    def __init__(self, factory):
        """Constructor.

        Args:
            factory (factory.FactoryCreate)
        """
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.odometry = odometry.Odometry()

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
                listX.append(self.odometry.x)
                listY.append(self.odometry.y)
                listXTrue.append(self.create.sim_get_position()[0])
                listYTrue.append(self.create.sim_get_position()[1])
                    # print("real X ",self.create.sim_get_position()[0])
                print("[{},{},{}]".format(self.odometry.x, self.odometry.y, math.degrees(self.odometry.theta)))
                print("actual x: ",self.create.sim_get_position()[0] )
                print("actual y: ",self.create.sim_get_position()[1] )
            t = self.time.time()
            if start + time_in_sec <= t:
                break
    
    

    def run(self):
        time = (math.pi) * (create2.Specs.WheelDistanceInMM)/400
        self.create.start()
        self.create.safe()

        # request sensors
        self.create.start_stream([
            create2.Sensor.LeftEncoderCounts,
            create2.Sensor.RightEncoderCounts,
        ])

        self.create.drive_direct(100, 100)
        self.sleep(1/0.1)
        self.create.drive_direct(-100, 100)
        self.sleep(time)
        self.create.drive_direct(100, 100)
        self.sleep(0.5/0.1)
        self.create.drive_direct(-100, 100)
        self.sleep(time)
        self.create.drive_direct(100, 100)
        self.sleep(1/0.1)
        self.create.drive_direct(-100, 100)
        self.sleep(time)
        self.create.drive_direct(100, 100)
        self.sleep(0.5/0.1)
        plt.plot(listX, listY)
        plt.plot(listXTrue, listYTrue)
        plt.show()
