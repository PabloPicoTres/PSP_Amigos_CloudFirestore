package pablo.ad.psp_amigos_pablo.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pablo.ad.psp_amigos_pablo.R;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;
import pablo.ad.psp_amigos_pablo.view.adapter.CallRecyclerAdapter;
import pablo.ad.psp_amigos_pablo.view.adapter.FriendRecyclerAdapter;
import pablo.ad.psp_amigos_pablo.viewmodel.ViewModelActivity;

public class CallsListFragment extends Fragment {

    ViewModelActivity viewModel;
    RecyclerView recyclerView;
    CallRecyclerAdapter adapter;
    List<Call> calls = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        recyclerView = view.findViewById(R.id.RVCalls);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CallRecyclerAdapter(calls, viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getLiveCallList().observe(getActivity(), new Observer<List<Call>>() {
            @Override
            public void onChanged(List<Call> newCalls) {
                calls.clear();
                calls.addAll(newCalls);
                adapter.notifyDataSetChanged();
            }
        });

        view.findViewById(R.id.btAtrasCalls).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CallsListFragment.this).navigate(R.id.action_callsListFragment_to_lista_amigos);
            }
        });
    }
}