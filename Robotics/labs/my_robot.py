class MyRobot:
    def __init__(self, constSpeed, selfCrea, selfTime):
        self.base_speed = constSpeed
        self.create = selfCrea
        self.time = selfTime

    def forward(self, distance):
        self.create.drive_direct(self.base_speed * 1000, self.base_speed * 1000)
        self.time.sleep(distance / self.base_speed)

    def backward(self, distance):
        self.create.drive_direct(self.base_speed * -1000, self.base_speed * -1000)
        self.time.sleep(distance / self.base_speed)

    def turn_right(self, duration):
        self.create.drive_direct(self.base_speed * -1000, self.base_speed * 1000)
        self.time.sleep(duration)

    def turn_left(self, duration):
        self.create.drive_direct(self.base_speed * 1000, self.base_speed * -1000)
        self.time.sleep(duration)

    def stop(self, duration):
        self.create.drive_direct(0, 0)
        self.time.sleep(duration)
