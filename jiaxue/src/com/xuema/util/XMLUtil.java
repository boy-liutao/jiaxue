package com.xuema.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XMLUtil {
	public static String toXML(Object obj){
	    XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        xs.alias("xml", obj.getClass());
        String xml = xs.toXML(obj);
        return xml;
	}
	
	public static <T> T toObject(String xml, Class<T> clz){
	    XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        xs.ignoreUnknownElements();
        xs.alias("xml", clz);
        T t = (T) xs.fromXML(xml);
        return t;
	}
}
