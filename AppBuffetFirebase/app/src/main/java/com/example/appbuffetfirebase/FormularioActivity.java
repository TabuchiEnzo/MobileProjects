package com.example.appbuffetfirebase;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FormularioActivity extends AppCompatActivity {
    EditText formNome, formNomeResponsavel, formTelefone, formDtNasc;
    RatingBar formNota;
    Button btnSalvar, btnCancel;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formNome = findViewById(R.id.nomeCrianca);
        formNomeResponsavel = findViewById(R.id.nomeResponsavel);
        formTelefone = findViewById(R.id.telefone);
        formDtNasc = findViewById(R.id.dtNasc);
        formNota = findViewById(R.id.pontos);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancel = findViewById(R.id.btnCancel);

        // Configura o DatePickerDialog para o campo de data de nascimento
        formDtNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormularioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formDtNasc.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}