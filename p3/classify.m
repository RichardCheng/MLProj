function classifiedLabels = classify(feature, w, b)

sampleSize = size(feature,1);
classifiedLabels = zeros(sampleSize,1);

for i = 1:sampleSize
    classifiedLabels(i) = sum(w .* feature(i, :)) + b;
end

