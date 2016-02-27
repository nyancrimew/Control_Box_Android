package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MultiUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_user);

        final GridView GRID_USERS = (GridView) findViewById(R.id.usersGrid);
        ArrayList<String> usersList = new ArrayList<>();
        ArrayList<Object[]> usersRaw = SuUtil.getUsers(GRID_USERS.getContext());


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
        final EditText EDIT_REMOVE_UID = (EditText) findViewById(R.id.txt_remove_uid);
        SuUtil.removeUser(view.getContext(), Integer.parseInt(EDIT_REMOVE_UID.getText().toString()));
        refreshActivity();
    }

    private void refreshActivity() {
        finish();
        startActivity(getIntent());
    }
}
