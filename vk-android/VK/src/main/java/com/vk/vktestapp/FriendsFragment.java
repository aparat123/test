package com.vk.vktestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.vk.sdk.api.*;
import com.vk.sdk.api.model.*;
import android.support.v4.app.*;
import com.vk.vktestapp.adapter.*;
import java.util.*;
import com.vk.sdk.api.methods.*;


public class FriendsFragment extends ListFragment
{
	ArrayList<VKApiUser> list;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id, first_name, last_name"));
		request.executeWithListener(new VKRequest.VKRequestListener() {

				private ListAdapter FriendsAdapter;
				@Override
				public void onComplete(VKResponse response){
					super.onComplete(response);

					VKList list = (VKList) response.parsedModel;
					
					//ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
					FriendsAdapter = new FriendsAdapter(getActivity(), list);
					setListAdapter(FriendsAdapter);
					
					

				

				}
			});
		String first_name = VKApiUser.FIELD_ONLINE;
		Toast.makeText(getContext(), first_name, Toast.LENGTH_LONG).show();
	}
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friends_fragment, container, false);
		
		
    }
	
}
