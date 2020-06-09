
## 선택 위젯 or 어댑터 뷰
> - 여러 개의 아이템 중에서 하나를 선택하는 방식을 가진 위젯
> - 어댑터를 통해 각각의 아이템과 데이터를 관리하는 패턴
> - 리스트뷰, 스피너, 그리드뷰, 갤러리

![1 (1)](https://user-images.githubusercontent.com/37764504/58173324-07f73d80-7cd6-11e9-8425-99a9a7ae27df.jpg)

### 리스트뷰
- 레이아웃에서 listView 위젯 사용
- Adapter : 레이아웃과 데이터를 결합하여 Adapter View에 표시할 뷰 생성
1. 리스트뷰의 각 아이템의 원본 데이터(DTO 클래스, Data Transfer Object 클래스), 레이아웃 정의
2. 어댑터 클래스 생성 - Generate -> Implement Methods  (아이템으로 표시할 뷰를 리턴하는 getView() 메소드 정의)
3. 아이템마다 뷰 설정 (인플레이션)
4. 리스트뷰 객체 생성
5. AdapterView.setAdapter(Adapter)로 AdapterView에 Adapter 연결

![listview_project](https://user-images.githubusercontent.com/37764504/84140205-33d59480-aa8c-11ea-982e-ae53903cd1c2.PNG)

listView_project :

- 항목 클릭 시 해당 항목 EditText에 표시
- EditText에 문자열 입력 후 추가 버튼 누르면 항목 추가
- 항목 클릭 시 EditText에 해당 항목 표시 -> 수정한 후 수정 버튼 누르면 리스트 뷰 항목 변경
- 롱클릭 시 해당 항목 삭제

[**소스파일**](https://github.com/yurrrri/Android_study/tree/master/listView_project)

DataManager.java - DTO 클래스
```java
public class SubjectManager {
    private ArrayList<String> subjectList;

//생성자
    public SubjectManager() {
        subjectList = new ArrayList();
        subjectList.add("육회");
        subjectList.add("연어");
        subjectList.add("삼겹살");
        subjectList.add("짬뽕");
        subjectList.add("자몽허니블랙티");
    }

//getter & setter
    public String getItem(int pos){
        return subjectList.get(pos);
    }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

//    추가
    public void addData(String newSubject) {
        subjectList.add(newSubject);
    }

//    삭제
    public void removeData(int idx) {
        subjectList.remove(idx);
    }

//    수정
    public void modifyData(int idx, String string){ subjectList.set(idx, string);}

}
```

MainActivity.java - 아답터 생성, 리스트 뷰 준비 및 아답터 연결
```java
public class MainActivity extends AppCompatActivity {

    SubjectManager subjectManager;
    ArrayList<String> subjectList;
    ArrayAdapter<String> adapter;
    Button btnUpdate;
    Button btnInsert;
    ListView listView;
    int selectedpos;
    EditText etItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	//원본 데이터 관리 매니저 객체 생성
        subjectManager = new SubjectManager();
        subjectList = subjectManager.getSubjectList();

        etItem = findViewById(R.id.etItem);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);

	//아답터 생성
        adapter = new ArrayAdapter<String>(
			        //기본 제공 레이아웃 사용
                this, android.R.layout.simple_list_item_1, subjectList
        );
	
	//리스트뷰 생성 및 아답터 연결
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    //항목 클릭시 해당 항목 EditText에 표시
                selectedpos = position;
                etItem.setText(subjectManager.getItem(selectedpos));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		//항목 롱 클릭 시 해당 항목 삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                subjectManager.removeData(position);
		//원본데이터가 변경했으므로 반드시 adapter에게 사실을 알려야함!
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onClick(View v){
        switch(v.getId()){
	    //음식 데이터 추가
            case R.id.btnInsert:
                subjectManager.addData(etItem.getText().toString());
                adapter.notifyDataSetChanged();
                break;
	    //음식 데이터 수정
            case R.id.btnUpdate:
                subjectManager.modifyData(selectedpos, etItem.getText().toString());
                adapter.notifyDataSetChanged();
                break;
        }
    }
}

```

### 커스텀 어댑터 뷰
> 직접 작성한 레이아웃을 사용한 View로 Adapter View를 구성할 경우
1. 개별로 커스텀 뷰를 표현하는 레이아웃 작성
2. 원본 데이터를 보관하는 DTO 클래스 구현
3. BaseAdapter를 상속하여 Custom Adapter 클래스 구현

![customadapter_lab](https://user-images.githubusercontent.com/37764504/84142668-28846800-aa90-11ea-9fde-ba66f05853e4.PNG)

[**소스파일**](https://github.com/yurrrri/Android_study/tree/master/CustomAdapterLab)
- 항목 롱클릭 시 삭제 수행
- ViewHolder로 스크롤 속도 개선
- 각 TextView 클릭 시 서로 다른 Toast가 뜨도록 작성

1. 개별 뷰 레이아웃 작성 - custom_adapter_view.xml
![customadapter_layout](https://user-images.githubusercontent.com/37764504/84143077-e576c480-aa90-11ea-8060-6389fbc11f07.PNG)

2. townData.java - DTO 클래스 작성
```java
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
```
DataManager.java - 데이터 매니저 클래스 생성하여 원본 데이터 준비
```java
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
```

3. townAdapter - 커스텀 어댑터 클래스 구현(BaseAdapter 클래스 상속)
```java
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

```

※ ViewHolder - 스크롤 속도를 개선하기 위한 설계 패턴
- 반복적으로 사용하는 View 객체의 findViewById() 호출을 감소시키기 위해 적용하는 설계 패턴
- listView의 화면상의 View 개수는 원본 데이터의 개수만큼 만들어지는 것이 아니라 화면에서 보여지는 정도의 수만 View 생성 -> 스크롤 시 화면에서 사라진 View 재사용
- 따라서 뷰 레이아웃의 내부 View를 반복사용할 수 있도록 Adapter 클래스 내부에서 정적 클래스(static)으로 생성

MainActivity.java
```java
public class MainActivity extends AppCompatActivity {  
  
  private DataManager dataManager;  
 private ArrayList<townData> townDataList;  
 private townAdapter townAdapter;  
 private ListView listView;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  

	  dataManager = new DataManager();  
	  townDataList = dataManager.getSubjectList();  

	  //어댑터 생성
	  townAdapter = new townAdapter(this, R.layout.custom_adapter_view, townDataList);  
	  //리스트뷰 준비
	  listView = (ListView) findViewById(R.id.listView);  

	  //리스트뷰와 어댑터 연결
	  listView.setAdapter(townAdapter);  
	  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  
		    @Override
		    //롱클릭시 아이템 삭제
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {  
			dataManager.removeData(position);
			//어댑터에게 데이터의 변화 알림
			townAdapter.notifyDataSetChanged();  
			return true;  
		}  
	   });  
	  }  
}
```

### 스피너

> ![spinner](https://user-images.githubusercontent.com/37764504/83966941-70708700-a8f8-11ea-99c3-1daa6fa71fd1.png)
