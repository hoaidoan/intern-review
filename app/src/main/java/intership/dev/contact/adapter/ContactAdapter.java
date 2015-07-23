package intership.dev.contact.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.fragment.EditContactFragment;
import intership.dev.contact.model.ContactModel;
import intership.dev.contact.widget.DeleteDialog;

/**
 * Create Adapter for Listview Contacts
 */
public class ContactAdapter extends BaseAdapter implements DeleteDialog.OnClickContactDialog,
        DialogInterface.OnDismissListener {
    private FragmentActivity mActivity;
    private ArrayList<ContactModel> mContacts = new ArrayList<>();
    private DeleteDialog dialog;

    /**
     * Constructor
     *
     * @param mActivity
     * @param mContacts
     */
    public ContactAdapter(FragmentActivity mActivity, ArrayList<ContactModel> mContacts) {
        this.mActivity = mActivity;
        this.mContacts = mContacts;
        dialog = new DeleteDialog(mActivity);
        dialog.setOnClickListViewContactListener(this);
        dialog.setOnDismissListener(this);

    }

    @Override
    public void onClickBtnOK(View v) {
        mContacts.remove(dialog.getPosition());
        notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void onClickBtnCancel(View v) {
        dialog.dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }


    /**
     * create ViewHolder class
     */
    static class ViewHolder {
        ImageView imgAvatar, imgDelete, imgEdit;
        TextView tvName;
        TextView tvDesc;
    }


    /**
     * @param position    position of ArrayList<ContactModel> mContacts
     * @param convertView View of item in ListView
     * @param viewGroup   View Parent of convertView
     * @return convertView
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_list_contact, viewGroup, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setValue(holder, position);
        setEvent(holder, position);

        return convertView;
    }

    /**
     * @return count of arrayList
     */
    @Override
    public int getCount() {
        return mContacts.size();
    }

    /**
     * @param position
     * @return Object possition
     */
    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    /**
     * Don't use
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void setValue(ViewHolder holder, int position) {
        ContactModel model = (ContactModel) getItem(position);
        holder.tvName.setText(model.getName());
        holder.imgAvatar.setImageResource(model.getAvatar());
    }

    private void setEvent(final ViewHolder holder, final int position) {
        final ContactModel model = (ContactModel) getItem(position);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setPosition(position);
                dialog.show();
                dialog.setDialogMessage(model);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivity, "Updating", Toast.LENGTH_LONG).show();
            }
        });
    }
}
