package asgardengine.utility.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoggingHandler {
// centrally handles logging

	private static final Logger log = Logger.getLogger(LoggingHandler.class.getName());
	private static Handler logHandler = null;
	private static String logFolder = "Logs//";
	private static String logFile = "Log_";
	private static int numLogFiles = 1;
	
	// start logging
	public static boolean startLogWriting() {
		File folder = new File(logFolder); // the folder containing all log files
		if (!folder.exists()) {
			folder.mkdirs(); // create directory if necessary
		}
		for(int i = numLogFiles - 2; i >= 0 ; i--) { // count down to zero, starting with second last
			File file = new File(logFolder + logFile + Integer.toString(i) + ".xml");
			if (file.exists() && file.isFile()) {
				try { // shift old logs overwriting the oldest
					Files.copy(file.toPath(), Paths.get(logFolder, logFile + Integer.toString(i+1) + ".xml"), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
				} catch (IOException e) {
					LoggingHandler.getLog().log(Level.WARNING, "Log file " + file + " could not be copied.", e);
					e.printStackTrace();
				}
			}
		}
		try {
			logHandler = new FileHandler(logFolder + logFile + "0.xml"); // always write to first log file
		} catch (SecurityException e) {
			log.log(Level.WARNING, "Security problem accessing log file.", e);
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			log.log(Level.WARNING, "The log file could not be written to.", e);
			e.printStackTrace();
			return false;
		}
		log.addHandler(logHandler);
		return true;
	}
	
	public static boolean stopLogWriting() {
		if(logHandler != null) {
			try {
				log.removeHandler(logHandler);
				return true;
			} catch (SecurityException e) { // false if logging could not be stopped
				log.log(Level.WARNING, "Logging could not be stopped.", e);
				e.printStackTrace();
				return false;
			}
		} else { // return true if there is no log handler set 
			return true;
		}
	}

	// return all log files
	public static File[] getLogFiles() {
		ArrayList<File> logFiles = new ArrayList<File>();
		for(int i = 0; i < numLogFiles ; i++) {
			File file = new File(logFolder + logFile + Integer.toString(i) + ".xml");
			if (file.exists() && file.isFile()) {
				logFiles.add(file);
			}
		}
		return logFiles.toArray(new File[logFiles.size()]);
	}

	public static Logger getLog() {
		return log;
	}
}
