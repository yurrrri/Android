package ddwucom.mobile.customadapterlab;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<townData> townList;

    public DataManager(){
        townList = new ArrayList<townData>();
        townList.add(new townData(1, "하월곡동", "서울시 성북구", "좋음"));
        townList.add(new townData(2, "개포동", "서울시 강남구", "보통"));
        townList.add(new townData(3, "대치동", "서울시 강남구", "좋음"));
        townList.add(new townData(4, "압구정동", "서울시 강남구", "나쁨"));
        townList.add(new townData(5, "잠실동", "서울시 송파구", "좋음"));
        townList.add(new townData(6, "마천동", "서울시 송파구", "보통"));
        townList.add(new townData(7, "신천동", "서울시 송파구", "좋음"));
        townList.add(new townData(8, "문정동", "서울시 송파구", "나쁨"));
        townList.add(new townData(9, "잠원동", "서울시 서초구", "좋음"));
        townList.add(new townData(10, "반포동", "서울시 서초구", "보통"));
        townList.add(new townData(11, "방배동", "서울시 서초구", "보통"));
        townList.add(new townData(12, "서초동", "서울시 서초구", "나쁨"));
        townList.add(new townData(13, "길동", "서울시 강동구", "좋음"));
        townList.add(new townData(14, "둔촌동", "서울시 강동구", "좋음"));
        townList.add(new townData(15, "명일동", "서울시 강동구", "나쁨"));
        townList.add(new townData(16, "암사동", "서울시 강동구", "보통"));
        townList.add(new townData(17, "성수동", "서울시 성동구", "좋음"));
        townList.add(new townData(18, "여의도동", "서울시 영등포구", "좋음"));
        townList.add(new townData(19, "아현동", "서울시 마포구", "나쁨"));
    }

    public ArrayList<townData> getSubjectList(){
        return townList;
    }

    public townData getItem(int pos){
        return townList.get(pos);
    }

    //멤버 추가
    public void addData(townData newSubject){
        townList.add(newSubject);
    }

    //삭제
    public void removeData(int idx){
        townList.remove(idx);
    }

    //수정
    public void modifyData(int idx, townData subject){
        townList.set(idx, subject);
    }
}
