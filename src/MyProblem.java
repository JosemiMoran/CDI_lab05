import java.util.*;

/**
 * @author JosemiMoran
 * @version 1.0, 15/04/21
 */
public class MyProblem {
    public static Map<Integer, Integer> statistic;
    public static Map<Integer, Integer> success;
    public static Integer redCounter;
    public static Integer blackCounter;
    private static int successCounter;

    public static void main(String[] args) {
        System.out.println("Starting main...");
        redCounter = 0;
        blackCounter = 0;
        successCounter = 0;
        int numThreads = Integer.parseInt(args[0]); //Number of threads of the program
        ArrayList<Thread> threadArrayList = new ArrayList<>(numThreads);
        ClassA classA = new ClassA(); //ClassA object
        statistic = new HashMap<>();
        success = new HashMap<>();
        //Creating threads
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new ClassB(classA, i), "Thread " + i);
            System.out.println("Creating: " + thread.getName());
            threadArrayList.add(thread); // Adding the thread into the arraylist
        }

        //Starting threads run routine
        for (Thread thread : threadArrayList) {
            thread.start();
        }

        for (Thread thread : threadArrayList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Join error in: " + thread.getName());
            }
        }
        for (Integer key : statistic.keySet()
        ) {
            System.out.println("Thread " + key + " ,value -> " + statistic.get(key));
            if (key % 2 == 0) redCounter += statistic.get(key);
            else blackCounter += statistic.get(key);
        }
        for (Integer key : success.keySet()
        ) {
            System.out.println("Thread " + key + " ,success -> " + success.get(key));
            successCounter += success.get(key);
        }
        System.out.println("Red counter value: " + redCounter);
        System.out.println("Black counter value: " + blackCounter);
        System.out.println("Total counter: " + (redCounter + blackCounter));
        System.out.println("Total success: " + successCounter);
        //System.out.println("Max Value: " + Collections.max(statistic.values()));
        //System.out.println("Min Value: " + Collections.min(statistic.values()));
        //System.out.println("Avg Value: " + statistic.values().stream().mapToDouble(Integer::doubleValue).average());
        System.out.println("Ending main...");
    }
}
