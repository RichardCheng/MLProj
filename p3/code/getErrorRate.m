function errorRate = getErrorRate(label, feature, w, b)

errors = 0.;
sampleSize = length(label);

for i = 1:sampleSize
    if label(i) * (sum(w .* feature(i, :)) + b) <= 0
        errors = errors + 1;
    end
end

errorRate = errors / sampleSize;
