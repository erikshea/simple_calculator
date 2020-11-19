package util;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class FileUtils {
	/**
	 * creates and gets an image according to a an image file path
	 * @param path string representing a file path relative to classpath
	 * @return Image object
	 */
	public static Image getImage(String path) {
		Image image = null;
		
		try {
			URL imageURL = (new java.io.File(path)).toURI().toURL();
			image =	 Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * Fetches all installed fonts, and returns the first valid font name 
	 * in a list of preferred font names
	 * @param fontNames list of list of preferred font names
	 * @return first valid font name, null if no valid font names
	 */
	public static String getFirstValidFontName(String[] fontNames) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        List<String> installedFontNames = Arrays.asList(ge.getAvailableFontFamilyNames());

        String validFontName = null;
        
        for (String fontName:fontNames) {
        	if (installedFontNames.contains(fontName)){
        		validFontName = fontName;
        		break;
        	}
        }
        
        return validFontName;
	}
}
