clc
x = [4 1 5 3];

transition = [0.05 0.1 0.25 0.6; 0.35 0.05 0.5 0.1; 0.1 0.5 0.1 0.3; 0.4 0.1 0.4 0.1];
emission = [0.4 0.2 0.1 0.2 0.1; 0.3 0.1 0.4 0.1 0.1; 0.1 0.1 0.1 0.2 0.5; 0.1 0.4 0.1 0.3 0.1];
start = [0.1 0.4 0.2 0.3];

leng_x = length(x); 
solution = zeros(4, leng_x); 

for i = 1:4
    solution(i,1) = start(i)*emission(i,x(1));
    fprintf('(%0.3f)(%0.3f)=%0.3f\n', start(i), emission(i,x(1)), solution(i,1));
end

fprintf('----------------------------------------\n');

temp = zeros(1,4);
for j = 2:leng_x
    for i2 = 1:4
        for i1 = 1:4
            temp(i1) = solution(i1,j-1)*emission(i2,x(j))*transition(i1,i2);
        end
        [solution(i2,j), index]=max(temp);
        for i1 = 1:4
            if index == i1
                fprintf('(%f)*(%f)(%f)=%f\t*\n',solution(i1,j-1), emission(i2,x(j)), transition(i1,i2), temp(i1));
            else
                fprintf('(%f)*(%f)(%f)=%f\n',solution(i1,j-1), emission(i2,x(j)), transition(i1,i2), temp(i1));
            end
        end
        fprintf('\n');
    end
    fprintf('----------------------------------------\n');
end

fprintf('Highest prob = %f\n', max(solution(:,leng_x)));