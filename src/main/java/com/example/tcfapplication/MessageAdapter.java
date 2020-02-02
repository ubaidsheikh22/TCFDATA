package com.example.tcfapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    //   private String imageurl;

    private FirebaseUser firebaseUser;

    public MessageAdapter(Context mContext, List<Chat> mChat) {
        this.mContext = mContext;
        this.mChat = mChat;
        //   this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.viewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.viewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.viewHolder viewHolder, int i) {

        Chat chat = mChat.get(i);
        viewHolder.show_message.setText(chat.getMessage());

        //   Glide.with(mContext).load(imageurl).into(viewHolder.image_profile);

        if(i == mChat.size()-1){

            if(chat.isseen()){
                viewHolder.txt_seen.setText("Seen");
            }else{
                viewHolder.txt_seen.setText("Delivered");
            }
        }else{
            viewHolder.txt_seen.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        //   public ImageView image_profile;
        public TextView show_message;

        public TextView txt_seen;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //    image_profile=itemView.findViewById(R.id.image_profile);

            show_message=itemView.findViewById(R.id.show_message);
            txt_seen = itemView.findViewById(R.id.txtseen);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(firebaseUser.getUid())){
            return  MSG_TYPE_RIGHT;
        }
        else{
            return MSG_TYPE_LEFT;
        }
    }
}
