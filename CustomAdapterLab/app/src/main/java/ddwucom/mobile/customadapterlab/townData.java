package ddwucom.mobile.customadapterlab;

//원본 데이터를 저장할 클래스
public class townData {
    private int _id;
    private String townName;
    private String townDetail;
    private String weather;

    //생성자
    public townData(int _id, String townName, String townDetail, String weather) {
        this._id = _id;
        this.townName = townName;
        this.townDetail = townDetail;
        this.weather = weather;
    }

    //getter & setter
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownDetail() {
        return townDetail;
    }

    public void setTownDetail(String townDetail) {
        this.townDetail = townDetail;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}