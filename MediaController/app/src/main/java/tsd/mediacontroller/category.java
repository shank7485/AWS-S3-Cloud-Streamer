package tsd.mediacontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Shashank on 11/29/2015.
 */
public class category extends Activity{

    Button music, movies, others;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        music = (Button) findViewById(R.id.music);
        movies = (Button) findViewById(R.id.movies);
        others = (Button) findViewById(R.id.others);

        music.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent music = new Intent(category.this, getMediaList.class);
                music.putExtra("value", "Music");
                startActivity(music);
            }
        });

        movies.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent movies = new Intent(category.this, getMediaList.class);
                movies.putExtra("value", "Movies");
                startActivity(movies);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent others = new Intent(category.this, getMediaList.class);
                others.putExtra("value","Others");
                startActivity(others);
            }

        });
    }

    @Override
    public void onBackPressed(){
        backButtonHandler();
    }

    public void backButtonHandler(){
        Intent BackToMain = new Intent(category.this, MainActivity.class);
        startActivity(BackToMain);
    }

}
