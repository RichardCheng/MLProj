#run svm_classify on the overall test set.
import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')

d = '4'
bestmodel = [0, 3, 0, 0, 4, 1, 1, 1, 6, 2];

carray = [0.0001,0.0005,0.001,0.005,0.01,0.05,0.1];   
for j in range(0, 10): 
    c = str(carray[bestmodel[j]])
    j = str(j)
    s2 = subprocess.check_output('svm_classify.exe "D:\Documents\Dropbox\MLProj\p4\digits\digits.test" "D:\Documents\Dropbox\MLProj\p4\digits\polymodel/'+j+'/digitsmod'+j+'_'+d+'_'+c+'.train" "D:\Documents\Dropbox\MLProj\p4\digits\polyout\digitsout'+j+'.test"')

    
    