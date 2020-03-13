package ke.co.shulecare.shulecare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class login_n_homefrag extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout2;
 Button button_login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.loginfrag, container, false);
       button_login = view.findViewById(R.id.button_login);
       button_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentTransaction ft_login = getFragmentManager().beginTransaction();
               ft_login.replace(R.id.fragment_interchange_area, new homefrag());
               ft_login.commit();
               button_login.setVisibility(View.GONE);
           }
       });

       return view;
    }

}
