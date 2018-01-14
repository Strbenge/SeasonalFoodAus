package ict376.murdoch.edu.au.seasonalfood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StateFragment extends Fragment {

    //widgets
    private Button wa = null;
    private Button nsw = null;
    private Button qld = null;
    private Button sa = null;
    private Button tas = null;
    private Button vic = null;


    public StateFragment() {
        // Required empty public constructor
    }

    public static StateFragment newInstance() {
        StateFragment fragment = new StateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate layout
        View v = inflater.inflate(R.layout.fragment_state, container, false);

        //Buttons
        wa = v.findViewById(R.id.wa);
        nsw = v.findViewById(R.id.nsw);
        qld = v.findViewById(R.id.qld);
        sa = v.findViewById(R.id.sa);
        vic = v.findViewById(R.id.vic);
        tas = v.findViewById(R.id.tas);

        return v;
    }


    public class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v){
            //button action here
        }
    }

}
