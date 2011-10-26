a = 1 - [0.722 0.780 0.844 0.910 0.963 0.991 1.000 1.000 1.000];
b = 1 - [0.667 0.702 0.736 0.764 0.779 0.795 0.781 0.784 0.784];
c = [0.25 0.5 1 2 4 8 16 32 64];
c = log(c)/log(2);
figure
hold on
plot(c, a, 'r')
plot(c, b, 'g')
legend('training set', 'validation set')
xlabel('log_2(C)')
ylabel('error rate')