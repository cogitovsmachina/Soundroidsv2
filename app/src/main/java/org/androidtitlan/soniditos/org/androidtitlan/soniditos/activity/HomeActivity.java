package org.androidtitlan.soniditos.org.androidtitlan.soniditos.activity;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.Menu;
import android.view.MenuInflater;

import org.androidtitlan.soniditos.R;


public class HomeActivity extends ActionBarActivity {
    private Integer NumBotones;
    private TableLayout tableLayout;
    ArrayList<View> rowList = new ArrayList<View>();
    private Button boton[];
    private Integer audio[] = {R.raw.grillos, R.raw.drama, R.raw.chewbacca,
            R.raw.nooo, R.raw.doh, R.raw.jaja,
            R.raw.triste, R.raw.trololo, R.raw.sparta,
            R.raw.zelda, R.raw.benny_hill, R.raw.badbrums,
            R.raw.bazzinga, R.raw.r2d2, R.raw.birdtheword,
            R.raw.inetporn, R.raw.kamehame, R.raw.burned,
            R.raw.chan, R.raw.chan_doble, R.raw.evillaugh,
            R.raw.aleluya, R.raw.keyboardcat, R.raw.lalala,
            R.raw.mario, R.raw.beisball, R.raw.muppets,
            R.raw.penny, R.raw.shhahh, R.raw.pacman,
    };

    private MediaPlayer mediaPlayer = null;
    Integer lastItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setIcon(R.drawable.logo_soundroids);
        NumBotones = audio.length;
        boton = new Button[NumBotones];
        tableLayout = (TableLayout) findViewById(R.id.gridView);
        TableRow row = new TableRow(this);
        lastItem = -1;
        int i;
        //TODO: Change this implementation into a GridLayout instead.
        for (i = 0; i < NumBotones; i++) {
            //Incializando todos los botones
            boton[i] = new Button(this);
            boton[i].setWidth(200);
            boton[i].setHeight(200);
            boton[i].setTextColor(getResources().getColor(R.color.actionbar_text));
            boton[i].setTextSize(14);
            boton[i].setText(getResources().getStringArray(R.array.nombre_botones)[i]);

            final int tmp = i;
            boton[i].setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    playSound(tmp, audio[tmp]);
                    lastItem = tmp;
                }
            });
            row.addView(boton[i]);

            if ((i + 1) % 3 == 0) {
                tableLayout.addView(row);
                row = new TableRow(this);
            }
        }

        if ((i + 1) % 3 != 0) tableLayout.addView(row);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) stopSound();
    }

    private void playSound(int i, int sonido) {
        if (lastItem == i && mediaPlayer != null && mediaPlayer.isPlaying())    //si el boton anterior y al actual son iguales
            stopSound();
        else {                //si el boton anterior y al actual son diferentes
            stopSound();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), sonido);
            mediaPlayer.start();
        }
    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /*Creando el Option Menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}