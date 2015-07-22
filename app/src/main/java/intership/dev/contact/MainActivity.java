package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;


import intership.dev.contact.fragment.ListContactFragment;


public class MainActivity extends ActionBarActivity {
    private LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContactsFragment();

    }

    //    function display fragment ListContact
    private void addContactsFragment() {
        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListContactFragment frag = new ListContactFragment();
        transaction.add(R.id.llContainer, frag);
        transaction.commit();
    }


}
