package patricia.deguzman.com.deguzman_practicalexam2;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText eFirstName, eLastName, eExam1, eExam2, eAverage;
    TextView tFirstName, tLastName, tExam1, tExam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eFirstName = findViewById(R.id.etFirstName);
        eLastName = findViewById(R.id.etLastName);
        eExam1 = findViewById(R.id.etExam1);
        eExam2 = findViewById(R.id.etExam2);
        eAverage = findViewById(R.id.etAverage);


        tFirstName = findViewById(R.id.etFirstName);
        tLastName = findViewById(R.id.etLastName);
        tExam1 = findViewById(R.id.etExam1);
        tExam2 = findViewById(R.id.etExam2);
    }

    public void saveData(View v ){
        String firstName = eFirstName.getText().toString();
        String lastName = eLastName.getText().toString();
        String exam1 = eExam1.getText().toString();
        String exam2 = eExam2.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("fname", firstName);
        writer.putString("lname", lastName);
        writer.putString("ex1", exam1);
        writer.putString("ex2", exam2);
        writer.commit();
        Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
    }


    public void saveExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();

        String efname = eFirstName.getText().toString();
        String elname = eLastName.getText().toString();
        Integer eex1 = Integer.parseInt(eExam1.getText().toString());
        Integer eex2 = Integer.parseInt(eExam2.getText().toString());
        Integer average = (eex1 + eex2)/2;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            fos.write(efname.getBytes());
            fos.write(elname.getBytes());
            fos.write(String.valueOf(average).getBytes());
            Toast.makeText(this, "Data saved in sd card", Toast.LENGTH_LONG).show();
            fos.close();
            fin = new FileInputStream(file);
            while((c = fin.read())!=-1){
                buffer.append((char)c);
            }
            eAverage.setText(buffer.toString());
        }
        catch(Exception e){
            Toast.makeText(this, "Error writing on sd card", Toast.LENGTH_LONG).show();
        }
    }

    public void loadData (View V){
        SharedPreferences pref = getSharedPreferences("Data1", MODE_PRIVATE);
        String fname = pref.getString("fname",null);
        String lname = pref.getString("lname",null);
        String ex1 = pref.getString("ex1",null);
        String ex2 = pref.getString("ex2",null);
        tFirstName.setText(fname);
        tLastName.setText(lname);
        tExam1.setText(ex1);
        tExam2.setText(ex2);
    }
}
