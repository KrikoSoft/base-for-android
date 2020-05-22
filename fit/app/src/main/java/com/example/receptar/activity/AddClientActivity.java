package com.example.receptar.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receptar.R;

import java.util.Objects;

public class AddClientActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "";

    private EditText editTextSurname;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextActualWeight;
    private EditText editTextSex;
    private EditText editTextWorkId;
    private EditText editTextNote;
    private EditText editTextBve;
    private EditText editTextVefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        editTextSurname = findViewById(R.id.edit_text_surname);
        editTextName = findViewById(R.id.edit_text_name);
        editTextAge = findViewById(R.id.edit_text_age);
        editTextHeight = findViewById(R.id.edit_text_height);
        editTextWeight = findViewById(R.id.edit_text_weight);
        editTextActualWeight = findViewById(R.id.edit_text_actual_weight);
        editTextSex = findViewById(R.id.edit_text_sex);
        editTextWorkId = findViewById(R.id.edit_text_work_id);
        editTextNote = findViewById(R.id.edit_text_note);
        editTextBve = findViewById(R.id.edit_text_bve);
        editTextVefa = findViewById(R.id.edit_text_vefa);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.icon_close);
        setTitle("Pridať klienta");
    }

    private void saveClient() {
        String surname = editTextSurname.getText().toString();
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String height = editTextHeight.getText().toString();
        String weight = editTextWeight.getText().toString();
        String actualWeight = editTextActualWeight.getText().toString();
        String sex = editTextSex.getText().toString();
        String workId = editTextWorkId.getText().toString();
        String note = editTextNote.getText().toString();
        String bve = editTextBve.getText().toString();
        String vefa = editTextVefa.getText().toString();

        if (checkStringEmpty(surname) || checkStringEmpty(name) || checkStringEmpty(age)
                || checkStringEmpty(height) || checkStringEmpty(weight) || checkStringEmpty(sex)
                || checkStringEmpty(workId) || checkStringEmpty(bve) || checkStringEmpty(vefa)) {
            Toast.makeText(this, "Zadajte informácie o klientovi!", Toast.LENGTH_LONG).show();
            return;
        }

        if (checkStringEmpty(actualWeight)) {
            actualWeight = "0";
        }
    }

    private boolean checkStringEmpty(String string) {
        return string.trim().isEmpty();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_client) {
            saveClient();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
