package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        final TextView CREDITS_TEXT = (TextView) findViewById(R.id.credits_text);
        CREDITS_TEXT.setText(CreditsText.DELETESCAPE_PART);
    }
}
