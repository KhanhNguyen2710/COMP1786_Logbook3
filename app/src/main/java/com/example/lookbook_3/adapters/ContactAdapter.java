package com.example.lookbook_3.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lookbook_3.Models.Person;
import com.example.lookbook_3.R;

import java.util.List;

// ContactAdapter.java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Person> persons;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Person person);
    }

    public ContactAdapter(List<Person> persons, OnDeleteClickListener onDeleteClickListener) {
        this.persons = persons;
        this.onDeleteClickListener = onDeleteClickListener;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.lookbook_3.R.layout.item_contact_card, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Person person = persons.get(position);
       holder.personName.setText(person.name);
       holder.personDetails.setText(person.dob + " " + person.email );//person.person_id + " " +
        if (person.imageSelection) {
            holder.personImage.setImageResource(R.drawable.pic1);
        } else {
            holder.personImage.setImageResource(R.drawable.pic2);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(person);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView personName, personDetails;
        ImageView personImage;
        Button deleteButton;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            personDetails = itemView.findViewById(R.id.personDetails);
            personImage = itemView.findViewById(R.id.personImage);
            deleteButton = itemView.findViewById(R.id.deleteButton);


        }
    }
}