package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar !=null) {
            setSupportActionBar(toolbar);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content , new SettingsFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            NavUtils.navigateUpFromSameTask(this);

        return super.onOptionsItemSelected(item);
    }
}
