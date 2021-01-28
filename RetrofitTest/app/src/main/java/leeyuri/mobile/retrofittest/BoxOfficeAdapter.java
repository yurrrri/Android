package leeyuri.mobile.retrofittest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder>{
    List<DailyBoxOfficeList> dailyBoxOfficeList = new ArrayList<DailyBoxOfficeList>();
    Context context;

    public BoxOfficeAdapter(List<DailyBoxOfficeList> dailyBoxOfficeList, Context context) {
        this.dailyBoxOfficeList = dailyBoxOfficeList;
        this.context = context;
    }

    @NonNull
    @Override
    public BoxOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        BoxOfficeViewHolder boxOfficeViewHolder = new BoxOfficeViewHolder(rootView);
        return boxOfficeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeViewHolder boxOfficeViewHolder, int i) {
        DailyBoxOfficeList dailyboxOffice = dailyBoxOfficeList.get(i);

        boxOfficeViewHolder.tvRank.setText(dailyboxOffice.getRank()+"");
        boxOfficeViewHolder.tvMovieName.setText(dailyboxOffice.getMovieNm());

    }

    @Override
    public int getItemCount() {
        return dailyBoxOfficeList.size();
    }

    public class BoxOfficeViewHolder extends RecyclerView.ViewHolder {
        TextView tvRank;
        TextView tvMovieName;


        public BoxOfficeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
        }
    }
}