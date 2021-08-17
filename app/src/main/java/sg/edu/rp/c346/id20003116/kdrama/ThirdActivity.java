package sg.edu.rp.c346.id20003116.kdrama;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etKDramaID, etKDramaName, etKDramaDesc, etKDramaCast, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rbStars;
    KDrama kdrama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etKDramaID = findViewById(R.id.etID);
        etKDramaName = findViewById(R.id.etName);
        etKDramaDesc = findViewById(R.id.etDescription);
        etKDramaCast = findViewById(R.id.etCast);
        etYear = findViewById(R.id.etYear);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        rbStars = findViewById(R.id.ratingBarStars);

        Intent i = getIntent();
        kdrama = (KDrama) i.getSerializableExtra("kdrama");

        etKDramaID.setText(kdrama.getId() + "");
        etKDramaName.setText(kdrama.getName());
        etKDramaDesc.setText(kdrama.getDesc());
        etKDramaCast.setText(kdrama.getCast());
        etYear.setText(kdrama.getYearReleased() + "");
        rbStars.setRating((float)kdrama.getStars());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island\n" + kdrama.getName());
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        dbh.deleteKDrama(kdrama.getId());
                        finish();
                    }
                });
                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                kdrama.setName(etKDramaName.getText().toString().trim());
                kdrama.setDesc(etKDramaDesc.getText().toString().trim());
                kdrama.setCast(etKDramaCast.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                kdrama.setYearReleased(year);

                kdrama.setStars((int) rbStars.getRating());
                int result = dbh.updateKDrama(kdrama);
                if (result > 0){
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                myBuilder.setPositiveButton("Do Not Discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}