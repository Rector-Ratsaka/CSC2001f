import java.io.*;
import java.util.Scanner;

/**this class concerns computing weights
 * that optimise insertion of a specific list of names in anLPHashTable
 */
public class Optimize {
    //initial minProbeCount(must be less than max int value) and combinationCount(combinations that achieve less probes)
    private static int minProbeCount = Integer.MAX_VALUE;
    private static int combinationCount = 0;

    /**print minProbeCount and combinationCount(that achieved minProbeCount)
     * write output into results.txt
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String textFile = input.next();
        int size = input.nextInt();
        int[] arr = new int[9];
        generateCombinations(arr, 0, textFile, size);

        System.out.println(minProbeCount+" "+combinationCount);
        try {
            FileWriter writer = new FileWriter("results.txt");
            writer.write(minProbeCount + " " + combinationCount);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**for a given array, index, textFile name and size of the table
     * this method reads the file and
     * insert contents from the file(usernames) into a hash Table
     * using different weights(used for calculating hash values)
     * There are 5^9 combinations of weights to be computed
     * check which combination(s) gives less probes(Linear Probing)
    */
        public static void generateCombinations(int[] arr, int index, String textFile, int size) {
        // read the list of usernames from mydata.txt
        try {
            final Scanner fileIn = new Scanner(new File(textFile));
            LPHashTable hashTable = new LPHashTable(size);
                if (index == arr.length) {
                    // Base case: get minProbeCount and number of weight combinations that achieved it
                    hashTable.setWeights(arr);
                    int usernamesNum = 36;
                    while (usernamesNum>0) {
                        hashTable.insert(fileIn.nextLine());
                        usernamesNum--;
                    }

                    int probeCount = hashTable.getProbeCount();
                    if (probeCount < minProbeCount) {
                        minProbeCount = probeCount;
                        combinationCount = 1;
                    } else if (probeCount == minProbeCount) {
                        combinationCount++;
                    }
                } else {
                    // Recursive case: try all possible values for the current position
                    for (int i = 0; i <= 4; i++) {
                        arr[index] = i;
                        generateCombinations(arr, index + 1, textFile, size);
                    }
                }
                fileIn.close();
        } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        }
    }
}
