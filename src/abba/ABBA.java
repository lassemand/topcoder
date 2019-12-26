package abba;

import java.math.BigInteger;
import java.util.*;

public class ABBA {

    String canObtain(String initial, String target) {
        Model filterModel = abStringToBigInteger(initial);
        Model targetModel = abStringToBigInteger(target);
        filterModel = targetModel.getbCount() - filterModel.getbCount() % 2 == 0 ? filterModel : abStringToBigInteger(new StringBuilder(initial).reverse().toString());
        return search(initial.length(), target.length(), filterModel, targetModel, targetModel.getbCount() - filterModel.getbCount() % 2 == 0) ? "Possible" : "Impossible";
    }

    private boolean search(int filterLength, int targetLength, Model initial, Model target, boolean forward) {
        BigInteger targetCopy = target.getValue();
        int cursor = 0;

        while (cursor + filterLength <= targetLength) {
            BigInteger result = initial.getValue().xor(targetCopy);
            //TODO
            int lowestBit = getLowestPositiveSetBit(filterLength, result);
            if (lowestBit < filterLength) {
                cursor += lowestBit + 1;
                targetCopy = targetCopy.shiftRight(lowestBit + 1);
            } else {
                //TODO this may actually be false positive as going reverse may change the result
                if (searchCandidate(cursor++, filterLength, targetLength, target, forward)) return true;
                targetCopy = targetCopy.shiftRight(1);
            }
        }
        return false;
    }

    private int getLowestPositiveSetBit(int filterLength, BigInteger result) {
        int lowestBitSet = filterLength;
        for (int i = 0; i<filterLength; i++) {
            if (result.testBit(i)) {
                lowestBitSet = i;
                break;
            }
        }
        return lowestBitSet;
    }

    private boolean searchCandidate(int position, int filterLength, int targetLength, Model target, boolean forward) {
        Deque<Candidate> candidates = new ArrayDeque<>();
        candidates.push(new Candidate(position, position + filterLength - 1, forward));
        while(!candidates.isEmpty()) {
            Candidate candidate = candidates.pop();
            if (candidate.getStart() == 0 && candidate.getEnd() == targetLength - 1)
                return true;

            if (candidate.isForward()) {
                if (targetLength > candidate.getEnd() + 1 && !target.getValue().testBit(candidate.getEnd() + 1)) {
                    candidates.push(new Candidate(candidate.getStart(), candidate.getEnd() + 1, candidate.isForward()));
                }
                if (0 <= candidate.getStart() - 1 && target.getValue().testBit(candidate.getStart() - 1)) {
                    candidates.push(new Candidate(candidate.getStart() - 1, candidate.getEnd(), !candidate.isForward()));
                }
            }
            else {
                if (0 <= candidate.getStart() - 1 && !target.getValue().testBit(candidate.getStart() - 1)) {
                    candidates.push(new Candidate(candidate.getStart() - 1, candidate.getEnd(), candidate.isForward()));
                }
                if (targetLength > candidate.getEnd() + 1 && target.getValue().testBit(candidate.getEnd() + 1)) {
                    candidates.push(new Candidate(candidate.getStart(), candidate.getEnd() + 1, !candidate.isForward()));
                }
            }
        }
        return false;
    }

    private Model abStringToBigInteger(String s) {
        BigInteger value = BigInteger.ZERO;
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < s.length(); i++){
            boolean isB = s.charAt(i) == 'B';
            if (isB) {
                value = value.setBit(i);
                bCount++;
            } else {
                aCount++;
            }
        }
        return new Model(aCount, bCount, value);
    }

}

class Candidate {
    private int start;
    private int end;
    private boolean forward;

    public Candidate(int start, int end, boolean forward) {
        this.start = start;
        this.end = end;
        this.forward = forward;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isForward() {
        return forward;
    }

}

class Model {
    private int aCount;
    private int bCount;
    private BigInteger value;


    Model(int aCount, int bCount, BigInteger value) {
        this.aCount = aCount;
        this.bCount = bCount;
        this.value = value;
    }

    public int getaCount() {
        return aCount;
    }

    public int getbCount() {
        return bCount;
    }

    public BigInteger getValue() {
        return value;
    }
}
