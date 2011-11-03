load arxiv/temp/nonnorm_train.result
load arxiv/arxiv.unnorm.stripped.test

false_positive = 0;
false_negative = 0;
leng = length(arxiv_unnorm_stripped);

for i = 1 : leng
    if arxiv_unnorm_stripped(i) > 0 
        arxiv_unnorm_stripped(i) = 1;
    else
        arxiv_unnorm_stripped(i) = -1;
    end
    
    if nonnorm_train(i) > 0 
        nonnorm_train(i) = 1;
    else
        nonnorm_train(i) = -1;
    end   
end
    
    
for i = 1 : leng
    if nonnorm_train(i) == 1 && arxiv_unnorm_stripped(i) == -1
        false_positive = false_positive + 1;
    elseif nonnorm_train(i) == -1 && arxiv_unnorm_stripped(i) == 1
        false_negative = false_negative + 1;
    end

end
 
 
fprintf('false positive rate is %d/%d=%f\n', false_positive, leng, false_positive/leng);
fprintf('false_negative rate is %d/%d=%f\n', false_negative, leng, false_negative/leng);