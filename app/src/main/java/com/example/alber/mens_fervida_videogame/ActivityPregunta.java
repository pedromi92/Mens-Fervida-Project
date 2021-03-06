package com.example.alber.mens_fervida_videogame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alber.mens_fervida_videogame.entidades.Jugador;
import com.example.alber.mens_fervida_videogame.entidades.Pregunta;

/**
 * Created by PedroMiguel on 01/02/2017.
 */

public class ActivityPregunta extends Activity implements View.OnClickListener{
    public int puntuacionNivel;
    private final int PREGUNTA_ACERTADA_MULT =10;
    private final int TIMER_A_CERO=60;
    private final int PREGUNTA_POR_NIVEL=20;
    private final int SEGUNDOS_TOTAL_MS=60000;
    private final int TIEMPO_RESTA_PUNTI=1000;
    public int puntosSegundos;
    public int vidas;
    public int nivel;
    public int numeroPregunta;
    TextView timerDisplay;
    Dialog diaPregunta;
    Pregunta pregunta;
    CountDownTimer timer;
    TextView puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        puntuacionNivel=0;
        numeroPregunta=1;
        vidas=6;
        Bundle bundle=getIntent().getExtras();
        nivel=bundle.getInt("nivel");
        requestWindowFeature(Window.FEATURE_NO_TITLE);//Línea para ocultar el titulo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Línea para ocultar la barra de información de la batería,etc...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_palabra_comp);
        puntuacion=(TextView) findViewById(R.id.text_puntuacion) ;
        puntuacion.setText(String.format("%d",Jugador.getInstance().getPuntuacion()));
        iniciarContador();
        nuevaPregunta();

    }

    private void iniciarContador() {
        puntosSegundos=TIMER_A_CERO;
        if(timer==null) {
            timer = new CountDownTimer(SEGUNDOS_TOTAL_MS, TIEMPO_RESTA_PUNTI) {
                @Override
                public void onTick(long l) {
                    puntosSegundos--;
                    timerDisplay=(TextView)findViewById(R.id.text_timer);
                    timerDisplay.setText(String.format("00:%02d",puntosSegundos));
                }

                @Override
                public void onFinish() {
                    diaPregunta.dismiss();
                    setResult(RESULT_CANCELED, null);
                    finish();
                }
            }.start();
        }
    }

    private void nuevaPregunta() {
        pregunta=new Pregunta(nivel);
        diaPregunta=new DialogPregPalComp(this, R.style.AppTheme, this);
        iniciarContador();
        timer.start();
        diaPregunta.show();
    }
    public void preguntaAcertada(){
        puntuacionNivel=puntuacionNivel+ nivel*PREGUNTA_ACERTADA_MULT+puntosSegundos;
        puntosSegundos=TIMER_A_CERO;
        puntuacion.setText(String.format("%d",puntuacionNivel));
        timer.cancel();
        if(numeroPregunta>=PREGUNTA_POR_NIVEL){
            finalizaNivelOk();
        }
        else{
            diaPregunta.dismiss();
            nuevaPregunta();
            numeroPregunta++;
        }
    }
    public void preguntaFallada(){
        vidas--;
        puntosSegundos=TIMER_A_CERO;
        timer.cancel();
        switch (vidas){
            case 5:
                LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linear_vida_6);
                linearLayout.setVisibility(View.INVISIBLE);
                nuevaPregunta();
                break;
            case 4:
                linearLayout = (LinearLayout) findViewById(R.id.linear_vida_5);
                linearLayout.setVisibility(View.INVISIBLE);
                nuevaPregunta();
                break;
            case 3:
                linearLayout = (LinearLayout) findViewById(R.id.linear_vida_4);
                linearLayout.setVisibility(View.INVISIBLE);
                nuevaPregunta();
                break;
            case 2:
                linearLayout = (LinearLayout) findViewById(R.id.linear_vida_3);
                linearLayout.setVisibility(View.INVISIBLE);
                nuevaPregunta();
                break;
            case 1:
                linearLayout=(LinearLayout) findViewById(R.id.linear_vida_2);
                linearLayout.setVisibility(View.INVISIBLE);
                nuevaPregunta();
                break;
            case 0:
                linearLayout=(LinearLayout) findViewById(R.id.linear_vida_1);
                linearLayout.setVisibility(View.INVISIBLE);
                setResult(RESULT_CANCELED, null);
                finish();
                break;
        }
    }
    public void finalizaNivelOk(){
        Jugador.getInstance().setPuntuacion(Jugador.getInstance().getPuntuacion()+puntuacionNivel);
        Intent intent= new Intent();
        intent.putExtra("nivelFinalizado",nivel);
        setResult(RESULT_OK, intent);
        diaPregunta.dismiss();
        this.finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

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
