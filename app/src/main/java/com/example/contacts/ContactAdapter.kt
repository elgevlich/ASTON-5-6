package com.example.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter(
	private var contactsList: List<Contact>,
	private val context: Context,
	onContactClickListener: OnContactClickListener,
	onLongClickListener: OnLongClickListener
) :
	RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

	interface OnContactClickListener {

		fun onContactClick(contact: Contact?, position: Int)
	}

	interface OnLongClickListener {

		fun onLongClick(contact: Contact?, position: Int)

	}


	private val onContactClickListener: OnContactClickListener
	private val onLongClickListener: OnLongClickListener


	init {
		this.onContactClickListener = onContactClickListener
	}

	init {
		this.onLongClickListener = onLongClickListener
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.item_contact, viewGroup, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(
		viewHolder: ViewHolder,
		@SuppressLint("RecyclerView") position: Int
	) {
		val contact = contactsList[position]
		viewHolder.name.text = contact.name
		viewHolder.lastname.text = contact.lastname
		viewHolder.number.text = contact.number
		viewHolder.id.text = contact.id
		Glide.with(context).load(contact.picture).into(viewHolder.image)

		viewHolder.itemView.rootView
			.setOnClickListener {
				onContactClickListener.onContactClick(
					contact,
					position
				)
			}

		viewHolder.itemView.rootView
			.setOnLongClickListener {
				onLongClickListener.onLongClick(
					contact, position
				)
				true
			}
	}

	override fun getItemCount(): Int {
		return contactsList.size
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setFilteredList(contactsList: List<Contact>) {
		this.contactsList = contactsList
		notifyDataSetChanged()
	}


	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val name: TextView
		val lastname: TextView
		val number: TextView
		val id: TextView
		val image: ImageView

		init {
			name = view.findViewById(R.id.name)
			lastname = view.findViewById(R.id.lastname)
			number = view.findViewById(R.id.number)
			image = view.findViewById(R.id.image_contact)
			id = view.findViewById(R.id.id_number)
		}
	}


}
