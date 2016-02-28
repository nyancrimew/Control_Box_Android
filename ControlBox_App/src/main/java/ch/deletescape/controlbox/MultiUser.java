package ch.deletescape.controlbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MultiUser extends AppCompatActivity {
    private ArrayList<Object[]> usersRaw = SuUtil.getUsers(this.getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_user);
        final Spinner EDIT_REMOVE_UID = (Spinner) findViewById(R.id.spinner_user);


        ArrayList<String> usersList = new ArrayList<>();

        for (Object[] user : usersRaw) {
            usersList.add(String.format("%s - %s", user[0].toString(), user[1].toString()));
        }
        ArrayAdapter<String> spinner_user_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usersList);

        EDIT_REMOVE_UID.setAdapter(spinner_user_adapter);

    }

    public void switchUser(View view) {
        final Spinner SPINNER_USER = (Spinner) findViewById(R.id.spinner_user);
        String selected = SPINNER_USER.getSelectedItem().toString();
        SuUtil.switchUser(view.getContext(), Integer.parseInt(selected.substring(0, selected.indexOf(' '))));
    }

    public void addUser(View view) {
        final EditText EDIT_ADD_USER = (EditText) findViewById(R.id.txt_add_user);
        SuUtil.addUser(view.getContext(), EDIT_ADD_USER.getText().toString());
        refreshActivity();
    }

    public void removeUser(View view) {
        final Spinner SPINNER_USER = (Spinner) findViewById(R.id.spinner_user);
        String selected = SPINNER_USER.getSelectedItem().toString();
        SuUtil.removeUser(view.getContext(), Integer.parseInt(selected.substring(0, selected.indexOf(' '))));
        refreshActivity();
    }

    private void refreshActivity() {
        finish();
        startActivity(getIntent());
    }
}
