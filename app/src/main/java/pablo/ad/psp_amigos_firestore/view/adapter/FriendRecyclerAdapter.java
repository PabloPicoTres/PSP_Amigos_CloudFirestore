package pablo.ad.psp_amigos_firestore.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.ad.psp_amigos_firestore.R;
import pablo.ad.psp_amigos_firestore.model.pojo.Friend;
import pablo.ad.psp_amigos_firestore.view.listeners.OnRVItemClick;
import pablo.ad.psp_amigos_firestore.viewmodel.ViewModelActivity;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.RecyclerViewHolder>{

    private List<Friend> data;
    private OnRVItemClick listener;

    public FriendRecyclerAdapter(ViewModelActivity viewModel, LifecycleOwner context, OnRVItemClick listener) {
        viewModel.getLiveFriendList().observe(context, new Observer<List<Friend>>() {
            @Override
            public void onChanged(List<Friend> newFriends) {
                data = newFriends;
                notifyDataSetChanged();
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Friend friend = data.get(position);

        holder.bind(friend.getName(),
                String.valueOf(friend.getNumber()),
                friend.getBirthdate()
        );


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(friend);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(data != null) {
            size = data.size();
        }
        return size;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNombreA, tvTelefonoA, tvFechaA;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreA = itemView.findViewById(R.id.tvNombreA);
            tvFechaA = itemView.findViewById(R.id.tvFechaNacimientoA);
            tvTelefonoA = itemView.findViewById(R.id.tvTelefonoA);
        }

        public void bind(String nombre, String telefono, String fecha) {
            tvNombreA.setText(nombre);
            tvTelefonoA.setText(telefono);
            tvFechaA.setText(fecha);
        }
    }
}