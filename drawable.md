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
[**1~3번 실습예제**](https://github.com/yurrrri/Android_study/tree/master/Drawable)

 1. 기본

![drawable1](https://user-images.githubusercontent.com/37764504/56054687-702b3900-5d92-11e9-93cc-553d7ae16ae5.PNG)

rect_shape.xml
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
