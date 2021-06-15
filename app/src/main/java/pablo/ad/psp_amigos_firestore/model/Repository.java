package pablo.ad.psp_amigos_firestore.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pablo.ad.psp_amigos_firestore.model.pojo.Contact;
import pablo.ad.psp_amigos_firestore.model.pojo.Friend;
import pablo.ad.psp_amigos_firestore.util.UtilThread;

public class Repository {


    private FirebaseFirestore db;
    private Context context;

    public Repository(Context context) {
        this.context = context;

        db = FirebaseFirestore.getInstance();
    }


    public MutableLiveData<List<Friend>> getLiveFriends() {

        MutableLiveData<List<Friend>> liveFriends = new MutableLiveData<>();
        db.collection("friends").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    liveFriends.setValue(task.getResult().toObjects(Friend.class));
                }
            }
        });
        return liveFriends;
    }

    public void saveFriend(Friend friend) {
        db.collection("friends").document(String.valueOf(friend.getNumber())).set(friend).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "amigo añadido", Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Amigo se ha muerto por el camino (ha fallado)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void update(Friend friend){
        db.collection("friends").document(String.valueOf(friend.getNumber()))
                .update("birthdate", friend.getBirthdate(), "name",friend.getName())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Tu amigo... ahora es tu amigA", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Amigo no se ha cambiado", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void delete(Friend friend){
        db.collection("friends").document(String.valueOf(friend.getNumber())).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "HAS ASESINADO A TU AMIGO", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Es probable que tu amigo sea inmortal, amigo sigue vivo", Toast.LENGTH_LONG).show();
                    }
                });
    }



    /* OTHER METHODS */

    public List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();

        String[] data = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String order = ContactsContract.Data.DISPLAY_NAME + " ASC";
        String selectionNumber = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor cursor =  context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                data,
                selectionNumber,
                null,
                order);
        while(cursor.moveToNext()){

            Contact contactoAux = new Contact(cursor.getString(0), cursor.getString(1), false);
            contactList.add(contactoAux);

        }
        cursor.close();

        return contactList;
    }


}