"D:\Documents\Dropbox\MLProj\p3\svm_learn" -c 64 "D:\Documents\Dropbox\MLProj\p3\movie\polarity.train" "D:\Documents\Dropbox\MLProj\p3\movie\model\64"


"D:\Documents\Dropbox\MLProj\p3\svm_classify" "D:\Documents\Dropbox\MLProj\p3\movie\polarity.train" "D:\Documents\Dropbox\MLProj\p3\movie\model\64" "D:\Documents\Dropbox\MLProj\p3\movie\output\64"


"D:\Documents\Dropbox\MLProj\p3\svm_classify" "D:\Documents\Dropbox\MLProj\p3\movie\polarity.validation" "D:\Documents\Dropbox\MLProj\p3\movie\model\64" "D:\Documents\Dropbox\MLProj\p3\movie\outputvalidation\64"


[0.722 0.780 0.844 0.910 0.963 0.991 1.000 1.000 1.000]
[0.667 0.702 0.736 0.764 0.779 0.795 0.781 0.784 0.784]


"D:\Documents\Dropbox\MLProj\p3\svm_learn" -c 8 "D:\Documents\Dropbox\MLProj\p3\movie\polarity-reorder-3.train" "D:\Documents\Dropbox\MLProj\p3\movie\output1c\8m3"

"D:\Documents\Dropbox\MLProj\p3\svm_classify" "D:\Documents\Dropbox\MLProj\p3\movie\polarity.train" "D:\Documents\Dropbox\MLProj\p3\movie\output1c\8m3" "D:\Documents\Dropbox\MLProj\p3\movie\output1c\8c3t"

"D:\Documents\Dropbox\MLProj\p3\svm_classify" "D:\Documents\Dropbox\MLProj\p3\movie\polarity.validation" "D:\Documents\Dropbox\MLProj\p3\movie\output1c\8m3" "D:\Documents\Dropbox\MLProj\p3\movie\output1c\8c3"

[0.991 0.991 0.991 0.991]
[0.795 0.795 0.795 0.795]