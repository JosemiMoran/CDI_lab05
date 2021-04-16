import java.util.concurrent.TimeUnit;

/**
 * @author JosemiMoran
 * @version 1.0, 15/04/2021
 */
public class ClassA {

    protected int counter;
    private String canPlay;

    /**
     * ClassA Constructor
     */
    public ClassA() {
        counter = 10;
        canPlay = "red";
    }

    /**
     * Print the thread id and make it wait n seconds.
     *
     * @param id Thread's id
     */
    public synchronized void EnterAndWait(int id) throws InterruptedException {
        System.out.println("Starting EnterAndWait in thread " + id);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Ending EnterAndWait in thread " + id);
    }

    /**
     * Decrements counter.
     *
     * @return false if counter is 0 else true
     */
    public synchronized boolean EnterAndDecrement() throws InterruptedException {
        if (counter == 0) return false;
        counter--;
        //notify();
        notifyAll();
        if (counter != 0) wait();
        System.out.println("Ending EnterAndDecrement on thread" + Thread.currentThread().getName());
        return true;
    }

    /**
     *
     *
     * @param team: The team that is actually playing
     * @return false if counter is 0 the team can't play this turn
     *          else return true
     */
    public synchronized boolean EnterAndPlay(final String team) throws InterruptedException {
        //System.out.println("EnterAndPlay entered for " + Thread.currentThread().getName());
        boolean toRet = false;
        if (HaveFinished()) return false;
        if (team.equals(canPlay)) {
            SwitchTeam();
            counter--;
            toRet = true;
        }
        notifyAll();
        if (!HaveFinished()) wait();
        //System.out.println("Ending EnterAndDecrement on thread" + Thread.currentThread().getName());
        return toRet;
    }

    /**
     * Switches the color of the team that can actually play
     */
    private void SwitchTeam() {
        System.out.println("Team " + canPlay + " is playing");
        if (canPlay.equals("red")) canPlay = "black";
        else canPlay = "red";
    }

    public synchronized boolean HaveFinished() {
        return counter == 0;
    }
}
