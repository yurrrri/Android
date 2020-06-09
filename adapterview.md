## 선택 위젯 or 어댑터 뷰
> - 여러 개의 아이템 중에서 하나를 선택하는 방식을 가진 위젯
> - 어댑터를 통해 각각의 아이템과 데이터를 관리하는 패턴
> - 리스트뷰, 스피너, 그리드뷰, 갤러리

![1 (1)](https://user-images.githubusercontent.com/37764504/58173324-07f73d80-7cd6-11e9-8425-99a9a7ae27df.jpg)

### 리스트뷰
- 레이아웃에서 listView 위젯 사용
- Adapter : 레이아웃과 데이터를 결합하여 Adapter View에 표시할 뷰 생성
1. 리스트뷰의 각 아이템의 원본 데이터, 레이아웃 정의
2. 어댑터 클래스 생성 - Generate -> Implement Methods  (아이템으로 표시할 뷰를 리턴하는 getView() 메소드 정의)
3. 아이템마다 뷰 설정 (XML 인플레이션)
4. 리스트뷰 객체 생성
5. AdapterView.setAdapter(Adapter)로 AdapterView에 Adapter 연결

![listview_project](https://user-images.githubusercontent.com/37764504/84140205-33d59480-aa8c-11ea-982e-ae53903cd1c2.PNG)

listView_project :

- 항목 클릭 시 해당 항목 EditText에 표시
- EditText에 문자열 입력 후 추가 버튼 누르면 항목 추가
- 항목 클릭 시 EditText에 해당 항목 표시 -> 수정한 후 수정 버튼 누르면 리스트 뷰 항목 변경
- 롱클릭 시 해당 항목 삭제

[**소스파일**](https://github.com/yurrrri/Android_study/tree/master/listView_project)

DataManager.java - 원본 데이터 준비
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


### 스피너

> ![spinner](https://user-images.githubusercontent.com/37764504/83966941-70708700-a8f8-11ea-99c3-1daa6fa71fd1.png)
