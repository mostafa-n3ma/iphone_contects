package com.example.iphone_contects.Fragments;

import static com.example.iphone_contects.MainActivity.mainViewModel;
import static com.example.iphone_contects.MainActivity.main_permition;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iphone_contects.Database.Contact;
import com.example.iphone_contects.MainActivity;
import com.example.iphone_contects.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class KeypadFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l0,add_bottom_sheet;
    private RelativeLayout R_S, R_H;
    private ImageButton call_btn,back_space;
    private TextView number, add_number;
    private EditText first_name, last_name, number_home, number_mobile, email, address;
    private TextView Done,cancel;

    private String mParam1;
    private String mParam2;
    private boolean permition_Boolean;

    public KeypadFragment() {
        // Required empty public constructor
    }


    public static KeypadFragment newInstance(String param1, String param2) {
        KeypadFragment fragment = new KeypadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_keypad, container, false);
//        R
        {
            l1 = view.findViewById(R.id.l1);
            l2 = view.findViewById(R.id.l2);
            l3 = view.findViewById(R.id.l3);
            l4 = view.findViewById(R.id.l4);
            l5 = view.findViewById(R.id.l5);
            l6 = view.findViewById(R.id.l6);
            l7 = view.findViewById(R.id.l7);
            l8 = view.findViewById(R.id.l8);
            l9 = view.findViewById(R.id.l9);
            l0 = view.findViewById(R.id.l0);
            R_S = view.findViewById(R.id.r_star);
            R_H = view.findViewById(R.id.r_H);
            call_btn = view.findViewById(R.id.call_btn);
            number = view.findViewById(R.id.dail_number);
            add_number = view.findViewById(R.id.add_number);
            back_space=view.findViewById(R.id.back_space);
            add_bottom_sheet=view.findViewById(R.id.Add_contact_bottom_sheet_layout);
        }


        //BottomSheet

        final BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(add_bottom_sheet);
        //Bottom  R
        {
            first_name = view.findViewById(R.id.first_name_edit);
            last_name = view.findViewById(R.id.last_name_edit);
            number_home = view.findViewById(R.id.number_home_edit);
            number_mobile = view.findViewById(R.id.number_mobile_edit);
            email = view.findViewById(R.id.email_edit);
            address = view.findViewById(R.id.address_edit);
            Done=view.findViewById(R.id.done_btn);
            cancel=view.findViewById(R.id.cancel_btn);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close_bottom_sheet(view, bottomSheetBehavior);
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sfirst_name = first_name.getText().toString();
                String slastname = last_name.getText().toString();
                String snumber_home = number_home.getText().toString();
                String snumber_mobile = number_mobile.getText().toString();
                String semail = email.getText().toString();
                String saddress = address.getText().toString();
                if (sfirst_name.trim().isEmpty() || snumber_home.trim().isEmpty()) {
                    Toast.makeText(getContext(), "please enter name and number", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(sfirst_name, slastname, snumber_home, snumber_mobile, semail, saddress,false);
                    mainViewModel.insert_contact(contact);
                    emptyEditTexts();
                    close_bottom_sheet(view, bottomSheetBehavior);
                }
            }
        });





        //clickers
        {
            l1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.setText(number.getText().toString()+"1");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.setText(number.getText().toString()+"2");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.setText(number.getText().toString()+"3");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"4");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"5");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"6");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"7");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"8");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"9");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            l0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"0");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            R_S.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number.setText(number.getText().toString()+"*");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            R_H.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.setText(number.getText().toString()+"#");
                    if (number.getText().toString()!=null){
                        add_number.setVisibility(View.VISIBLE);
                    }
                }
            });
            call_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (main_permition){
                        String dail = "tel:" + number.getText().toString();
                        if (number.getText().toString().isEmpty()){
                            return;
                        }else {
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));

                        }
                    }else {
                        Toast.makeText(getContext(), "Don't have permition", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            add_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            });
            back_space.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (number.getText().toString().length()==1){
                        add_number.setVisibility(View.INVISIBLE);
                        StringBuilder builder=new StringBuilder(number.getText());
                        builder.deleteCharAt(number.getText().length()-1);
                        number.setText(builder.toString());
                    }else if (number.getText().toString().length()>1){
                        StringBuilder builder=new StringBuilder(number.getText());
                        builder.deleteCharAt(number.getText().length()-1);
                        number.setText(builder.toString());
                    }else {

                    }


                }
            });

            return view;
        }


    }

    private void close_bottom_sheet(View view, BottomSheetBehavior bottomSheetBehavior) {
        InputMethodManager inputobject = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputobject != null) {
            inputobject.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });
        t.start();
        emptyEditTexts();
    }

    private void emptyEditTexts() {
        first_name.setText(null);
        last_name.setText(null);
        number_home.setText(null);
        number_mobile.setText(null);
        email.setText(null);
        address.setText(null);
    }
}