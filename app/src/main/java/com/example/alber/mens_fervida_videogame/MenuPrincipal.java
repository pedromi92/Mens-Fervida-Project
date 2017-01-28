package com.example.alber.mens_fervida_videogame;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuPrincipal extends Activity {
    private Button buttonPlay,buttonOptions,buttonArcade,buttonExit;
    LinearLayout relativeLayout;
    private TextView titulo;
    private dialogoOpciones dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//Línea para ocultar el titulo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Línea para ocultar la barra de información de la batería,etc...
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_principal);
        buttonPlay = (Button)findViewById(R.id.bt_jugar);
        buttonOptions = (Button)findViewById(R.id.bt_opciones);
        buttonExit = (Button)findViewById(R.id.bt_salir);
        buttonArcade = (Button)findViewById(R.id.bt_arcade);
        //buttonPlay.setTextSize(30);
        //buttonOptions.setTextSize(30);
        //buttonExit.setTextSize(30);
        buttonPlay.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chewy.ttf"));
        buttonOptions.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chewy.ttf"));
        buttonExit.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chewy.ttf"));
        buttonArcade.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chewy.ttf"));
        relativeLayout=(LinearLayout) findViewById(R.id.activity_main);

    }

    public void jugar(View view){
        Toast.makeText(this, "Entra", Toast.LENGTH_SHORT);

    }

    public void opciones(View view){
        Dialog();
    }


    public void salir(View view){
        finish();
    }

    private void Dialog() {    String gender;

    // creating a dialog object and specifying the activity it pops on .
    // R.style.FullHeightDialog specifies that dialog box has height
    // equivalent to screen height

        final dialogoOpciones dialog = new dialogoOpciones(MenuPrincipal.this,R.style.AppTheme, this);

        //dialog.setContentView(R.layout.layout_dialog_inicio);//setting the dialog xml layout

        /* adding action when image buttons of dialog are clicked */
        /*dialog.findViewById(R.id.ImgBtn_id).setOnClickListener(
                new OnClickListener() {
                    public void onClick(View v) {
                        //put your code here
                        dialog.dismiss();//closes the dialog box
                    }
                });*/

        dialog.show();//pops the dialog box

    }





}


