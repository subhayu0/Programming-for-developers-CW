package WeatherAnamoly;

public class WeatherAnomalyDetector {

    public static int countValidAnomalies(int[] temperatureChanges, int low, int high) {
        int count = 0;

        for (int start = 0; start < temperatureChanges.length; start++) {
            int sum = 0;

            for (int end = start; end < temperatureChanges.length; end++) {
                sum += temperatureChanges[end];

                if (sum >= low && sum <= high) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1
        int[] changes1 = {3, -1, -4, 6, 2};
        int low1 = 2, high1 = 5;
        System.out.println("Output 1: " + countValidAnomalies(changes1, low1, high1));  // Output: 3

        // Example 2
        int[] changes2 = {-2, 3, 1, -5, 4};
        int low2 = -1, high2 = 2;
        System.out.println("Output 2: " + countValidAnomalies(changes2, low2, high2));  // Output: 5
    }
}


//The Java program goes through your data and checks every possible stretch of days, starting with day one, then days one to two, then one to three, 
//and so on. Then it does the same from day two, day three, and so on until it covers all possible combinations. For each of these periods, it adds 
//up the temperature changes. If the total is within your suspicious range (between your chosen low and high thresholds), it counts that period as 
//"anomalous." It keeps doing this until every possible period has been checked. In the end, the program simply tells you how many such unusual periods 
//it found.