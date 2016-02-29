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

        final TextView TXT_DPI = (TextView) findViewById(R.id.dpiDisplay);
        final EditText EDIT_DPI = (EditText) findViewById(R.id.txt_dpi);

        EDIT_DPI.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SuUtil.setDensity(v.getContext(), Integer.parseInt(EDIT_DPI.getText().toString()));
                    handled = true;
                }
                return handled;
            }
        });

        int density = SuUtil.getDensity();

        TXT_DPI.setText(String.format(getString(R.string.dpi_display), density));
        EDIT_DPI.setText(String.format(getString(R.string.integerString), density));


    }

    public void setDPI(View view) {
        final EditText EDIT_DPI = (EditText) findViewById(R.id.txt_dpi);

        SuUtil.setDensity(view.getContext(), Integer.parseInt(EDIT_DPI.getText().toString()));
    }

    public void resetDPI(View view) {
        SuUtil.setDensity(view.getContext(),SuUtil.getPhysicalDensity());
    }
}
