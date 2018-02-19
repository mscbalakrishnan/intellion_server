package com.intellion.cms.util;


import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

	static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	public static String getFilePath(String path){
		try {
			URI uri = new URI(path);
			path = uri.getPath();
		} catch (URISyntaxException e) {
			logger.error("---------------Exception in CommonUtil: solveFilePathSpaces -----------",e);
		}
		return path;
	}
	
}
