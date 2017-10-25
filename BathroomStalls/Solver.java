import java.util.*;
import java.io.*;

public class Solver {

    private long stallsCount;
    private long peopleCount;

    private long max;
    private long min;

    private PriorityQueue<Segment> pq = new PriorityQueue();
    private HashMap<Long, Long> ht = new HashMap();

    public Solver(long stallsCount, long peopleCount) {
        this.stallsCount = stallsCount;
        this.peopleCount = peopleCount;
        solve();
    }

    private void solve() {
        // Add initial segment
        long totalCount = 0;

        Segment initial = new Segment(0, stallsCount - 1);
        pq.add(initial);
        ht.put(initial.getSize(), (long) 1);

        Segment biggest;
        Segment leftSeg;
        Segment rightSeg;

        while (totalCount < peopleCount) {
            biggest = pq.poll();
            long x = ht.get(biggest.getSize());            
            ht.remove(biggest);

            // System.out.println(biggest.toString());
            // System.out.println("x : " + x); 
             
            leftSeg = new Segment(biggest.getStart(), biggest.getMiddle() - 1);
            rightSeg = new Segment(biggest.getMiddle() + 1, biggest.getEnd());

            // System.out.println(leftSeg.getSize());
            // System.out.println(rightSeg.getSize());

            if (!pq.contains(leftSeg) && leftSeg.getSize() > 0) {
                pq.add(leftSeg);
            }

            if (!pq.contains(rightSeg) && rightSeg.getSize() > 0) {
                pq.add(rightSeg);
            }

            if (ht.get(leftSeg.getSize()) != null) {
                long lx = ht.get(leftSeg.getSize());
                ht.put(leftSeg.getSize(), lx + x);
            } else {
                ht.put(leftSeg.getSize(), x);
            }

            if (ht.get(rightSeg.getSize()) != null) {
                long rx = ht.get(rightSeg.getSize());
                ht.put(rightSeg.getSize(), rx + x);
            } else {
                ht.put(rightSeg.getSize(), x);
            }

            totalCount += x;

            // System.out.println("x : " + x);             

            if (totalCount >= peopleCount) {
                max = Math.max(leftSeg.getSize(), rightSeg.getSize());
                min = Math.min(leftSeg.getSize(), rightSeg.getSize());
            }
        }
    }

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    /* SUBCLASS */

    public class Segment implements Comparable {
        long start;
        long end;

        public Segment (long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getMiddle() {
            return (long) ((start + end) / 2);
        }

        public long getSize() {
            return end - start + 1;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }

        @Override
        public int compareTo(Object other) {
            if (other instanceof Segment) {
                Segment s = (Segment) other;
                if (getSize() > s.getSize()) {
                    return -1;
                } else if (getSize() < s.getSize()) {
                    return 1;
                } else {
                    if (getStart() < s.getStart()) {
                        return -1;
                    } else if (getStart() > s.getStart()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
            return 0; 
        }

        @Override
        public String toString() {
            return "Segment : Start=" + getStart() 
                                    + ", End=" + getEnd()
                                    + ", Middle=" + getMiddle()
                                    + ", Size=" + getSize();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Segment) {
                return ((Segment) obj).getSize() == getSize();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (int) getSize();
        }
    }

    /* MAIN */

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            long stallsCount = in.nextLong();
            long peopleCount = in.nextLong();            
            Solver solver = new Solver(stallsCount, peopleCount);

            long max = solver.getMax();
            long min = solver.getMin();            

            System.out.println("Case #" + i + ": " + max + " " + min); 
        }
    }
}