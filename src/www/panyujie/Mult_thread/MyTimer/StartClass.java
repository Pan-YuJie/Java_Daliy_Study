package www.panyujie.Mult_thread.MyTimer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-08
 * Time: 17:54
 */
public class StartClass {

    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello timer");
            }
        },3000);

        System.out.println("main");
    }

}
