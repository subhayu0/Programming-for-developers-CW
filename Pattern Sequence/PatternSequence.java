public class PatternSequence {

    public static int getMaxP2FromP1(String p1, int t1, String p2, int t2) {
        StringBuilder seqA = new StringBuilder();
        for (int i = 0; i < t1; i++) {
            seqA.append(p1);
        }

        String fullSeqA = seqA.toString();
        int indexInA = 0; 
        int countP2 = 0;

        while (indexInA < fullSeqA.length()) {
            int indexInP2 = 0;
            for (int i = indexInA; i < fullSeqA.length(); i++) {
                if (fullSeqA.charAt(i) == p2.charAt(indexInP2)) {
                    indexInP2++;
                    if (indexInP2 == p2.length()) {

                        countP2++;
                        indexInA = i + 1;
                        break;
                    }
                }
            }
            if (indexInP2 != p2.length()) {

                break;
            }
        }


        return countP2 / t2;
    }

    public static void main(String[] args) {
        String p1 = "bca";
        int t1 = 6;
        String p2 = "ba";
        int t2 = 1; 
        int result = getMaxP2FromP1(p1, t1, p2, t2);
        System.out.println("Output: " + result);
    }
}

//The core goal of the program is to determine how many full repetitions of a target pattern p2 (repeated t2 times) can be derived from a longer source
//sequence, which is formed by repeating pattern p1 for t1 times. The condition is that we are allowed to remove characters from the source sequence (seqA),
//but we must preserve their original order — that is, characters cannot be rearranged.
//To solve this problem, we need to determine the maximum number of times a given pattern p2 can be repeated such that the resulting sequence is a subsequence 
//of another sequence formed by repeating pattern p1 a specified number of times t1. The solution involves efficiently matching the patterns without explicitly 
//constructing the potentially enormous sequences, leveraging the periodicity of the sequences to optimize the matching process.