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
try {
    ArrayList<String> usersList = new ArrayList<>();
    ArrayList<String[]> usersRaw = SuUtil.getUsers(GRID_USERS.getContext());


    usersList.add(getString(R.string.table_header_uid));
    usersList.add(getString(R.string.table_header_uname));
    usersList.add(getString(R.string.table_header_iscurrent));

    for (String[] user : usersRaw) {
        usersList.add(user[0]);
        usersList.add(user[1]);
        usersList.add(user[2]);
    }

    GRID_USERS.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            usersList));
} catch (NullPointerException e){
    //NullPointerException due to null returned
    //TODO: Well.. Returning null is not so beautiful
}
    }

    public void switchUser(View view) {
        final EditText EDIT_UID = (EditText) findViewById(R.id.txt_uid);
        SuUtil.switchUser(view.getContext(), Integer.parseInt(EDIT_UID.getText().toString()));
    }
}
