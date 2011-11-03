load digits/digitsout.test
load digits/polyout/digitsout0.test
load digits/polyout/digitsout1.test
load digits/polyout/digitsout2.test
load digits/polyout/digitsout3.test
load digits/polyout/digitsout4.test
load digits/polyout/digitsout5.test
load digits/polyout/digitsout6.test
load digits/polyout/digitsout7.test
load digits/polyout/digitsout8.test
load digits/polyout/digitsout9.test

leng = length(digitsout); 
polyout = zeros(leng, 10); 
polyout(:, 1) = digitsout1; 
polyout(:, 2) = digitsout2; 
polyout(:, 3) = digitsout3; 
polyout(:, 4) = digitsout4; 
polyout(:, 5) = digitsout5; 
polyout(:, 6) = digitsout6; 
polyout(:, 7) = digitsout7; 
polyout(:, 8) = digitsout8; 
polyout(:, 9) = digitsout9; 
polyout(:, 10) = digitsout0; 

[valuemax, indexmax] = max(polyout, [], 2); 


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