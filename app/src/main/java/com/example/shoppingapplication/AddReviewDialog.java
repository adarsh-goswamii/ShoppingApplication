package com.example.shoppingapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.shoppingapplication.GroceryItemActivity.GROCERY_ITEM_KEY;

public class AddReviewDialog extends DialogFragment
{
    public interface AddReview
    {
        void onAddReviewResults(Reviews review);
    }

    private AddReview addReview;
    private TextView productName, warning;
    private EditText username, reviewText;
    private Button btn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view= getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review, null);
        initViews(view);

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity()).setView(view);
        Bundle bundle= getArguments();
        if(bundle!= null)
        {
            final GroceryItem item= bundle.getParcelable(GROCERY_ITEM_KEY);
            if(item!= null)
            {
                productName.setText(item.getName());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name= username.getText().toString();
                        String text= reviewText.getText().toString().trim();
                        String date= getCurrentDate();

                        if(name.equals("") || text.equals(""))
                        {
                            warning.setText("Please fill all the details");
                            warning.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            warning.setVisibility(View.GONE);
                            try{
                                addReview= (AddReview) getActivity();
                                addReview.onAddReviewResults(new Reviews(name, text, date, item.getId()));
                                dismiss();
                            }
                            catch (ClassCastException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        return builder.show();
    }

    private String getCurrentDate()
    {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-YYYY");
        return sdf.format(calendar.getTime());
    }

    private void initViews(View view) {
        productName= view.findViewById(R.id.text_product_name);
        warning= view.findViewById(R.id.text_warning);
        username= view.findViewById(R.id.edit_text_name);
        reviewText= view.findViewById(R.id.edit_text_review);
        btn= view.findViewById(R.id.btn_add_review);
    }
}
