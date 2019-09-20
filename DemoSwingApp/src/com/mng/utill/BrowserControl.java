package com.mng.utill;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class BrowserControl {

    
    UrlGeneration urlGeneration = null;
    
    public String error= "Error 404--Not Found" +"\n" +"\n"
    		
    		+ "From RFC 2068 Hypertext Transfer Protocol -- HTTP/1.1:" +"\n" +"\n"
    		+ "10.4.5 404 Not Found" +"\n" +"\n"
    		+ "The server has not found anything matching the Request-URI. No indication is given of whether the condition is temporary or permanent." +"\n" +"\n"
    		+ "If the server does not wish to make this information available to the client," +"\n" 
    		+ "the status code 403 (Forbidden) can be used instead. The 410 (Gone) status code SHOULD be used if the server knows, through some internally configurable mechanism," +"\n" +"\n"
    		+ "that an old resource is permanently unavailable and has no forwarding address.";
    		     
    private String response=null;
	public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    // Used to identify the windows platform.
	private static final String WIN_ID = "Windows";
	// The default system browser under windows.
	private static final String WIN_PATH = "rundll32";
	// The flag to display a url.
	private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
	// The default browser under unix.
	private static final String UNIX_PATH = "netscape";
	// The flag to display a url.
	private static final String UNIX_FLAG = "-remote openURL";

	/**
	 * Display a file in the system browser. If you want to display a file, you
	 * must include the absolute path name.
	 *
	 * @param url
	 *            the file's url (the url must start with either "http://" or
	 *            "file://").
	 */
	public  String displayURL(String url) {
		boolean windows = isWindowsPlatform();
		String cmd = null;
		try {
			if (windows) {
				// cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
				cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
				Process p = Runtime.getRuntime().exec(cmd);
			} else {
				// Under Unix, Netscape has to be running for the "-remote"
				// command to work. So, we try sending the command and
				// check for an exit value. If the exit command is 0,
				// it worked, otherwise we need to start the browser.
				// cmd = 'netscape -remote openURL(http://www.javaworld.com)'
				cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
				Process p = Runtime.getRuntime().exec(cmd);
				try {
					// wait for exit code -- if it's 0, command worked,
					// otherwise we need to start the browser up.
					int exitCode = p.waitFor();
					if (exitCode != 0) {
						// Command failed, start up the browser
						// cmd = 'netscape http://www.javaworld.com'
						cmd = UNIX_PATH + " " + url;
						p = Runtime.getRuntime().exec(cmd);
					}
				} catch (InterruptedException x) {
					System.err.println("Error bringing up browser, cmd='" + cmd + "'");
					System.err.println("Caught: " + x);
				}
			}
		} catch (IOException x) {
			// couldn't exec browser
			System.err.println("Could not invoke browser, command=" + cmd);
			System.err.println("Caught: " + x);
		}
		return serviceStaus(url);
	}

	public  String serviceStaus(String urlStr) {
		URL url = null;
		String status = null;
		URLConnection urlConnection = null;
		try {
			url = new URL(urlStr);
			urlConnection = url.openConnection();
			
			if (urlConnection.getContent() != null) {
			    
			    urlGeneration = new  UrlGeneration();
			    
			    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = null;
                try {
                    builder = domFactory.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	            Document doc = null;
                try {
                    doc = builder.parse(urlConnection.getInputStream());
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	            

	            TransformerFactory factory = TransformerFactory.newInstance();
	            Transformer xform = null;
                try {
                    xform = factory.newTransformer();
                } catch (TransformerConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	            
	            
	            StringWriter outWriter = new StringWriter();
	            StreamResult result = new StreamResult( outWriter );
	        
	            xform.transform( new DOMSource(doc), result ); 
	            
	            StringBuffer sb = outWriter.getBuffer(); 
	            //urlGeneration.setResponse(sb.toString());
	            setResponse(sb.toString());
	            
				
				System.out.println("Url contenet:  "+urlConnection.getContent());
				System.out.println("Server up");
				status="Server up";
			} else {
				System.out.println("BAD URL");
				status="Bad URl";
			}
		} catch (MalformedURLException ex) {
			System.out.println("bad URL");
			status="Bad URl";
		} catch (IOException ex) {
			System.out.println("Failed opening connection. Perhaps WS is not up?");
			System.out.println("Exception Details:  " + ex);
			
			status="Failed opening connection. Perhaps WS is not up? "+'\n'+ex;
		} catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return status;
	}

	/**
	 * Try to determine whether this application is running under Windows or
	 * some other platform by examing the "os.name" property.
	 *
	 * @return true if this application is running under a Windows OS
	 */
	public static boolean isWindowsPlatform() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith(WIN_ID))
			return true;
		else
			return false;
	}

}