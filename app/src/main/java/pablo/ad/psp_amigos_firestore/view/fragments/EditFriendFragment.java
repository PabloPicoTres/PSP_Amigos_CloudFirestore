package pablo.ad.psp_amigos_firestore.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import pablo.ad.psp_amigos_firestore.R;
import pablo.ad.psp_amigos_firestore.model.pojo.Friend;
import pablo.ad.psp_amigos_firestore.viewmodel.ViewModelActivity;

public class EditFriendFragment extends Fragment {

    private ViewModelActivity viewModel;
    private EditText nombreD,fechaD;
    private TextView telefonoD;
    private TextView cuentaLlamadasD;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.edit_friend_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombreD = view.findViewById(R.id.etNombreD);
        telefonoD = view.findViewById(R.id.tvTelefonoD);
        fechaD = view.findViewById(R.id.etFechaD);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        Friend friend = viewModel.getCurrentFriend();

        bind(   friend.getName(),
                String.valueOf(friend.getNumber()),
                friend.getBirthdate()
        );

        view.findViewById(R.id.btAtras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditFriendFragment.this).navigate(R.id.action_edit_friend_to_lista_amigos);
            }
        });

        view.findViewById(R.id.btConfirmar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friend friendAux = friend;
                friendAux.setName(nombreD.getText().toString());
                friendAux.setBirthdate(fechaD.getText().toString());

                viewModel.updateFriend(friendAux);
                NavHostFragment.findNavController(EditFriendFragment.this).navigate(R.id.action_edit_friend_to_lista_amigos);
            }
        });

        view.findViewById(R.id.btDeleteFriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteFriend(viewModel.getCurrentFriend());
                Log.v("xyzyx", "Borrando id: ?");
                NavHostFragment.findNavController(EditFriendFragment.this).navigate(R.id.action_edit_friend_to_lista_amigos);
            }
        });
    }

    public void bind(String nombre, String telefono, String fecha) {
        nombreD.setText(nombre);
        telefonoD.setText("Telefono: " + telefono);
        fechaD.setText(fecha);
    }
}