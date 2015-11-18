package tsd.mediacontroller;

/**
 * Created by Shashank on 11/14/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

    public static String IPWebApp;
    public static String IPRenderer;

    Button music, movies, educational;
    EditText WebApp;
    EditText Renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        music = (Button) findViewById(R.id.music);
        movies = (Button) findViewById(R.id.movies);
        educational = (Button) findViewById(R.id.others);

        WebApp = (EditText) findViewById(R.id.webapp);
        Renderer = (EditText) findViewById(R.id.renderer);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPWebApp = WebApp.getText().toString();
                IPRenderer = Renderer.getText().toString();

                if(IPRenderer.isEmpty() || IPWebApp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter IP",Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Music Selected",Toast.LENGTH_LONG).show();

                    Intent music = new Intent(MainActivity.this, getMediaList.class);
                    music.putExtra("value", "Music");
                    startActivity(music);
                }
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPWebApp = WebApp.getText().toString();
                IPRenderer = Renderer.getText().toString();

                if(IPRenderer.isEmpty() || IPWebApp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter IP",Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Movies Selected",Toast.LENGTH_LONG).show();

                    Intent movies = new Intent(MainActivity.this, getMediaList.class);
                    movies.putExtra("value", "Movies");
                    startActivity(movies);
                }
            }
        });

        educational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPWebApp = WebApp.getText().toString();
                IPRenderer = Renderer.getText().toString();

                if(IPRenderer.isEmpty() || IPWebApp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter IP",Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Others Selected",Toast.LENGTH_LONG).show();

                    Intent educational  = new Intent(MainActivity.this, getMediaList.class);
                    educational.putExtra("value", "Others");
                    startActivity(educational);
                }
            }
        });

    }

}
