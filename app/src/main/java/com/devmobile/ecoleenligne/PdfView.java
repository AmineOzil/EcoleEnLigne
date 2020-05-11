package com.devmobile.ecoleenligne;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class PdfView extends Fragment {
    private PDFView pdfView;
    private ImageView retour;
    private String URL="https://www.lyceedadultes.fr/sitepedagogique/documents/math/mathTermS/01_rappels_suites_algorithme/01_cours_rappels_suites_algorithme.pdf";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pdfviewer,container,false);
        pdfView= view.findViewById(R.id.pdfview);
        retour=view.findViewById(R.id.retour_pdf);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        new RetrievePDFStream().execute(URL);
        return view;
    }
    class RetrievePDFStream extends AsyncTask<String,Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try{
                URL url=new URL(strings[0]);
                Log.v("URL:",url+"");
                HttpsURLConnection urlConnection=(HttpsURLConnection) url.openConnection();
                Log.v("Réponse",urlConnection.getResponseCode()+"");
                if(urlConnection.getResponseCode()==200){
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());

                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }
        @Override
        protected void onPostExecute(InputStream inputStream){
         pdfView.fromStream(inputStream).load();
        }

    }
    public void setUrl(String URL){
        this.URL=URL;
    }
}
