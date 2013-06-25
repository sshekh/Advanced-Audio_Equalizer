
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Intro
{
    int returnVal;
    static Intermediate introframe;
    public static void main(String[] args)
    {
        Thread thread = new Thread(){
            public void run(){
                try {
                    introframe = new Intermediate();
                } catch (IOException ex) {
                    Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        };
        thread.start();
        try {
            thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
        }
        introframe.setVisible(false);
        Final frame2 = new Final();
    }
}