package ch.deletescape.controlbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

public class ControlBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_box);
        new AppUpdater(this.getBaseContext())
                .setUpdateFrom(UpdateFrom.GITHUB)
                .setGitHubUserAndRepo("deletescape", "Control_Box_Android")
                .setDisplay(Display.SNACKBAR)
                .showAppUpdated(true)
                .start();
    }

    public void startRebooter(View view) {
        startActivity(new Intent(ControlBox.this, Reboot.class));
    }

    public void startLookAndFeel(View view) {
        startActivity(new Intent(ControlBox.this, LookAndFeel.class));
    }

    public void startMultiuser(View view) {
        startActivity(new Intent(ControlBox.this, MultiUser.class));
    }

    public void credits(View view) {
        startActivity(new Intent(ControlBox.this, Credits.class));
    }
}
