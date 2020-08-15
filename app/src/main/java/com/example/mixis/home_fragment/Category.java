package com.example.mixis.home_fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mixis.MainActivity;
import com.example.mixis.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class Category extends Fragment {

    ListView lstCat;
    SimpleAdapter simpleAdapter;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();

    public Category() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        lstCat = (ListView) view.findViewById(R.id.lstCat);
        setCategory();

        return view;
    }

    private void setCategory(){

        for (int i = 1; i <= 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title",  "Category - " + String.valueOf(i));
            arrayList.add(map);
        }
        simpleAdapter = new SimpleAdapter(getActivity(), arrayList, R.layout.adapter_category,
                new String[] { "title" },
                new int[] { R.id.txtTitle});

        lstCat.setAdapter(simpleAdapter);
        lstCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(lstCat, ((TextView) view.findViewById(R.id.txtTitle)).getText().toString(),
                        Snackbar.LENGTH_LONG).show();
                MainActivity.tabLayout.setupWithViewPager(MainActivity.viewPager);
                MainActivity.viewPager.setCurrentItem(0); // selected position
            }
        });
    }

}
