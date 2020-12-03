"""
Sample Code for Lab2 for testing the sonar
Use "run.py [--sim] lab2_sonar_test" to execute
"""
from statistics import mean
import numpy
import matplotlib.pyplot as plt

class Run:
    def __init__(self, factory):
        """Constructor.

        Args:
            factory (factory.FactoryCreate)
        """
        self.create = factory.create_create()
        self.time = factory.create_time_helper()
        self.sonar = factory.create_sonar()

    def run(self):
        index = 1
        errors=0
        list=[]
        binlist = []
        reading=0.470

        bin0 = 0
        bin1=0
        bin2=0
        bin3=0
        bin4=0
        bin5=0
        bin6=0
        bin7=0
        bin8 = 0
        bin9 = 0
        bin10 = 0

        while index<=100 :

            diff=abs(reading-numpy.round(self.sonar.get_distance(),decimals=3))

            #diff = numpy.round(diff,decimals=3)


            if diff <=0.001:
                bin0+=1
            elif diff <=0.002:
                bin1+=1

            elif diff <=0.003:
                bin2+=1
            elif diff <=0.004:
                bin3 = bin3+1
            elif diff <= 0.005:
                bin4 = bin4+1
            elif diff <= 0.006:
                bin5 = bin5+1
            elif diff <= 0.007:
                bin6 = bin6+1
            elif diff <= 0.008:
                bin7 = bin7+1
            elif diff <= 0.009:
                bin8 = bin8+1
            elif diff <= 0.010:
                bin9 = bin9+1
            else:
                bin10 = bin10+1

            list.append(diff)
            print(self.sonar.get_distance())
            self.time.sleep(0.1)
            index = index+1
        binlist.append(bin0)
        binlist.append(bin1)
        binlist.append(bin2)
        binlist.append(bin3)
        binlist.append(bin4)
        binlist.append(bin5)
        binlist.append(bin6)
        binlist.append(bin7)
        binlist.append(bin8)
        binlist.append(bin9)
        binlist.append(bin10)

        print('Mean:')
        print(numpy.mean(list))

        print('Std:')
        print(numpy.std(list))

        print("bin0 ", bin0)
        print("bin1 ",bin1)
        print("bin2 ", bin2)
        print("bin3 ", bin3)
        print("bin4 ", bin4)
        print("bin5 ", bin5)
        print("bin6 ", bin6)
        print("bin7 ", bin7)
        print("bin8 ", bin8)
        print("bin9 ", bin9)
        print("bin10 ", bin10)

        print("histogram...hardcoded with one example")
        y = [17, 161, 176, 138, 103, 80, 57, 35, 22, 11, 200]
        # x = numpy.random.randn(10000)
        # plt.hist(y, 10, orientation="horizontal", density=1, alpha=0.75)
        plt.bar(range(len(y)), y)
        plt.show()

        # plt.hist(binlist, 10, density=1, alpha=0.75)
        # plt.show()



