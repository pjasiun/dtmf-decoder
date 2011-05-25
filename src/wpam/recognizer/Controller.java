package wpam.recognizer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Controller 
{
	private boolean started;
	
	private RecordTask recordTask;	
	private RecognizerTask recognizerTask;	
	private MainActivity mainActivity;
	BlockingQueue<DataBlock> blockingQueue;

	private Character lastValue;
		
	public Controller(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity;
	}

	public void changeState() 
	{
		if (started == false)
		{
			
			lastValue = ' ';
			
			blockingQueue = new LinkedBlockingQueue<DataBlock>();
			
			mainActivity.start();
			
			recordTask = new RecordTask(this,blockingQueue);
			
			recognizerTask = new RecognizerTask(this,blockingQueue);
			
			recordTask.execute();
			recognizerTask.execute();
			
			started = true;
		} else {
			
			mainActivity.stop();
			
			recognizerTask.cancel(true);
			recordTask.cancel(true);
			
			started = false;
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
	
	public void debug(String text) 
	{
		mainActivity.setText(text);
	}
}
