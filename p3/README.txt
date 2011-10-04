README:
Main.m deals with problem 1a and 1c.
Oneb.m deals with problem 1b.
Oned.m deals with problem 1d.

Before running oneb.m and oned.m, make sure the global variables:

global featurenum;
global datanum; 
featurenum= 29328;
datanum = 1000;

are defined. 

This is how Main.m works:
It calls on readData() to read the data into matrices usable by matlab.
It runs classify() on the matrices to get the w and b matrix/vector.
getErrorRate() predicts the label using the produced w and b, and compares with the real w and b to determine the error rate.
The solution is then graphed.

This is how oneb.m works:
It simply serves as a plot tool. I ran SVM_light with the different parameters and produced the results.
oneb.m simply takes this result and plots it.

This is how oned.m works:
It calls readData() on the the result generated with SVM_light, as well as the result from classifying using perceptron primal (explained in Main.m). Then it compares each of the predicted result to the real results and tally up the exclusive errors each made. This is used in the documentation to complete the statistical test. 