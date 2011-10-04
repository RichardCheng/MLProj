global datanum;

disp 'reading validation data...'
[validY, validX] = readData('movie/polarity.validation');

disp 'reading training data...'
[trainY, trainX] = readData('movie/polarity.train');

disp 'getting W for training data...'
[w, b] = getW(trainY, trainX);

trainingLabels_primal = sign(classify(validX, w(20,:), b(20)));
realLabels = validY;
[trainingLabels_SVM, ~] = readData('movie/output1c/8c');
trainingLabels_SVM = sign(trainingLabels_SVM);

primalError = 0;
SVMError = 0;
bothError = 0;

for i = 1:datanum
    if realLabels(i) ~= trainingLabels_primal(i) || realLabels(i) == 0
        primalError = primalError + 1;
        
        if realLabels(i) ~= trainingLabels_SVM(i) || realLabels(i) == 0
            bothError = bothError + 1;
        end
    end
    
    if realLabels(i) ~= trainingLabels_SVM(i) || realLabels(i) == 0
        SVMError = SVMError + 1;
    end
end

SVMonlyError = SVMError - bothError
primalonlyError = primalError - bothError 