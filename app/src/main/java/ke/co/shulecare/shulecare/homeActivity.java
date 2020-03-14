package ke.co.shulecare.shulecare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class homeActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    String url = "https://google.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        webView = (WebView) findViewById(R.id.login_and_home_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
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

    }
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error)
        {
            final AlertDialog.Builder mybuilder = new AlertDialog.Builder(getApplicationContext());
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

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
