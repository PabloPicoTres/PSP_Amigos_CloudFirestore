package pablo.ad.psp_amigos_firestore.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pablo.ad.psp_amigos_firestore.R;
import pablo.ad.psp_amigos_firestore.model.pojo.Friend;
import pablo.ad.psp_amigos_firestore.util.Permisos;
import pablo.ad.psp_amigos_firestore.view.adapter.FriendRecyclerAdapter;
import pablo.ad.psp_amigos_firestore.view.listeners.OnRVItemClick;
import pablo.ad.psp_amigos_firestore.viewmodel.ViewModelActivity;

public class FriendListFragment extends Permisos implements OnRVItemClick {

    private ViewModelActivity viewModel;
    private RecyclerView recyclerView;
    private FriendRecyclerAdapter adapter;
    private List<Friend> friends = new ArrayList<>();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lista_amigos, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        recyclerView = view.findViewById(R.id.RVAmigos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FriendRecyclerAdapter(viewModel, getActivity(),this);
        recyclerView.setAdapter(adapter);




        view.findViewById(R.id.btAddFriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tengoPermiso(getActivity())){
                    NavHostFragment.findNavController(FriendListFragment.this).navigate(R.id.action_lista_amigos_to_contactListFragment);
                }
            }
        });

    }


    @Override
    public void onItemClicked(Friend friend) {
        viewModel.setCurrentFriend(friend);
        NavHostFragment.findNavController(FriendListFragment.this).navigate(R.id.action_lista_amigos_to_edit_friend);
    }

    @Override
    public void pidoPermisos() {

        if( Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && codigosDenegados.size() > 0 ){

            Object[] adaptador= codigosDenegados.toArray();
            String[] pideCodigos = Arrays.copyOf(adaptador, adaptador.length, String[].class);

            getActivity().requestPermissions( pideCodigos , REQUEST_CODE_ASK_PERMISSION );

        }

    }
}