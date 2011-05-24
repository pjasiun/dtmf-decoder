package wpam.recognizer;

import java.util.concurrent.BlockingQueue;

import android.os.AsyncTask;

public class RecognizerTask extends AsyncTask<Void, Object, Void> {
	
	private Controller controller;

	private BlockingQueue<Spectrum> blockingQueue;
	
	private Recognizer recognizer;

	public RecognizerTask(Controller controller, BlockingQueue<Spectrum> blockingQueue) 
	{
		this.controller = controller;
		this.blockingQueue = blockingQueue;
		
		recognizer = new Recognizer();
	}

	@Override
	protected Void doInBackground(Void... params)
	{
		while(controller.isStarted())
		{
			try {
				Spectrum spectrum = blockingQueue.take();
				
				spectrum.normalize();				
				
				StatelessRecognizer statelessRecognizer = new StatelessRecognizer(spectrum);
				
				Character key = recognizer.getRecognizedKey(statelessRecognizer.getRecognizedKey());
								
				publishProgress(spectrum, key);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		return null;
	}
	
	protected void onProgressUpdate(Object... progress) 
	{
		Spectrum spectrum = (Spectrum)progress[0];
		controller.spectrumReady(spectrum);
		
		Character key = (Character)progress[1];
		controller.keyReady(key);
    }
}
