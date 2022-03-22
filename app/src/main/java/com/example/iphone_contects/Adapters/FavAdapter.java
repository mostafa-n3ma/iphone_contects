package com.example.iphone_contects.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iphone_contects.Database.Contact;
import com.example.iphone_contects.R;

public class FavAdapter extends ListAdapter<Contact,FavAdapter.favHolder> {

    Callonfavclick callonfavclick;
    public FavAdapter() {
        super(diffCallback_fav);
    }

   private static DiffUtil.ItemCallback<Contact>diffCallback_fav=new DiffUtil.ItemCallback<Contact>() {
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
                   oldItem.getAddress().equals(newItem.getAddress())
                   ;
       }
   } ;


    @NonNull
    @Override
    public favHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,parent,false);
        return new favHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favHolder holder, int position) {
        Contact favContact=getItem(position);
        holder.fav_name.setText(favContact.getFirst_name()+" "+favContact.getLastname());

    }
    public Contact getFavAt(int position){
        return getItem(position);
    }

    public class favHolder extends RecyclerView.ViewHolder {
        private TextView fav_name;
        private ImageView fav_info_btn;
        public favHolder(@NonNull View itemView) {
            super(itemView);
            fav_name=itemView.findViewById(R.id.fav_name_text);
            fav_info_btn=itemView.findViewById(R.id.fav_info_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (callonfavclick!=null&&position!=RecyclerView.NO_POSITION){
                        callonfavclick.onclickforcalling(getItem(position));

                    }
                }
            });
        }
    }

    public interface Callonfavclick{
        void onclickforcalling(Contact contact);
    }
    public void setCallonfavClickListener(Callonfavclick listener){
        this.callonfavclick=listener;
    }
}
