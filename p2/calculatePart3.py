import math

def main():
    data = []

    f = open('hyp_test_pred.txt', 'r')
    for line in f:
        s = eval('('+line+')')
        data.append(s)

    knnError = 0
    dtError = 0
    bothError = 0
    totalSample = len(data)

    for entry in data:
        if entry[0] != entry[1]:
            knnError += 1

            if entry[0] != entry[2]:
                bothError += 1

        if entry[0] != entry[2]:
            dtError += 1

    meanKnn = float(knnError)/totalSample
    meanDt = float(dtError)/totalSample

    print "Average error for knn = %f" % meanKnn
    print "Average error for dt = %f" % meanDt

    print "knn exclusive number of errors = %d" % (knnError - bothError)
    print "dt exclusive number of errors = %d" % (dtError - bothError)
    print "Both algorithm number of errors = %d" % (bothError)


if __name__ == "__main__":
    main()
