package ua.vk.jey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.vk.jey.models.FriendsModel;

public interface VKApi {
    @GET("/dev/friends.get")
    Call<List<FriendsModel>> getData(@Query("first_name") String first_name, @Query("last_name") String last_name, @Query("id") int count);
}
