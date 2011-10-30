#run svm_classify on the overall test set.
import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')

best_c = "5";
 
s2 = subprocess.check_output('svm_multiclass_classify.exe "D:\Documents\Dropbox\MLProj\p4\digits\digits.test" "D:\Documents\Dropbox\MLProj\p4\digits\multimodel/digitsmod_c'+best_c+'.train" "D:\Documents\Dropbox\MLProj\p4\digits\multiout/digitsout_c'+best_c+'.val"')
    
sys.stdout.write("%s\n" % s2)    