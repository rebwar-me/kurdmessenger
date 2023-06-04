package com.rai.kurdmessenger.recycler;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rai.kurdmessenger.R;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.db.smsDB;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.sms.SMS;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created Created by @rebwar_me.
 */

public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MyViewHolder> {

    //contains the list of movies
    private ArrayList<MsgModel> msgs = new ArrayList<>();
    private LayoutInflater mInflater;
    public ProgressDialog prDialog;
    String formattedDate;
    String sms;
    CardView msgdelete,msgcopy,msgresend;


    //formatter for parsing the dates in the specified format below
//keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;
    Context context;
    smsDB db;
    private HashMap<String,Object> user;
    int i=0,j=0;

    public MsgListAdapter(Context context ) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMsgList(ArrayList<MsgModel> msgs) {
        this.msgs = msgs;
        //update the adapter to reflect the new set of movies
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, msgs.size());
    }

    @Override
    public MsgListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.msg_row, parent, false);

        return new MsgListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MsgListAdapter.MyViewHolder holder, final int position) {
        final MsgModel msg = msgs.get(position);


        holder.txtmsg.setText(msg.getmsg());
        holder.txttime.setText(msg.gettime());
        holder.txtmsgy.setText(msg.getmsg());
        holder.txttimey.setText(msg.gettime());

        if(msg.getHow().equals("0"))
        {
            holder.txtusetn.setText(" You ");
            holder.msgview.setVisibility(View.GONE);
            holder.msgviewy.setVisibility(View.VISIBLE);

        }else if(msg.getHow().equals("1")){
            holder.txtusetn.setText(msg.getName());
            // holder.msgviewy.setVisibility(View.GONE);
            holder.msgview.setVisibility(View.VISIBLE);
            holder.msgviewy.setVisibility(View.GONE);
        }else {}
        if(msg.gettahvil().equals("1")) {
            holder.tahvil.setImageResource(R.drawable.sending);
            holder.tahvil.setVisibility(View.VISIBLE);
            holder.sent.setImageResource(R.drawable.sent);
            holder.sent.setVisibility(View.VISIBLE);
        }
        else if(msg.gettahvil().equals("2")) {
            holder.tahvil.setImageResource(R.drawable.notdliv);
            holder.tahvil.setVisibility(View.VISIBLE);
            holder.sent.setImageResource(R.drawable.notsent);
            holder.sent.setVisibility(View.VISIBLE);
        }
        else if(msg.gettahvil().equals("3")) {
            holder.tahvil.setImageResource(R.drawable.dliv);
            holder.tahvil.setVisibility(View.VISIBLE);
            holder.sent.setImageResource(R.drawable.sent);
            holder.sent.setVisibility(View.VISIBLE);
        } else if(msg.gettahvil().equals("4")) {
            holder.tahvil.setImageResource(R.drawable.notsent);
            holder.tahvil.setVisibility(View.VISIBLE);
            holder.sent.setImageResource(R.drawable.notdliv);
            holder.sent.setVisibility(View.VISIBLE);
        }else{
            holder.tahvil.setImageResource(R.drawable.sending);
            holder.tahvil.setVisibility(View.VISIBLE);
            holder.sent.setImageResource(R.drawable.sending);
            holder.sent.setVisibility(View.VISIBLE);
        }
        holder.msgview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                try
                {
                    AlertDialog.Builder imageLoader = new AlertDialog.Builder(context);

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

                    View layout = inflater.inflate(R.layout.msg_menu,null);
                    View view= (ViewGroup) layout.findViewById(R.id.msgmenuview);

                    imageLoader.setView(view);
                    imageLoader.setCancelable(true);
                    imageLoader.create();
                    final AlertDialog alertDialog= imageLoader.show();
                    msgdelete=(CardView) layout.findViewById(R.id.msgdelete);
                    msgcopy=(CardView) layout.findViewById(R.id.msgcopy);
                    msgresend=(CardView) layout.findViewById(R.id.msgresend);
                    msgresend.setVisibility(View.GONE);
                    msgdelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(MyApplication.getWritableDatabase().deleteMsg(msg.getId())>0){
                                L.t(MyApplication.getAppContext(), "حذف شد");
                                // MyApplication.getWritableDatabase().setDelFlag("1");
                                msgs.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();

                            }else {
                                L.t(MyApplication.getAppContext(), "حذف نشد");
                            }
                            alertDialog.dismiss();
                        }
                    });
                    msgcopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            L.t(MyApplication.getAppContext(), " کپی شد");
                            ClipboardManager clipboard=(ClipboardManager) MyApplication.getAppContext().getSystemService(MyApplication.getAppContext().CLIPBOARD_SERVICE);
                            ClipData clipData=ClipData.newPlainText("Copy msg",msg.getmsg());
                            clipboard.setPrimaryClip(clipData);
                            alertDialog.dismiss();
                        }
                    });
