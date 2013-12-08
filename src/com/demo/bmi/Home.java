package com.demo.bmi;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class Home extends Activity {
	
	private long value_sex=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Spinner s = (Spinner)findViewById(R.id.spinner_sex);
		final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter2);
		s.setOnItemSelectedListener(spinner_listener);
		
		Button button=(Button)findViewById(R.id.button_submit);
		button.setOnClickListener(calcBMI);
				
	}

	private final OnItemSelectedListener spinner_listener = new OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,int position, long id){
			value_sex = parent.getItemIdAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	
	};

	private final OnClickListener calcBMI = new View.OnClickListener(){
		@SuppressLint("ShowToast")
		@Override
		public void onClick(View v){
	DecimalFormat nf= new DecimalFormat("0.00");
	EditText fieldheight = (EditText)findViewById(R.id.height);
	EditText fieldweight = (EditText)findViewById(R.id.weight);
	
	double height = Double.parseDouble(fieldheight.getText().toString())/100;
	double weight = Double.parseDouble(fieldweight.getText().toString());
	
	double BMI = weight/(height*height);

//	TextView result = (TextView)findViewById(R.id.result);
//	Toast.makeText(Home.this, "Your BMI is"+nf.format(BMI)+"性别"+nf.format(value_sex), 3).show();
//	result.setText("Your BMI is "+nf.format(BMI)+"性别："+nf.format(value_sex));
	
//	TextView fieldsuggest= (TextView)findViewById(R.id.suggest);
	int value;
	if(BMI>25)
		value = R.string.heavy;
	else if (BMI>18.5)
		value = R.string.standard;
	else
		value = R.string.light;
	//Toast.makeText(Home.this, "Your BMI is"+nf.format(BMI)+"性别\n"+nf.format(value_sex)+value, 3).show();
	Toast.makeText(Home.this, value, Toast.LENGTH_LONG).show();
	//fieldsuggest.setText(value);
	/*
	new AlertDialog.Builder(Home.this)
	.setTitle("About")
	.setMessage("Version 0.01")
	.setPositiveButton("确认",
	 new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub	
		}
	})
	.setNegativeButton("首页",
			new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					Uri url=Uri.parse("http://www.sina.com.cn");
					Intent intent=new Intent(Intent.ACTION_VIEW,url);
					startActivity(intent);
					
				}
			})
	.show();
	*/
	}
	};
	
	private static final int MENU_ABOUT= Menu.FIRST;
	private static final int MENU_QUIT = Menu.FIRST+1;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		menu.add(0, MENU_ABOUT, 0, "About")
		.setIcon(android.R.drawable.ic_dialog_info);
		menu.add(0, MENU_QUIT,0,"Home");
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case MENU_ABOUT:
			new AlertDialog.Builder(Home.this)
			.setTitle("About")
			.setMessage("Version 0.01")
			.setPositiveButton("确认",
			 new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub	
				}
			})
			.show();
			break;
		case MENU_QUIT:
			finish();
			break;
		case R.id.action_search:
		case R.id.action_settings:
		default:
			Toast.makeText(Home.this, item.toString(), 10).show();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	

}
