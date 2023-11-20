package com.tnl.lab10_ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Holder> {

    public ContactUser getContact(int position){ return contacts.get(position);}

    public interface ContactListener{
        void onClick(int position);
    }

    private List<ContactUser> contacts;
    private Context context;
    private ContactListener listener;

    public ContactAdapter(Context context){
        this.contacts = new ArrayList<>();
        this.context = context;
    }

    public void setListener(ContactListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.Holder holder, int position) {
        ContactUser user = contacts.get(position);

        if(user.getThumbnails() != null){
            holder.avatar.setImageURI(user.getThumbnails());
        } else{
            holder.avatar.setImageResource(R.drawable.logo1);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void addAll(List<ContactUser> users){
        this.contacts.addAll(users);
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView avatar;
        TextView name;
        public Holder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatarImageView);
            name = itemView.findViewById(R.id.fullNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(getAdapterPosition());
            }
        }
    }


}
