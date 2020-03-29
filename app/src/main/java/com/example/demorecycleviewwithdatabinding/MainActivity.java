package com.example.demorecycleviewwithdatabinding;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demorecycleviewwithdatabinding.model.Cat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/*
-----------------
| RecyclerView  |
| LayoutManager |  ->  Adapter  ->  Dataset
-----------------

            https://guides.codepath.com/android/using-the-recyclerview#binding-the-adapter-to-the-recyclerview
*/

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private ArrayList<Cat> cats = new ArrayList<>();

    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setupFab();


    }


    private void initData() {
        Log.d(TAG, "initData: ");
        for (int i = 0; i < 20; i++) {
            Cat cat = new Cat("cat id:" + i , "https://cdn2.thecatapi.com/images/163.jpg");
            cats.add(cat);
        }
        initRecycleView();
    }


    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: ");
         recyclerView = findViewById(R.id.recycleView);
         adapter = new RecycleViewAdapter(this, cats);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

//      setLayoutManager is to set the layout of the contents, i.e. list of repeating views in the recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newCatPos = cats.size();
                Cat cat = new Cat("cat nr:" + newCatPos, "https://cdn2.thecatapi.com/images/163.jpg");
                cats.add(cat);


                adapter.notifyItemInserted(newCatPos);
                scrollToPosition(newCatPos);

                Snackbar.make(view, "bla bla bla!", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }


    private void scrollToPosition(int position){
//       1- abrupt scrolling
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);

    }






}


