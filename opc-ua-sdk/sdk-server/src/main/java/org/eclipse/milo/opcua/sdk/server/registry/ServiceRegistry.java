package org.eclipse.milo.opcua.sdk.server.registry;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.me.*;

public class ServiceRegistry {
   
    private static JSONObject currentServiceRegistry = new JSONObject();

    private final static Object CurrentRegistryLock = new Object();
    
    private static boolean parsingDone=false;
    private final static Object RegistryParsingStatLock=new Object();

    
    //private final static Object RegistryParsingStatLock=new Object();

    /*
     * Add Service to the Registry
     * 
     */
    public static void AppendNodeServicesToCurrentRegistry(String ServerID, JSONObject js) throws JSONException{
        
        //if (IsInternalServ){
            
            synchronized (CurrentRegistryLock){
                currentServiceRegistry.put(ServerID,js);
            }
          
        //} 
    }
    
    public static void UpdateExistingRegistryEntry(String ServerID, String JSONObject js, boolean updating){
        
        try {

            JSONObject servAdvList = (JSONObject) js.get("serviceList");
             //long advertisementLength = Long.parseLong(js.getString("expiryTime"));
            //JSONObject filteredServList = new JSONObject();
           
             //  filteredServList = servAdvList;

               boolean IsNewSSServAdded = false;
                
                    synchronized (CurrentRegistryLock){
                    	
                    	if (updating) {
                    		
                    		if (currentServiceRegistry.has(ServerID)){
                                currentServiceRegistry.remove(ServerID);
                            } else {
                                IsNewSSServAdded = true;
                            } 
                            currentServiceRegistry.put(ServerID,servAdvList);
                    		
                    	} 
                
                        
                    }
                
                    /*
                synchronized (serviceAdvertisementReceivedTimeLock){
                    
                    if(serviceAdvertisementReceivedTime.has(js.getString("associatedSS"))){
                        serviceAdvertisementReceivedTime.remove(js.getString("associatedSS"));
                    }
                    serviceAdvertisementReceivedTime.put(js.getString("associatedSS"),System.currentTimeMillis());
                } 
                            synchronized (ServExpiryLengthLock){
                                if(serviceExpiryLength.has(js.getString("associatedSS"))){
                                    serviceExpiryLength.remove(js.getString("associatedSS"));
                                } 
                                serviceExpiryLength.put(js.getString("associatedSS"),advertisementLength);
                            }
                JSONObject AllCDStat = js.getJSONObject("CDStats");
                
                */
                //Removed for dependency purpose
                
                //RegAllCDStats.UpdateCDStat(js.getString("associatedSS"), AllCDStat);
                //RegAllSSAddr.AddSSAddr(js.getString("associatedSS"), js.getString("SSAddr"));
                
                //END
                
                //boolean notifySSStat = Boolean.parseBoolean(js.getString("Notify"));
                //boolean notifyChangedCDStat = Boolean.parseBoolean(js.getString("changedCDStat"));
                //if(notifySSStat || notifyChangedCDStat || IsNewSSServAdded){
                    
                	/* Removed for dependency purpose
                    SOABuffer.SetRegNotifySS(true);
                    */
                //}
                    /*
                    if(notifySSStat){
                        SOABuffer.SetNotifyChangedTotalSS(true);
                    }
                
                   if(notifyChangedCDStat){
                        SOABuffer.SetNotifyChangedCDStat(true);
                    }
                   */
              //  }
                
                //if(IsNewSSServAdded){
                //    SOABuffer.SetRegNotifySS(true);
                    //SOABuffer.SetNotifyChangedCDStat(true);
                    //SOABuffer.SetNotifyChangedTotalSS(true);
               // }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
   
    
    public static void setParsingStatus(boolean stat){
        
        synchronized(RegistryParsingStatLock){
            parsingDone=stat;
        }
        
    }
    
    public static boolean getParsingStatus(){
        
        
        synchronized (RegistryParsingStatLock){
            
            return parsingDone;
            
        }
        
    }

    /*
    public static void RemoveServiceOfCD(String CDName){
        try {
            JSONObject jsInt = obtainInternalRegistry();
            
            Enumeration servIndKeys = jsInt.keys();
            
            while(servIndKeys.hasMoreElements()){
                
                String servInd = servIndKeys.nextElement().toString();
                
                JSONObject jsServDet = jsInt.getJSONObject(servInd);
                
                String assocCDName = jsServDet.getString("associatedCDName");
                
                if(assocCDName.equals(CDName)){
                    jsInt.remove(servInd);
                    
                    // Removed for dependency purpose
                    SOABuffer.removeAdvStatOfServName(servInd);
                    //END
                }
                
            }
            
            UpdateAllInternalRegistry(jsInt);
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    */
    
    /*
    public static boolean HasNonLocalServiceCD(String cdname){
        
        boolean stat = false;
        
        try {
            JSONObject jsServ = SJServiceRegistry.obtainCurrentRegistry();
            
            Enumeration keysJsServ = jsServ.keys();
            
            while(keysJsServ.hasMoreElements()){
                
                String keyJsServ = keysJsServ.nextElement().toString();
                
                if(!keyJsServ.equals(SJSSCDSignalChannelMap.getLocalSSName())){
                    JSONObject jsIndivServs = jsServ.getJSONObject(keyJsServ);
                    
                    Enumeration keysServName = jsIndivServs.keys();
                    
                    while(keysServName.hasMoreElements()){
                        
                        String ServName = keysServName.nextElement().toString();
                        
                        JSONObject jsIndivServ = jsIndivServs.getJSONObject(ServName);
                        
                        String CDName = jsIndivServ.getString("associatedCDName");
                        
                        if(CDName.equals(cdname)){
                            stat = true;
                        }
                        
                    }
                    
                }
                
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
            
        }
        return stat;
    }
    */
    
    /*
    public static boolean IsCDFromRemoteSS(String cdname, String SS){
        
        boolean stat = false;
        
        try {
            JSONObject jsServ = SJServiceRegistry.obtainCurrentRegistry();
            
            Enumeration keysJsServ = jsServ.keys();
            
            while(keysJsServ.hasMoreElements()){
                
                String keyJsServ = keysJsServ.nextElement().toString();
                
                if(!keyJsServ.equals(SJSSCDSignalChannelMap.getLocalSSName())){
                    JSONObject jsIndivServs = jsServ.getJSONObject(keyJsServ);
                    
                    Enumeration keysServName = jsIndivServs.keys();
                    
                    while(keysServName.hasMoreElements()){
                        
                        String ServName = keysServName.nextElement().toString();
                        
                        JSONObject jsIndivServ = jsIndivServs.getJSONObject(ServName);
                        
                        String CDName = jsIndivServ.getString("associatedCDName");
                        
                        if(CDName.equals(cdname) && keyJsServ.equals(SS)){
                            stat = true;
                        }
                        
                    }
                    
                }
                
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
            
        }
        
        return stat;
    }
    */
    
    /*
    public static String GetCDRemoteSSLocation(String cdname){
        
        String stat = "";
        
        try {
            
            JSONObject jsServ = RegAllCDStats.getAllCDStats();
            
          
            Enumeration keysJsServ = jsServ.keys();
            
            while(keysJsServ.hasMoreElements()){
                
                String keyJsServ = keysJsServ.nextElement().toString();
                
                if(!keyJsServ.equals(SJSSCDSignalChannelMap.getLocalSSName())){
                    JSONObject jsIndivServs = jsServ.getJSONObject(keyJsServ);
                    
                    Enumeration keysCDName = jsIndivServs.keys();
                    
                    while(keysCDName.hasMoreElements()){
                        
                        String keyCDName = keysCDName.nextElement().toString();
                        
                        //JSONObject jsIndivServ = jsIndivServs.getJSONObject(ServName);
                        
                        //String CDName = jsIndivServ.getString("associatedCDName");
                        
                        if(keyCDName.equals(cdname) ){
                            //stat = keyJsServ;
                            stat = keyJsServ;
                        }
                        
                    }
                    
                }
                
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
            
        }
        
        return stat;
    }
    */
    
    public static void RemoveRegistryEntryOfID(String ServerID){
       // try {
            synchronized (CurrentRegistryLock) {
            if (currentServiceRegistry.has(ServerID)){
                
               
                //System.out.println("SJServiceRegistry, prev SR: " +currentDetailedServiceRegistry);
                
                    currentServiceRegistry.remove(ServerID);
                
                //System.out.println("SJServiceRegistry, curr SR: " +currentDetailedServiceRegistry);
                //synchronized regnodavail here
               
            } else {
                System.out.println("ServiceRegistry, RemoveRegistryEntryOfID : ServerID "+ServerID+ " is not listed");
            }
            
            }
          
    }
    
    public static void RemoveRegistryEntryOfServerID(String ServerID){
        // try {
             synchronized (CurrentRegistryLock) {
             if (currentServiceRegistry.has(ServerID)){
                 
                
                 //System.out.println("SJServiceRegistry, prev SR: " +currentDetailedServiceRegistry);
                 
                     currentServiceRegistry.remove(ServerID);
                 
                 //System.out.println("SJServiceRegistry, curr SR: " +currentDetailedServiceRegistry);
                 //synchronized regnodavail here
                
             } else {
                 System.out.println("ServiceRegistry, RemoveRegistryEntryOfServerID: ServerID " +ServerID+" is not listed");
             }
             
             }
           
     }

    /*
    public static JSONObject obtainInternalRegistry() throws JSONException{
        
        JSONObject intReg = new JSONObject();
        
        //System.out.println("obtained internal Registry: " +currentDetailedServiceRegistry.getJSONObject("Node0").toPrettyPrintedString(2, 0));
        if (!currentServiceRegistry.toString().equalsIgnoreCase("{}")){
            
            synchronized (CurrentRegistryLock){
                intReg= currentServiceRegistry.getJSONObject(SJSSCDSignalChannelMap.getLocalSSName());
            }
            
            //System.out.println("SJServiceRegistry Current Registry: " +currentDetailedServiceRegistry.toPrettyPrintedString(2, 0));
            return intReg;
        } else {
            return new JSONObject();
        } 
    }
    */
    
    public static JSONObject obtainCurrentRegistry() throws JSONException{
        //System.out.println("current Registry: " +currentDetailedServiceRegistry.toPrettyPrintedString(2, 0));
        
        JSONObject jsAllCurr = new JSONObject();
        
        synchronized (CurrentRegistryLock){
            jsAllCurr = currentServiceRegistry;
        }
        
        
        return jsAllCurr;
        
    }
    
  /*
    public static void UpdateAllInternalRegistry(JSONObject js){
        
        synchronized(CurrentRegistryLock){
            try {
                //currentDetailedServiceRegistry.remove(SJSSCDSignalChannelMap.getLocalSSName());
                currentServiceRegistry.put(SJSSCDSignalChannelMap.getLocalSSName(), js);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    */
 
    //need to get node with expired advertisement
   
     /*
    //method to save registry to a file, features MAY NOT be available on smaller java platform
    public static void WriteCurrentServiceRegistryToFile(JSONObject registry) throws IOException{

		FileWriter file = new FileWriter("CurrentReg.txt");
                
		file.write(registry.toString());
		file.flush();
		file.close();
    }

    public static JSONObject ReadCurrentServiceRegistryFromFile() throws IOException {
        JSONObject js;
        BufferedReader br = new BufferedReader(new FileReader("CurrentReg.txt"));
        try {
        StringBuilder sb = new StringBuilder();
        String line;
        
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append('\n');
                line = br.readLine();
            }
        js = (JSONObject) (Object)sb;
        
        //String everything = sb.toString();
        } finally {
            br.close();
        }
        return js;
    }
    */

}