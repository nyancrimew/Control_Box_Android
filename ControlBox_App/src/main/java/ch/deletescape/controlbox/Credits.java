package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        InputStream is = this.getResources().openRawResource(R.raw.license);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuffer text = new StringBuffer("");
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.credits_text)).setText(text);
    }
}
