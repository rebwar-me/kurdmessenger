package com.rai.kurdmessenger.recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rai.kurdmessenger.R;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.log.L;

import java.util.ArrayList;

/**
 * Created by DS on 10/25/2017.
 */

public class ContactByCodeAdapter  extends RecyclerView.Adapter<ContactByCodeAdapter.MyViewHolder> {

    //contains the list of movies
    private ArrayList<CodeContacModel> code = new ArrayList<>();
    private LayoutInflater mInflater;


    //formatter for parsing the dates in the specified format below
//keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;
    Context context;


    public ContactByCodeAdapter(Context context ) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setContactByCodeList(ArrayList<CodeContacModel> code) {
        this.code = code;
        //update the adapter to reflect the new set of movies
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, code.size());
    }

    @Override
    public ContactByCodeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contact_codes_folder_row, parent, false);

        return new ContactByCodeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactByCodeAdapter.MyViewHolder holder, final int position) {
        final CodeContacModel co = code.get(position);


        holder.contact_name_code_folder.setText(co.getConname());
        holder.num_phone_folder.setText(co.getPhone());
        holder.delete_contact_name_code_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder( context );
                alert.setCancelable( true );
                alert.setMessage("این مخاطب حذف شود؟");
                alert.setPositiveButton(" بله ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(MyApplication.getWritableDatabase().deleteContactByCode(co.getId())>0){
                                    L.t(MyApplication.getAppContext(), "حذف شد");
                                    // MyApplication.getWritableDatabase().setDelFlag("1");
                                    code.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();

                                }else {
                                    L.t(MyApplication.getAppContext(), "حذف نشد");
                                }
                                L.t(MyApplication.getAppContext(),"حذف شد");
                                dialog.dismiss();
                            }
                        }
                );

                alert.setNeutralButton(" خیر ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                );
                alert.create();
                alert.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return code.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView contact_name_code_folder,num_phone_folder;
        private ImageView delete_contact_name_code_folder,upic;


        public MyViewHolder(View itemview) {
            super(itemview);

            delete_contact_name_code_folder = (ImageView) itemview.findViewById(R.id.delete_contact_name_code_folder);
            upic = (ImageView) itemview.findViewById(R.id.upic);
            contact_name_code_folder = (TextView) itemview.findViewById(R.id.contact_name_code_folder);
            num_phone_folder = (TextView) itemview.findViewById(R.id.num_phone_folder);

            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}