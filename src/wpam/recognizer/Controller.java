package wpam.recognizer;


public class Controller 
{
	private boolean started;
	
	private RecordTask recordTask;	
	private MainActivity mainActivity;
	
	private Recognizer recognizer;
	private Character lastValue;
	
	public Controller(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity;
		recognizer = new Recognizer();
	}

	public void changeState() 
	{
		if (started == false)
		{
			started = true;
			
			lastValue = ' ';
			
			mainActivity.start();
			
			recordTask = new RecordTask(this);
			recordTask.execute();
		} else {
			started = false;
			
			mainActivity.stop();
			
			recordTask.cancel(true);
			
		}
	}

	public void clear() {
		mainActivity.clearText();
	}
	
	public void updateSpectrum(Spectrum spectrum) 
	{
		spectrum.normalize();
		mainActivity.drawSpectrum(spectrum);
		
		StatelessRecognizer statelessRecognizer = new StatelessRecognizer(spectrum);
		
		Character key = recognizer.getRecognizedKey(statelessRecognizer.getRecognizedKey());
		
		mainActivity.setAciveKey(key);
		
		if(key != ' ')
			if(lastValue != key)
				mainActivity.addText(key);
		
		lastValue = key;
		
	}

	public boolean isStarted() {
		return started;
	}


	public int getAudioSource()
	{
		return mainActivity.getAudioSource();
	}

}
