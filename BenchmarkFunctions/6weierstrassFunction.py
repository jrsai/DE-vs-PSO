from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm
import matplotlib.pyplot as plt
import numpy as np

def f6(x):
    sum1, sum2, sum3 = 0.0, 0.0, 0.0
    a = 0.5
    b = 3
    kmax = 20
    for i in range(len(x)):
        for k in range(0, kmax):
            sum2 += (a ** k) * np.cos(2 * np.pi * (b ** k) * (x[i] + 0.5))
            sum3 += (a ** k) * np.cos(2 * np.pi * (b ** k) * 0.5)
    sum1 += sum2 - (len(x) * sum3)
    return sum1

#Function 6
X = np.linspace(-100, 100, 100)            # points from 0..10 in the x axis
Y = np.linspace(-100, 100, 100)            # points from 0..10 in the y axis
X, Y = np.meshgrid(X, Y)               # create meshgrid
Z = f6([X, Y])                         # Calculate Z

# Plot the 3D surface for first function from project
fig = plt.figure()
ax = fig.gca(projection='3d')         # set the 3d axes
ax.plot_surface(X, Y, Z,
                rstride=3,
                cstride=3,
                alpha=0.3,
                cmap='hot')
plt.show()
