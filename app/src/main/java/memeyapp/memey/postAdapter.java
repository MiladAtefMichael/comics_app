package memeyapp.memey;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;


public class postAdapter  extends RecyclerView.Adapter<postAdapter.postHolder > {
    ArrayList<dataModel> listItems;
    Context context;
    int fragementNumber;

    public postAdapter(ArrayList<dataModel> listItems, Context context,int FragementNumber) {
        this.listItems = listItems;
        this.context = context;
        fragementNumber=FragementNumber;
    }

    @NonNull
    @Override
    public postHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        final postHolder listHolder = new postHolder(view);
        return listHolder;
    }

    @Override
public void onBindViewHolder(@NonNull postHolder holder, final int position) {

if(position!=0&&position %5==0) {
    holder.mAdView.setVisibility(View.VISIBLE);
  // holder.content.setVisibility(View.GONE);

    MobileAds.initialize(context, new OnInitializationCompleteListener() {
        @Override
        public void onInitializationComplete(InitializationStatus initializationStatus) {
        }
    });
    AdRequest adRequest = new AdRequest.Builder().build();
    holder.mAdView.loadAd(adRequest);
}
    holder.image.layout(0,0,0,0);
    Glide.with(context)
            .load(listItems.get(position).getLink()).diskCacheStrategy(DiskCacheStrategy.ALL)

            .thumbnail(Glide.with(context).load(R.drawable.loading))
           //.fitCenter()

            .into(holder.image);
holder.image.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        openImage(new image(),listItems.get(position).getLink());
    }
});
    if (listItems.get(position).getBy() == null) {
        holder.memey.setVisibility(View.VISIBLE);
        holder.facebook.setVisibility(View.GONE);
        holder.by.setText("Memey");


    }
    else {
        holder.by.setText(listItems.get(position).getBy());
        holder.facebook.setVisibility(View.VISIBLE);
        holder.memey.setVisibility(View.GONE);
    }

    }


@Override
public int getItemCount() {

        return listItems.size();
}

    public class postHolder extends RecyclerView.ViewHolder{
    TextView by;

    ImageView image;
    AdView mAdView;
    public View container;
    RecyclerView rv;
    ImageView memey;
    ImageView facebook;
    LinearLayout content;

    public postHolder(@NonNull View itemView) {
        super(itemView);
        container=itemView;
        image=itemView.findViewById(R.id.image);
        mAdView = itemView.findViewById(R.id.adView);
        by=itemView.findViewById(R.id.by);
        rv=itemView.findViewById(R.id.rv);
        memey =itemView.findViewById(R.id.memey);
        facebook =itemView.findViewById(R.id.facebook);
        content =itemView.findViewById(R.id.content);


        }
    }

    private void openImage(Fragment fragment, String link) {


        Bundle args = new Bundle();
        args.putString("link", link);
        fragment.setArguments(args);
        FragmentManager manager =((FragmentActivity) context).getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.image_fragment,fragment).commit();

    }


}



