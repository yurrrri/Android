package ddwucom.mobile.customadapterlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class townAdapter extends BaseAdapter {
    private Context context; //inflater 객체 생성시 필요
    private int layout; //항목 각각의 layout
    private ArrayList<townData> townDataList;
    private LayoutInflater inflater;

    public townAdapter(Context context, int layout, ArrayList<townData> townDataList) {
        this.context = context;
        this.layout = layout;
        this.townDataList = townDataList;
        //개별 항목에 보여줄 뷰 인플레이션
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //총 항목의 개수 -> 필수적으로 구현 ★
    @Override
    public int getCount() {
        return townDataList.size();
    }

    //특정 위치의 데이터 항목 반환
    @Override
    public Object getItem(int position) {
        return townDataList.get(position);
    }

    // 특정 위치의 데이터 항목의 아이디 반환
    @Override
    public long getItemId(int position) {
        return townDataList.get(position).get_id();
    }

    //n번째 항목 반환
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //onClick에서 사용하기 위해 상수 저장
        final int pos = position;

        //리스트뷰의 스크롤속도를 개선하기 위한 ViewHolder
        //항목마다 findViewById가 호출되므로 빈번한 호출로 인한 성능 감소를 막기 위한 설계 패턴
        //따라서 반복사용할 수 있도록 내부 View를 멤버로 가지는 정적 클래스로 생성 -> 아래에!
        ViewHolder holder;

        //아직 뷰가 없으면, 인플레이션 수행
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            //viewHolder 객체의 멤버에 view 객체 지정
            holder = new ViewHolder();
            holder.tvNo = convertView.findViewById(R.id.tvNo);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvDetail = convertView.findViewById(R.id.tvDetail);
            holder.tvWeather = convertView.findViewById(R.id.tvWeather);

            //생성한 view holder 객체 저장
            convertView.setTag(holder);
        }
        //convertview가 이미 존재하는 경우, tag에 저장한 viewHolder 객체를 getTag()로 사용하여 반환
        else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tvNo.setText(String.valueOf(townDataList.get(pos).get_id()));
        holder.tvName.setText(townDataList.get(pos).getTownName());
        holder.tvDetail.setText(townDataList.get(pos).getTownDetail());
        holder.tvWeather.setText(townDataList.get(pos).getWeather());

    //번호 textView를 선택했으면 순번을 나타내는 Toast가 뜨도록 구현
        holder.tvNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, "순번: "+townDataList.get(pos).get_id(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //이름 textView를 선택했으면 동네 이름을 나타내는 Toast가 뜨도록 구현
        holder.tvName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, "동네 이름: "+townDataList.get(pos).getTownName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //주소 상세 textView를 선택했으면 동네 세부정보를 나타내는 Toast가 뜨도록 구현
        holder.tvDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, "동네 세부 정보: "+townDataList.get(pos).getTownDetail(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //날씨 textView를 선택했으면 날씨를 나타내는 Toast가 뜨도록 구현
        holder.tvWeather.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, townDataList.get(pos).getTownDetail()+" "+townDataList.get(pos).getTownName()+"의 날씨는 "+townDataList.get(pos).getWeather(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //레이아웃과 데이터를 결합 후 마지막으로 view 반환!
        return convertView;
    }

    //viewHolder 정적 클래스
    static class ViewHolder{
        TextView tvNo;
        TextView tvName;
        TextView tvDetail;
        TextView tvWeather;
    }
}


