load arxiv/temp/norm_train_4fold.result
load arxiv/temp/arxiv.4fold.stripped.valid

false_positive = 0;
false_negative = 0;
leng = length(arxiv_4fold_stripped);

for i = 1 : leng
    if arxiv_4fold_stripped(i) > 0 
        arxiv_4fold_stripped(i) = 1;
    else
        arxiv_4fold_stripped(i) = -1;
    end
    
    if norm_train_4fold(i) > 0 
        norm_train_4fold(i) = 1;
    else
        norm_train_4fold(i) = -1;
    end   
end
    
    
for i = 1 : leng
    if norm_train_4fold(i) == 1 && arxiv_4fold_stripped(i) == -1
        false_positive = false_positive + 1;
    elseif norm_train_4fold(i) == -1 && arxiv_4fold_stripped(i) == 1
        false_negative = false_negative + 1;
    end

end
 
 
fprintf('false positive rate is %d/%d=%f\n', false_positive, leng, false_positive/leng);
fprintf('false_negative rate is %d/%d=%f\n', false_negative, leng, false_negative/leng);