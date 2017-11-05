package android.vericoin.info.veriumminer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PoolCredentialsActivity extends AppCompatActivity {

    public static String POOL_ID_KEY = "pool-name";
    public static String POOL_NAME_KEY = "pool-id";
    public static String POOL_ADDRESS_KEY = "pool-address";
    public static String POOL_WORKER_KEY = "pool-worker";
    public static String POOL_USERNAME_KEY = "pool-username";
    public static String POOL_PASSWORD_KEY = "pool-password";

    private EditText mAddress;
    private EditText mWorker;
    private EditText mUserName;
    private EditText mPassword;
    private Button mBtnSave;

    private Toolbar mToolbar;
    private int mPoolId;
    private String mPoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_credentials);

        if(savedInstanceState == null) {
            mPoolId = getIntent().getIntExtra(POOL_ID_KEY, -1);
            mPoolName = getIntent().getStringExtra(POOL_NAME_KEY);
        }
        else {
            mPoolId = savedInstanceState.getInt(POOL_ID_KEY);
            mPoolName = savedInstanceState.getString(POOL_NAME_KEY);
            mAddress.setText(savedInstanceState.getString(POOL_ADDRESS_KEY));
            mWorker.setText(savedInstanceState.getString(POOL_WORKER_KEY));
            mUserName.setText(savedInstanceState.getString(POOL_USERNAME_KEY));
            mPassword.setText(savedInstanceState.getString(POOL_PASSWORD_KEY));
        }

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(mPoolName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddress = findViewById(R.id.address);
        mWorker = findViewById(R.id.worker_name);
        mUserName = findViewById(R.id.user_name);
        mPassword = findViewById(R.id.password);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences(MainActivity.PREF_POOL, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(POOL_ID_KEY, mPoolId);
                editor.putString(POOL_NAME_KEY, mPoolName);
                editor.putString(POOL_ADDRESS_KEY, mAddress.getText().toString());
                editor.putString(POOL_WORKER_KEY, mWorker.getText().toString());
                editor.putString(POOL_USERNAME_KEY, mUserName.getText().toString());
                editor.putString(POOL_PASSWORD_KEY, mPassword.getText().toString());

                // Commit the edits!
                editor.commit();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(POOL_ID_KEY, mPoolId);
        outState.putString(POOL_NAME_KEY, mPoolName);
        outState.putString(POOL_ADDRESS_KEY, mAddress.getText().toString());
        outState.putString(POOL_WORKER_KEY, mWorker.getText().toString());
        outState.putString(POOL_USERNAME_KEY, mUserName.getText().toString());
        outState.putString(POOL_PASSWORD_KEY, mPassword.getText().toString());

        super.onSaveInstanceState(outState);
    }
}
