t = int(raw_input())
for i in xrange(1, t + 1):
    Smax, audience = [s for s in raw_input().split(" ")]
    Smax = int(Smax)
    count = 0
    friends = 0

    print ""
    print "------"
    print "{}, {}".format(Smax, friends)

    for j in range(0, len(audience)):
        
        num = int(audience[j])
        count += num

        if (count < (j + 1)):
            friends += (j + 1) - count
            print "Added to friends : " + str((j + 1) - count)
    
    print "Case #{}: {}".format(i, friends)
    