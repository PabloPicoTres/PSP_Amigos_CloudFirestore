package pablo.ad.psp_amigos_pablo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.ad.psp_amigos_pablo.R;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Contact;
import pablo.ad.psp_amigos_pablo.viewmodel.ViewModelActivity;

public class CallRecyclerAdapter extends RecyclerView.Adapter<CallRecyclerAdapter.RecyclerViewHolder>{

    private List<Call> data;
    private ViewModelActivity viewModel;

    public CallRecyclerAdapter(List<Call> data, ViewModelActivity viewModel) {
        this.data = data;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Call call = data.get(position);

        /*holder.bind(viewModel.getFriendNameById(call.getIdFriend()),
                    call.getCallDate(),
                    String.valueOf(call.getId()),
                    String.valueOf(call.getIdFriend()));*/
        holder.bind("Llamada:",
                call.getCallDate(),
                "id: " + String.valueOf(call.getId()),
                "id Amigo: " + String.valueOf(call.getIdFriend()));
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

        private final TextView tvNameCall, tvIdFriend, tvIdCall, tvDateCall;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCall = itemView.findViewById(R.id.tvNameCall);
            tvIdFriend = itemView.findViewById(R.id.tvIdFriend);
            tvDateCall = itemView.findViewById(R.id.tvDateCall);
            tvIdCall   = itemView.findViewById(R.id.tvIdCall);
        }

        public void bind(String name, String date, String idCall, String idFriend) {

            tvNameCall.setText(name);
            tvDateCall.setText(date);
            tvIdCall.setText(idCall);
            tvIdFriend.setText(idFriend);

        }
    }
}
