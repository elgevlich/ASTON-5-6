package com.example.contacts;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListFragment listFragment = new ListFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.framelayout_left, listFragment);

		if (findViewById(R.id.framelayout_right) != null) {
			DetailsFragment detailFragment = new DetailsFragment();
			fragmentTransaction.add(R.id.framelayout_right, detailFragment);
		}

		fragmentTransaction.commit();
	}

}