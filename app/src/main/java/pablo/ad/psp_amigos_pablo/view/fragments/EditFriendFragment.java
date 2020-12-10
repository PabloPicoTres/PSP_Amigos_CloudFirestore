package pablo.ad.psp_amigos_pablo.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import pablo.ad.psp_amigos_pablo.R;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;
import pablo.ad.psp_amigos_pablo.viewmodel.ViewModelActivity;

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
        cuentaLlamadasD = view.findViewById(R.id.tvNumeroLlamadasD);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        FriendCallCount friend = viewModel.getCurrentFriend();

        bind(   friend.getFriend().getName(),
                String.valueOf(friend.getFriend().getNumber()),
                friend.getFriend().getBirthdate(),
                String.valueOf(friend.getCount())
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
                Friend friendAux = friend.getFriend();
                friendAux.setName(nombreD.getText().toString());
                friendAux.setBirthdate(fechaD.getText().toString());
                friendAux.setId(friend.getFriend().getId());

                viewModel.updateFriend(friendAux);
                NavHostFragment.findNavController(EditFriendFragment.this).navigate(R.id.action_edit_friend_to_lista_amigos);
            }
        });

        view.findViewById(R.id.btDeleteFriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteFriend(friend.getFriend().getId());
                Log.v("xyzyx", "Borrando id: " + String.valueOf(friend.getFriend().getId()));
                NavHostFragment.findNavController(EditFriendFragment.this).navigate(R.id.action_edit_friend_to_lista_amigos);
            }
        });
    }

    public void bind(String nombre, String telefono, String fecha, String cuentaLlamadas) {
        nombreD.setText(nombre);
        telefonoD.setText("Telefono: " + telefono);
        cuentaLlamadasD.setText(cuentaLlamadas);
        fechaD.setText(fecha);
    }
}