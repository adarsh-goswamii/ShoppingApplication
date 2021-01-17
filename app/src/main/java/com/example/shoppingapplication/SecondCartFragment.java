package com.example.shoppingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.zip.Inflater;

public class SecondCartFragment extends Fragment
{
    private EditText name, number, pin, houseno, area, city, state, landmark;
    private Button next, back;
    private TextView warning;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caset_second, container, false);

        initViews(view);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new FirstCartFragment());
                transaction.commit();
            }
        });

        // Todo: next on click listener.

        return view;
    }

    private void initViews(View view) {
        back= view.findViewById(R.id.frag2_back);
        next= view.findViewById(R.id.frag2_next);
        name= view.findViewById(R.id.edittext_name);
        number= view.findViewById(R.id.edittext_number);
        pin= view.findViewById(R.id.edittext_pincode);
        houseno= view.findViewById(R.id.edittext_houseno);
        area= view.findViewById(R.id.edittext_area);
        city= view.findViewById(R.id.edittext_city);
        state= view.findViewById(R.id.edittext_state);
        landmark= view.findViewById(R.id.edittext_landmark);
        warning= view.findViewById(R.id.warning_text_frag2);
    }
}
