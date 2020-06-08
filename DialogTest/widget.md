# 위젯

## 토스트
> 메시지를 잠깐 보여주었다가 없어지는 뷰
> 
>![sdf](https://user-images.githubusercontent.com/37764504/56305276-f244a480-617a-11e9-8d80-2358eb3a6f6b.PNG)

- Toast.makeText(Context, Text, Toast.LENGTH_LONG/SHORT).show();

## 스낵바
> 아래에 잠깐 뜨는 상자
>
> ![sdgh](https://user-images.githubusercontent.com/37764504/56305486-61ba9400-617b-11e9-972c-0e242eec1ba1.PNG)

- 디자인 라이브러리 추가
File -> Project Structure.... -> app-> Dependencies -> + 아이콘 -> Library dependency -> com.android.support:design -> OK
- Snackbar.make(View, Text, Snackbar.LENGTH_LONG/SHORT).show(); 

## 대화상자
> ![hfch](https://user-images.githubusercontent.com/37764504/56305646-a1817b80-617b-11e9-83b0-9e0d82dbbaa9.PNG)

AlertDialog.Builder 클래스로 생성

[**1. 기본 대화상자**](https://github.com/yurrrri/Android_study/tree/master/BasicDialog)
```java
public class MainActivity extends AppCompatActivity {  
---중략---
	  button.setOnClickListener(new View.OnClickListener() {  
      @Override  
	  public void onClick(View v) {  
                showMessage();  
  }  
        });  
  }  
  
  private void showMessage(){  
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
  // 대화상자 만들기 위한 빌더 객체 생성  
  // 객체 이름 생략: chaining
  
  builder.setTitle("안내");  
		  .setMessage("종료하시겠습니까?");  
		  .setIcon(android.R.drawable.ic_dialog_alert);  
		  //예 버튼 추가
		  //클릭시 이벤트 처리:DialogInterface.OnClickListener
		  .setPositiveButton("예", new DialogInterface.OnClickListener() {  
        @Override 
			  public void onClick(DialogInterface dialog, int which) {  
		          String message = "예 버튼이 눌렸습니다. ";  
			      textView.setText(message);  
  }  
        });  
  
			 //취소 버튼 추가
		  .setNeutralButton("취소", new DialogInterface.OnClickListener() {  
            @Override  
			  public void onClick(DialogInterface dialog, int which) {  
			                String message = "취소 버튼이 눌렸습니다.";  
			  textView.setText(message);  
			  }  
        });  
  
		//아니오 버튼 추가  
		  .setNegativeButton("아니오", new DialogInterface.OnClickListener() {  
            @Override  
			  public void onClick(DialogInterface dialog, int which) {  
			                String message = "아니오 버튼이 눌렸습니다.";  
			  textView.setText(message);  
  }  
        });  
  
		  //대화상자 객체 생성 후 보여주기
		  
		  AlertDialog dialog = builder.create();  
		  dialog.show();
		//builder.show() 로 대체 가능
  }  
}
```
※ 대화상자의 종료에 관한 메소드 
- AlertDialog.Builder setCancelable (boolean cancelable) : 대화상자의 버튼이 눌렸을 때 Back 버튼을 누르면 대화상자가 닫히게 하는 메소드
- void Dialog.setCancelOnTouchOutside (Boolean cancel) : 대화상자 외부 클릭 시 대화상자가 종료할 수 있도록 설정하는 메소드 (Dialog 클래스 생성 필요)

**2. 목록을 가지고 있는 대화상자**

![dialog](https://user-images.githubusercontent.com/37764504/83961632-f62c0c80-a8cf-11ea-843f-57212092b053.GIF)

res/values/arrays.xml  : values 폴더에 대화상자에 표시할 목록의 배열 생성
```xml
<resources>  
 <string-array name="foods">  
 <item>짜장면</item>  
 <item>짬뽕</item>  
 <item>우동</item>  
 <item>탕수육</item>  
 </string-array></resources>
```

MainActivity.java
```java
... 중략
//목록 대화상자에 표시
builder.setItems(R.array.foods, new DialogInterface.OnClickListener() {  
    @Override  
  public void onClick(DialogInterface dialog, int which) {  
        String[] foods = getResources().getStringArray(R.array.foods);  
        ...중략 ...
  }  
})
```

**3. 라디오 버튼 목록을 가지고 있는 대화상자**

![2](https://user-images.githubusercontent.com/37764504/83961839-27a5d780-a8d2-11ea-90d2-b783787f4de6.GIF)

```java
...중략
int selectedindex;
//selectedindex : 이전에 선택한 항목의 index를 저장하는데, 
//이 값을 보유하려면 별도의 멤버 변수를 선언해서 저장해두어야 함

		.setSingleChoiceItems(R.array.foods, selectedindex, new 		DialogInterface.OnClickListener() {  
		    @Override  
		  public void onClick(DialogInterface dialog, int which) {  
		        selectedindex = which;  
		  }  
		})
```
**4. 체크박스 목록을 가지고 있는 대화상자**

![캡처](https://user-images.githubusercontent.com/37764504/83961884-9f740200-a8d2-11ea-9ac4-c53cfea851be.GIF)

```java
//체크 여부를 저장하는 boolean array 선언
boolean[] selectedItems = {false, false, false, false};
...중략 ...
.setMultiChoiceItems(R.array.foods, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {  
    @Override  
  public void onClick(DialogInterface dialog, int which, boolean isChecked) {  
        selectedItems[which] = isChecked;  
  }  
})
```
**5. 커스텀 대화상자** : final layout 변수로 레이아웃 inflation 후 builder.setView(layout);

## 프로그레스바
> 어떤 작업의 진행 상태를 보여줄 때 쓰는 위젯
> 
> 
> ![dfjdf](https://user-images.githubusercontent.com/37764504/56305836-fa511400-617b-11e9-9b0e-11e14285e9ec.PNG)
> 
> ![fgjfgj](https://user-images.githubusercontent.com/37764504/56305825-f6bd8d00-617b-11e9-952e-470060a2eb31.PNG)
>   


## 시크바
> ![dfhdf](https://user-images.githubusercontent.com/37764504/56454572-f1d02780-638d-11e9-958b-2210aea1a546.PNG)


## 드로어블

> 뷰에 설정할 수 있는 객체이며 그 위에 그래픽 그리기 가능

### 드로어블 종류

- 비트맵 드로어블 : 이미지 파일을 보여줄 때 사용 비트맵 그래픽 파일(png, jpg, gif)를 사용해서 생성
- 상태 드로어블 : 상태별로 다른 비트맥 그래픽을 참조
- 셰이프 드로어블 : 색상과 그라데이션 등 도형 모양을 상세히 설정 가능

### 상태 드로어블 만들기

- 터치하면 이미지가 바뀌는 드로어블
```xml
<selector>  
<!-- drawable의 최상위 태그 -->

 <item android:state_pressed="true"
 android:drawable="@drawable/finger_pressed" />   
 
 <!-- state_pressed="true": 눌렸을 때 -->
 
 <item android:drawable="@drawable/finger" />  
 
</selector>
```

### 셰이프 드로어블

 1. 기본

![drawable1](https://user-images.githubusercontent.com/37764504/56054687-702b3900-5d92-11e9-93cc-553d7ae16ae5.PNG)

```xml
<?xml version="1.0" encoding="utf-8"?>  
<shape xmlns:android="http://schemas.android.com/apk/res/android"  
  android:shape="rectangle">  

<!-- shape= : 도형 모양 -->
  
 <size android:width="200dp" android:height="120dp"/>   
 <stroke android:width="1dp" android:color="#0000ff"/> 
<!-- stroke: 테두리 -->

 <solid android:color="#aaddff"/>  
<!-- solid: 채움색 -->
 
 <padding android:bottom="1dp"/>
 <!-- padding: 테두리 안쪽 공간 띄우기 -->
  
</shape>
```

 2. 그라데이션

![drawable2](https://user-images.githubusercontent.com/37764504/56054731-8e913480-5d92-11e9-9085-2e5a01e57ff4.PNG)
```xml
<?xml version="1.0" encoding="utf-8"?>  
<shape xmlns:android="http://schemas.android.com/apk/res/android">  
  
	 <gradientandroid:startColor="#7288DB"  
	  android:centerColor="#3250B4"  
	  android:endColor="#254095"  
	  android:angle="90"  
	  android:centerY="0.5"/>
	  <!-- gradientandroid: 그라데이션 드로어블 객체
		  시작, 중간, 끝 컬러 지정 -->  
	 <corners android:radius="2dp" />  
  </shape>
```

3. 투명한 드로어블

```xml
<?xml version="1.0" encoding="utf-8"?> 
<!-- 드로어블 중첩 -->
<layer-list
xmlns:android="http://schemas.android.com/apk/res/android">  
 <item> 
	 <shape android:shape="rectangle">  
	 <stroke android:width="1dp" android:color="#BE55DA"/>  
	 <solid android:color="#00000000"/>
   
 <!-- #00000000 : 투명한 색 -->
 
	 <size android:width="200dp" android:height="100dp"/>  
	 </shape> 
 </item>  
 
 <item android:top="1dp" android:bottom="1dp"  
  android:right="1dp" android:left="1dp">  

<!-- top bottom right left : 테두리 선으로부터 바깥으로 얼마만큼 공간을 띄울 것인지? -->

	<shape android:shape="rectangle">  
		 <stroke android:width="1dp" android:color="#FF55DA"/>  
		<solid android:color="#00000000"/>  
	</shape> 
	</item>
 </layer-list>
```

## 선택 위젯
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


