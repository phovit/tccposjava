package tcc.unitri.edu.br.testalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Jefferson Lanzieri
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = "br.rj.lanzieri.action.ACTION_ALARM_RECEIVER";

        if (intent.getAction().equals(action)) {
            Toast.makeText(context, "Alarme disparado, mostre uma notificação ou faça uma consulta num servidor.", Toast.LENGTH_SHORT).show();
        }
    }

}