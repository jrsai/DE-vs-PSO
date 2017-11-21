from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
import matplotlib.pyplot as plt
import numpy as np

def f8(x):
    """Rastrigin's Function"""
    sum = 0.0
    for i in range(0, len(x)):
        sum += (x[i]**2 - 10 * np.cos(2*np.pi*x[i]) + 10)
    return sum

#Function 8
X = np.linspace(-100, 100, 100)            # points from 0..10 in the x axis
Y = np.linspace(-100, 100, 100)            # points from 0..10 in the y axis
X, Y = np.meshgrid(X, Y)               # create meshgrid
Z = f8([X, Y])                         # Calculate Z

# Plot the 3D surface for first function from project
fig = plt.figure()
ax = fig.gca(projection='3d')         # set the 3d axes
ax.plot_surface(X, Y, Z,
                rstride=3,
                cstride=3,
                alpha=0.3,
                cmap='hot')
plt.show()

