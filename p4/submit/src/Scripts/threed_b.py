#train 10 different biased classifiers with a linear kernel for each of the training sets

import os, shutil, subprocess, sys, re;
import fileinput, random

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')
#shutil.rmtree('D:\\Documents\\Dropbox\\MLProj\\p4\\arxiv\\temp\\')
#os.mkdir('D:\\Documents\\Dropbox\\MLProj\\p4\\arxiv\\temp\\')

#read line
doc = []
for line in fileinput.input(['D:/Documents/Dropbox/MLProj/p4/arxiv/arxiv.norm.train']):
    doc.append(line)

#shuffle lines
random.shuffle(doc)

testfile = 'D:/Documents/Dropbox/MLProj/p4/arxiv/temp/arxiv.4fold.test'
validfile = 'D:/Documents/Dropbox/MLProj/p4/arxiv/temp/arxiv.4fold.valid'


stuff = re.compile(".*(Accuracy on test set: )(.*)% \\(.*")

leng = len(doc)
for i in range(0,4):

    #flush the document
    f1 = open (testfile, 'w')
    f2 = open (validfile, 'w')
    f1.write('');
    f2.write('');
    f1.close()
    f2.close()
    
    f1 = open (testfile, 'a')
    f2 = open (validfile, 'a')
    
    sys.stdout.write("i is " + str(i) + "\n")
    
    for j in range(0, leng):
        if (j >= leng * i / 4 and j < leng * (i+1) / 4):
            f2.write(doc[j])
        else:
            f1.write(doc[j])
                        
    f1.close()
    f2.close()
    
    for c in ('0.001', '0.01', '0.1', '1'):
        s1 = subprocess.check_output('svm_learn.exe -c '+c+' -j 10 "'+testfile+'" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train_4fold.model"')
        s2 = subprocess.check_output('svm_classify.exe "'+validfile+'" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train_4fold.model" "D:/Documents/Dropbox/MLProj/p4/arxiv/temp/norm_train_4fold.result"')

        stemp = s2.split('\n')
        for s in stemp:
            res = stuff.match(s)
            if res:
                sys.stdout.write("%s " % res.group(2))
                break
        
        filer = open("arxiv\\temp\\arxiv.4fold.valid")
        filew = open("arxiv\\temp\\arxiv.4fold.stripped.valid","w")

        while 1:
            line = filer.readline()
            if not line:
                break
            stemp = line.split(' ')

            filew.write(stemp[0]+'\n'); 
            
        filew.close()
        filer.close()

        input(': ')
    
    sys.stdout.write("\n")

                
sys.stdout.write("%s\n" % s2)
    