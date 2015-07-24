package intership.dev.contact.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.ContactModel;

/**
 * Created by hoai on 23/07/2015.
 */
public class DeleteDialog extends Dialog implements View.OnClickListener {
    private TextView mMessage;
    private Button mBtnOK;
    private Button mBtnCancel;
    private int mPosition;
    private OnClickContactDialog mListener;


    public DeleteDialog(Context context) {
        super(context, R.style.CustomThemeDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_delete);
        init();

    }


    private void init(){
        mMessage = (TextView) findViewById(R.id.tvMessenger);
        mBtnOK = (Button) findViewById(R.id.btnOk);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

    }

    /**
     * set message for dialog with Model name is bold
     * @param contactModel
     */
    public void setDialogMessage(ContactModel contactModel) {
        mMessage.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" +
                contactModel.getName() + "</b>" + "?"));
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    public void setOnClickListViewContactListener(OnClickContactDialog listener) {
        mListener = listener;
    }


    @Override
    public void onClick(View v) {
        if (mListener == null) {
            return;
        }
        int id = v.getId();
        if (id == mBtnOK.getId()) {
            mListener.onClickBtnOK(v);
        }
        if (id == mBtnCancel.getId()) {
            mListener.onClickBtnCancel(v);
        }


    }

    public interface OnClickContactDialog {
        /**
         * Called when a button OK has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnOK(View v);

        /**
         * Called when the button Cancel has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnCancel(View v);
    }
}
