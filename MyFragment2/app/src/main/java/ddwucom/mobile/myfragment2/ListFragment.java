package ddwucom.mobile.myfragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    Button btnCream;
    Button btnVanilla;
    Button btnJamon;
    MainActivity activity;

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        btnCream = rootView.findViewById(R.id.btnCream);
        btnVanilla = rootView.findViewById(R.id.btnVanilla);
        btnJamon = rootView.findViewById(R.id.btnJamon);

        btnCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onImageChange(0);
            }
        });

        btnVanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onImageChange(1);
            }
        });

        btnJamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onImageChange(2);
            }
        });

        return rootView;
    }
}
