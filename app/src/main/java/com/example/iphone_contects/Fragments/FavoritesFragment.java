package com.example.iphone_contects.Fragments;

import static com.example.iphone_contects.MainActivity.mainViewModel;
import static com.example.iphone_contects.MainActivity.main_permition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iphone_contects.Adapters.ContactAdapter;
import com.example.iphone_contects.Adapters.FavAdapter;
import com.example.iphone_contects.Database.Contact;
import com.example.iphone_contects.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView main_favrecycler;
    public static FavAdapter favAdapter;
    private ContactAdapter addfavContactAdapter;
    private RecyclerView addfavRecyclerView;
    private LinearLayout addfavlinear,addfavFinal,final_click_layout;
    private TextView addfav,fav_cancel,finalnumber,cancel_final_sheet;
    Contact favContact1;
    private boolean permition_Boolean=true;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        addfavRecyclerView=view.findViewById(R.id.recycler_fav_bottom_sheet);
        addfavlinear =view.findViewById(R.id.Add_fav_bottom_sheet_layout);
        addfavFinal=view.findViewById(R.id.bottom_sheet_final_layout);
        final_click_layout=view.findViewById(R.id.add_fav_final_click_layout);
        addfav=view.findViewById(R.id.add_fav_bar);
        fav_cancel=view.findViewById(R.id.fav_bottomsheet_cancel_btn9);
        finalnumber=view.findViewById(R.id.finalnumber);
        cancel_final_sheet=view.findViewById(R.id.cancel_final_sheet);

        final BottomSheetBehavior favSheetBehavior=BottomSheetBehavior.from(addfavlinear);
        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        fav_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        main_favrecycler =view.findViewById(R.id.recycler_fav);
        main_favrecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        main_favrecycler.setHasFixedSize(true);
         favAdapter =new FavAdapter();
        main_favrecycler.setAdapter(favAdapter);
        mainViewModel.getFavContact().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                favAdapter.submitList(contacts);
            }
        });
        addfavRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        addfavRecyclerView.setHasFixedSize(true);
        addfavContactAdapter=new ContactAdapter();
        addfavRecyclerView.setAdapter(addfavContactAdapter);
        mainViewModel.getAll_Contact().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                addfavContactAdapter.submitList(contacts);
            }
        });

        final BottomSheetBehavior favSheet_finalBehavior=BottomSheetBehavior.from(addfavFinal);

        addfavContactAdapter.setItemClicker(new ContactAdapter.onItemClicker() {
            @Override
            public void click(Contact contact) {
                 favContact1=contact;
//
                favSheet_finalBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                finalnumber.setText(favContact1.getNumber_home());

//
            }
        });
        final_click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favContact1.setFav(true);
                mainViewModel.update_contact(favContact1);
                favSheet_finalBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                favSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        cancel_final_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favSheet_finalBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                favSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                Contact contact= favAdapter.getFavAt(position);
                mainViewModel.delete_contact(contact);
            }
        }).attachToRecyclerView(main_favrecycler);

        favAdapter.setCallonfavClickListener(new FavAdapter.Callonfavclick() {
            @Override
            public void onclickforcalling(Contact contact) {
                String Contactnumber=contact.getNumber_home();
                call_fav(Contactnumber);
            }
        });



        return view;
    }
    private void call_fav(String dailnumber){
        if (main_permition){
            String dail = "tel:" +dailnumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
        }


    }
}