package diego.salcedo.readermanager.ui.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import diego.salcedo.readermanager.R;

public class LogActivity extends AppCompatActivity {

    private Button sendMail;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Text that shows the log data
        textView = findViewById(R.id.debugTextView);

        // Send the log data using mailing
        sendMail = findViewById(R.id.bSendMail);
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText = textView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"diego.salcedo@seresco.es"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "RFID LOG");
                intent.putExtra(Intent.EXTRA_TEXT, enteredText);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        showLogs();
    }

    private void showLogs() {
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }
            textView.setText(log.toString());
        } catch (IOException e) {
            // Handle Exception
        }
    }
}