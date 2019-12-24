package m.dp.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavouriteScreen extends AppCompatActivity {
    RecyclerView recyclerView ;
    List<ProductsIteam> recData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_screen);
        prepraeLis();
        recyclerView = findViewById(R.id.activity_recycler);
        FavouriteAdapter adapter = new FavouriteAdapter(recData);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }

    public void prepraeLis(){
        recData = new ArrayList<>();
        recData.add(new ProductsIteam( R.drawable.ic_1 , 120));
        recData.add(new ProductsIteam( R.drawable.ic_2 , 100));
        recData.add(new ProductsIteam( R.drawable.ic_3 , 250));
        recData.add(new ProductsIteam( R.drawable.ic_4 , 301));
        recData.add(new ProductsIteam( R.drawable.ic_5 , 80));
        recData.add(new ProductsIteam( R.drawable.ic_6 , 48));
        recData.add(new ProductsIteam( R.drawable.ic_7 , 200));
        recData.add(new ProductsIteam( R.drawable.ic_8 , 350));
        recData.add(new ProductsIteam( R.drawable.ic_9 , 90));
        recData.add(new ProductsIteam( R.drawable.ic_10 , 100));

    }
}
