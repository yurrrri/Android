## 선택 위젯 or 어댑터 뷰
> - 여러 개의 아이템 중에서 하나를 선택하는 방식을 가진 위젯
> - 어댑터를 통해 각각의 아이템과 데이터를 관리하는 패턴
> - 리스트뷰, 스피너, 그리드뷰, 갤러리

![1 (1)](https://user-images.githubusercontent.com/37764504/58173324-07f73d80-7cd6-11e9-8425-99a9a7ae27df.jpg)

### 리스트뷰
- Adapter : 레이아웃과 데이터를 결합하여 Adapter View에 표시할 뷰 생성
1. 리스트뷰의 각 아이템의 원본 데이터, 레이아웃 정의
2. 어댑터 클래스 생성 - Generate -> Implement Methods  (아이템으로 표시할 뷰를 리턴하는 getView() 메소드 정의)
3. 아이템마다 뷰 설정 (XML 인플레이션)
4. 리스트뷰 객체 생성
5. AdapterView.setAdapter(Adapter)로 AdapterView에 Adapter 연결

DataManager.java - 원본 데이터 준비
```java
public class DataManager {  
    private ArrayList<String> subjectList;  
  
 public DataManager(){ //생성자 -> 데이터 생성  
	  subjectList = new ArrayList<String>();  
	  subjectList.add("모바일소프트웨어");  
	  subjectList.add("네트워크");  
	  subjectList.add("웹서비스");  
	  subjectList.add("운영체제");  
	  subjectList.add("웹프로그래밍2");  
  }  
  
    public ArrayList<String> getSubjectList(){  
        return subjectList;  
  }  
  
    public String getItem(int pos){  
        return subjectList.get(pos);  
  }  
  
    //멤버 추가  
  public void addData(String newSubject){  
        subjectList.add(newSubject);  
  }  
  
    //삭제  
  public void removeData(int idx){  
        subjectList.remove(idx);  
  }  
  
    //수정  
  public void modifyData(int idx, String subject){  
        subjectList.set(idx, subject);  
  }  
}
```

MainActivity.java - 아답터 생성, 리스트 뷰 준비 및 아답터 연결
```java
public class MainActivity extends AppCompatActivity {  
  
  DataManager dataManager;  
  Button button;  
  EditText editText;  
  ArrayAdapter<String> adapter;  
  ListView listView; //변수 생성은 멤버로 하는 것이 좋음.  
  int selectedPosition;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
	  
	  dataManager = new DataManager();  
	  button = findViewById(R.id.button);  
	  selectedPosition = 0;  
  
  //데이터 원본 준비  
  ArrayList<String> subjectList = dataManager.getSubjectList();  

 // 직접 코드에 데이터 추가 가능. 원본 데이터에 일반 배열을 사용할 경우  
  // String[] subjectList = {"모바일소프트웨어", "네트워크", "웹서비스", "운영체제", "웹프로그래밍2"};  
  
 //별도의 arrays.xml 파일 생성하여 사용 가능
//       String[] subjectList = getResources().getStringArray(R.array.subjectList);  
  
 //어댑터 생성  
  adapter = new ArrayAdapter<String>(this, 
  //기본 레이아웃 사용 가능
  android.R.layout.simple_list_item_1, dataManager.getSubjectList());  
  //simple_list_item_single_choice: 라디오 버튼
		  //xml의 checkMode는 singlechoice로 바꿔주기 
 
  //어댑터 뷰 준비 및 어댑터 연결  
  listView = (ListView)findViewById(R.id.listView);  
  listView.setAdapter(adapter);  
  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
            @Override  
	  public void onItemClick(AdapterView<?> parent, View view, int 	position, long id) {  
	                Toast.makeText(MainActivity.this, "pos: " + position, Toast.LENGTH_SHORT).show();  
	  selectedPosition = position;  
	  String subject = dataManager.getItem(position);  
	  editText.setText(subject);  
  }  
        });  
  
  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  
            @Override  
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {  
         dataManager.removeData(position);  
  adapter.notifyDataSetChanged(); **//어댑터에 원본 데이터의 변경 알림 필수 !** 
  return true;  
  }  
        });  
  }  

```


### 스피너

> ![spinner](https://user-images.githubusercontent.com/37764504/83966941-70708700-a8f8-11ea-99c3-1daa6fa71fd1.png)
