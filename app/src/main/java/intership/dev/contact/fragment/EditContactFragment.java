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

/**
 * Created by hoai on 22/07/2015.
 */
public class EditContactFragment extends Fragment {
    ImageView imgAvatar;
    TextView tvName;
    EditText edtDescription;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mEditView = inflater.inflate(R.layout.fragment_custom_edit,container,false);
        return mEditView;
    }

    void init(View v){
        imgAvatar =(ImageView)v.findViewById(R.id.imgAvatar);
        tvName =(TextView)v.findViewById(R.id.tvName);
        edtDescription =(EditText)v.findViewById(R.id.edtDesc);
        btnSave =(Button)v.findViewById(R.id.btnSave);
        btnCancel=(Button)v.findViewById(R.id.btnCancel);

    }
}
