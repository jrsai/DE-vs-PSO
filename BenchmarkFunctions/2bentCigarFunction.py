from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
import matplotlib.pyplot as plt
import numpy as np


def f2(x):
    """Bent cigar function"""
    sum = 0.0
    sum += x[0]**2
    for i in range(2, len(x)+1):
        sum += x[i-1]**2
    sum *= (10**6)
    return sum

#Function 2
X = np.linspace(-100, 100, 100)            # points from 0..10 in the x axis
Y = np.linspace(-100, 100, 100)            # points from 0..10 in the y axis
X, Y = np.meshgrid(X, Y)               # create meshgrid
Z = f2([X, Y])                         # Calculate Z

# Plot the 3D surface for first function from project
fig = plt.figure()
ax = fig.gca(projection='3d')         # set the 3d axes
ax.plot_surface(X, Y, Z,
                rstride=3,
                cstride=3,
                alpha=0.3,
                cmap='hot')
plt.show()


