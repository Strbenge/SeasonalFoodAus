package ict376.murdoch.edu.au.seasonalfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            StateFragment sf = StateFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.main_fragment_container, sf).commit();
        }
    }
}
