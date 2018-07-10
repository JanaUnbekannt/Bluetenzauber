package webeng03;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Es gibt bekanntlich Resourcen, die exklusiv vergeben werden m�ssen, 
 * hierzu geh�ren u.a. Ausgaben auf den Drucker und Schreibzugriffe auf Dateien. 
 * Damit ein Thread eine Aufgabe exklusiv abarbeiten kann stellt Java 
 * das Schl�sselwort synchronized bereit. Betritt ein Thread einen Codebereich, 
 * der mit synchronized gesch�tzt ist, kann er diesen Bereich exklusiv abarbeiten. 
 * Kein anderer Thread kann dann diesen Bereich betreten. Dazu hat jedes Javaobjekt 
 * einen Monitor. Diese �berwachungsinstanz enth�lt pro Objekt genau einen Lock. 
 * Will ein Thread einen mit synchronized gesch�tzten Bereich betreten, 
 * mu� er vom zugeh�rigen Objekt einen Lock anfordern. Ist dieser bereits vergeben, 
 * so mu� er warten. Da es nur einen Lock gibt sperrt ein Thread, der diesen Lock
 * besitzt zwangsl�ufig alle anderen mit synchronized gesch�tzten Bereiche dieses Objekts. 
 * Es werden damit evtl. auch Bereiche gesperrt, die mit der von dem Thread gerade abgearbeiteten 
 * Code garnichts zu tun haben. Performanceverluste sind die logische Folge dieses sozusagen 
 * rabiaten Verhaltens. 
 * @author Epy
 *
 */
@WebListener("CountSession")
public class CountSession implements HttpSessionListener {
	
	public static int count = 0;


	@Override
	public synchronized void sessionCreated(HttpSessionEvent se) {

		count++;
		
	}

	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent se) {
		
		count--;
		
	}
	
	
	public int getCountUser() {
		
		return count;
	}
	
	

}
