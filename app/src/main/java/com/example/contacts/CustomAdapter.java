package com.example.contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

	public interface OnContactClickListener {

		void onContactClick(Contact contact, int position);

	}

	private final List<Contact> contactsList;
	private final LayoutInflater inflater;
	private final OnContactClickListener onContactClickListener;

	public CustomAdapter(List<Contact> listContacts, Context context, OnContactClickListener onContactClickListener) {
		this.contactsList = listContacts;
		this.inflater = LayoutInflater.from(context);
		this.onContactClickListener = onContactClickListener;
	}

	@NonNull @Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View view = LayoutInflater.from(viewGroup.getContext())
			.inflate(R.layout.item_contact, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
		final Contact contact = contactsList.get(position);
		viewHolder.name.setText(contact.getName());
		viewHolder.lastname.setText(contact.getLastName());
		viewHolder.number.setText(contact.getNumber());
		viewHolder.itemView.getRootView()
			.setOnClickListener(view -> onContactClickListener.onContactClick(contact, position));
	}

	@Override
	public int getItemCount() {
		return contactsList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView name;
		private final TextView lastname;
		private final TextView number;

		public ViewHolder(View view) {
			super(view);
			name = view.findViewById(R.id.name);
			lastname = view.findViewById(R.id.lastname);
			number = view.findViewById(R.id.number);
		}

	}

}
