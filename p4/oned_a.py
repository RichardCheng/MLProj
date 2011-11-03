#train 10 different biased classifiers with a linear kernel for each of the training sets

import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')
shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\multimodel\\')
shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\multiout\\')
os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\multimodel\\')
os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\multiout\\')

stuff = re.compile(".*(Zero/one-error on test set: )(.*)% \\(.*")
    
for i in ("0.00001", "0.00005", "0.0001", "0.0005", "0.001", "0.005", "0.01", "0.05", "0.1", "0.5", "1", "5"):  
    s1 = subprocess.check_output('svm_multiclass_learn.exe -c '+i+' "D:\Documents\Dropbox\MLProj\p4\digits\digits.train" "D:\Documents\Dropbox\MLProj\p4\digits\multimodel/digitsmod_c'+i+'.train"')
    s2 = subprocess.check_output('svm_multiclass_classify.exe "D:\Documents\Dropbox\MLProj\p4\digits\digits.val" "D:\Documents\Dropbox\MLProj\p4\digits\multimodel/digitsmod_c'+i+'.train" "D:\Documents\Dropbox\MLProj\p4\digits\multiout/digitsout_c'+i+'.val"')
    
    #sys.stdout.write("%s\n" % s2)
    stemp = s2.split('\n')
    for s in stemp:
        res = stuff.match(s)
        if res:
            sys.stdout.write("%s " % res.group(2))
            break
    sys.stdout.write("\n")