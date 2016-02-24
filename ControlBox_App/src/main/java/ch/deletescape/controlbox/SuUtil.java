package ch.deletescape.controlbox;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

/**
 * This is supposed to be a small utility to make it easier to work with root access!
 * Created by Deletescape(Till Kottmann) on 20/02/16.
 * All rights reserved.
 */
public class SuUtil {
    private static final boolean IS_ROOTED() {
        return Shell.SU.available();
    }

    public static void doReboot(Context context, RebootType rebootType) {
        /**
         * Reboots the device according to the selected RebootType
         */
        if (IS_ROOTED()) {
            switch (rebootType) {
                case REBOOT_NORMAL:
                    Shell.SU.run("reboot");
                    break;
                case REBOOT_SOFT:
                    Shell.SU.run("pkill zygote");
                    break;
                case RESTART_SYSTEMUI:
                    Shell.SU.run("pkill com.android.systemui");
                    break;
                case SHUTDOWN:
                    Shell.SU.run("reboot -p");
                    break;
                case REBOOT_RECOVERY:
                    Shell.SU.run("reboot recovery");
                    break;
            }
        } else {
            noRootToast(context);
        }
    }

    public static int getDensity() {
        /*String temp = Shell.SH.run("wm density").toArray()[1].toString();
        return temp;*/
        try {
            String temp = Shell.SH.run("wm density").toArray()[1].toString();
            return Integer.parseInt(temp.substring(temp.indexOf("Override density:") + 18));
        } catch (Exception e) {
            String temp = Shell.SH.run("wm density").toArray()[0].toString();
            return Integer.parseInt(temp.substring(temp.indexOf("Physical density:") + 18));
        }
    }

    public static void setDensity(Context context, int dpi) {
        if (IS_ROOTED()) {
            Shell.SU.run("wm density " + dpi);
        } else {
            noRootToast(context);
        }
    }

    public static ArrayList<String[]> getUsers(Context context) {
        if (IS_ROOTED()) {
            ArrayList<String[]> userList = new ArrayList<>();
            List<String> tempList = Shell.SU.run("pm list users");
            Object[] temp = tempList.toArray();
            for (int i = 1; i < temp.length; i++) {
                String userTemp = temp[i].toString();
                String uId = userTemp.substring(userTemp.indexOf("{") + 1, userTemp.indexOf(":"));
                String uName = userTemp.substring(userTemp.indexOf(":") + 1, userTemp.lastIndexOf(":"));
                String uCurrent = userTemp.contains("running") ? "true" : "false";
                String[] user = new String[]{uId, uName, uCurrent};
                userList.add(user);

            }
            return userList;
        } else {
            noRootToast(context);
        }
        return new ArrayList<>();
    }

    public static void switchUser(Context context, int uId) {
        if (IS_ROOTED()) {
            Shell.SU.run("am switch-user " + uId);
        } else {
            noRootToast(context);
        }
    }

    private static void noRootToast(Context context) {
        Toast.makeText(context, R.string.no_root_msg, Toast.LENGTH_LONG).show();
    }

    public enum RebootType {
        /**
         * This enumerator is used to define how you want to reboot when using ${doReboot}
         */
        REBOOT_NORMAL, REBOOT_SOFT, RESTART_SYSTEMUI, SHUTDOWN, REBOOT_RECOVERY
    }

}
