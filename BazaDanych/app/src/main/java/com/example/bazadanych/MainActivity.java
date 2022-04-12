package com.example.bazadanych;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText make, model, year;
    Button insert, update, delete, view, clear;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dekalaracja zmiennych i powiazania z obiektami
        make = findViewById(R.id.editMake);
        model = findViewById(R.id.editModel);
        year = findViewById(R.id.editYear);
        insert = findViewById(R.id.buttonInsert);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
        view = findViewById(R.id.buttonView);
        clear = findViewById(R.id.buttonClear);
        DB = new DBHelper(this);

        //listener, obsugujacy przycisk umozliwiajacy wstawianie elementow do bazy

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makeTXT = make.getText().toString();
                String modelTXT = model.getText().toString();
                String yearTXT = year.getText().toString();

                Boolean checkInsertData = DB.insertCar(makeTXT, modelTXT, yearTXT);
                if(checkInsertData==true)
                    Toast.makeText(MainActivity.this, "New car inserted successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "!!!New car not inserted!!!", Toast.LENGTH_SHORT).show();
            }        });

        //listener, obslugujacy aktualizowanie istniejacych obiektow w bazie, id to marka pojazdu
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makeTXT = make.getText().toString();
                String modelTXT = model.getText().toString();
                String yearTXT = year.getText().toString();

                Boolean checkUpdateData = DB.updateCar(makeTXT, modelTXT, yearTXT);
                if(checkUpdateData==true) //komunikat toast w zaleznosci od powodzenia aktualizacji danych
                    Toast.makeText(MainActivity.this, "Car data updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "!!!Car data not updated!!!", Toast.LENGTH_SHORT).show();
            }        });

        //listener, obslugujacy usuwanie istniejacych w bazie wpisow na podstawie id, czyli marki pojazdu
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makeTXT = make.getText().toString();
                Boolean checkDeleteData = DB.deleteCar(makeTXT);
                if(checkDeleteData==true) //komunikat toast w zaleznosci od powodzenia akcji usuwania
                    Toast.makeText(MainActivity.this, "Entry deleted successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "!!!Entry not deleted!!!", Toast.LENGTH_SHORT).show();
            }        });

        //listener obslugujacy podglad obiektow w bazie danych
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0){ //jezeli baza pusta
                    Toast.makeText(MainActivity.this, "!!!Database empty!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //wyswietlenie obiektow
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Make :"+res.getString(0)+"\n");
                    buffer.append("Model :"+res.getString(1)+"\n");
                    buffer.append("Year of production :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Cars");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        //listener obslugujacy przycisk clear, do szybkiego wyczyszczenia editTextow w aplikacji
        clear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                make.getText().clear();
                model.getText().clear();
                year.getText().clear();
            }
        });
    }}