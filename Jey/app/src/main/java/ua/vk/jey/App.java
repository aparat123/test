package ua.vk.jey;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static VKApi vkApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
			.baseUrl("http://www.umori.li/") //Базовая часть адреса
			.addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
			.build();
        vkApi = retrofit.create(VKApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static VKApi getApi() {
        return vkApi;
    }
}
