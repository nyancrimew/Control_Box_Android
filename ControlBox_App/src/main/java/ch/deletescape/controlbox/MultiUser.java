package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MultiUser extends AppCompatActivity {
    private ArrayList<Object[]> usersRaw = SuUtil.getUsers(this.getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_user);

        final GridView GRID_USERS = (GridView) findViewById(R.id.usersGrid);
        final Spinner EDIT_REMOVE_UID = (Spinner) findViewById(R.id.txt_remove_uid);
        ArrayList<String> usersList = new ArrayList<>();


        usersList.add(getString(R.string.table_header_uid));
        usersList.add(getString(R.string.table_header_uname));
        usersList.add(getString(R.string.table_header_iscurrent));

        for (Object[] user : usersRaw) {
            usersList.add(user[0].toString());
            usersList.add(user[1].toString());
            usersList.add(user[2].toString());
        }

        GRID_USERS.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                usersList));


        ArrayList<String> usersList2 = new ArrayList<>();

        for (Object[] user : usersRaw) {
            usersList2.add(String.format("%s - %s", user[0].toString(), user[1].toString()));
        }
        ArrayAdapter<String> spinner_user_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usersList2);

        EDIT_REMOVE_UID.setAdapter(spinner_user_adapter);

    }

    public void switchUser(View view) {
        final EditText EDIT_UID = (EditText) findViewById(R.id.txt_uid);
        SuUtil.switchUser(view.getContext(), Integer.parseInt(EDIT_UID.getText().toString()));
    }

    public void addUser(View view) {
        final EditText EDIT_ADD_USER = (EditText) findViewById(R.id.txt_add_user);
        SuUtil.addUser(view.getContext(), EDIT_ADD_USER.getText().toString());
        refreshActivity();
    }

    public void removeUser(View view) {
        final Spinner EDIT_REMOVE_UID = (Spinner) findViewById(R.id.txt_remove_uid);
        String selected = EDIT_REMOVE_UID.getSelectedItem().toString();
        SuUtil.removeUser(view.getContext(), Integer.parseInt(selected.substring(0, selected.indexOf(' '))));
        refreshActivity();
    }

    private void refreshActivity() {
        finish();
        startActivity(getIntent());
    }
}
