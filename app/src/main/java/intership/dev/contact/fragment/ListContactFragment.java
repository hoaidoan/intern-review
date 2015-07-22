package intership.dev.contact.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.ContactModel;
import intership.dev.contact.widget.LoadMoreListView;

/**
 * Created by hoai on 22/07/2015.
 */
public class ListContactFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mListContact = inflater.inflate(R.layout.fragment_list_contact, container, false);
        lvContact = (LoadMoreListView) mListContact.findViewById(R.id.lvContact);
        for (int i = 0; i < mNames.length; i++) {
            ContactModel item = new ContactModel(mNames[i], mDescriptions[i], mAvatars[i]);
            mContacts.add(item);
        }

        mContactAdapter = new ContactAdapter(getActivity(), mContacts);

        lvContact.setAdapter(mContactAdapter);
        lvContact = (LoadMoreListView) mListContact.findViewById(R.id.lvContact);
        for (int i = 0; i < mNames.length; i++) {
            ContactModel item = new ContactModel(mNames[i], mDescriptions[i], mAvatars[i]);
            mContacts.add(item);
        }
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                EditContactFragment frag = new EditContactFragment();
                transaction.replace(getId(), frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mContactAdapter = new ContactAdapter(getActivity(), mContacts);

        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });


        return mListContact;

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
