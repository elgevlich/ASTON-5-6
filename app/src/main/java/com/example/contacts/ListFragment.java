package com.example.contacts;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListFragment extends Fragment {

	Map<String, Contact> map = new HashMap<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		map.put("1", new Contact("Pieter", "Parker", "+1 234 567 88 99", "1"));
		map.put("2", new Contact("Harry", "Potter", "+6 666 666 66 66", "2"));
		map.put("3", new Contact("Peppa", "Pig", "+7 914 205 00 33", "3"));
		map.put("4", new Contact("Hermione", "Granger", "+5 555 555 55 55", "4"));
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("list", (Serializable)map);
	}

	@Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		if (savedInstanceState != null) {
			map = (Map<String, Contact>)savedInstanceState.getSerializable("list");
		}
	}

	@Override
	public View onCreateView(
		LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState
	) {
		return inflater.inflate(R.layout.fragment_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ArrayList<Contact> contactsList = new ArrayList<>(map.values());

		getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
			ArrayList<String> result = bundle.getStringArrayList("bundleKey");
			String id = result.get(3);
			map.get(id).setName(result.get(0));
			map.get(id).setLastName(result.get(1));
			map.get(id).setNumber(result.get(2));
			recyclerViewInit(view, contactsList);
		});
		recyclerViewInit(view, contactsList);
	}

	private void recyclerViewInit(View view, ArrayList<Contact> contactsList) {
		RecyclerView recyclerView = view.findViewById(R.id.contacts_list);
		CustomAdapter.OnContactClickListener onContactClickListener = (contact, position) -> {
			DetailsFragment detailFragment = DetailsFragment.newInstance(
				contact.getName(),
				contact.getLastName(),
				contact.getNumber(),
				contact.getID()
			);
			FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
			if (view.findViewById(R.id.fragmentTablet) != null) {
				fragmentManager.beginTransaction()
					.replace(R.id.framelayout_right, detailFragment)
					.addToBackStack("contacts")
					.commit();
			} else {
				fragmentManager.beginTransaction()
					.replace(R.id.framelayout_left, detailFragment)
					.addToBackStack("contacts")
					.commit();
			}
		};
		CustomAdapter adapter = new CustomAdapter(contactsList, requireActivity(), onContactClickListener);
		recyclerView.setAdapter(adapter);
	}

}


