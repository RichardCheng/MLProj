load digits/digitsout.test
load digits/out/digitsout0.test
load digits/out/digitsout1.test
load digits/out/digitsout2.test
load digits/out/digitsout3.test
load digits/out/digitsout4.test
load digits/out/digitsout5.test
load digits/out/digitsout6.test
load digits/out/digitsout7.test
load digits/out/digitsout8.test
load digits/out/digitsout9.test

leng = length(digitsout); 
out = zeros(leng, 10); 
out(:, 1) = digitsout1; 
out(:, 2) = digitsout2; 
out(:, 3) = digitsout3; 
out(:, 4) = digitsout4; 
out(:, 5) = digitsout5; 
out(:, 6) = digitsout6; 
out(:, 7) = digitsout7; 
out(:, 8) = digitsout8; 
out(:, 9) = digitsout9; 
out(:, 10) = digitsout0; 

[valuemax, indexmax] = max(out, [], 2); 


error_a = 0;
error_b = 0; 
for i = 1 : leng
    if valuemax(i) < 0
        error_a = error_a + 1; 
    elseif indexmax(i) ~= digitsout(i)
        error_b = error_b + 1; 
    end
end

error = error_a + error_b; 

fprintf('accuracy rate is %d/%d=%f\n', leng-error, leng, 1-error/leng); 
fprintf('type A error rate is %d/%d=%f\n', error_a, leng, error_a/leng);
fprintf('type B error rate is %d/%d=%f\n', error_b, leng, error_b/leng);