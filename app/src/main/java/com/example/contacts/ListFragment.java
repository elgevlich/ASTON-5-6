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

import java.util.ArrayList;


public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Contact> contactsList = new ArrayList<>();
    private CustomAdapter adapter;
    private Boolean isReset = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isReset) {
            listContacts();
            isReset = true;
        }
        recyclerView = view.findViewById(R.id.contacts_list);
        CustomAdapter.OnContactClickListener onContactClickListener = (contact, position) -> {
            DetailsFragment detailFragment = DetailsFragment.newInstance(
                    contact.getName(),
                    contact.getLastName(),
                    contact.getNumber()
            );
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack("contacts")
                    .commit();
        };
        adapter = new CustomAdapter(contactsList, requireActivity(), onContactClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void listContacts() {
        contactsList.add(new Contact("Pieter", "Parker", "+1 234 567 88 99"));
        contactsList.add(new Contact("Harry", "Potter", "+6 666 666 66 66"));
        contactsList.add(new Contact("Angelina", "Jolie", "+7 981 203 56 77"));
        contactsList.add(new Contact("Hermione", "Granger", "+5 555 555 55 55"));
    }
}


