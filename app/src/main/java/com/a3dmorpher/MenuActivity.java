package com.a3dmorpher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a3dmorpher.foodpenguin.R;
import com.a3dmorpher.resDetails.ProductAdapter;
import com.a3dmorpher.signup.Constant;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.listView);
        ProductAdapter adapter=new ProductAdapter(this);
        adapter.updateProducts(Constant.PRODUCT_LIST);
        listView.setAdapter(adapter);
    }
}
