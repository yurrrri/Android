package ddwucom.mobile.diaglogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int selectedindex = 0;

    //체크 여부를 저장하기 위한 배열
    boolean[] selectedItems = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final ConstraintLayout orderLayout = (ConstraintLayout) View.inflate(this, R.layout.order_layout, null);
                //final int selectedIndex;
                //객체 이름 생략 : 체이닝
                builder.setTitle("대화 상자 제목")
                        //.setMessage("대화상자 메시지")
                        //대화상자에 목록 띄우기
                        //values 폴더에 만들지 않아도 여기에 직접 String 객체 사용 가능
//                        .setItems(R.array.foods, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String[] foods = getResources().getStringArray(R.array.foods);
//                                Toast.makeText(MainActivity.this, "선택 :"+foods[which], Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setSingleChoiceItems(R.array.foods, selectedindex, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                selectedindex = which;
//                            }
//                        })
                        .setMultiChoiceItems(R.array.foods, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectedItems[which] = isChecked;
                            }
                        })
                        //.setView(orderLayout)
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //그냥 this하면 객체가 대화상제 자체이므로 오류 발생.
                                //앞에 명시적으로 MainActivity 붙여주기
                                String[] foods = getResources().getStringArray(R.array.foods);
                                Toast.makeText(MainActivity.this, foods[selectedindex] + "(을)를 선택", Toast.LENGTH_SHORT).show();
//                                String result = "선택 :";
//                                for (int i=0; i<selectedItems.length; i++){
//                                    if (selectedItems[i])
//                                        result += foods[i] + " ";
//                                }
//                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                                EditText edtProduct = orderLayout.findViewById(R.id.edtProduct);
                                EditText edtQuantity = orderLayout.findViewById(R.id.edtQuantity);
                                CheckBox cbPayment = orderLayout.findViewById(R.id.cbPayment);

                                String result = edtProduct.getText().toString();
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("대기", null)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //대화상자 종료
                                finish();
                            }
                        })
                        //대화상자 안의 버튼을 눌러야만 종료
                        .setCancelable(false)
                        .show(); //대화상자 만들고 표시

                //Dialog dig = builder.create();
                //대화상자 외부를 눌러도 대화상자가 닫히지 않음
                //dig.setCanceledOnTouchOutside(false);
                //dig.show(); //builder.show()는 만들고 보여주기까지 한번에 해줌
                break;
        }
    }
}