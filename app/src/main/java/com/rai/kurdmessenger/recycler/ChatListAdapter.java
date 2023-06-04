package com.rai.kurdmessenger.recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rai.kurdmessenger.ChatActivity;
import com.rai.kurdmessenger.R;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.db.smsDB;
import com.rai.kurdmessenger.log.L;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created Created by @rebwar_me.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {

    //contains the list of movies
    private ArrayList<ChatModel> cons = new ArrayList<>();
    private LayoutInflater mInflater;


    //formatter for parsing the dates in the specified format below
//keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;
    Context context;
    smsDB db;
    private HashMap<String,Object> user;
    int i=0,j=0;

    public ChatListAdapter(Context context ) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setConList(ArrayList<ChatModel> con) {
        this.cons = con;
        //update the adapter to reflect the new set of movies
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, con.size());
    }

    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chat_row, parent, false);

        return new ChatListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChatListAdapter.MyViewHolder holder, final int position) {
        final ChatModel con = cons.get(position);

        holder.username.setText(con.getName());
        holder.numofmsg.setText(con.getNummsg());
        holder.numphonemsg.setText(con.getNum());


        if(con.getNewmsg().equals("0")) {
            holder.newmsg.setVisibility(View.GONE);
        }else {
            holder.newmsg.setVisibility(View.VISIBLE);
        }
        holder.conitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("num",con.getNum());
                intent.putExtra("id",con.getId());
                intent.putExtra("name",con.getName());
                intent.putExtra("pic",con.getPic());
                context.startActivity(intent);
            }
        });
        holder.conitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder( context );
                alert.setCancelable( true );
                alert.setMessage("این چت حذف شود؟");
                alert.setPositiveButton(" بله ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(MyApplication.getWritableDatabase().DeleteChat(con.getId(),con.getName(),con.getNum())>0){
                                    L.t(MyApplication.getAppContext(), "حذف شد");
                                    // MyApplication.getWritableDatabase().setDelFlag("1");
                                    cons.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();

                                }else {
                                    L.t(MyApplication.getAppContext(), "حذف نشد");
                                }
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
                return false;
            }
        });

        holder.newmsg.setText(" "+con.getNewmsg()+" ");
    }

    @Override
    public int getItemCount() {
        return cons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView upic;
        private TextView username,numofmsg,numphonemsg,newmsg;
        private CardView conitem;

        public MyViewHolder(View itemview) {
            super(itemview);
            conitem = (CardView) itemview.findViewById(R.id.conitem);
            newmsg = (TextView) itemview.findViewById(R.id.newmsg);
            username = (TextView) itemview.findViewById(R.id.username);
            numofmsg = (TextView) itemview.findViewById(R.id.numofmsg);
            numphonemsg = (TextView) itemview.findViewById(R.id.numphonemsg);
            upic=(ImageView) itemview.findViewById(R.id.upic);

            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}



