package ddwucom.mobile.test08.listView_project;

import java.util.ArrayList;

public class SubjectManager {
    private ArrayList<String> subjectList;

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
