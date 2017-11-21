from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
import matplotlib.pyplot as plt
import numpy as np

def f9(x):
    """Katsuura Function"""
    product = 1
    for i in range(0, len(x)):
        sum = 0
        for j in range(1,33):
            term = np.power(2,j) * x[i]
            sum += np.abs(term - np.round(term))/(np.power(2,j))
        product *= np.power(1+((i+1)*sum),10.0/ np.power(len(x),1.2))
    return (10/len(x) * len(x) * product - (10/len(x) * len(x)))

#Function 9
X = np.linspace(-100, 100, 100)            # points from 0..10 in the x axis
Y = np.linspace(-100, 100, 100)            # points from 0..10 in the y axis
X, Y = np.meshgrid(X, Y)               # create meshgrid
Z = f9([X, Y])                         # Calculate Z

# Plot the 3D surface for first function from project
fig = plt.figure()
ax = fig.gca(projection='3d')         # set the 3d axes
ax.plot_surface(X, Y, Z,
                rstride=3,
                cstride=3,
                alpha=0.3,
                cmap='hot')
plt.show()
