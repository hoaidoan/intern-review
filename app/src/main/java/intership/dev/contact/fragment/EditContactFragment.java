package intership.dev.contact.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intership.dev.contact.R;

/**
 * Created by hoai on 22/07/2015.
 */
public class EditContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mEditView = inflater.inflate(R.layout.fragment_custom_edit,container,false);
        return mEditView;
    }
}
