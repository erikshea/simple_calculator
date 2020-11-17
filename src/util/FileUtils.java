package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;


public class FileUtils {
	public static Image getImage(String path) {
		Image image = null;
		
		try {
			URL imageURL = (new java.io.File(path)).toURI().toURL();
			image =	 Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (MalformedURLException e) {
		}
		
		return image;
	}
}
