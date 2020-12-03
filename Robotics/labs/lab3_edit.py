"""
Sample Code for Lab3
Use "run.py [--sim] lab3" to execute
"""

from pyCreate2 import create2
import datetime
import math as math
#import my_robot as rbt #NEW

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

        #CONSTANTS
        diameter = 72.0
        W = 235.0
        countPerRev = 508.8
        speed = 100
        leftTime = 3.9
        forwardTime = 11

        # request sensors
        self.create.start_stream([
            create2.Sensor.LeftEncoderCounts,
            create2.Sensor.RightEncoderCounts,
        ])

        result = 1
        start = datetime.datetime.now()
        self.create.drive_direct(speed, speed) 
        state = self.create.update()
        startLeft = 0
        startRight = 0
        distance = 0
        theta = 0
        x = 0
        y = 0
        leftSign = 1
        rightSign = 1
        
            
        while result < forwardTime:       #GO FORWARDS
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            print(startLeft)
            
            state = self.create.update()
            
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            print(endLeft)

            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            #CALCULATIONS            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft*leftSign
            dR = cR*nRight*rightSign

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS            
    

            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()

        self.create.drive_direct(speed/2, -speed/2) 
        start = datetime.datetime.now()
        result = 1
        while result < leftTime:       #GO LEFT
            
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS  
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()
        
        self.create.drive_direct(speed, speed) 
        start = datetime.datetime.now()
        result = 1
        
        while result < forwardTime:       #GO FORWARDS
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS            
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()

        self.create.drive_direct(speed/2, -speed/2) 
        start = datetime.datetime.now()
        result = 1
        while result < leftTime:       #GO LEFT
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS  
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()

        self.create.drive_direct(speed, speed) 
        start = datetime.datetime.now()
        result = 1
        
        while result < forwardTime:       #GO FORWARDS
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS            
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()

        self.create.drive_direct(speed/2, -speed/2) 
        start = datetime.datetime.now()
        result = 1
        
        while result < leftTime:       #GO LEFT
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS  
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()

        self.create.drive_direct(speed, speed) 
        start = datetime.datetime.now()
        result = 1
       
        while result < forwardTime:       #GO FORWARDS
            if state is not None:
                print(state.__dict__)
            startLeft = state.leftEncoderCounts
            startRight = state.rightEncoderCounts
            state = self.create.update()
            if state is not None:
                print(state.__dict__)
            endLeft = state.leftEncoderCounts
            endRight = state.rightEncoderCounts
            
            #CALCULATIONS            
            nLeft = endLeft - startLeft
            if nLeft < 0:
                leftSign = -1
            nRight = endRight - startRight
            if nRight < 0:
                rightSign = -1

            print("NLEFT:", nLeft)
            print("NRIGHT:",nRight)
            
            cL = 3.1415*diameter/countPerRev 
            cR = 3.1415*diameter/countPerRev 
            
            dL = cL*nLeft
            dR = cR*nRight

            dDist = (dR + dL)/(2*1000.0)
            distance += dDist
            
            dTheta = 180/3.1415*(dR - dL)/W
            theta += dTheta
            
            print("DISTANCE: ", round(distance,2))
            print("THETA: ", round(theta,3))
            
            x += dDist*math.cos(theta)
            y += dDist*math.sin(theta)
            print("X-DISTANCE: ", round(x,2))
            print("Y-DISTANCE:  ", round(y,2))
            #CALCULATIONS            
            
            stop = datetime.datetime.now()
            result = (stop - start).total_seconds()
