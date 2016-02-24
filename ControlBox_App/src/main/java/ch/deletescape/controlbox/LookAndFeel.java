package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LookAndFeel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_and_feel);

        TextView dpiDisplay = (TextView) findViewById(R.id.dpiDisplay);
        int density = SuUtil.getDensity();
        dpiDisplay.setText(String.format(getString(R.string.dpi_display), density));
        final EditText txtDPI = (EditText) findViewById(R.id.txt_dpi);
        txtDPI.setText(String.format(getString(R.string.integerString), density));
        txtDPI.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SuUtil.setDensity(v.getContext(), Integer.parseInt(txtDPI.getText().toString()));
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void setDPI(View view) {
        EditText txtDPI = (EditText) findViewById(R.id.txt_dpi);
        SuUtil.setDensity(view.getContext(), Integer.parseInt(txtDPI.getText().toString()));
    }
}
