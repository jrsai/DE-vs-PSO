from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
import matplotlib.pyplot as plt
import numpy as np

def f5(x):
    """Ackley's Function"""
    sum1, sum2 = 0.0, 0.0
    for i in range(0, len(x)):
        sum1 += x[i]**2
    sum1 = sum1 / float(len(x))
    for i in range(0, len(x)):
        sum2 += np.cos(2*np.pi*x[i])
    sum2 = sum2 / float(len(x))

    # Calculate first exp
    exp1 = -20.0 * (np.e ** (-0.2 * sum1))
    exp2 = np.e ** sum2

    # Calculate final result
    result = exp1 - exp2 + 20 + np.e
    return result

#Function 5
X = np.linspace(-100, 100, 100)            # points from 0..10 in the x axis
Y = np.linspace(-100, 100, 100)            # points from 0..10 in the y axis
X, Y = np.meshgrid(X, Y)               # create meshgrid
Z = f5([X, Y])                         # Calculate Z

# Plot the 3D surface for first function from project
fig = plt.figure()
ax = fig.gca(projection='3d')         # set the 3d axes
ax.plot_surface(X, Y, Z,
                rstride=3,
                cstride=3,
                alpha=0.3,
                cmap='hot')
plt.show()
