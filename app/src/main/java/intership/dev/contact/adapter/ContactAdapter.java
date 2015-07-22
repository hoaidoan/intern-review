package intership.dev.contact.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.model.ContactModel;
import intership.dev.contact.widget.DeleteDialog;

/**
 * Create Adapter for Listview Contacts
 */
public class ContactAdapter extends BaseAdapter implements DeleteDialog.OnClickContactDialog, DialogInterface.OnDismissListener {
    private Context mContext;
    private ArrayList<ContactModel> mContacts = new ArrayList<>();
    private DeleteDialog dialog;

    /**
     * Constructor
     *
     * @param mContext
     * @param mContacts
     */
    public ContactAdapter(Context mContext, ArrayList<ContactModel> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
        dialog = new DeleteDialog(mContext);
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
        setValue(holder, position);
        setEvent(holder, position);
        //       holder.tvName.setText(mContacts.get(position).getName());
        //       holder.imgAvatar.setImageResource(mContacts.get(position).getAvatar());
//        /**
//         * Listener for imageView Delete
//         */
//        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog mDialogDelete = new Dialog(mContext, R.style.CustomThemeDialog);
//                mDialogDelete.setContentView(R.layout.dialog_custom_delete);
//                TextView tvMessenger = (TextView) mDialogDelete.findViewById(R.id.tvMessenger);
//                tvMessenger.setText(Html.fromHtml("Are you sure you want to edit " + "<b>" +
//                        mContacts.get(position).getName() + "</b>" + "?"));
//                Button btnOk = (Button) mDialogDelete.findViewById(R.id.btnOk);
//                btnOk.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mContacts.remove(position);
//                        notifyDataSetChanged();
//                        mDialogDelete.dismiss();
//                    }
//                });
//                Button btnCancel = (Button) mDialogDelete.findViewById(R.id.btnCancel);
//                btnCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mDialogDelete.dismiss();
//                    }
//                });
//                mDialogDelete.show();
//            }
//
//        });
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
        ContactModel model = (ContactModel) getItem(position);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setPosition(position);
                dialog.show();
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Updating", Toast.LENGTH_LONG).show();
            }
        });
    }
}
