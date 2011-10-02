function [wM, bA] = getW(labelArr, featureM)

global featurenum;
global datanum; 

n = 1;
wM = zeros(datanum, featurenum);
bA = zeros(datanum, 1);
k = 1;
totalk = 0;

normv = zeros(datanum, 1);
for i = 1:datanum
    normv(i) = norm(featureM(i, :));
end
R = max(normv);

cont = 1;
j = 1;

while (cont && j < 50)
    cont = 0;

    wM(j, :) = wM(k, :);
    bA(j) = bA(k);
    totalk = totalk + k;
    k = j;
    
    
    for i = 1:datanum

        if labelArr(i) * (sum(wM(k, :) .* featureM(i, :)) + bA(k)) <= 0
            wM(k+1, :) = wM(k, :) + n * labelArr(i) * featureM(i, :);
            bA(k+1) = bA(k) + n * labelArr(i) * R ^ 2;
            k = k + 1;
            cont = 1;
        end
    end
    
    j = j+1;
end

for p = j : datanum
    wM(p, :) = wM(j-1, :);
    bA(p) = bA(j-1);
end
