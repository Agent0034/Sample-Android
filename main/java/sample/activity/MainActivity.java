package sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.retrofitexample.R;
import com.andexert.retrofitexample.app.App;
import com.andexert.retrofitexample.rest.model.ApiResponse;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    protected String businessName = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveCoordinates(businessName); // call network function
    }

    public void retrieveCoordinates(String businessName){

        getCoordinateList getCoordinateList = RestClient.getCoordinateListInterface();
        getCoordinateList.getCoorList(businessName, new Callback<List<ApiResponse>>() {



            public void failure(RetrofitError arg0) {

                if (arg0.isNetworkError()) {
                    Log.d("NetworkError");
                        Intent i = new Intent(context, NetworkError.class);
                        context.startActivity(i);
                        finish();
                }
            }
            @Override
            public void success(List<ApiResponse> arg0, Response arg1) {
               //success logic
               //process data
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
