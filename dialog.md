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

[**2~5번까지의 실습예제**](https://github.com/yurrrri/Android_study/tree/master/DialogTest)

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