//                    msgresend.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            L.t(MyApplication.getAppContext(), "دوبار فرستاده شد"+"\n\n"+msg.getmsg());
//                            alertDialog.dismiss();
//                        }
//                    });


                }
                catch ( Exception e )
                {
                    /*
                     * Log.i( "MatiMessage" , "error 1 -> " + e.toString() );
                     */
                }

                return false;
            }
        });

        holder.msgviewy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                try
                {
                    AlertDialog.Builder imageLoader = new AlertDialog.Builder(context);
                    DialogInterface dialogInterface= new DialogInterface() {
                        @Override
                        public void cancel() {
                            cancel();
                        }

                        @Override
                        public void dismiss() {
                            dismiss();
                        }
                    };

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

                    View layout = inflater.inflate(R.layout.msg_menu,null);
                    View view= (ViewGroup) layout.findViewById(R.id.msgmenuview);

                    imageLoader.setView(view);
                    imageLoader.setCancelable(true);
                    imageLoader.create();

                    final AlertDialog alertDialog= imageLoader.show();

                    msgdelete=(CardView) layout.findViewById(R.id.msgdelete);
                    msgcopy=(CardView) layout.findViewById(R.id.msgcopy);
                    msgresend=(CardView) layout.findViewById(R.id.msgresend);

                    msgdelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(MyApplication.getWritableDatabase().deleteMsg(msg.getId())>0){
                                L.t(MyApplication.getAppContext(), "حذف شد");
                                // MyApplication.getWritableDatabase().setDelFlag("1");
                                msgs.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                            }else {
                                L.t(MyApplication.getAppContext(), "حذف نشد");
                            }
                            alertDialog.dismiss();
                        }
                    });
                    msgcopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            L.t(MyApplication.getAppContext(), " کپی شد");
                            ClipboardManager clipboard=(ClipboardManager) MyApplication.getAppContext().getSystemService(MyApplication.getAppContext().CLIPBOARD_SERVICE);
                            ClipData clipData=ClipData.newPlainText("Copy msg",msg.getmsg());
                            clipboard.setPrimaryClip(clipData);
                            alertDialog.dismiss();
                        }
                    });
                    msgresend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            L.t(MyApplication.getAppContext(), "دوبار فرستاده شد");
                            if(msg.getmsg()==null)
                            {
                                sendSMS("",msg.getNum(),msg.getName());

                            }else {
                                sendSMS(msg.getmsg(),msg.getNum(),msg.getName());
                            }

                            alertDialog.dismiss();
                        }
                    });

                }
                catch ( Exception e )
                {
                    /*
                     * Log.i( "MatiMessage" , "error 1 -> " + e.toString() );
                     */
                }


                return false;
            }
        });

    }
    public void sendSMS(final String sms,final String number,final String name) {
        //////////////
        final Handler pauseSms = new Handler();
        Runnable seekSms = new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        new SMS(MyApplication.getAppContext(), sms, number, name, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        pauseSms.postDelayed(seekSms,1000);
    }
    @Override
    public int getItemCount() {

        return msgs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView tahvil,sent;
        private TextView txtusetn,txtmsg,txtmsgy,txttime,txttimey;
        private CardView msgview,msgviewy;


        public MyViewHolder(View itemview) {
            super(itemview);
            txtusetn = (TextView) itemview.findViewById(R.id.txtusetn);
            txtmsg = (TextView) itemview.findViewById(R.id.txtmsg);
            txtmsgy = (TextView) itemview.findViewById(R.id.txtmsgy);
            txttime = (TextView) itemview.findViewById(R.id.txttime);
            txttimey = (TextView) itemview.findViewById(R.id.txttimey);
            msgview = (CardView) itemview.findViewById(R.id.msgview);
            msgviewy = (CardView) itemview.findViewById(R.id.msgviewy);
            tahvil=(ImageView) itemview.findViewById(R.id.tahvil);
            sent=(ImageView) itemview.findViewById(R.id.sent);

            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}


//                AlertDialog.Builder alert = new AlertDialog.Builder( context );
//                alert.setCancelable( true );
//                // alert.setMessage("do you want to send empty sms ?");
//                alert.setPositiveButton("  حذف شود  ",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if(MyApplication.getWritableDatabase().deleteMsg(msg.getId())>0){
//                                    L.t(MyApplication.getAppContext(), "حذف شد");
//                                  // MyApplication.getWritableDatabase().setDelFlag("1");
//                                    msgs.remove(holder.getAdapterPosition());
//                                    notifyDataSetChanged();
//                                }else {
//                                    L.t(MyApplication.getAppContext(), "حذف نشد");
//                                }
//                                dialog.dismiss();
//                            }
//                        }
//                );
//
//                    alert.setNeutralButton("  کپی متن پیام  ",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    L.t(MyApplication.getAppContext(), " کپی شد");
//                                    ClipboardManager clipboard=(ClipboardManager) MyApplication.getAppContext().getSystemService(MyApplication.getAppContext().CLIPBOARD_SERVICE);
//                                    ClipData clipData=ClipData.newPlainText("Copy msg",msg.getmsg());
//                                    clipboard.setPrimaryClip(clipData);
//                                    dialog.dismiss();
//                                }
//                            }
//                    );
//
//                    alert.create();
//                    alert.show();


//                AlertDialog.Builder alert = new AlertDialog.Builder( context );
//                alert.setCancelable( true );
//                // alert.setMessage("do you want to send empty sms ?");
//                alert.setPositiveButton("  حذف شود  ",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if(MyApplication.getWritableDatabase().deleteMsg(msg.getId())>0){
//                                    L.t(MyApplication.getAppContext(), "حذف شد");
//                                   // MyApplication.getWritableDatabase().setDelFlag("1");
//                                    msgs.remove(holder.getAdapterPosition());
//                                    notifyDataSetChanged();
//
//                                }else {
//                                    L.t(MyApplication.getAppContext(), "حذف نشد");
//                                }
//                                dialog.dismiss();
//                            }
//                        }
//                );
//
//                    alert.setNeutralButton("  کپی متن پیام  ",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    L.t(MyApplication.getAppContext(), " کپی شد");
//                                    ClipboardManager clipboard=(ClipboardManager) MyApplication.getAppContext().getSystemService(MyApplication.getAppContext().CLIPBOARD_SERVICE);
//                                    ClipData clipData=ClipData.newPlainText("Copy msg",msg.getmsg());
//                                    clipboard.setPrimaryClip(clipData);
//                                    dialog.dismiss();
//                                }
//                            }
//                    );
//                if(msg.getHow().equals("0")) {
//                    alert.setNegativeButton("  ارسلال دوباره  ",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                    L.t(MyApplication.getAppContext(), "دوباره فرستاده شد");
//                                    dialog.dismiss();
//                                }
//                            }
//                    );
//                }
//
//                alert.create();
//                alert.show();
///////////////////////////////////////////////////////////////////////////
//
//    public Bitmap StringToBitMap(String encodedString){
//        try{
//            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
//            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        }catch(Exception e){
//            e.getMessage();
//            return null;
//        }
//    }
//    public String BitMapToString(Bitmap bitmap){
//        try{
//            ByteArrayOutputStream bAOS=new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG,90,bAOS);
//            byte[] b=bAOS.toByteArray();
//            String spic= Base64.encodeToString(b,Base64.DEFAULT);
//            return spic;
//        }catch(Exception e){
//            e.getMessage();
//            return null;
//        }
//    }

