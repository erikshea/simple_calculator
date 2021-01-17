package com.erikshea.simplecalculator.util;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.List;


public class FileUtils {
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
