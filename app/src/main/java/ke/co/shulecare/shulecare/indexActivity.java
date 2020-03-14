package ke.co.shulecare.shulecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class indexActivity extends AppCompatActivity
{
SwipeRefreshLayout swipeRefreshLayout3;
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
          {
                super.onCreate(savedInstanceState);
                //load the default fragment
               FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
               tx.replace(R.id.fragment_interchange_area, new login_n_homefrag());
               tx.commit();
                setContentView(R.layout.index);

                webView= findViewById(R.id.login_and_home_webview);
          //check if there is a network connection
                          if(haveNetwork())
                          {   //if network is there, show login frag
                              // login frag carries the login webview
                              FragmentTransaction ft_internet_present = getSupportFragmentManager().beginTransaction();
                              ft_internet_present.replace(R.id.fragment_interchange_area, new login_n_homefrag());
                              ft_internet_present.commit();
                          }
                          else if(!haveNetwork())
                          {//show no internet frag
                              FragmentTransaction ft_internet_miss = getSupportFragmentManager().beginTransaction();
                              ft_internet_miss.replace(R.id.fragment_interchange_area, new no_internetfrag());
                              ft_internet_miss.commit();
                          }
  //swipe refresh to try if connection has been restored
              swipeRefreshLayout3 = findViewById(R.id.refreshlayout_index);
              swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
              {
                  @Override
                  public void onRefresh()
                              {
                                          if(haveNetwork())
                                          {   //if network is there, show login frag
                                              // login frag carries the login webview
                                              FragmentTransaction ft_internet_present = getSupportFragmentManager().beginTransaction();
                                              ft_internet_present.replace(R.id.fragment_interchange_area, new login_n_homefrag());
                                              ft_internet_present.addToBackStack(null).commit();
                                          }
                                          else if(!haveNetwork())
                                          {//show no internet frag
                                              FragmentTransaction ft_internet_miss = getSupportFragmentManager().beginTransaction();
                                              ft_internet_miss.replace(R.id.fragment_interchange_area, new no_internetfrag());
                                              ft_internet_miss.addToBackStack(null).commit();
                                          }
                                  Toast.makeText(getApplicationContext(), "Internet Connection Refreshed", Toast.LENGTH_SHORT).show();
                                  swipeRefreshLayout3.setRefreshing(false);
                              }
              });
          }

    private boolean haveNetwork()
            {
                boolean have_WIFI = false;
                boolean have_Mobiledata =false;

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                for(NetworkInfo info:networkInfos)
                {
                    if(info.getTypeName().equalsIgnoreCase("WIFI"))
                    {
                        if(info.isConnected())
                            have_WIFI = true;
                    }
                    else if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                    {
                        if(info.isConnected())
                            have_Mobiledata = true;
                    }
                }
                return  have_Mobiledata || have_WIFI;
            }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
