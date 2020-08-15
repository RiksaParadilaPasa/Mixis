package com.example.mixis;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mixis.adapter.CartAdapter;
import com.example.mixis.model.CartModel;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    SwipeRefreshLayout swipe;
    RecyclerView recyclerView;
    ArrayList<CartModel> carts;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        swipe           = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView    = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carts           = new ArrayList<>();
        setCart();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carts.clear();
                recyclerView.setAdapter(null);
                setCart();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Keranjang");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void setCart(){
        for (int i=0; i < 3; i++) {
            CartModel model = new CartModel();
            carts.add(model);
        }

        cartAdapter = new CartAdapter(Cart.this, carts);
        cartAdapter.notifyDataSetChanged();
        Log.d("_COUNT", String.valueOf(cartAdapter.getItemCount()) );
        recyclerView.setAdapter(cartAdapter);

        swipe.setRefreshing(false);
    }
}
