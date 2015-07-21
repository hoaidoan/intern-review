package intership.dev.contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.ContactModel;


public class MainActivity extends Activity {
    public static final String[] NAME = new String[]{"Strawberry",
            "Banana", "Orange", "Mixed", "Abbott", "Abraham", "Alvin", "Dalton", "Gale", "Halsey", "Isaac", "Philbert"};
    public static final String[] DESCRIPTION = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m"};
    public static final Integer[] AVATAR = {R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4};
    ListView lvContact;
    ArrayList<ContactModel> mContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (ListView) findViewById(R.id.lvContact);
        for (int i = 0; i < NAME.length; i++) {
            ContactModel item = new ContactModel(NAME[i], DESCRIPTION[i], AVATAR[i]);
            mContacts.add(item);
        }
        ContactAdapter mContactAdapter = new ContactAdapter(MainActivity.this, mContacts);
        lvContact.setAdapter(mContactAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
