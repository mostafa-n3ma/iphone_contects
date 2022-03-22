package com.example.iphone_contects.Fragments;

import static com.example.iphone_contects.MainActivity.mainViewModel;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iphone_contects.Adapters.ContactAdapter;
import com.example.iphone_contects.Database.Contact;
import com.example.iphone_contects.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;


public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    LinearLayout linearLayout;
    EditText first_name, last_name, number_home, number_mobile, email, address;
    public static ContactAdapter adapter;
    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
//        init views
        recyclerView = view.findViewById(R.id.contact_recycler);
        first_name = view.findViewById(R.id.first_name_edit);
        last_name = view.findViewById(R.id.last_name_edit);
        number_home = view.findViewById(R.id.number_home_edit);
        number_mobile = view.findViewById(R.id.number_mobile_edit);
        email = view.findViewById(R.id.email_edit);
        address = view.findViewById(R.id.address_edit);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
          adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);

        mainViewModel.getAll_Contact().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.submitList(contacts);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mainViewModel.delete_contact(adapter.getContactAt(position));
            }
        }).attachToRecyclerView(recyclerView);


        linearLayout = (LinearLayout) view.findViewById(R.id.Add_contact_bottom_sheet_layout);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        Button add_btn = (Button) view.findViewById(R.id.add_contact_button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close_bottom_sheet(view, bottomSheetBehavior);
            }
        });
        TextView done_btn = (TextView) view.findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }

    private void emptyEditTexts() {
        first_name.setText(null);
        last_name.setText(null);
        number_home.setText(null);
        number_mobile.setText(null);
        email.setText(null);
        address.setText(null);
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


}