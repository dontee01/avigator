package com.avigator.avigator.activity.network;

import com.avigator.avigator.activity.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static android.app.SearchManager.QUERY;

/**
 * Created by JOHN on 20/04/2017.
 */

public interface ApiInterface {

    @GET("aviator.php")
    Call<List<Message>> getInbox();

}
