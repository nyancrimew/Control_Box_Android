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
        GridView userGrid = (GridView) findViewById(R.id.usersGrid);
        ArrayList<String[]> temp = SuUtil.getUsers(userGrid.getContext());
        ArrayList<String> usersList = new ArrayList<>();
        usersList.add(getString(R.string.table_header_uid));
        usersList.add(getString(R.string.table_header_uname));
        usersList.add(getString(R.string.table_header_iscurrent));
        for (String[] i : temp) {
            String uId = i[0];
            String uName = i[1];
            String isCurrent = i[2];
            usersList.add(uId);
            usersList.add(uName);
            usersList.add(isCurrent);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                usersList);

        userGrid.setAdapter(arrayAdapter);
    }

    public void switchUser(View view) {
        EditText txtUid = (EditText) findViewById(R.id.txt_uid);
        SuUtil.switchUser(view.getContext(), Integer.parseInt(txtUid.getText().toString()));
    }
}
