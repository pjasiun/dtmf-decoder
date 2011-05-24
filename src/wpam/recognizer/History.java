package wpam.recognizer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class History implements Serializable {

	private ArrayList<HistoryItem> history;
	
	private Context context;
	
	private static String filename = "history";
	
	public History(Context context) 
	{
		this.context = context;
		
		history = new ArrayList<HistoryItem>();		
	}
	
	public void save()
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			out = new ObjectOutputStream(fos);
			out.writeObject(history);
			out.close();
		}
		catch(IOException ex)
		{
			Log.e("IOException", "History.save()");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load()
	{
		try {
		    FileInputStream fin =  context.openFileInput(filename);
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    history = (ArrayList<HistoryItem>) ois.readObject();
		    ois.close();
		}
		catch (Exception e)
		{
			Log.e("Exception", "History.load()");
		}
	}
	
	public HistoryItem get(int index) 
	{
		return history.get(index);
	}
	
	public void add(String text)
	{
		if(!text.equals(""))
			history.add(0, new HistoryItem(text));
	}

	public int getCount() {
		return history.size();
	}
}
