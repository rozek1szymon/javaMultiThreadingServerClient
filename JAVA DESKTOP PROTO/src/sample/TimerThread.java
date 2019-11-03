package sample;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimerThread extends Task<String>
{
    @Override
    protected String call() throws Exception
    {
        int seconds = 600;
        int sec, min;
        while (seconds >= 0) {
            min = seconds / 60;
            sec = seconds - min * 60;
            String secs = Integer.toString(sec);
            if (sec / 10 == 0)
                secs = "0" + secs;
            if (seconds > 0)
            {
                updateProgress(min,sec);
                String info = "You have : " + min + ":" + secs + "\n to choose a hotel" + "\r";
                updateMessage(info);
            }
            else
                System.out.print("End of time");
            try {
                ///User made reservation
                Thread.sleep(1000);
            } catch (Exception ex) {

            }
            seconds--;
        }
        return null;
    }
    @Override
    protected void updateProgress(double min,double sec)
    {

        super.updateProgress(min,sec);
    }


}
