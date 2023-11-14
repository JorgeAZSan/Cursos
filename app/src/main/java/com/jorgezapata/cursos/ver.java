package com.jorgezapata.cursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ver extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("SELECT * FROM records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int curso = c.getColumnIndex("curso");
        int costo = c.getColumnIndex("costo");
        titulos.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titulos);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<student>();

        if (c.moveToFirst());
        {
            do {
                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.curso = c.getString(curso);
                stu.costo = c.getString(costo);

                stud.add(stu);

                titulos.add(c.getString(id) + "\t " + c.getString(name) + " \t " + c.getString(curso) + "\t " + c.getString(costo));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();

        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa = titulos.get(position).toString();

                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(),edit.class);

                i.putExtra("id",stu.id);
                i.putExtra("name",stu.name);
                i.putExtra("curso",stu.curso);
                i.putExtra("costo",stu.costo);
                startActivity(i);








            }
        });


    }
}