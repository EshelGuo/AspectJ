package script.android.aspect.demo;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2020/11/21 16:28
 * <br>desc: TODO
 */
@Aspect
public class MainActivityNumberAspect {
	public static final String TAG = "NumberAspect";

	@After("set(int demo.android.aspectj.MainActivity.mNumber) && args(target)")
	public void numberChanged(JoinPoint joinPoint, Object target){
		Log.i(TAG, "numberChanged() called with: target = " + target);
	}

	@After("call(void demo.android.aspectj.MainActivity.onResume(..)) && this(self) && target(target)")
	public void invokeOnResume(JoinPoint jp, Object self, Object target){
		Log.i(TAG, "invokeOnResume() called with: jp = [" + jp + "], self = [" + self + "], target = [" + target + "]");
	}
}
