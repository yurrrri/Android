package leeyuri.mobile.retrofittest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// 전체 데이터 얻어오기
public class Result {

    @SerializedName("boxOfficeResult")
    @Expose
    private BoxOfficeResult boxOfficeResult;

    public BoxOfficeResult getBoxOfficeResult() {
        return boxOfficeResult;
    }

    public void setBoxOfficeResult(BoxOfficeResult boxOfficeResult) {
        this.boxOfficeResult = boxOfficeResult;
    }
}