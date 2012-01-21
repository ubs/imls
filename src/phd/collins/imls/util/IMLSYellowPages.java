package phd.collins.imls.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.tilab.wsig.WSIGConfiguration;
import com.tilab.wsig.store.WSIGService;
import com.tilab.wsig.store.WSIGStore;

public class IMLSYellowPages {

	protected static Map<String, Set<String>> operationsMap = new Hashtable<String, Set<String>>();
	
	private static Date lastRefreshTime = new Date();
	private static long refreshTimeLatency = 180000;
	private static WSIGService service;

	public static String findService(WSIGStore wsigStore, String strOperation){
		boolean refreshServices = false;
		String strServiceName = getServiceFromOperationsMap(strOperation);
		
		if (strServiceName != null){ service = wsigStore.getService(strServiceName); /*Test Service is up*/ }
		
		refreshServices  = ((strServiceName == null) || (service == null));
		
		if ( refreshServices || itsTimeToRefreshServices() ){
			findAllAvailableServices(wsigStore);
			strServiceName = getServiceFromOperationsMap(strOperation);
		}
		
		return strServiceName;
	}
	
	public static void addToOperationsMap(String operationName, String serviceName){
		operationName = operationName.toLowerCase();
		if (!operationsMap.containsKey(operationName)){
			Set<String> servicesSet = new HashSet<String>();
			operationsMap.put(operationName, servicesSet);
		}
		
		Set<String> servicesSet = operationsMap.get(operationName);
		if (!servicesSet.contains(serviceName)){ servicesSet.add(serviceName); }
	}
	
	public static void dumpOperationsMap(){
		Info.sout("DUMPING Operations Map, Size: " + operationsMap.size());
		Iterator<String> itOpMap = operationsMap.keySet().iterator();
		
		String strInfo = "";
		while(itOpMap.hasNext()) {
			String keyName = itOpMap.next();
			Set<String> servicesSet = operationsMap.get(keyName);
			Iterator<String> itServicesSet = servicesSet.iterator();
			while(itServicesSet.hasNext()) {
				strInfo = "Operation: " + keyName + " Service: " + itServicesSet.next();
				Info.sout(strInfo);
			}
		}
	}
	
	public static String getWSIGServiceURL(WSIGConfiguration wsigConfig){
		String strServiceURL = wsigConfig.getWsigUri();
		return strServiceURL;
	}
	
	protected static void findAllAvailableServices(WSIGStore wsigStore){
		Info.sout("Visiting findAllAvailableServices @ " + new Date());
		// Get services list
		WSIGService service;
		String serviceName = null, operationName = null;
		clearOperationsMap();
		
		Iterator<WSIGService> itServices = wsigStore.getAllServices().iterator();
		
		while(itServices.hasNext()) {
			service = (WSIGService)itServices.next();
			serviceName = service.getServiceName();
			
			Iterator<String> itOperations = service.getOperations().iterator();
			
			while(itOperations.hasNext()) {
				operationName = (String)itOperations.next();
				addToOperationsMap(operationName, serviceName);
			}
		}
		
		lastRefreshTime = new Date();
	}
	
	protected static String getServiceFromOperationsMap(String strOperation){
		String serviceName = null;
		strOperation = strOperation.toLowerCase();
		if (operationsMap.containsKey(strOperation)){
			Set<String> servicesSet = operationsMap.get(strOperation);
			String[] arrServiceNames = servicesSet.toArray(new String[0]);
			serviceName = arrServiceNames[new Random().nextInt(arrServiceNames.length)];
		}
		else {
			Info.serr("Error: Service [" + strOperation + "] Not found in Operations Map!");
		}
		return serviceName;
	}
	
	protected static boolean itsTimeToRefreshServices(){
		return ((new Date().getTime() - lastRefreshTime.getTime()) > refreshTimeLatency);
	}
	
	protected static void clearOperationsMap(){
		operationsMap.clear();
	}
}
