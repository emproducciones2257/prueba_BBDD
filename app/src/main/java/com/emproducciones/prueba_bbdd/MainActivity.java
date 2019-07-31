package com.emproducciones.prueba_bbdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsertar, btnActualizar, btnBuscar, btnBorrar;

    EditText edtId, edtNombre,edtApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar= (Button) findViewById(R.id.btnInsertar);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        btnBuscar= (Button) findViewById(R.id.btnBuscar);
        btnBorrar= (Button) findViewById(R.id.btnBorrar);

        edtId = (EditText) findViewById(R.id.edtID);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);

        final BBDD_Helper helper = new BBDD_Helper(this);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA1, edtId.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, edtNombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3, edtApellido.getText().toString());

                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(),"Registro cargado correctamente",Toast.LENGTH_SHORT).show();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
                String[] projection = {

                        Estructura_BBDD.NOMBRE_COLUMNA2,
                        Estructura_BBDD.NOMBRE_COLUMNA3
                };

// Filter results WHERE "title" = 'My Title'
                String selection = Estructura_BBDD.NOMBRE_COLUMNA1 + " = ?";
                String[] selectionArgs = { edtId.getText().toString() };

// How you want the results sorted in the resulting Cursor
                /*String sortOrder =
                        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/

                Cursor cursor = db.query(
                        Estructura_BBDD.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        null               // The sort order
                );

                //cursor.moveToFirst();

                if (cursor.moveToFirst()){

                    edtNombre.setText(cursor.getString(0));
                    edtApellido.setText(cursor.getString(1));
                }else {

                    Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();

                }




            }
        });


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


    }
}
