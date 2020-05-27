package com.devmobile.ecoleenligne;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import Model.Eleve;
import Model.Progression;


public class PdfView extends Fragment implements OnPageChangeListener {
    private PDFView pdfView;
    private ImageView retour;
    private String chapitreNom;
    private String URL="https://www.lyceedadultes.fr/sitepedagogique/documents/math/mathTermS/01_rappels_suites_algorithme/01_cours_rappels_suites_algorithme.pdf";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pdfviewer,container,false);
        ((dashboard)getActivity()).selectedFromRetour(2);
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

    @Override
    public void onPageChanged(int page, int pageCount) {
        Log.v("Page Changed, Actual",page+"");
        if (page+1==pageCount){
            if (!chapitreDejaLu(chapitreNom,dashboard.eleve.getProgression()))
                updateChapitresLus(dashboard.eleve,dashboard.type,chapitreNom);
        }
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
         pdfView.fromStream(inputStream).onPageChange(PdfView.this).load();
        }

    }
    public void setUrl(String URL){
        this.URL=URL;
    }
    public boolean chapitreDejaLu(String nom, Progression progression){
        ArrayList<String> chapitreslus=progression.getChapitresLus();
        for (String chapitre :chapitreslus) {
            if(nom.matches(chapitre)) return true;
        }
        return false;
    }
    public void setChapitreNom(String titre){
        this.chapitreNom=titre;
    }
    public void updateChapitresLus(final Eleve eleve, String type,String chapitreNom){
        eleve.getProgression().ajouterChapitre(chapitreNom);
        FirebaseDatabase.getInstance().getReference(type)
                .child(String.valueOf(eleve.getId())).setValue(eleve);
    }
}
