import java.util.Dictionary;

/**
 * @author JosemiMoran
 * @version 1.0, 15/04/21
 */
public class ClassB implements Runnable {

    protected ClassA classA;
    protected int id;
    protected int counter;
    protected String team;
    protected int success;

    /**
     * ClassB constructor
     *
     * @param id     Thread's id
     * @param classA ClassA Object
     */
    public ClassB(ClassA classA, int id) {
        this.classA = classA;
        this.id = id;
        counter = 0;
        if (id % 2 == 0) team = "red";
        else team = "black";
        success = 0;
    }

    //Default ClassB constructor
    public ClassB() {
    }


    @Override
    public void run() {
        //System.out.println("Starting run " + id);
        try {
            while (!classA.HaveFinished()) {
                if (classA.EnterAndPlay(team)) success++;
                counter++;
            }
        } catch (Exception e) {
            System.out.println("Error on EnterAndDecrement!");
        } finally {
            System.out.println("Thread: " + id + " Counter Value: " + counter);
            MyProblem.statistic.put(id, counter);
            MyProblem.success.put(id, success);
            // System.out.println("Ending run " + id);
        }
    }
}
