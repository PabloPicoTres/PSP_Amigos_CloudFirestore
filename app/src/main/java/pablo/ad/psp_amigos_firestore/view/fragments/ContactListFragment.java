package pablo.ad.psp_amigos_firestore.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pablo.ad.psp_amigos_firestore.R;
import pablo.ad.psp_amigos_firestore.view.adapter.ContactsRecyclerAdapter;
import pablo.ad.psp_amigos_firestore.viewmodel.ViewModelActivity;

public class ContactListFragment extends Fragment {

    private ViewModelActivity viewModel;
    private RecyclerView rvContactos;
    private ContactsRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        Log.v("xyzyx", viewModel.getContactList().toString());



        rvContactos = view.findViewById(R.id.RVContactos);
        rvContactos.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactsRecyclerAdapter(viewModel);
        rvContactos.setAdapter(adapter);

        view.findViewById(R.id.btGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.guardaContactos();
                NavHostFragment.findNavController(ContactListFragment.this).navigate(R.id.action_contactListFragment_to_lista_amigos);
            }
        });

        view.findViewById(R.id.btCancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ContactListFragment.this).navigate(R.id.action_contactListFragment_to_lista_amigos);
            }
        });
    }


}