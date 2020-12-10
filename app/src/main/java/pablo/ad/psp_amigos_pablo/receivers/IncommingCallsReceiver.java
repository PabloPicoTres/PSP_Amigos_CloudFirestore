package pablo.ad.psp_amigos_pablo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import pablo.ad.psp_amigos_pablo.room.Repository;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;

public class IncommingCallsReceiver extends BroadcastReceiver {

    private Repository repository;

    @Override
    public void onReceive(Context context, Intent intent) {
        repository = new Repository(context);

        Log.v("xyzyx", "Receiver de IncommingCallsReceiver");

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            Log.v("xyzyx", "LLAMADA ESTABLECIDA");
        }else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
            Log.v("xyzyx", "LLAMADA TERMINADA");

        }else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Log.v("xyzyx", "TE ESTAN LLAMANDO");
            repository = new Repository(context);
            Calendar c = Calendar.getInstance();
            String tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String date =  c.get(Calendar.DAY_OF_MONTH) + "/" +
                            c.get(Calendar.MONTH) + "/" +
                            c.get(Calendar.YEAR);

            repository.checkNewCall(tlf, date);

        }


    }

}
