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
    Context context;
    private VKList list;
	private VKApiUserFull user;


	private TextView name;


    public FriendsAdapter(Context context, VKList list){
        this.context = context;
        this.list = list;
	
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
		name = (TextView) view.findViewById(R.id.nams);
     
		name.setText(" " + user.last_name);
     
        return view;
    }


	
	
}
