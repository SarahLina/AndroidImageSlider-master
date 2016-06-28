package com.example.msia.julina;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;

import java.util.Calendar;

/**
 * Created by mac on 25/05/16.
 */
public class AlarmService {


        public void launcherAlarmOnce(Context context)
        {

            AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent= new Intent(context,AlaramReceiver.class);

            //on veut que l'alarme lance l'intent et non pas l 'application
            //2eme param: le code de l intent (entier)
            //4eme para: le flag ex AlaramReceiver (entier)
            PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);
           // alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+1000*30,1000*60*2 ,pendingIntent);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+1000*30,pendingIntent);

        }

        public void launcherAlarmRepeat(Context context)
        {

            AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent= new Intent(context,AlaramReceiver.class);

            //on veut que l'alarme lance l'intent et non pas l 'application
            //2eme param: le code de l intent (entier)
            //4eme para: le flag ex AlaramReceiver (entier)
            PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);
             alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+1000*30,1000*30 ,pendingIntent);

        }

        public void launcherAlarmHour(Context context, int hour, int min)
        {

            AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent= new Intent(context,AlaramReceiver.class);

            //on veut que l'alarme lance l'intent et non pas l 'application
            //2eme param: le code de l intent (entier)
            //4eme para: le flag ex AlaramReceiver (entier)
            PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);

            //on crée un calendrier
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

        }

            public void launcherAlarmHourRepeat(Context context, int hour, int min)
            {

                AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent= new Intent(context,AlaramReceiver.class);

                //on veut que l'alarme lance l'intent et non pas l 'application
                //2eme param: le code de l intent (entier)
                //4eme para: le flag ex AlaramReceiver (entier)
                PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);

                //on crée un calendrier
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60,pendingIntent);

            }

            public void desactiveAlarm(Context context)
            {
                AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent= new Intent(context,AlaramReceiver.class);
                PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);
                alarmManager.cancel(pendingIntent);


            }

            public void activateBroadcast(Context context)
            {
                ComponentName receiver = new ComponentName(context, RestartReceiver.class);
                PackageManager pm = context.getPackageManager();

                pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
            }

            public void desactivateBroadcast(Context context)
            {
                ComponentName receiver = new ComponentName(context, RestartReceiver.class);
                PackageManager pm = context.getPackageManager();

                pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
            }
}
