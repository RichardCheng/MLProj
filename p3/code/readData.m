function [labelArr, featureM] = readData(filename)

global datanum;
global featurenum;

labelArr = zeros(datanum, 1);
featureM = zeros(datanum, featurenum);

fid = fopen(filename);
tline = fgetl(fid);
i = 1;
while ischar(tline)
    [l, f] = readLine(tline, featurenum);
    labelArr(i) = l;
    featureM(i, :) = f;
    
    tline = fgetl(fid);
    i = i + 1;
    if i ~= 1001 && mod(i, 50) == 0
        fprintf('%3.0f%% complete\n', i/10);
    end
end

fclose(fid);


function [label, features] = readLine(line, featurenum)
[label, line] = strtok(line);
label = str2double(label);
features = zeros(1, featurenum);

while ~isempty(line)
    [l, line] = strtok(line);
    [k, v] = strtok(l, ':');
    [v, tt] = strtok(v, ':');
    features(str2double(k)) = str2double(v);
end