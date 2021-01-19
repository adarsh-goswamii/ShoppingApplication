package com.example.shoppingapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.shoppingapplication.SecondCartFragment.ORDER_KEY;

public class ThirdCartFragment extends Fragment
{

    private RadioGroup radioGroup;
    private TextView items, address, number, price;
    private Button checkOut, back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_caset_third, container, false);

        initViews(view);
        Bundle bundle= getArguments();
        if(bundle!= null)
        {
            final String JsonOrder= bundle.getString(ORDER_KEY);
            if(JsonOrder!= null) {
                Gson gson = new Gson();
                Type type= new TypeToken<OrderItem>(){}.getType();
                final OrderItem orderItem = gson.fromJson(JsonOrder, type);
                if (orderItem != null) {
                    StringBuilder item = new StringBuilder("\t");
                    for (GroceryItem i : orderItem.getItems())
                        item.append(i.getName()+"\n\t");

                    items.setText(item.toString());
                    address.setText("\t" + orderItem.getHouseno() + "\n\t" + orderItem.getArea() + "\n\t" + orderItem.getCity() + "\n\t" + orderItem.getState());
                    number.setText("\t"+orderItem.getNumber());
                    price.setText(getTotalPrice(orderItem.getItems()));

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO
                        }
                    });

                    checkOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO
                        }
                    });
                }

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle= new Bundle();
                        bundle.putString(ORDER_KEY, JsonOrder);
                        SecondCartFragment fragment= new SecondCartFragment();
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, fragment);
                        transaction.commit();
                    }
                });

                checkOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch(radioGroup.getCheckedRadioButtonId())
                        {
                            case R.id.radio_google_pay:
                                orderItem.setPaymentMethod("google pay");
                                break;

                            case R.id.radio_debit_card:
                                orderItem.setPaymentMethod("debit card");
                                break;

                            case R.id.radio_credit_card:
                                orderItem.setPaymentMethod("credit card");
                                break;

                            case R.id.radio_cash_on_delivery:
                                orderItem.setPaymentMethod("cash on delivery");
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        }

        return view;
    }

    private String getTotalPrice(ArrayList<GroceryItem> item)
    {
        double price= 0.0;
        for(GroceryItem i: item)
            price+= i.getPrice();

        price= Math.round(price*100.0)/100.0;
        return price+"$";
    }

    private void initViews(View view) {
        radioGroup= view.findViewById(R.id.radio_group);
        address= view.findViewById(R.id.text_item_address);
        number= view.findViewById(R.id.text_item_number);
        price= view.findViewById(R.id.text_item_price);
        items= view.findViewById(R.id.text_item_names);
        checkOut= view.findViewById(R.id.frag3_next);
        back= view.findViewById(R.id.frag3_back);
    }
}
