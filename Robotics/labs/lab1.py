"""
Example to move robot forward for 10 seconds
Use "python3 run.py [--sim] example1" to execute
"""


class Run:
    def __init__(self, factory):
        """Constructor.

        Args:
            factory (factory.FactoryCreate)
        """
        self.create = factory.create_create()
        self.time = factory.create_time_helper()

    def run(self):
        self.create.start()
        self.create.safe()

        self.create.drive_direct(100, 100)
        self.time.sleep(6)
        self.create.drive_direct(45, 100)
        
        #self.create.turn_angle(45, 100)
        #self.drive_straight(100)
        self.time.sleep(15)
        self.create.drive_direct(100, 100)
        self.time.sleep(10)
        self.create.drive_direct(0, 45)
        self.time.sleep(15)
        self.create.drive_direct(100, 0)
        self.time.sleep(10)
        
        
        
        
        
        #self.create.drive_direct(100, 60)
        
        #self.create.drive_direct(150, -100)
        # self.create.drive_direct(10, 5)

        self.create.stop()
