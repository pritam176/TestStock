/**
 * 
 */
package com.mmt.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pkumar
 *
 */
public class Util {
	
	public static <T> Map<String, T> mapMe(Collection<T> list) {
		   Map<String, T> map = new HashMap<String, T>();
		   for (T el : list) {
		       map.put(el.toString(), el);
		   }   
		   return map;
		}

}
