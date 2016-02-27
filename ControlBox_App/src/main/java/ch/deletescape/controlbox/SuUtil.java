package ch.deletescape.controlbox;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.chainfire.libsuperuser.Shell;

/**
 * This is supposed to be a small utility to make it easier to work with root access!
 *
 * @author Deletescape
 * @version 1.2.0
 * @since 20/02/16
 */
public class SuUtil {
    public enum rebootType {
        /**
         * This enumerator is used to define how you want to reboot
         */
        REBOOT_NORMAL, REBOOT_SOFT, RESTART_SYSTEMUI, SHUTDOWN, REBOOT_RECOVERY
    }

    public static boolean IS_ROOTED() {
        return Shell.SU.available();
    }

    public static void doReboot(Context context, rebootType rebootType) {
        /**
         * Reboots the device according to the selected {@link SuUtil.rebootType}
         */
        if (IS_ROOTED()) {
            String temp = null;
            switch (rebootType) {
                case REBOOT_NORMAL:
                    temp = "reboot";
                    break;
                case REBOOT_SOFT:
                    temp = "pkill zygote";
                    break;
                case RESTART_SYSTEMUI:
                    temp = "pkill com.android.systemui";
                    break;
                case SHUTDOWN:
                    temp = "reboot -p";
                    break;
                case REBOOT_RECOVERY:
                    temp = "reboot recovery";
                    break;
            }
            Shell.SU.run(temp);
        } else {
            noRootToast(context);
        }
    }

    public static int getDensity() {
        Object[] temp = Shell.SH.run("wm density").toArray();
        try {
            return Integer.parseInt(temp[1].toString().substring(temp[1].toString().indexOf("Override density:") + 18));
        } catch (Exception e) {
            return Integer.parseInt(temp[0].toString().substring(temp[0].toString().indexOf("Physical density:") + 18));
        }
    }

    public static void setDensity(Context context, int dpi) {
        if (IS_ROOTED()) {
            Shell.SU.run(String.format("wm density %s", dpi));
        } else {
            noRootToast(context);
        }
    }

    public static ArrayList<Object[]> getUsers(Context context) {
        if (IS_ROOTED()) {
            ArrayList<Object[]> userList = new ArrayList<>();
            List<String> tempList = Shell.SU.run("pm list users");
            Object[] temp = tempList.toArray();
            for (int i = 1; i < temp.length; i++) {
                String userTemp = temp[i].toString();
                Integer uId = Integer.parseInt(userTemp.substring(userTemp.indexOf("{") + 1, userTemp.indexOf(":")));
                String uName = userTemp.substring(userTemp.indexOf(":") + 1, userTemp.lastIndexOf(":"));
                Boolean uCurrent = userTemp.contains("running");

                userList.add(new Object[]{uId,uName,uCurrent});
            }
            return userList;
        } else {
            noRootToast(context);
            return new ArrayList<>();
        }
    }

    public static void switchUser(Context context, int uId) {
        if (IS_ROOTED()) {
            Shell.SU.run(String.format(Locale.US,"am switch-user %d", uId));
        } else {
            noRootToast(context);
        }
    }
    public static void removeUser(Context context, int uId) {
        if (IS_ROOTED()) {
            Shell.SU.run(String.format(Locale.US,"pm remove-user %d", uId));
        } else {
            noRootToast(context);
        }
    }
    public static void addUser(Context context, String name) {
        if (IS_ROOTED()) {
            Shell.SU.run(String.format(Locale.US,"pm create-user \"%s\"", name));
        } else {
            noRootToast(context);
        }
    }
    public static void noRootToast(Context context) {
        Toast.makeText(context, R.string.no_root_msg, Toast.LENGTH_LONG).show();
    }

}
