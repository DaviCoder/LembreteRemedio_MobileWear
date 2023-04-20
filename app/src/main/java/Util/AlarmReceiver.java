package Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        // Aqui você pode definir a ação que será executada quando o alarme for disparado
        Toast.makeText(context, "Alarme disparado", Toast.LENGTH_LONG).show();
    }
}
