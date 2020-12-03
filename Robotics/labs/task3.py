import numpy
import matplotlib.pyplot as plt


def mysum2(listofints):
    return numpy.sum(listofints)


def plotcircle1():
    angle = numpy.linspace(0, 6.3, 100)
    # #generating 2 arrays for x and y coords
    xarr= 2 * numpy.cos(angle)
    yarr = 2 * numpy.sin(angle)

    #plotting
    fig, ax = plt.subplots(1)
    ax.plot(xarr, yarr)
    ax.set_aspect(1)

    #display
    plt.show()

def plotnorm1():
    x = numpy.random.randn(10000)
    plt.hist(x, 20, density=1, alpha=0.75)
    plt.show()

def main():
    list1 = [1,5,7]
    print(mysum2(list1))
    list1 = [-5, 3]
    print(mysum2(list1))
    list1 = []
    print(mysum2(list1))

    plotcircle1()

    plotnorm1()


main()
