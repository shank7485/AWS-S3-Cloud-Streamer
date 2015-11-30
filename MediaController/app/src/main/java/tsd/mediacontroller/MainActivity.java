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

    EditText WebApp;
    EditText Renderer;

    Button categories;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WebApp = (EditText) findViewById(R.id.webapp);
        Renderer = (EditText) findViewById(R.id.renderer);

        categories = (Button) findViewById(R.id.categories);

        categories.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                IPWebApp = WebApp.getText().toString().trim();
                IPRenderer = Renderer.getText().toString().trim();

                if(IPRenderer.isEmpty() || IPWebApp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter IP",Toast.LENGTH_LONG).show();
                }

                else{
                    Intent category = new Intent(MainActivity.this, category.class);
                    startActivity(category);
                }
            }

        });
    }

}
