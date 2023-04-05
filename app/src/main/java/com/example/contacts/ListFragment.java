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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class ListFragment extends Fragment {

	Map<String, Contact> map = new HashMap<>();


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for (int i = 1; i < 110; i++) {
			String url = "https://picsum.photos/id/" + i + "/1200/900";
			String name = "Name" + i;
			String lastname = "Lastname" + i;
			String number = "+7 914 206 73 0" + i;
			map.put(
				String.valueOf(i),
				new Contact(name, lastname, number, String.valueOf(i), url)
			);
		}
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
		Collections.sort(contactsList);

		getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
			ArrayList<String> result = bundle.getStringArrayList("bundleKey");
			String id = result.get(3);
			map.get(id).setName(result.get(0));
			map.get(id).setLastname(result.get(1));
			map.get(id).setNumber(result.get(2));
			recyclerViewInit(view, contactsList);
		});
		recyclerViewInit(view, contactsList);
	}

	private void recyclerViewInit(View view, ArrayList<Contact> contactsList) {
		RecyclerView recyclerView = view.findViewById(R.id.contacts_list);
		ContactAdapter.OnContactClickListener onContactClickListener = (contact, position) -> {
			DetailsFragment detailFragment = DetailsFragment.newInstance(
				contact.getName(),
				contact.getLastname(),
				contact.getNumber(),
				contact.getId(),
				contact.getPicture()
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
		ContactAdapter adapter = new ContactAdapter(contactsList, requireActivity(), onContactClickListener);
		recyclerView.setAdapter(adapter);
	}


}


