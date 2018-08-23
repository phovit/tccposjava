package tcc.unitri.edu.br.testalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * @author Jefferson Lanzieri
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 6); //6 horas
            calendar.set(Calendar.MINUTE, 50); //50 minutos

            long time = calendar.getTimeInMillis();
            long interval = 24 * 60 * 60 * 1000; //1 dia

            Intent i = new Intent("br.rj.lanzieri.action.ACTION_ALARM_RECEIVER");
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, time, interval, pi);
        }

    }
}