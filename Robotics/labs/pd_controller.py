import numpy as np

class PDController:
    def __init__(self, kp, kb, minOutput, maxOutput, base_speed = 100):
        self.kp = kp
        self.kb = kb
        self.minOutput = minOutput
        self.maxOutput = maxOutput
        self.base_speed = base_speed

    def update(self, error, prev_error):
        '''
        param:
        error = goal_distance - actual_distance

        return:
        speed of left and right wheels in mm/s
        '''
        delta_speed = np.abs(self.kp * error + self.kb * (error - prev_error))
        print("delta_speed = ", delta_speed)

        left_wheel_speed = self.base_speed
        right_wheel_speed = self.base_speed

        # too far from the wall, turn left
        if error < 0:
            right_wheel_speed += delta_speed

        # too close to the wall, turn right
        elif error > 0:
            left_wheel_speed += delta_speed

        # exactly the distance, no turning
        else:
            pass

        result_left = self._clamp(left_wheel_speed)
        result_right = self._clamp(right_wheel_speed)
        return result_left, result_right

    def _clamp(self, speed):
        '''
        function to clamp the speed within the given range
        '''
        if speed > self.maxOutput:
            speed = self.maxOutput

        if speed < self.minOutput:
            speed = self.minOutput

        return speed
