package memeyapp.memey;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<dataModel> postData;
    RecyclerView recyclerView;

    BottomNavigationView bottomNavigation;
    DatabaseReference databaseReference;
    dataModel data;

    LinearLayout gold;
    LinearLayout home;
    LinearLayout trend;
    LinearLayout about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
        bottomNavigation = findViewById(R.id.nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        postData=new ArrayList<dataModel>();
        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        data=new dataModel();
        gold=findViewById(R.id.f3);
        home=findViewById(R.id.f1);
        trend=findViewById(R.id.f2);
        about=findViewById(R.id.f4);


    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            home.setVisibility(View.VISIBLE);
                            trend.setVisibility(View.INVISIBLE);
                            gold.setVisibility(View.INVISIBLE);
                            about.setVisibility(View.INVISIBLE);
                            return true;
                        case R.id.trend:
                            home.setVisibility(View.INVISIBLE);
                            trend.setVisibility(View.VISIBLE);
                            gold.setVisibility(View.INVISIBLE);
                            about.setVisibility(View.INVISIBLE);
                            return true;
                        case R.id.gold:
                            home.setVisibility(View.INVISIBLE);
                            trend.setVisibility(View.INVISIBLE);
                            gold.setVisibility(View.VISIBLE);
                            about.setVisibility(View.INVISIBLE);
                            return true;
                        case R.id.about:
                            home.setVisibility(View.INVISIBLE);
                            trend.setVisibility(View.INVISIBLE);
                            gold.setVisibility(View.INVISIBLE);
                            about.setVisibility(View.VISIBLE);
                            return true;



                    }
                    return false;
                }
            };


   void start(){
       FragmentManager manager =getSupportFragmentManager();
       manager.beginTransaction()
               .replace(R.id.image_fragment,new start()).commit();
   }

}
