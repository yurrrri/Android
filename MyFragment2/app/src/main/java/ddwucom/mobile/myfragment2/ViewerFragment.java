package ddwucom.mobile.myfragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewerFragment extends Fragment {

    ImageView imageView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        return rootView;
    }

    public void setImage(int index){
        if (index==0){
            imageView.setImageResource(R.drawable.starbucks_1);
        }
        else if (index==1){
            imageView.setImageResource(R.drawable.starbucks_2);
        }
        else if (index==2){
            imageView.setImageResource(R.drawable.starbucks_3);
        }
    }
}
