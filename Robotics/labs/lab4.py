from p_controller import PController
from pd_controller import PDController

class Run:
    def __init__(self, factory):
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.sonar = factory.create_sonar()
        self.servo = factory.create_servo()
        # define the gains here
        self.kp_p = 500
        self.kp_pd = 800
        self.kd = 800
        self.minOutput = -500
        self.maxOutput = 500
        self.base_speed = 50

        # instantiate your controllers here
        self.p_controller = PController(self.kp_p, self.minOutput, self.maxOutput, self.base_speed)
        self.pd_controller = PDController(self.kp_pd, self.kd, self.minOutput, self.maxOutput, self.base_speed)


    def run(self):
        self.create.start()
        self.create.safe()

        self.servo.go_to(70)
        self.time.sleep(2)

        goal_distance = 0.5

        prev_error = 0

        while True:
            distance = self.sonar.get_distance()
            if distance is not None:
                # update controllers and move robot here
                error = goal_distance - distance
                #left_wheel_speed, right_wheel_speed = self.pd_controller.update(error, prev_error)
                left_wheel_speed, right_wheel_speed = self.p_controller.update(error)
                self.create.drive_direct(right_wheel_speed, left_wheel_speed)
                prev_error = error
                print("actual_distance = ", distance)
                print("error = ", error)
                print("left_wheel_speed, right_wheel_speed = ", str(left_wheel_speed) + ", " + str(right_wheel_speed))
                print()
                self.time.sleep(0.01)
