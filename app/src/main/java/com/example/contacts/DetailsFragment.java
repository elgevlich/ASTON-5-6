package com.example.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

	private static final String NAME_KEY = "nameKey";
	private static final String LASTNAME_KEY = "lastnameKey";
	private static final String NUMBER_KEY = "numberKey";
	private static final String ID_KEY = "IDKey";

	private String name;
	private String lastname;
	private String number;
	private String id;

	TextInputLayout tilName;
	TextInputLayout tilLastname;
	TextInputLayout tilNumber;
	EditText etName;
	EditText etLastname;
	EditText etNumber;
	Button saveButton;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			name = getArguments().getString(NAME_KEY);
			lastname = getArguments().getString(LASTNAME_KEY);
			number = getArguments().getString(NUMBER_KEY);
			id = getArguments().getString(ID_KEY);
		}
	}

	@Override
	public View onCreateView(
		LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState
	) {
		return inflater.inflate(R.layout.fragment_details, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}


	public void initViews(View view) {

		tilName = view.findViewById(R.id.til_name);
		tilLastname = view.findViewById(R.id.til_lastname);
		tilNumber = view.findViewById(R.id.til_number);

		tilName.getEditText().setText(name);
		tilLastname.getEditText().setText(lastname);
		tilNumber.getEditText().setText(number);

		etName = view.findViewById(R.id.et_name);
		etLastname = view.findViewById(R.id.et_lastname);
		etNumber = view.findViewById(R.id.et_number);
		saveButton = view.findViewById(R.id.save_button);

		saveButton.setOnClickListener(v -> {
			ArrayList<String> contactInfo = new ArrayList<>();
			contactInfo.add(String.valueOf(etName.getText()));
			contactInfo.add(String.valueOf(etLastname.getText()));
			contactInfo.add(String.valueOf(etNumber.getText()));
			contactInfo.add(id);
			Bundle result = new Bundle();
			result.putStringArrayList("bundleKey", contactInfo);
			getParentFragmentManager().setFragmentResult("requestKey", result);
			getParentFragmentManager().popBackStack();

		});
	}

	public static DetailsFragment newInstance(String name, String lastname, String number, String id) {
		DetailsFragment detailsFragment = new DetailsFragment();
		Bundle bundle = new Bundle();
		bundle.putString(NAME_KEY, name);
		bundle.putString(LASTNAME_KEY, lastname);
		bundle.putString(NUMBER_KEY, number);
		bundle.putString(ID_KEY, id);
		detailsFragment.setArguments(bundle);
		return detailsFragment;
	}

}
