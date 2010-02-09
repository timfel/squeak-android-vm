package org.squeak.android;

import android.app.Activity;
import android.os.Bundle;

import org.squeak.android.SqueakVM;
import org.squeak.android.SqueakView;

public class SqueakActivity extends Activity {
	SqueakVM vm;
	SqueakView view;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("onCreate");

    	/* stupid setup dance but I'm not sure who is going to need what here */
    	vm = new SqueakVM();
    	vm.context = this;
    	view = new SqueakView(this);
    	view.vm = vm;
    	vm.view = view;
  
    	vm.loadImage("android.image", 16*1024*1024);
    	super.onCreate(savedInstanceState);
        setContentView(view);
    }
}
