package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Reboot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reboot);
    }

    public void rebootNormal(View view) {
        SuUtil.doReboot(view.getContext(), SuUtil.RebootType.REBOOT_NORMAL);
    }

    public void rebootRecovery(View view) {
        SuUtil.doReboot(view.getContext(), SuUtil.RebootType.REBOOT_RECOVERY);
    }

    public void rebootSoft(View view) {

        SuUtil.doReboot(view.getContext(), SuUtil.RebootType.REBOOT_SOFT);
    }

    public void restartSystemUI(View view) {
        SuUtil.doReboot(view.getContext(), SuUtil.RebootType.RESTART_SYSTEMUI);
    }

    public void Shutdown(View view) {
        SuUtil.doReboot(view.getContext(), SuUtil.RebootType.SHUTDOWN);
    }
}
