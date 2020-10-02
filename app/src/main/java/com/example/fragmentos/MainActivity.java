package com.example.fragmentos;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int  mRadioButtonChoice=2;
    public static int votos1=0;
    public static int votos2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            isFragmentDisplayed=savedInstanceState.getBoolean(FRAGMENT_STATE);
            if(isFragmentDisplayed){
                mButton.setText(R.string.close);
            }
            else{
                mButton.setText(R.string.open);
            }
        }

        mButton=(Button) findViewById(R.id.open_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : llamada a un nuevo fragmento
                if(!isFragmentDisplayed) displayNewFragment();
                else closeFragment();
            }
        });

        Button btnVotos =(Button)findViewById(R.id.btnVerVotos);
        btnVotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("votos1",String.valueOf(votos1));
                intent.putExtra("votos2",String.valueOf(votos2));
                startActivity(intent);
            }
        });

        Button btnVotar =(Button)findViewById(R.id.btnEmitirVoto);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(mRadioButtonChoice==1){
                   votos2++;
                   Toast.makeText(getApplicationContext(), "Usted acaba de votar por "+ getString(R.string.title2), Toast.LENGTH_SHORT).show();
               }else if(mRadioButtonChoice==0){
                   Toast.makeText(getApplicationContext(), "Usted acaba de votar por "+ getString(R.string.title1), Toast.LENGTH_SHORT).show();
                   votos1++;
               }
            }
        });
    }

    public void displayNewFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        //TODO : obtener el FragmentManager e iniciar la transaccion
        //TODO : Agregar el Fragment
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment)
                .addToBackStack(null)
                .commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed=true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager= getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if(simpleFragment!=null){
            fragmentManager.beginTransaction()
                    .remove(simpleFragment)
                    .commit();
            mButton.setText(R.string.open);
            isFragmentDisplayed=false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        ImageView imageView= (ImageView)findViewById(R.id.imageView);
        TextView title = (TextView)findViewById(R.id.title);
        TextView description =(TextView)findViewById(R.id.description);

        mRadioButtonChoice=choice;
        if(mRadioButtonChoice==0){
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.avengers_endgame));
            title.setText(R.string.title1);
            description.setText(R.string.article1);

        }else if(mRadioButtonChoice==1){
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.avengers_infinity));
            title.setText(R.string.title2);
            description.setText(R.string.article2);
        }
    }
}