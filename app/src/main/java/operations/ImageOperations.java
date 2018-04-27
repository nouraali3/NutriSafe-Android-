package operations;

/**
 * Created by user on 23/04/2018.
 */

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.params.BasicHttpParams;


public class ImageOperations
{
    static String scanResult;

    public ImageOperations()
    {
    }

    public String scanIngredients(File image, List<String> allergies) throws FileNotFoundException {
        scanResult = new String();

        RequestParams rp = new RequestParams();

        rp.put("image",image);
        rp.put("allergies",allergies);
        Log.d("ImageOperations ",rp.toString());
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://nouraali.heliohost.org/cgi-bin/python.py?allergies=aaaaa&image=iiiii",new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("ImageOperations ","status code is "+statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString)
            {
                Log.d("ImageOperations ","status code is "+statusCode+"success in client.get and the headers are  "+headers.toString());
                scanResult=responseString;
            }
        });
        return scanResult;
    }
}

