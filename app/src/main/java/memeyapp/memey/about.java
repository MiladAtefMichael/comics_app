package memeyapp.memey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class about extends Fragment {
    TextView t2;

    public about() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        // Inflate the layout for this fragment
       View root=inflater.inflate(R.layout.fragment_about, container, false);
         t2 = root.findViewById(R.id.link_page);
         t2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                 String facebookUrl = "fb://page/109272494238670";
                 facebookIntent.setData(Uri.parse(facebookUrl));
                 startActivity(facebookIntent);
             }
         });
        return root;
    }
}
