import math

def split_top(plates):
    result = []
    max_pile = max(plates)
    for i in range(0, len(plates)):
        if (plates[i] != max_pile):
            result.append(plates[i])
        else:
            if (plates[i] % 2 == 0):
                result.append(plates[i] / 2)
                result.append(plates[i] / 2)
            else:
                result.append(plates[i] / 2)
                result.append((plates[i] / 2) + 1)
    return result

def get_time(plates):
    top = max(plates)
    if (top / 2 > plates.count(top)):
        splitted = split_top(plates)
        #print (str(splitted))
        time = get_time(splitted)
        if (min((top - time), top/2) > plates.count(top)):
            #print (str(plates) + " Split (time : " + str(plates.count(top)) +")")
            return time + plates.count(top)
        else:
            #print (str(plates) + " Canceled")
            return top
    else:
        #print (str(plates) + " Bottom")
        return top

def main():
    t = int(raw_input())
    for i in xrange(1, t + 1):
        n = int(raw_input())
        plates = [int(s) for s in raw_input().split(" ")]
        #print (str(plates))
        time = get_time(plates)
        print "Case #{}: {}".format(i, time)

main()

    
'''
def calculate_minimum(plates):
    top = max(plates)
    while (top / 2 > plates.count(top)):
        plates = split_top(plates)
        top = max(plates)
    return top

def calculate_time(plates, minimum):
    time = 0
    top = max(plates)
    while (min((top - minimum), top/2) > plates.count(top)):
        time += plates.count(top)
        plates = split_top(plates)
        top = max(plates)
    time += top
    print (plates)
    return time
'''