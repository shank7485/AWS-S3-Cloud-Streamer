package tsd.mediacontroller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Shashank on 11/14/2015.
 */
public class getMediaList extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        final String value = getIntent().getStringExtra("value");
        String IPofWebApp = MainActivity.IPWebApp;
        final String URL = "https://" + IPofWebApp +":8443/WebApp/rest/category/parameters?folder=" + value;
        ArrayList<String> list = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Wrapper wrap = new Wrapper(URL,value,list);
        new BackGroundHTTP2(wrap,getMediaList.this).execute();

    }

    public class Wrapper {

        public String URL;
        public String value;
        public ArrayList<String> list;

        public Wrapper(String URL, String value, ArrayList<String> list){
            this.value = value;
            this.URL = URL;
            this.list = list;
        }

    }

    private class BackGroundHTTP2 extends AsyncTask<Void, Void, Wrapper>{
        private ProgressDialog dialog;

        private Wrapper wrap;

        BackGroundHTTP2(Wrapper wrap, getMediaList activity){
            this.wrap = wrap;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Fetching List from AWS S3..");
            dialog.show();
        }

        @Override
        protected Wrapper doInBackground(Void... param) {

            String output = null;
            ArrayList<String> list = new ArrayList<>();

            String value = wrap.value;
            String URL = wrap.URL;

            SSLSocketFactory sslSocketFactory = null;

            try {
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }
                        }
                };

                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                sslSocketFactory = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            OkHttpClient client = new OkHttpClient();

            client.setSslSocketFactory(sslSocketFactory);

            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            Request request = new Request.Builder().url(URL).build();

            try {

                Response response = client.newCall(request).execute();
                Log.d("TestHttp", "OkHttp-Selected-Protocol: " + response.header("OkHttp-Selected-Protocol"));
                output = response.body().string();
                JSONObject jObject = new JSONObject(output);
                JSONArray jArray = jObject.getJSONArray("ListOfItemsinFolder");
                for (int i = 0; i < jArray.length(); i++) {
                    list.add(jArray.optString(i));

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Wrapper w = new Wrapper(value, URL, list);
            return w;

        }

        protected void onPostExecute(Wrapper w){

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            ArrayList<String> list = w.list;
            final String value = w.value;

            final ListView listView = (ListView) findViewById(R.id.list);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMediaList.this,android.R.layout.simple_list_item_1,android.R.id.text1,list);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    String[] parts = value.split("=");
                    String NewitemValue = parts[1] + "/" + itemValue;

                    Intent player = new Intent(getMediaList.this, player.class);
                    player.putExtra("value2", NewitemValue);
                    startActivity(player);

                }

            });

        }
    }

    @Override
    public void onBackPressed(){
        backButtonHandler();
    }

    public void backButtonHandler(){
        Intent BackToCategories = new Intent(getMediaList.this, category.class);
        startActivity(BackToCategories);
    }

}
