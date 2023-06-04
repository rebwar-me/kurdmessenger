package com.rai.kurdmessenger.recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rai.kurdmessenger.GetContactActivity;
import com.rai.kurdmessenger.R;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.db.smsDB;
import com.rai.kurdmessenger.log.L;

import java.util.ArrayList;

/**
 * Created by DS on 01/01/2009.
 */

public class CodeListAdapter extends RecyclerView.Adapter<CodeListAdapter.MyViewHolder> {

    //contains the list of movies
    private ArrayList<CodeModel> code = new ArrayList<>();
    private LayoutInflater mInflater;



    //formatter for parsing the dates in the specified format below
//keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;
    Context context;
    smsDB db;


    public CodeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setCodeList(ArrayList<CodeModel> code) {
        this.code = code;
        //update the adapter to reflect the new set of movies
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, code.size());
    }
    @Override
    public CodeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cods_row, parent, false);

        return new CodeListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CodeListAdapter.MyViewHolder holder, final int position) {
        final CodeModel CODE = code.get(position);
        holder.txtcreatedcode.setText("" + CODE.getCode());

        holder.imgdeletcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setCancelable(true);
                alert.setMessage("این کد حذف شود؟");
                alert.setPositiveButton(" بله ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (MyApplication.getWritableDatabase().deleteCode(CODE.getCode()) > 0) {
                                    L.t(MyApplication.getAppContext(), "حذف شد");
                                    // MyApplication.getWritableDatabase().setDelFlag("1");
                                    code.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();

                                } else {
                                    L.t(MyApplication.getAppContext(), "حذف نشد");
                                }
                                L.t(MyApplication.getAppContext(), "حذف شد");
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
        ////////////////////////////////////////////////////deletecode
        //////////////////////////////////////////////////addcon
        holder.imgcodecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,GetContactActivity.class);
                i.putExtra("code",CODE.getCode());
                i.putExtra("send","0");
                context.startActivity(i);

            }
        });
        ///////////////////////////////////////////////////addcon
        ////////////////////////////////////////////////////sendcode
        holder.imgsendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, GetContactActivity.class);
                i.putExtra("code",CODE.getCode());
                i.putExtra("send","1");
                context.startActivity(i);

            }
        });
        ////////////////////////////////////////////////////sendcode
        ////////////////////////////////////////////////////sendcode
    }

    @Override
    public int getItemCount() {
        return code.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgdeletcode, imgcodecontact, imgsendcode;

        private TextView txtcreatedcode;


        public MyViewHolder(View itemview) {
            super(itemview);

            txtcreatedcode = (TextView) itemview.findViewById(R.id.txtcreatedcode);
            imgdeletcode = (ImageView) itemview.findViewById(R.id.imgdeletcode);
            imgcodecontact = (ImageView) itemview.findViewById(R.id.imgcodecontact);
            imgsendcode = (ImageView) itemview.findViewById(R.id.imgsendcode);

            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
//////////////////////////////////////////////////////////////////////////////////////
