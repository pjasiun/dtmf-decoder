package wpam.recognizer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Controller 
{
	private boolean started;
	
	private RecordTask recordTask;	
	private RecognizerTask recognizerTask;	
	private MainActivity mainActivity;

	private Character lastValue;
		
	public Controller(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity;
	}

	public void changeState() 
	{
		if (started == false)
		{
			started = true;
			
			lastValue = ' ';
			
			BlockingQueue<Spectrum> blockingQueue = new LinkedBlockingQueue<Spectrum>();
			
			mainActivity.start();
			
			recordTask = new RecordTask(this,blockingQueue);
			
			recognizerTask = new RecognizerTask(this,blockingQueue);
			
			recordTask.execute();
			recognizerTask.execute();
		} else {
			started = false;
			
			mainActivity.stop();
			
			recordTask.cancel(true);
			
		}
	}

	public void clear() {
		mainActivity.clearText();
	}

	public boolean isStarted() {
		return started;
	}


	public int getAudioSource()
	{
		return mainActivity.getAudioSource();
	}
	
	public void spectrumReady(Spectrum spectrum) 
	{
		mainActivity.drawSpectrum(spectrum);
	}

	public void keyReady(char key) 
	{
		mainActivity.setAciveKey(key);
		
		if(key != ' ')
			if(lastValue != key)
				mainActivity.addText(key);
		
		lastValue = key;
	}
}
