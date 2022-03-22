package com.example.iphone_contects.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iphone_contects.Database.Contact;
import com.example.iphone_contects.R;

public class ContactAdapter extends ListAdapter<Contact, ContactAdapter.ContactHolder> {
private onItemClicker onItemClicker;

    public ContactAdapter() {
        super(diffCallback_contact);
    }

    private static DiffUtil.ItemCallback<Contact> diffCallback_contact = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getFirst_name().equals(newItem.getFirst_name()) &&
                    oldItem.getLastname().equals(newItem.getLastname()) &&
                    oldItem.getNumber_home().equals(newItem.getNumber_home()) &&
                    oldItem.getNumber_mobile().equals(newItem.getNumber_mobile()) &&
                    oldItem.getEmail().equals(newItem.getEmail())&&
                    oldItem.getAddress().equals(newItem.getAddress());
        }
    };

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact=getItem(position);
        holder.contact_name.setText(contact.getFirst_name()+" "+contact.getLastname());

    }

    public Contact getContactAt(int position){
        return getItem(position);
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        private TextView contact_name;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            contact_name=itemView.findViewById(R.id.contact_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (onItemClicker!=null&&position!=RecyclerView.NO_POSITION){
                        onItemClicker.click(getItem(position));
                    }

                }
            });
        }
    }
    public interface onItemClicker{
        void click(Contact contact);
    }
    public void setItemClicker(onItemClicker itemClicker){
        this.onItemClicker=itemClicker;
    }
}
