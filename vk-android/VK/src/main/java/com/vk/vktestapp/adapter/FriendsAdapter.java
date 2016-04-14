package com.vk.vktestapp.adapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.vk.vktestapp.*;
import com.vk.sdk.api.model.*;
import com.vk.sdk.api.*;
import com.vk.sdk.api.methods.*;
import android.widget.*;
import android.database.*;
import com.squareup.picasso.*;
import android.graphics.*;
import com.squareup.okhttp.internal.framed.*;
import android.content.*;
import android.os.*;
import android.app.*;

public class FriendsAdapter extends BaseAdapter
{
    Context context;
    private VKList list;
	String Name;
	
	String second;
	Picasso picasso;
	Activity activity;

    public FriendsAdapter(Context context, VKList list){
        this.context = context;
        this.list = list;
		picasso = Picasso.with(context);
		}


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
	

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
		
		
		
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.friends_item, parent, false);
			
        } else {
            view = convertView;
			
        }
		
		final VKApiUser user = (VKApiUser) getItem(position);
	 if (user.online){
		 TextView online = (TextView) view.findViewById(R.id.onli);
		 online.setText("Онлайн");
	 }
	 
	 else {
		 TextView online = (TextView) view.findViewById(R.id.onli);
		 online.setText("Оффлайн");
	 }
	 
		
		TextView namers = (TextView) view.findViewById(R.id.nams);
		ImageView Photo = (ImageView) view.findViewById(R.id.photo);
		namers.setText(user.toString());
		
		picasso.load(user.photo_50)
			.config(Bitmap.Config.RGB_565)
		.into(Photo);
		view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Intent myIntent = new Intent(context, ProfileActivity.class);
					myIntent.putExtra("user_id", (int)user.id);
					context.startActivity(myIntent);
					
				}
			});
        return view;
    }
	
	
}
