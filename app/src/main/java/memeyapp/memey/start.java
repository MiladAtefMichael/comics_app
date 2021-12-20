package memeyapp.memey;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class start extends DialogFragment {
    ImageView animation;
    ConstraintLayout background;

    public start() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_start, container, false);
        background=getActivity().findViewById(R.id.container);
        background.setVisibility(View.INVISIBLE);
        animation=root.findViewById(R.id.anim);
        Glide
                .with(getActivity())
                .load(R.drawable.splash)

                .into(animation);
        splashTimer();
        return root;
    }
    public void splashTimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                background.setVisibility(View.VISIBLE);
            }
        }, 7000);
    }
}
