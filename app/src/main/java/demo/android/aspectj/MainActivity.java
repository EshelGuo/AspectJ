package demo.android.aspectj;

import android.os.Bundle;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import script.android.aspect.trace.DebugTrace;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity___";
	private int mNumber;

	@Override
	@DebugTrace
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		onResume();
		File filesDir = getExternalFilesDir(null);
		try {
			new File(filesDir, "nul.txt").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileInputStream is = new FileInputStream(new File(filesDir, "aw_cali_lra.txt"));
			byte[] bytes = new byte[is.available()];
			removeInvalidChar(bytes);
			int read = is.read(bytes);
//			byte b = 0;
//			char c = (char) b;
//			System.out.println(c);

			String s = new String(bytes, 0, bytes.length, "GBK");
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				System.out.println(c + "-" + (int) c);
				if(c == '\u0000'){
					System.out.println("NUL");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String s = null;
//		s = new String(new byte[]{48,48}, 0, 2, Charset.defaultCharset());
//		System.out.println(s);
	}

	private byte[] removeInvalidChar(byte[] bytes) {

		int first0Index = -1;
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			if(b == 0 && first0Index == -1) {
				first0Index = i;
			}else if(first0Index != -1){
				bytes[first0Index] = b;
				bytes[i] = 0;
				first0Index++;
			}
		}

		if(first0Index == -1) return bytes;
		byte[] result = new byte[first0Index];
		for (int i = 0; i < first0Index; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mNumber = 100;
	}

}