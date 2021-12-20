package memeyapp.memey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
    ArrayList<memeyapp.memey.dataModel> postData;
    RecyclerView recyclerView;
    memeyapp.memey.postAdapter postAdapter;
    DatabaseReference databaseReference;
    memeyapp.memey.dataModel data;
    private  int fragementNumber=0;
    FirebaseStorage storage;

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        if (container != null) {
            container.removeAllViews();
        }
      storage = FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("posts");
        postData=new ArrayList<dataModel>();
        recyclerView=root.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        data=new dataModel();


        return root;
    }
    public void RecyclerView(){

        postAdapter=new postAdapter(postData,getActivity(),fragementNumber);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    void getData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postData.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    data =ds.getValue(dataModel.class);
                    postData.add(data);
                    RecyclerView();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
            }
@Override
public void onStart() {

    super.onStart();
    getData();
    }
}
