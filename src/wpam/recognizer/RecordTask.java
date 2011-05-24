package wpam.recognizer;

import java.util.concurrent.BlockingQueue;

import math.FFT;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.AsyncTask;

public class RecordTask extends AsyncTask<Void, Object, Void> {
	
	
	int frequency = 8000;
	int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

	int blockSize = 1024;
	
	Controller controller;
	BlockingQueue<Spectrum> blockingQueue;

	public RecordTask(Controller controller, BlockingQueue<Spectrum> blockingQueue) {
		this.controller = controller;
		this.blockingQueue = blockingQueue;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);

			AudioRecord audioRecord = new AudioRecord(controller.getAudioSource(), frequency, channelConfiguration, audioEncoding, bufferSize);

			short[] buffer = new short[blockSize];
			double[] toTransform = new double[blockSize];

			audioRecord.startRecording();

			while (controller.isStarted()) {
									
//				int bufferReadSize = audioRecord.read(buffer, 0, blockSize);
//				
//				DataBlock dataBlock = new DataBlock(buffer, bufferSize, bufferReadSize);
//
//				Spectrum spectrum = dataBlock.FFT();
				
				int bufferReadResult = audioRecord.read(buffer, 0,
						blockSize);
				
				
				for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
					toTransform[i] = (double) buffer[i];
				}
				
				//transformer.ft(toTransform);
				Spectrum spectrum = new Spectrum(FFT.magnitudeSpectrum(toTransform));
				
				blockingQueue.put(spectrum);
			}

			audioRecord.stop();
		} catch (Throwable t) {
			//Log.e("AudioRecord", "Recording Failed");
		}

		return null;
	}
}