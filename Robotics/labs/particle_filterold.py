from pyCreate2 import create2
from lab8_map import Map
import lab8_map
import copy
import scipy.stats
import numpy as np
from numpy.random import choice
import math
import odometry


#create another clss for particle - x,y,thetha
class ParticleFilter:
    def __init__(self,numParticles, Map):
        self.particleNum = numParticles
        self.map = Map
    
    #first define numparticles - randomly in map (500/100)
    #prob - 1/150
    
    
    
    
    #for all particles create normal distrib. noise
    #add noise to x,y,th
    #translation - x and y noise
    
    def Movement(self,x,y,theta,listOfParticles):
        newlist = []
        print("IN MOVEMENT FUNC BEFORE THETA ROBOT: ", theta)
        for particle in listOfParticles:
            #noise = 0.2
            particle.x = particle.x + x + np.random.normal(0.0,0.01 )
            particle.theta = theta + np.random.normal(0.0, 0.05) 
            
#            if particle.theta>2*math.pi:
#                particle.theta = particle.theta-2*math.pi
#            particle.theta = math.fmod(particle.theta,2*math.pi)
            particle.y = particle.y + y + np.random.normal(0.0, 0.01)
            particle.x = min(self.map.top_right[0], max(particle.x, self.map.bottom_left[0]))
            particle.y = min(self.map.top_right[1], max(particle.y, self.map.bottom_left[1]))
            newlist.append(particle)
#            newlist.append(particle.x)
#            newlist.append(particle.y)
#            newlist.append(0.1)
#            newlist.append(particle.theta)
        print("IN MOVEMENT FUNC AFTER FOR LOOP:::", listOfParticles[0].theta,listOfParticles[1].theta,listOfParticles[2].theta,listOfParticles[3].theta,listOfParticles[4].theta, listOfParticles[5].theta)
        return newlist


        
        
    
#        normDist = np.random.normal(0,0.1)
        #return anything?
    
#
#    #update the probability- assign high probability and low; get rid of small
    def Sensing(self, listOfParticles, angle, senseDist):
        arrH = []
        for particle in listOfParticles:
            disHold = particle.theta + angle 
            arr = [particle.x, particle.y]
            dist = self.map.closest_distance(arr,disHold)
            probs = scipy.stats.norm(dist, 0.1).pdf(senseDist)
            if probs == 0:
                particle.p = float("-inf")
                arrH.append(particle.p)
            else:
                particle.p +=math.log(probs)
                arrH.append(particle.p)
        probArr = np.array(arrH)
        hold =scipy.special.logsumexp(probArr)
        probArr = probArr - hold
        for i in range(0,len(probArr)):
            listOfParticles[i].p = probArr[i]
        pA = []
        for part in listOfParticles:
            pA.append(math.exp(part.p))
        listOfParticles = choice(listOfParticles, len(listOfParticles),pA)
        listOfParticles= [copy.copy(particle) for particle in listOfParticles]
        return listOfParticles









#Sensing: compute what distance should be if particle position is accurate
#(closest_distance) + servo angle when using the closest distance
#compute the probability (1/numparticles)
#
#

#    #find the new x,y,th;
    def Estimation(self, listOfParticles):
        str = []
        x=[]
        y=[]
        ang=[]
        for particle in listOfParticles:
            str.append(math.exp(particle.p))
            x.append(particle.x)
            y.append(particle.y)
            ang.append(particle.theta)
        xavg = np.average(x,weights=str)
        yavg = np.average(y,weights=str)
        thetavg = np.average(ang,weights=str)
        return xavg, yavg, thetavg

#estimation: get an average from the location and orientation of your particles and return an x y theta
