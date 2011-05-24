package wpam.recognizer;

import math.FFT;

public class DataBlock 
{
	private double[] block;

	public DataBlock(short[] block, int blockSize, int bufferReadSize)
	{
		this.block = new double[blockSize];
		
		for (int i = 0; i < blockSize && i < bufferReadSize; i++) {
			this.block[i] = (double) block[i];
		}
	}
	
	public Spectrum FFT()
	{
		return new Spectrum(FFT.magnitudeSpectrum(block));
	}
}
