package pablo.ad.psp_amigos_firestore.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.ad.psp_amigos_firestore.R;
import pablo.ad.psp_amigos_firestore.model.pojo.Contact;
import pablo.ad.psp_amigos_firestore.viewmodel.ViewModelActivity;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.RecyclerViewHolder>{

    private List<Contact> data;
    private ViewModelActivity viewModel;

    public ContactsRecyclerAdapter( ViewModelActivity viewModel) {
        this.viewModel = viewModel;
        data = viewModel.getContactList();
     }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Contact contacto = data.get(position);

        holder.bind(contacto.getName(),
                    contacto.getPhone(),
                    contacto.isChecked()
        );

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contacto.setChecked(!contacto.isChecked());
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

        private final TextView tvNombreC, tvTelefonoC;
        private CheckBox checkBox;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreC = itemView.findViewById(R.id.tvNombreC);
            tvTelefonoC = itemView.findViewById(R.id.tvTelefonoC);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void bind(String nombre, String telefono, boolean checked) {
            tvNombreC.setText(nombre);
            tvTelefonoC.setText(telefono);
            checkBox.setChecked(checked);
        }
    }
}