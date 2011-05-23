package wpam.recognizer;


public class Controller 
{
	boolean started;
	
	RecordTask recordTask;
	
	MainActivity mainActivity;
	
	public Controller(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity; 
	}


	public void changeState() 
	{
		if (started == false)
		{
			started = true;
			
			mainActivity.setStateButtonText("Stop");
			mainActivity.setEnabled(true);
			
			recordTask = new RecordTask(this);
			recordTask.execute();
		} else {
			started = false;
			
			mainActivity.setStateButtonText("Start");
			mainActivity.setEnabled(false);
			
			recordTask.cancel(true);
		}
	}


	public boolean isStarted() {
		return started;
	}


	public int getAudioSource() {
		return mainActivity.getAudioSource();
	}


	public void updateSpectrum(Spectrum spectrum) 
	{
		spectrum.normalize();
		mainActivity.drawSpectrum(spectrum);
		
		Recognizer recognizer = new Recognizer(spectrum);
		mainActivity.setAciveKey(recognizer.getRecognizedKey());
		
	}


	public void clear() {
		mainActivity.clearText();
	}
}
