package com.vk.vktestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.vk.sdk.api.*;
import com.vk.sdk.api.model.*;
import java.util.*;
import com.vk.vktestapp.adapter.*;


public class MessFragment extends Fragment {
	ListView lv;
	int id;
	MessageAdapter MessageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


	}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mess_fragment, container, false);
		final ListView lv = (ListView)rootView.findViewById(R.id.messlist);
		
		
		VKRequest request = VKApi.messages().getDialogs(VKParameters.from(VKApiConst.COUNT, 10));
		request.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response) {
					super.onComplete(response);

					VKApiGetDialogResponse getDialogResponse = ((VKApiGetDialogResponse)response.parsedModel);
					final  VKList<VKApiDialog> list= getDialogResponse.items;

					ArrayList<String> messages = new ArrayList<>();
					ArrayList<String> users = new ArrayList<>();
					for (VKApiDialog msg : list){

						users.add(String.valueOf(msg.message.user_id));
						messages.add(msg.message.body);
					}
					
					lv.setAdapter(new MessageAdapter(getActivity(), users, messages, list));
					
					

				}
			});
		


		return rootView;
    }

}
