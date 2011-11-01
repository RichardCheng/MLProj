#strip digits.test (i.e. leave only the label. For easier matlab processing)
import os, shutil, subprocess, sys, re;

os.chdir('D:\\Documents\\Dropbox\\MLProj\\p4\\')

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
