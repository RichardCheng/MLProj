import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')
#shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\arxiv\\temp\\')
#os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\arxiv\\temp\\')

stuff = re.compile(".*(Accuracy on test set: )(.*)% \\(.*")

bestc = "1"

s1 = subprocess.check_output('svm_learn.exe -c '+bestc+' "D:/Documents/Dropbox/MLProj/p4/arxiv/arxiv.norm.train" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train.model"')
s2 = subprocess.check_output('svm_classify.exe "D:/Documents/Dropbox/MLProj/p4/arxiv/arxiv.norm.test" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train.model" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train.result"')

sys.stdout.write("%s\n" % s2)
    