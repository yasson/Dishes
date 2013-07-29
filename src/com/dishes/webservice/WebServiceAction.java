package com.dishes.webservice;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebServiceAction {

	public static SoapObject getSoapObject( String Wsdl, String methodName, Map<String, Object> inputmap, String NAMESPACE ) {

		// TODO Auto-generated method stub
		SoapObject reqSoapObject = new SoapObject( NAMESPACE, methodName );
		// 遍历inputmap
		Iterator iter = inputmap.entrySet().iterator();
		while( iter.hasNext() ) {
			Map.Entry entry = ( Map.Entry )iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			reqSoapObject.addProperty( ( String )key, val );
		}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope( SoapEnvelope.VER11 );
		envelope.bodyOut = reqSoapObject;
		envelope.dotNet = false;
		( new MarshalBase64() ).register( envelope );
		HttpTransportSE transport = new HttpTransportSE( Wsdl, 30000 );
		transport.debug = true;
		envelope.setOutputSoapObject( reqSoapObject );
		envelope.encodingStyle = "UTF-8";
		try {
			transport.call( NAMESPACE + methodName, envelope );
			if( envelope.getResponse() != null ) {
				SoapObject get_soap = ( SoapObject )envelope.getResponse();
				return get_soap;
			}
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch( XmlPullParserException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
