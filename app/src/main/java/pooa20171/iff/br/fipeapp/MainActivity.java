package pooa20171.iff.br.fipeapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    TextView webService;
    String uri = "http://fipeapi.appspot.com/api/1/carros/marcas.json";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = URI.create(uri).toString();
        String resultado= "";
        webService = (TextView) findViewById(R.id.tvWebService);

        BuscaTask busca = new BuscaTask(url,null,resultado);
        busca.execute();

    }

    public class BuscaTask extends AsyncTask<String, Void, String>{

        ProgressDialog dialog;

        public BuscaTask(String url, Object o, String resultado) {
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(String jsonObject) {

            if (jsonObject != null) {
                webService.setText(jsonObject);
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            return HTTPUtils.acessar(url);
        }
    }
}
