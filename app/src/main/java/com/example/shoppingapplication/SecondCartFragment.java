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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SecondCartFragment extends Fragment
{
    private EditText name, number, pin, houseno, area, city, state, landmark;
    private Button next2, back2;
    private TextView warning;
    public static String ORDER_KEY= "order_key";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caset_second, container, false);
        initViews(view);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, new FirstCartFragment());
                transaction.commit();
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidateData())
                {
                    warning.setVisibility(View.GONE);

                    ArrayList<GroceryItem> items= Utils.getCart(getActivity());
                    if(items!= null)
                    {
                        OrderItem orderItem= new OrderItem();
                        orderItem.setName(name.getText().toString());
                        orderItem.setNumber(number.getText().toString());
                        orderItem.setPin(pin.getText().toString());
                        orderItem.setHouseno(houseno.getText().toString());
                        orderItem.setArea(area.getText().toString());
                        orderItem.setLandmark(landmark.getText().toString());
                        orderItem.setCity(city.getText().toString());
                        orderItem.setState(state.getText().toString());
                        orderItem.setItems(items);

                        Gson gson= new Gson();
                        String JsonOrder= gson.toJson(orderItem);
                        Bundle bundle= new Bundle();
                        bundle.putString(ORDER_KEY, JsonOrder);

                        ThirdCartFragment thirdCartFragment= new ThirdCartFragment();
                        thirdCartFragment.setArguments(bundle);
                        FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, thirdCartFragment);
                        transaction.commit();
                    }
                }
                else
                {
                    warning.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    private boolean ValidateData()
    {
        if(name.getText().toString().trim().length()>0)
            if(number.getText().toString().trim().length()>0)
                if(pin.getText().toString().trim().length()>0)
                    if(houseno.getText().toString().trim().length()>0)
                        if(area.getText().toString().trim().length()>0)
                            if(city.getText().toString().trim().length()>0)
                                if(state.getText().toString().trim().length()>0)
                                    return true;

        return false;
    }

    private void initViews(View view) {
        back2= view.findViewById(R.id.frag2_back);
        next2= view.findViewById(R.id.frag2_next);
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
