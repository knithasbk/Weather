package com.example.tm.weather.View.Details;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tm.weather.R;
import com.example.tm.weather.View.MainAcivity.MainActivity;
import com.example.tm.weather.View.SettingsActivity.SettingsActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by TM on 28/10/2016.
 */


/**
 * Created by TM on 10/10/2016.
 */

public class DetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Context mContext;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

/* Handle toolbar for DetailsActivity.class */
        mToolbar = (Toolbar) findViewById(R.id.details_layout_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mContext = getApplicationContext();
        mToolbar.setTitle(null);
        Log.i("TEST_TRACE", "Verify step toolbar");

/*Using Data list position from RecycleView click to get Data for Details Layout*/
        //   arrayDataTest = new ArrayDataTest();
        //    weatherListDataArrayList = arrayDataTest.buildDataTest();
        /* Handle click action for Navigation button back to MainActivity.class */
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_chevron_left_black_24dp));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDrawableDetails(v);
            }
        });
        Log.i("TEST_TRACE", "Verify set Navigation button ");
        Log.i("TEST_TRACE", "End OnCreate DetailActivity.class");
        HandleDetailsFragment();

        /*Handle for share fb action */
        FacebookSdk.sdkInitialize(this);


    }

    /**
     * Create and infalte menu layout for Detaisl Layout
     * Get bundle data (int Position) from previous Activity
     */

    /*Add fragment to activity*/
    @Override
    protected void onResume() {
        HandleDetailsFragment();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        HandleDetailsFragment();

        super.onRestart();
    }

    public void HandleDetailsFragment() {
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION");
        Log.i("TEST_TRACE", "Position at DetailActivity.class: " + String.valueOf(position));
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setPosition(position);
        Log.i("TEST_TRACE", "Start OnCreate DetailActivity.class");

        Log.i("TEST_TRACE", "DetailActivity.class : 1 ");
        Log.i("TEST_TRACE", "Verify step Details layout");
        Log.i("TEST_TRACE", "Step 1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.i("TEST_TRACE", "Step 2");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.i("TEST_TRACE", "Step 3");
        fragmentTransaction.add(R.id.details_activity_id, detailsFragment);
        Log.i("TEST_TRACE", "Step 4");
        fragmentTransaction.commit();
        Log.i("TEST_TRACE", "Verify step Details Layout data");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("TEST_TRACE", "Start onCreateOptionsMenu DetailActivity.class");

        getMenuInflater().inflate(R.menu.details_layout, menu);
        Log.d("TEST_TRACE", "End onCreateOptionsMenu DetailActivity.class");

        return super.onCreateOptionsMenu(menu);
    }

    /* Handle settings click actions */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("TEST_TRACE", "Start onOptionsItemSelected DetailActivity.class");

        Intent intent;
        int id = item.getItemId();
        if (id == R.id.details_layout_action_setting) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.details_layout_action_share) {
            intent = new Intent(this, SharingActivity.class);
            startActivity(intent);
        }

        Log.d("TEST_TRACE", "End onOptionsItemSelected DetailActivity.class");
        return super.onOptionsItemSelected(item);


    }

    /*Function will be called when a Navigation button clicked event*/
    public void onClickDrawableDetails(View v) {
        Log.d("TEST_TRACE", "Start onClickDrawableDetails DetailActivity.class");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.d("TEST_TRACE", "End onClickDrawableDetails DetailActivity.class");

    }




/*
    private void takeScreenshot() {

        int now = 1;

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //          openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
    */
}

    /*Handle getDrawable for others android version - NOT USE */
       /*     public Drawable getDrawableNavigate() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    return getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp, this.getTheme());
                } else {
                    return getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
                }
            }
       */


