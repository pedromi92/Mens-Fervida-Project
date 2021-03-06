package com.example.alber.mens_fervida_videogame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuArcades extends Activity implements View.OnClickListener{
    Dialog juego1, juego2, juego3;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//Línea para ocultar el titulo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Línea para ocultar la barra de información de la batería,etc...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcades);
        volver=(Button)findViewById(R.id.btn_atras_juegos);
        volver.setOnClickListener(this);
        cargarPaneles();
    }

    private void cargarPaneles() {
        juego1 = new DialogJuegoUno(this,R.style.AppTheme, this);
        juego2 = new DialogJuegoDos(this,R.style.AppTheme, this);
        juego3 = new DialogJuegoTres(this,R.style.AppTheme, this);
        juego1.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_atras_juegos:
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }
}
