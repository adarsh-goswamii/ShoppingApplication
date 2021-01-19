package com.example.shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.shoppingapplication.SecondCartFragment.ORDER_KEY;

public class PaymentResultFragment extends Fragment
{
    private LottieAnimationView successfull, failed;
    private Button back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_payment, container, false);

        successfull= view.findViewById(R.id.successfull);
        back= view.findViewById(R.id.btn_back_to_shopping);
        failed= view.findViewById(R.id.failed);

        Bundle bundle= getArguments();
        if(bundle!= null)
        {
            String JsonOrder= bundle.getString(ORDER_KEY);
            if(JsonOrder!= null)
            {
                back.setText("Back To Shopping");
                failed.setVisibility(View.GONE);
                successfull.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                Type type= new TypeToken<OrderItem>(){}.getType();
                OrderItem orderItem = gson.fromJson(JsonOrder, type);

                Utils.deleteCart(getActivity());
                for(GroceryItem i: orderItem.getItems())
                    Utils.increasePopularity(getActivity(), i, 1);

                // TODO: userPoints
            }
            else
            {
                back.setText("Retry Later");
                failed.setVisibility(View.VISIBLE);
                successfull.setVisibility(View.GONE);
            }
        }
        else
        {
            back.setText("Back To Shopping");
            failed.setVisibility(View.GONE);
            successfull.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
