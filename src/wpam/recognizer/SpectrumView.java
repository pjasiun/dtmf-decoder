package wpam.recognizer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class SpectrumView 
{
	ImageView imageView;
	
	Bitmap bitmap;
	Canvas canvas;
	Paint paint;
	
	public void setImageView(ImageView imageView) 
	{
		this.imageView = imageView;
		
		bitmap = Bitmap.createBitmap((int) 512, (int) 100, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		paint = new Paint();
		this.imageView.setImageBitmap(bitmap);
	}

	public void draw(Spectrum spectrum) {
		canvas.drawColor(Color.BLACK);

		for (int i = 0; i < spectrum.length(); ++i) 
		{
			int downy = (int) (100 - (spectrum.get(i) * 100));
			
			int upy = 100;

			if(i >= 40 && i <= 65)
				paint.setColor(Color.rgb(130, 130, 130));
			else if(i >= 75 && i <= 100)
				paint.setColor(Color.rgb(130, 130, 130));
			else
				paint.setColor(Color.WHITE);
			
			canvas.drawLine(i, downy, i, upy, paint);
		}
		
		paint.setColor(Color.RED);
		
		SpectrumFragment fragment = new SpectrumFragment(80, 200, spectrum);
		boolean[] distincts = fragment.getDistincts();

		int averageLineLevel = (int)(100 - fragment.getAverage() * 2 * 100);
		canvas.drawLine(0, averageLineLevel, 500, averageLineLevel, paint);
		
		imageView.invalidate();
	}
}
