package wpam.recognizer;

import android.app.ListActivity;
import android.os.Bundle;

public class HistoryActivity extends ListActivity 
{
    private HistoryRowAdapter historyRowAdapter;	
	
	@Override
     public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.history);
		
		this.historyRowAdapter = new HistoryRowAdapter(this, R.layout.history_row);
        setListAdapter(this.historyRowAdapter);
		
	}
}
