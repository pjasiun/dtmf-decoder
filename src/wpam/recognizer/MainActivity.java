package wpam.recognizer;

import pl.polidea.apphance.Apphance;
import android.R.string;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button stateButton;

	private Button clearButton;
	
	private EditText recognizeredText;

	private SpectrumView spectrumView;
	
	private NumericKeyboard numKeyboard;
	
	Controller controller; 
	
	public static final String APP_KEY = "806785c1fb7aed8a867039282bc21993eedbc4e4";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Apphance.start(this, APP_KEY);
		
		setContentView(R.layout.main);
		
		controller = new Controller(this);

		stateButton = (Button)this.findViewById(R.id.stateButton);		
		stateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				controller.changeState();
			}
		});
		
		clearButton = (Button)this.findViewById(R.id.clearButton);		
		clearButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				controller.clear();
			}
		});

		spectrumView = new SpectrumView(); 
		spectrumView.setImageView((ImageView) this.findViewById(R.id.spectrum));
				
		recognizeredText = (EditText)this.findViewById(R.id.recognizeredText);
		recognizeredText.setFocusable(false);
		
		numKeyboard = new NumericKeyboard();		
		numKeyboard.add('0', (Button)findViewById(R.id.button0));
		numKeyboard.add('1', (Button)findViewById(R.id.button1));
		numKeyboard.add('2', (Button)findViewById(R.id.button2));
		numKeyboard.add('3', (Button)findViewById(R.id.button3));
		numKeyboard.add('4', (Button)findViewById(R.id.button4));
		numKeyboard.add('5', (Button)findViewById(R.id.button5));
		numKeyboard.add('6', (Button)findViewById(R.id.button6));
		numKeyboard.add('7', (Button)findViewById(R.id.button7));
		numKeyboard.add('8', (Button)findViewById(R.id.button8));
		numKeyboard.add('9', (Button)findViewById(R.id.button9));
		numKeyboard.add('0', (Button)findViewById(R.id.button0));
		numKeyboard.add('#', (Button)findViewById(R.id.buttonHash));
		numKeyboard.add('*', (Button)findViewById(R.id.buttonAsterisk));
		
		setEnabled(false);
	}

	public void setStateButtonText(String text) 
	{
		stateButton.setText(text);
	}
	
	public int getAudioSource()
	{
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
		
		if (telephonyManager.getCallState() != TelephonyManager.PHONE_TYPE_NONE)
			return MediaRecorder.AudioSource.VOICE_DOWNLINK;
		
		return MediaRecorder.AudioSource.MIC;
	}

	public void drawSpectrum(Spectrum spectrum) {
		spectrumView.draw(spectrum);		
	}
	
	public void clearText() 
	{
		recognizeredText.setText("");
	}
	
	public void addText(String c) 
	{//Todo: zmieniæ na char
		recognizeredText.setText(c);
	}
	
	public void setEnabled(boolean enabled) 
	{
		recognizeredText.setEnabled(enabled);
		numKeyboard.setEnabled(enabled);
	}
	public void setAciveKey(char key)
	{
		numKeyboard.setActive(key);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.icon:     Toast.makeText(this, "You pressed the icon!", Toast.LENGTH_LONG).show();
	                            break;
	        case R.id.text:     Toast.makeText(this, "You pressed the text!", Toast.LENGTH_LONG).show();
	                            break;
	        case R.id.icontext: Toast.makeText(this, "You pressed the icon and text!", Toast.LENGTH_LONG).show();
	                            break;
	    }
	    return true;
	}
}