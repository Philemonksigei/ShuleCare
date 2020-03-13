package ke.co.shulecare.shulecare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class homefrag extends Fragment
    {
        WebView webView;
        String url = "https://www.google.com";
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
            {
               View view = inflater.inflate(R.layout.homefrag, container, false);
                webView = (WebView) view.findViewById(R.id.webview_main);
                WebSettings webSettings =webView.getSettings();
                webSettings.setJavaScriptEnabled(true); //allowing for javascript pages to be loaded too
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(url);


               return view;
            }

        private class MyWebViewClient extends WebViewClient {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
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
                mybuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                //creating alert dialog
                final AlertDialog dialog = mybuilder.create();
                dialog.show();
            }
        }
    }
