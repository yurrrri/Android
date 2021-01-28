package leeyuri.mobile.retrofittest;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RetrofitClient retrofitClient;
    RetrofitInterface retrofitInterface;

    RecyclerView recycler;

    String API_KEY = "3f8026dbde674a8c266c9945563d4fc5";

    List<DailyBoxOfficeList> dailyBoxOfficeList_list = new ArrayList<>();
    BoxOfficeAdapter boxOfficeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler= findViewById(R.id.recycler);

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();

        retrofitInterface.getBoxOffice(API_KEY, "20210127").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Log.d("retro", 1+"");
                    Result result = response.body();
                    BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                    List<DailyBoxOfficeList> dailyBoxOfficeLists = boxOfficeResult.getDailyBoxOfficeList();
                    for (DailyBoxOfficeList dailyBoxOfficeList : dailyBoxOfficeLists){
                        dailyBoxOfficeList_list.add(dailyBoxOfficeList);
                    }

                    boxOfficeAdapter = new BoxOfficeAdapter(dailyBoxOfficeList_list, MainActivity.this);
                    recycler.setAdapter(boxOfficeAdapter);
                }else{
                    Log.d("retro", 2+"Error");
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }
}
