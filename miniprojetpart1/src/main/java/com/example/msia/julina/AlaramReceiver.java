package com.example.msia.julina;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.mac.miniprojetpart1.R;

import Helpers.DataBaseHelper;

/**
 * Created by mac on 25/05/16.
 */
public class AlaramReceiver extends BroadcastReceiver{
    //On doit enregistrer le broadcastReceiverd dans le AndroidManifest
    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification("Le panier a été vidé", context);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Card_line");
        db.execSQL("CREATE TABLE Card_line( id_line_card INTEGER PRIMARY KEY AUTOINCREMENT, id_fullKey  REFERENCES  FullProduct(id_fullKey),"+
                "quantity_line_card INTEGER);");
    }

    private void sendNotification(String message,Context ctx) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }
}
