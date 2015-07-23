package intership.dev.contact.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.ContactModel;

/**
 * Created by hoai on 22/07/2015.
 * display all information of listview item
 */
public class EditContactFragment extends Fragment implements View.OnClickListener {
    private ImageView imgAvatar;
    private TextView tvName;
    private EditText edtDescription,edtName;
    private Button btnSave, btnCancel;
    private OnChangeItemListener mListenerOnChange;

    //using for method onClick
    private ContactModel mModel;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mEditView = inflater.inflate(R.layout.fragment_custom_edit, container, false);
        init(mEditView);
        return mEditView;
    }

    void init(View v) {
        imgAvatar = (ImageView) v.findViewById(R.id.imgAvatar);
        tvName = (TextView) v.findViewById(R.id.tvName);
        edtDescription = (EditText) v.findViewById(R.id.edtDesc);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        edtName = (EditText)v.findViewById(R.id.edtName);

        // give data from ListContactFragment
        Bundle dataBundle = this.getArguments();
        mModel = (ContactModel) dataBundle.getSerializable("dataBundle");
        mPosition= dataBundle.getInt("position");
        tvName.setText(mModel.getName());
        imgAvatar.setImageResource(mModel.getAvatar());
        edtDescription.setText(mModel.getDescription());
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSave:
                mModel.setName(edtName.getText().toString());
                mModel.setDescription(edtDescription.getText().toString());
                mListenerOnChange.onChange(mModel, mPosition);
                getActivity().onBackPressed();
                break;
            case R.id.btnCancel:
                getActivity().onBackPressed();
        }
    }

    /**
     * Register a callback to be invoked when a changed user was given from
         * EditContactFragment to ListContactFragment
     *
     * @param listener The callback will run
     */
    public void setOnChangeItemListener(OnChangeItemListener listener) {
        mListenerOnChange = listener;
    }

    public interface OnChangeItemListener {
        void onChange(ContactModel contactModelmodel, int position);
    }
}
