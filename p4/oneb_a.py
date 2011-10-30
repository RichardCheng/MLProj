#train 10 different biased classifiers with a linear kernel for each of the training sets

import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')
shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\model\\')
shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\out\\')
os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\model\\')
os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\out\\')

for j in range(0,10):
    os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\model\\'+str(j)+"\\")
    os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\digits\\out\\'+str(j)+"\\")

stuff = re.compile(".*(Accuracy on test set: )(.*)% \\(.*")
    
for i in (0.0001,0.0005,0.001,0.005,0.01,0.05,0.1):
    for j in range(0, 10): 
        i = str(i)
        j = str(j)
        s1 = subprocess.check_output('svm_learn.exe -c '+i+' "D:\Documents\Dropbox\MLProj\p4\digits\digits'+j+'.train" "D:\Documents\Dropbox\MLProj\p4\digits\model/'+j+'/digitsmod'+j+'_'+i+'.train"')
        s2 = subprocess.check_output('svm_classify.exe "D:\Documents\Dropbox\MLProj\p4\digits\digits'+j+'.val" "D:\Documents\Dropbox\MLProj\p4\digits\model/'+j+'/digitsmod'+j+'_'+i+'.train" "D:\Documents\Dropbox\MLProj\p4\digits\out/'+j+'/digitsout'+j+'_'+i+'.val"')
        stemp = s2.split('\n')
        for s in stemp:
            res = stuff.match(s)
            if res:
                sys.stdout.write("%s " % res.group(2))
                break
    sys.stdout.write("\n")