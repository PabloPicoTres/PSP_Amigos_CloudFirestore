package pablo.ad.psp_amigos_firestore.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;

public abstract class Permisos extends Fragment {

    public static final String[] PERMISOS_USADOS = {READ_CONTACTS, READ_PHONE_STATE, READ_CALL_LOG};
    public static ArrayList<String> codigosDenegados = new ArrayList<>();
    public final int REQUEST_CODE_ASK_PERMISSION = 111;


    public boolean tengoPermiso (Context context){

        boolean tengoPermiso = true;

        if( Build.VERSION.SDK_INT>=Build.VERSION_CODES.M ){
            codigosDenegados.clear();
            for(String permiso : PERMISOS_USADOS){
                if (getContext().checkSelfPermission(permiso) == PackageManager.PERMISSION_DENIED) {

                    codigosDenegados.add(permiso);
                    tengoPermiso = false;

                }
            }
            if( codigosDenegados.size() > 0){

                Object[] adaptador= codigosDenegados.toArray();
                String[] pideCodigos = Arrays.copyOf(adaptador, adaptador.length, String[].class);
                explainPermission(context, pideCodigos);

            }
        }

        return tengoPermiso;

    }




    public abstract void pidoPermisos();




    public void explainPermission(Context context, String... permissions) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Se necesitan "+ permissions.length + " permisos");

        builder.setMessage( "Sin los permisos correspondientes, no puede funcionar la aplicación correctamente. " +
                "Si es tan amable, haga click aceptar en las siguientes ventanas.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {



                pidoPermisos();

            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();

    }



}
