package pablo.ad.psp_amigos_pablo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.ad.psp_amigos_pablo.R;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;
import pablo.ad.psp_amigos_pablo.view.listeners.OnRVItemClick;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.RecyclerViewHolder>{

    private List<FriendCallCount> data;
    private OnRVItemClick listener;

    public FriendRecyclerAdapter(List<FriendCallCount> data, OnRVItemClick listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        FriendCallCount friend = data.get(position);

        holder.bind(friend.getFriend().getName(),
                String.valueOf(friend.getFriend().getNumber()),
                String.valueOf(friend.getCount()),
                friend.getFriend().getBirthdate()
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

        private final TextView tvNombreA, tvTelefonoA, tvCuentaLlamadasA, tvFechaA;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreA = itemView.findViewById(R.id.tvNombreA);
            tvCuentaLlamadasA = itemView.findViewById(R.id.tvNLlamadasA);
            tvFechaA = itemView.findViewById(R.id.tvFechaNacimientoA);
            tvTelefonoA = itemView.findViewById(R.id.tvTelefonoA);
        }

        public void bind(String nombre, String telefono, String cuentaLlamadas, String fecha) {
            tvNombreA.setText(nombre);
            tvTelefonoA.setText(telefono);
            tvCuentaLlamadasA.setText(cuentaLlamadas);
            tvFechaA.setText(fecha);
        }
    }
}