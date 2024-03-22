package com.project.kudawala;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.project.kudawala.R.id;
import com.project.kudawala.adapters.SetAllCategoryAdapter;
import com.project.kudawala.reponses.CategoryResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sell_scrap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sell_scrap extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SetAllCategoryAdapter setAllCategoryAdapter;

    List<CategoryResponse> categoryResponses;

    public Sell_scrap() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sell_scrap.
     */
    // TODO: Rename and change types and number of parameters
    public static Sell_scrap newInstance(String param1, String param2) {
        Sell_scrap fragment = new Sell_scrap();
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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sell_scrap, container, false);
        View view1 = inflater.inflate(R.layout.main_categories_layout, container, true);

        //functionality of sell_scrap_button

        Button btn_sell_scrap ;
        btn_sell_scrap = view.findViewById(R.id.sell_scrap_btn);

        //functionality for getting user name dynamically from google authentication using firebase

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String tempuserName = firebaseAuth.getCurrentUser().getDisplayName();
        TextView userNameText = view.findViewById(id.greeting_text1);
        String userName = tempuserName.split(" (?!.* )")[0];
        userNameText.setText("Hi "+userName+",");

        //Setting user name first letter in textview
        TextView user_icon_text = view.findViewById(id.user_name_icon);
        user_icon_text.setText(userName.substring(0,1));

        //calling api for fetching category data and images
        APIInterface apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);

        //grid view initialization
        GridView selectCategory = view.findViewById(id.main_category_grid_layout);

        //calling api interface function
        Call<List<CategoryResponse>> fetchCategoryCall = apiInterface.getAllCategoryInterface();
        fetchCategoryCall.enqueue(new Callback<List<CategoryResponse>>() {

            @Override
            public void onResponse(@NonNull Call<List<CategoryResponse>> call, @NonNull Response<List<CategoryResponse>> response) {
                if(response.body() != null){
                    categoryResponses = response.body();
                    setAllCategoryAdapter = new SetAllCategoryAdapter(getContext(), categoryResponses, btn_sell_scrap);
                    selectCategory.setAdapter(setAllCategoryAdapter);
                   // Toast.makeText(getApplicationContext(),selectedCategories.get(1).getCategoryName(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Fetch Category error", String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Log.e("categories fetch failed",t.getLocalizedMessage().toString());
            }
        });

        btn_sell_scrap.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Select_scrap_item.class);
            intent.putExtra("subcategories",setAllCategoryAdapter.getSelectedCategory());
            startActivity(intent);
        });

        return view;
    }
}