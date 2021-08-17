package sg.edu.rp.c346.id20003116.kdrama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowList, btnInsert;
    EditText etName, etDesc, etCast, etYear;
    //RadioGroup rgRating;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDescription);
        etCast = findViewById(R.id.etCast);
        etYear = findViewById(R.id.etYear);
        //rgRating = findViewById(R.id.star);
        rb = findViewById(R.id.ratingBarStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String cast = etCast.getText().toString().trim();
                if (name.length() == 0 || desc.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String km_str = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(km_str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid area", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertKDrama(name, desc, cast, year, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etName.setText("");
                etDesc.setText("");
                etCast.setText("");
                etYear.setText("");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    private int getStars() {
        int stars = 1;
        stars =(int)rb.getRating();
        return stars;
    }
}