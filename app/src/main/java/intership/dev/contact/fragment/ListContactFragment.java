package intership.dev.contact.fragment;

import android.content.res.TypedArray;
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
 * Fragment ListView Contact to dis play listview on screen
 */
public class ListContactFragment extends Fragment implements AdapterView.OnItemClickListener, LoadMoreListView.OnLoadMoreListener {
    LoadMoreListView lvContact;
    private ArrayList<ContactModel> mContacts = new ArrayList<>();
    private ContactAdapter mContactAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mListContact = inflater.inflate(R.layout.fragment_list_contact, container, false);
        init(mListContact);
        createDefaultData();
        mContactAdapter = new ContactAdapter(getActivity(), mContacts);
        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(this);
        lvContact.setOnItemClickListener(this);
        return mListContact;

    }


    // Listener for  onclick Item
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        EditContactFragment frag = new EditContactFragment();
        transaction.replace(getId(), frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Init fo first load listview
    public void init(View v) {
        lvContact = (LoadMoreListView) v.findViewById(R.id.lvContact);
    }

    // Generate data from first load listview
    public void createDefaultData() {
        String[] mNames = getResources().getStringArray(R.array.list_name);
        TypedArray mAvatars = getResources().obtainTypedArray(R.array.list_avatar);
        String[] mDescriptions = getResources().getStringArray(R.array.list_description);
        for (int i = 0; i < mNames.length; i++) {
            ContactModel mModel = new ContactModel(mNames[i], mDescriptions[i], mAvatars.getResourceId(i, 0));
            mContacts.add(mModel);
        }
    }

    //  Load more lisview
    @Override
    public void onLoadMore() {
        new LoadDataTask().execute();
    }

    //  Class Load new data when listview go to end row
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
            createDefaultData();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            mContactAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            lvContact.onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            lvContact.onLoadMoreComplete();
        }
    }
}
