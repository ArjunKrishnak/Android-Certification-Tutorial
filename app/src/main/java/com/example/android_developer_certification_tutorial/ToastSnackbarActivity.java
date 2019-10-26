package com.example.android_developer_certification_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ToastSnackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_toast );
    }

    public void triggerDefaultToast(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration );
        toast.setGravity( Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }
    public void triggerCustomToast(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate( R.layout.custom_toast,
                (ViewGroup) findViewById( R.id.custom_toast_container ) );

        TextView text = (TextView) layout.findViewById( R.id.text );
        text.setText( "This is a custom toast" );

        Toast toast = new Toast( getApplicationContext() );
        toast.setGravity( Gravity.CENTER_VERTICAL, 0, 0 );
        toast.setDuration( Toast.LENGTH_LONG );
        toast.setView( layout );
        toast.show();
    }

    public void triggerSnackbar(View view){
//        Snackbar snackbar = Snackbar
//                .make(view, "www.journaldev.com", Snackbar.LENGTH_SHORT); //No action
        Snackbar snackbar = Snackbar
                .make(view, "www.journaldev.com", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar2 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                snackbar2.show();
            }
        });
        snackbar.show();
    }

    public void triggerCustomSnackbar(View view) {
        Snackbar snackbar = Snackbar
                .make(view, "Try again!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.setActionTextColor( Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


}

