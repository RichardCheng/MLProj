global featurenum;
global datanum; 

featurenum= 29328;
datanum = 1000;


disp 'reading validation data...'
[validY, validX] = readData('movie/polarity.validation');


totIter = 20;
validErrorRate = zeros(1, totIter);
trainErrorRate = zeros(1, totIter);
validErrorRate1 = zeros(1, totIter);
trainErrorRate1 = zeros(1, totIter);
validErrorRate2 = zeros(1, totIter);
trainErrorRate2 = zeros(1, totIter);
validErrorRate3 = zeros(1, totIter);
trainErrorRate3 = zeros(1, totIter);



disp 'reading training data...'
[trainY, trainX] = readData('movie/polarity.train');

disp 'getting W for training data...'
[w, b] = getW(trainY, trainX);

disp 'getting Error rates...'
for i = 2:(totIter+1)
    validErrorRate(i-1) = getErrorRate(validY, validX, w(i,:), b(i));
    trainErrorRate(i-1) = getErrorRate(trainY, trainX, w(i,:), b(i));
end

clear w;
clear b;
clear trainx;
clear trainY;



disp 'reading training data1...'
[trainY1, trainX1] = readData('movie/polarity-reorder-1.train');

disp 'getting W for training data1...'
[w1, b1] = getW(trainY1, trainX1);

disp 'getting Error rates1...'
for i = 2:(totIter+1)
    validErrorRate1(i-1) = getErrorRate(validY, validX, w1(i,:), b1(i));
    trainErrorRate1(i-1) = getErrorRate(trainY1, trainX1, w1(i,:), b1(i));
end

clear w1;
clear b1;
clear trainY1;
clear trainX1;



disp 'reading training data2...'
[trainY2, trainX2] = readData('movie/polarity-reorder-2.train');

disp 'getting W for training data2...'
[w2, b2] = getW(trainY2, trainX2);

disp 'getting Error rates2...'
for i = 2:(totIter+1)
    validErrorRate2(i-1) = getErrorRate(validY, validX, w2(i,:), b2(i));
    trainErrorRate2(i-1) = getErrorRate(trainY2, trainX2, w2(i,:), b2(i));
end

clear w2;
clear b2;
clear trainY2;
clear trainX2;



disp 'reading training data3...'
[trainY3, trainX3] = readData('movie/polarity-reorder-3.train');

disp 'getting W for training data3...'
[w3, b3] = getW(trainY3, trainX3);

disp 'getting Error rates3...'
for i = 2:(totIter+1)
    validErrorRate3(i-1) = getErrorRate(validY, validX, w3(i,:), b3(i));
    trainErrorRate3(i-1) = getErrorRate(trainY3, trainX3, w3(i,:), b3(i));
end

clear w3;
clear b3;
clear trainY3;
clear trainX3;


figure 
hold on
plot (1:totIter, validErrorRate, 'g')
plot (1:totIter, trainErrorRate, 'r')
xlabel ('Iteration Number')
ylabel ('Error Rate')
legend ('validation error rate', 'training error rate')
title ('1a')

figure 
hold on
plot (1:totIter, validErrorRate, 'g')
plot (1:totIter, validErrorRate1, 'r')
plot (1:totIter, validErrorRate2, 'k')
plot (1:totIter, validErrorRate3, 'm')
xlabel ('Iteration Number')
ylabel ('Error Rate')
legend ('validErrorRate on train', 'validErrorRate on train1', 'validErrorRate on train2', 'validErrorRate on train3')
title ('1c part1')

figure 
hold on
plot (1:totIter, trainErrorRate, 'g')
plot (1:totIter, trainErrorRate1, 'r')
plot (1:totIter, trainErrorRate2, 'k')
plot (1:totIter, trainErrorRate3, 'm')
xlabel ('Iteration Number')
ylabel ('Error Rate')
legend ('trainErrorRate on train', 'trainErrorRate on train1', 'trainErrorRate on train2', 'trainErrorRate on train3')
title ('1c part2')