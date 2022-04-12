package com.example.activityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dekklaracja obiektow
        webview = (WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        searchView = (SearchView)findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                webview.loadUrl("https://www.google.com/search?q=" + searchView.getQuery()); //URL googlea do wprowadzenia wyszukiwania
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        Button button = (Button) findViewById(R.id.button);  //zmienna przycisku uruchamiajacego przekazanie wartosci (logowanie)
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity2();
            }  //listener dla przycisku
        });
    }

    public void openActivity2(){ //funkcja zmieniajaca activity
        //otwieramy activity2
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }
}