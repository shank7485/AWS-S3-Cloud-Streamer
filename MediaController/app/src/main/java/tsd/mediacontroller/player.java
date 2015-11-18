package tsd.mediacontroller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Shashank on 11/15/2015.
 */
public class player extends Activity{

    Button play, pause, rewind, forward, resume, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        final String value2 = getIntent().getStringExtra("value2");
        String IPofRenderer = MainActivity.IPRenderer;
        final String URL2 = "http://" + IPofRenderer + ":8080/vlc/rest/vlc/parameters?URL"+"="+"http://s3-us-west-1.amazonaws.com/shank7485/" + value2;

        //Toast.makeText(getApplicationContext(), value2, Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        rewind = (Button) findViewById(R.id.rewind);
        forward = (Button) findViewById(R.id.forward);
        resume = (Button) findViewById(R.id.resume);
        stop = (Button) findViewById(R.id.stop);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing" + value2, Toast.LENGTH_SHORT).show();
                String URLPlay = URL2 + "&code=start";
                new BackgroundHTTP2().execute(URLPlay);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Paused" , Toast.LENGTH_SHORT).show();
                String URLPause = URL2 + "&code=pause";
                new BackgroundHTTP2().execute(URLPause);

            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Rewind" , Toast.LENGTH_SHORT).show();
                String URLRewind = URL2 + "&code=rewind";
                new BackgroundHTTP2().execute(URLRewind);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forward" , Toast.LENGTH_SHORT).show();
                String URLForward = URL2 + "&code=forward";
                new BackgroundHTTP2().execute(URLForward);
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resumed" , Toast.LENGTH_SHORT).show();
                String URLResume = URL2 + "&code=resume";
                new BackgroundHTTP2().execute(URLResume);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Stopped" , Toast.LENGTH_SHORT).show();
                String URLStop = URL2 + "&code=stop";
                new BackgroundHTTP2().execute(URLStop);
            }
        });
    }

    private class BackgroundHTTP2 extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... param){
        String URL = param[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(URL).build();
            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
        }
    }
}
