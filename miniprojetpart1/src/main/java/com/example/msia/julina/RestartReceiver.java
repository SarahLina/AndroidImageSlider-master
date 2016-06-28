package com.example.msia.julina;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mac on 25/05/16.
 */
public class RestartReceiver extends BroadcastReceiver {
    //On enregistre le receiver dans le fichier manifest
    //On rajoute une permission
    @Override
    public void onReceive(Context context, Intent intent) {
        new AlarmService().launcherAlarmRepeat(context);
    }
}
