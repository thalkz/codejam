import math

def solve(plates):
    result = []
    for x in range(1, max(plates) + 1):
        sum = 0
        for plate in plates:
            plate = float(plate)
            sum += (int) (math.ceil(plate / x) - 1)
        #print (str(x) + " + " + str(sum) + " = " + str(x + sum))
        result.append(x + sum)
    return min(result)


def main():
    t = int(raw_input())
    for i in xrange(1, t + 1):
        n = int(raw_input())
        plates = [int(s) for s in raw_input().split(" ")]
        #print (str(plates))
        time = solve(plates)
        print "Case #{}: {}".format(i, time)

main()