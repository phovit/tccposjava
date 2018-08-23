package tcc.unitri.edu.br.testalarm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Jefferson Lanzieri
 */
public class MainActivity extends Activity implements OnClickListener {

    private AlarmManager manager;

    private Intent i;
    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        i = new Intent("br.rj.lanzieri.action.ACTION_ALARM_RECEIVER");
        pi = PendingIntent.getBroadcast(this, 0, i, 0);

        Button start = (Button) findViewById(R.id.start);
        Button cancel = (Button) findViewById(R.id.cancel);

        start.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cancel) {
            manager.cancel(pi); //cancela o alarme
        } else {
            long time = System.currentTimeMillis() + 2000; //começa em 2 segundos
            long interval = 5000; //roda a cada 5 segundos

            manager.setRepeating(AlarmManager.RTC_WAKEUP, time, interval, pi);
        }
    }

}
