package intership.dev.contact;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;


import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.ContactModel;
import intership.dev.contact.widget.LoadMoreListView;


public class MainActivity extends Activity {
    private String[] mNames = new String[]{"Strawberry",
            "Banana", "Orange", "Mixed", "Abbott", "Abraham", "Alvin", "Dalton", "Gale", "Halsey", "Isaac", "Philbert"};
    private String[] mDescriptions = new String[]{
            "Beckham", "Rooney", "Ronaldo", "Messi", "Robben", "Cassilas", "Suarez", "Zidane", "Figo", "Carlos", "Maria", "Idol"};
    private int[] mAvatars = {R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4};
    LoadMoreListView lvContact;
    private ArrayList<ContactModel> mContacts = new ArrayList<>();
    private ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (LoadMoreListView) findViewById(R.id.lvContact);
        for (int i = 0; i < mNames.length; i++) {
            ContactModel item = new ContactModel(mNames[i], mDescriptions[i], mAvatars[i]);
            mContacts.add(item);
        }

        mContactAdapter = new ContactAdapter(MainActivity.this, mContacts);

        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // add Loadmore Item
            for (int i = 0; i < mNames.length; i++) {
                ContactModel item = new ContactModel(mNames[i], mDescriptions[i], mAvatars[i]);
                mContacts.add(item);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            mContactAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((LoadMoreListView) lvContact).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((LoadMoreListView) lvContact).onLoadMoreComplete();
        }
    }

}
