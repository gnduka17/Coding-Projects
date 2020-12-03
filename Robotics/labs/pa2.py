"""
Code for PA2
Use "run.py [--sim] pa2" to execute
"""
import math

class Run:
    def __init__(self, factory):
        """Constructor.

        Args:
            factory (factory.FactorySimulation)
        """
        self.arm = factory.create_kuka_lbr4p()
        self.time = factory.create_time_helper()

    def run(self):
         print("doing 1")
         self.arm.go_to(5,math.radians(90));
         self.arm.go_to(4,math.radians(-90));
         self.time.sleep(5);
        
         print("doing 2")
         self.arm.go_to(3,math.radians(-70));
         self.time.sleep(2);
         self.arm.go_to(1,math.radians(-45));
         self.time.sleep(2);
         print("end ofdoing 2")

         print("FORWARD KINEMATICS")
         x = 0.4*math.sin(math.radians(45))+ 0.5430*math.sin(math.radians(-90) + math.radians(45) )
         z = 0.4*math.cos(math.radians(45))+ 0.5430*math.cos(math.radians(-90) + math.radians(45) )
         
         x1 = 0.4*math.sin(math.radians(-45))+ 0.5430*math.sin(math.radians(-70) + math.radians(-45) )
         z1 = 0.4*math.cos(math.radians(-45))+ 0.5430*math.cos(math.radians(-70) + math.radians(-45) )
         
         x2 = 0.4*math.sin(math.radians(20))+ 0.5430*math.sin(math.radians(80) + math.radians(20) )
         z2 = 0.4*math.cos(math.radians(20))+ 0.5430*math.cos(math.radians(80) + math.radians(20) )

         x3 = 0.4*math.sin(math.radians(45))+ 0.5430*math.sin(math.radians(-10) + math.radians(45) )
         z3 = 0.4*math.cos(math.radians(45))+ 0.5430*math.cos(math.radians(-10) + math.radians(45) )

         print("Using joint 2  ( range -90 to 90 degrees )")
         print("Using joint 4  ( range -120 to 120 degrees )")
         print("Go to 45 , -90 deg, FK : [",-1*x,",",0.0001,",",z+0.307,"]")
         print("Go to -45 , -70 deg, FK : [",-1*x1,",",0.0001,",",z1+0.307,"]")
         print("Go to 20 , 80 deg, FK : [",-1*x2,",",0.0001,",",z2+0.307,"]")
         print("Go to 45 , -10 deg, FK : [",-1*x3,",",0.0001,",",z3+0.307,"]")
         
         print("INVERSE KINEMATICS")
         r=math.sqrt((-0.10112*-0.10112)+((0.97380-0.307)*(0.97380-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(0.97380-0.307,-0.10112)-b-math.pi/2
         
         print("Go to [",0.10112,",",0.97380,"], IK: [",'%.1f'%math.degrees(-1*angle1),"deg,",'%.1f'%math.degrees(-1*angle2),"deg]")
         
         r=math.sqrt((-0.77497*-0.77497)+((0.36036-0.307)*(0.36036-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(0.36036-0.307,-0.77497)-b-math.pi/2
         
         print("Go to [",0.77497,",",0.36036,"], IK: [",'%.1f'%math.degrees(-1*angle1),"deg,",'%.1f'%math.degrees(-1*angle2),"deg]")
         
         r=math.sqrt((-0.67156*-0.67156)+((0.58859-0.307)*(0.58859-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(0.58859-0.307,-0.67156)-b-math.pi/2
         
         print("Go to [",-0.67156,",",0.58859,"], IK: [",'%.1f'%math.degrees(angle1),"deg,",'%.1f'%math.degrees(angle2),"deg]")
         
         print("DRAWING RECTANGLE ATTEMPT 1")
         
         
#         self.arm.go_to(1,0);
#         self.time.sleep(2)
#         self.arm.go_to(3,0);
#         self.time.sleep(2)
#         print("doneee")
         r=math.sqrt((-0.3*-0.3)+((1.0-0.307)*(0.58859-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(1.0-0.307,-0.3)-b-math.pi/2
#         self.arm.set_color(0.0,0.0,0.0)
         
         
         self.arm.disable_painting()
         self.arm.go_to(1,angle1);
#         self.time.sleep(2)
         self.arm.go_to(3,angle2);
         self.time.sleep(2)
         self.arm.set_color(0.0,0.0,0.0)
         self.arm.enable_painting()
         self.time.sleep(2)
#         self.arm.enable_painting()
#         self.time.sleep(4)

         
         r=math.sqrt((-0.3*-0.3)+((1.0-0.307)*(1.0-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(1.0-0.307,-0.3)-b-math.pi/2
         self.arm.set_color(0.0,0.0,0.0)
        
         self.arm.disable_painting()
         self.arm.go_to(1,-1*angle1);
#         self.time.sleep(2)
         self.arm.go_to(3,-1*angle2);
         self.time.sleep(2)
         self.arm.enable_painting()
         self.time.sleep(2)
#         self.time.sleep(4)

         r=math.sqrt((-0.3*-0.3)+((0.9-0.307)*(0.9-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(0.9-0.307,-0.3)-b-math.pi/2
         self.arm.set_color(0.0,0.0,0.0)
        
         self.arm.disable_painting()
         self.arm.go_to(1,-1*angle1);
#         self.time.sleep(2)
         self.arm.go_to(3,-1*angle2);
         self.time.sleep(5)
         self.arm.enable_painting()
         self.time.sleep(2)
#         self.time.sleep(4)

         r=math.sqrt((-0.3*-0.3)+((0.9-0.307)*(0.9-0.307)))
         a = math.acos(((0.4*0.4)+(0.5430*0.5430)-(r*r))/(2*0.4*0.5430))
         b=math.acos(((r*r)+(0.4*0.4)-(0.5430*0.5430))/(2*0.4*r))
         angle2 = math.pi - a
         angle1 = math.atan2(0.9-0.307,-0.3)-b-math.pi/2
         self.arm.set_color(0.0,0.0,0.0)
         self.arm.disable_painting()
         self.arm.go_to(1,angle1);
#         self.time.sleep(2)
         self.arm.go_to(3,angle2);
         self.time.sleep(5)
         self.arm.enable_painting()
         self.time.sleep(2)
         
         
         print("DRAWING RECTANGLE ATTEMPT 2")
         self.arm.set_color(0.0,0.0,1.0)
         self.arm.go_to(1,0);
         self.arm.go_to(3,0);
         self.time.sleep(3)
         self.arm.go_to(5,math.radians(90));
         self.arm.go_to(4,math.radians(-90));
         self.time.sleep(1);
         self.arm.enable_painting()
         self.time.sleep(2)
         
         self.arm.set_color(1.0,0.0,0.0)
         self.arm.disable_painting()
         self.arm.go_to(3,math.radians(-90));
         self.time.sleep(5)
         self.arm.enable_painting()
         self.time.sleep(2)
         
         
         self.arm.set_color(0.0,1.0,0.0)
         self.arm.disable_painting()
         self.arm.go_to(3,math.radians(90));
         self.time.sleep(5)
         self.arm.enable_painting()
         self.time.sleep(2)
         
        
         
         
         
         
         pass
