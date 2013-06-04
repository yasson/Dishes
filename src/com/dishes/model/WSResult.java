package com.dishes.model;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;


public class WSResult {
    private String state;//
    private List<Object> result;//

    public WSResult() {

    }

    public WSResult(SoapObject so) {
	parseSoapObject(so);
    }

    private void parseSoapObject(SoapObject so) {
	// TODO Auto-generated method stub
	state = so.getPrimitivePropertySafelyAsString("state");
	result = new ArrayList<Object>();
	switch (Integer.parseInt(state)) {
	case 201:
	    SoapObject object = (SoapObject) so.getPropertySafely("result");
	    result.add(object);
	    break;
	case 202:
	    for (int i = 0; i < so.getPropertyCount(); i++) {
		PropertyInfo info = new PropertyInfo();
		so.getPropertyInfo(i, info);
		if (info.getName().contains("result")) {
		    result.add(info.getValue());
		}
	    }
	    break;
	case 203:
	    String string = ((SoapPrimitive) so.getPropertySafely("result"))
		    .toString();
	    result.add(string);
	    break;
	case 555:
	    for (int i = 0; i < so.getPropertyCount(); i++) {
		PropertyInfo info = new PropertyInfo();
		so.getPropertyInfo(i, info);
		if (info.getName().contains("result")) {
		    result.add(info.getValue());
		}
	    }
	    break;
	default:
	    break;
	}
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public List<Object> getResult() {
	return result;
    }

    public void setResult(List<Object> result) {
	this.result = result;
    }

    public List<Object> parseResult(WSResult resobj) {
	if (null == resobj || null == resobj.getState()
		|| "".equals(resobj.getState())) {
	    return null;
	}
	SoapObject sopobtmp = null;
	List<Object> retrun_list = new ArrayList<Object>();
	int status = Integer.parseInt(resobj.getState());
	switch (status) {
	case 201:
	    sopobtmp = (SoapObject) resobj.getResult().get(0);
	    retrun_list.add(sopobtmp);
	    break;
	case 202:
	    for (int i = 0; i < resobj.getResult().size(); i++) {
		sopobtmp = (SoapObject) resobj.getResult().get(i);
		retrun_list.add(sopobtmp);
	    }
	    break;
	case 203:
	    SoapPrimitive sopprim = null;
	    sopprim = (SoapPrimitive) resobj.getResult().get(0);
	    String str = sopprim.toString();
	    retrun_list.add(str);
	    break;
	case 401:
	    break;
	case 402:
	    break;
	case 200:
	    break;
	default:
	    break;
	}
	return retrun_list;
    }

}
