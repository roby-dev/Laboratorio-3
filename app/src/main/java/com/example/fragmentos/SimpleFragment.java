package com.example.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    private static final int YES =1;
    private static final int NO =0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_simple,container,false);

        final RadioGroup radioGroup = root.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index= radioGroup.indexOfChild(radioButton);
                TextView tv = root.findViewById(R.id.fragment_header);
                switch (index){
                    case YES:
                        tv.setText(R.string.yes_message);
                        break;
                    case NO:
                        tv.setText(R.string.no_message);
                        break;
                    default:
                        break;
                }

            }
        });

        return root;
    }
}