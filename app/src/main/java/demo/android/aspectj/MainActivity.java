package demo.android.aspectj;

import android.os.Bundle;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import script.android.aspect.DebugTrace;

@Aspect
public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity___";
	private int mNumber;

	@Override
	@DebugTrace
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mNumber = 100;
	}

	@After("set(int demo.android.aspectj.MainActivity.mNumber)")
	public void numberChanged(JoinPoint joinPoint){

		Log.i(TAG, "numberChanged() called with: mNumber = [" + mNumber + "]");
	}
}