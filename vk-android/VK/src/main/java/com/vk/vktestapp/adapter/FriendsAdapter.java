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

public class FriendsAdapter extends BaseAdapter
{
VKList listr;
Context context;
	public VKApiUser user;

	private Picasso picasso;
    public FriendsAdapter(Context context, VKList list) {
        picasso = Picasso.with(context);
listr=list;
        
    }

	@Override
    public int getCount() {
        return listr.size();
    }

	@Override
    public Object getItem(int position) {
        return listr.get(position);
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

        
        ((TextView)view.findViewById(R.id.names)).setText(user.first_name + " " + user.last_name);
       
        return view;
    }


	
	
}
