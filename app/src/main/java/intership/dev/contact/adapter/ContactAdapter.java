package intership.dev.contact.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.model.ContactModel;

/**
 * Create Adapter for Listview Contacts
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ContactModel> mContacts = new ArrayList<>();

    /**
     * Constructor
     *
     * @param mContext
     * @param mContacts
     */
    public ContactAdapter(Context mContext, ArrayList<ContactModel> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_contact, viewGroup, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mContacts.get(position).getName());
        holder.imgAvatar.setImageResource(mContacts.get(position).getAvatar());
        /**
         * Listener for imageView Delete
         */
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delete")
                        .setMessage(Html.fromHtml("Are you sure yout want to delete " + "<b>" + mContacts.get(position).getName() + "</b>" + " ?"))
                        .setCancelable(false)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mContacts.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder.create().show();

            }
        });
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
}
