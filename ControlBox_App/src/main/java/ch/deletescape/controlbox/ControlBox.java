package ch.deletescape.controlbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new AppUpdater(this.getApplicationContext())
                .setUpdateFrom(UpdateFrom.GITHUB)
                .setGitHubUserAndRepo("deletescape", "Control_Box_Android")
                .setDisplay(Display.NOTIFICATION)
                .showAppUpdated(false)
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
