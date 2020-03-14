package ke.co.shulecare.shulecare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;


public class login_n_homefrag extends Fragment{
    WebView webView;
    ProgressBar progressBar;
    String url = "https://google.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.loginfrag, container, false);

        webView = (WebView) view.findViewById(R.id.login_and_home_webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(10);


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //allowing for javascript pages to be loaded too

       webView.setWebViewClient(new WebViewClient(){
           @Override
           public void onPageStarted(WebView view, String url, Bitmap favicon) {
               progressBar.setVisibility(View.VISIBLE);
               super.onPageStarted(view, url, favicon);
           }
           @Override
           public void onPageFinished(WebView view, String url) {
               progressBar.setVisibility(View.GONE);
               super.onPageFinished(view, url);
           }
       });


       return view;
    }
    private class MyWebViewClient extends WebViewClient
            {
                @Override
                public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error)
                {
                    final AlertDialog.Builder mybuilder = new AlertDialog.Builder(getContext());
                    mybuilder.setMessage(R.string.permision);

                    //setting messagedialog for allowing the permission.
                    mybuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.proceed();
                        }
                    });
                    //To stop from allowing the permission.
                    mybuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                     {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            handler.cancel();
                        }
                    });
                    //creating alert dialog
                    final AlertDialog dialog = mybuilder.create();
                    dialog.show();
                }
            }

}
