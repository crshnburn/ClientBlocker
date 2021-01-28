/*
 Copyright IBM Corporation 2014

 LICENSE: Apache License
          Version 2.0, January 2004
          http://www.apache.org/licenses/

 The following code is sample code created by IBM Corporation.
 This sample code is not part of any standard IBM product and
 is provided to you solely for the purpose of assisting you in
 the development of your applications.  The code is provided
 'as is', without warranty or condition of any kind.  IBM shall
 not be liable for any damages arising out of your use of the
 sample code, even if IBM has been advised of the possibility
 of such damages.
*/
package com.ibm.ctg.samples.clientblocker;

import java.net.InetAddress;
import java.util.*;

import com.ibm.ctg.ha.*;

public class ClientBlocker implements CICSRequestExit {

   /**
    * List of IP addresses that are allowed to call CICS programs
    */
   private List<String> allowList = Arrays.asList("127.0.0.1");

   @Override
   public String getCICSServer(Map<RequestDetails, Object> details) throws InvalidRequestException {
      String server = details.get(RequestDetails.Server).toString();
      String clientIP = ((InetAddress) details.get(RequestDetails.ClientLocation)).getHostAddress();
      if (!allowList.contains(clientIP)) {
         throw new InvalidRequestException("Client not allowed to call CICS");
      }
      return server;
   }

   @Override
   public int getRetryCount() {
      //Return 0 as we don't want to retry requests in this exit
      return 0;
   }

   @Override
   public void eventFired(ExitEvent event, Map<ExitEventData, Object> eventData) {
      switch (event) {
      case Command:
         //Get the command that was passed in
         String commandData = eventData.get(ExitEventData.CommandData).toString();
         if (commandData.startsWith("ADD")) {
            int location = commandData.indexOf("=");
            //Create a new list with the supplied IP address added and set it as the
            //current list
            if (location > 0) {
               ArrayList<String> newAllowList = new ArrayList<String>(allowList);
               newAllowList.add(commandData.substring(location + 1));
               allowList = newAllowList;
            }
         } else if (commandData.startsWith("DEL")) {
            int location = commandData.indexOf("=");
            //Create a new list with the supplied IP address removed and set it
            //as the current list
            if (location > 0) {
               ArrayList<String> newAllowList = new ArrayList<String>(allowList);
               newAllowList.remove(commandData.substring(location + 1));
               allowList = newAllowList;
            }
         } else if (commandData.startsWith("LIST")) {
            //Write the list of allowed IP addresses to the standard output stream
            StringBuffer list = new StringBuffer("Allowed IP addresses:");
            for (String ip : allowList) {
               list.append("\n\t");
               list.append(ip);
            }
            System.out.println(list);
         }
         break;
      case ShutDown:
         // do nothing
         break;
      }
   }
}
