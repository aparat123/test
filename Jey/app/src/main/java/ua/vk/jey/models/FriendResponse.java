package ua.vk.jey.models;
import java.util.*;

public class FriendResponse{
	private int count;
	private List<FriendsModel> items;

	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}
	public List<FriendsModel> getItems(){
		return items;
	}
	public void setItems(List<FriendsModel> items){
		this.items=items;
	}

}
