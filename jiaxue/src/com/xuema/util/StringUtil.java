package com.xuema.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


public class StringUtil {
	
	public String[] splitStoreName(String storeNameList){
		return storeNameList.split(",");
	}
	
	/**
	 * 权限位运算
	 * @param authority 权限int值，之后转成二进制
	 * @param position 第几个属性的权限
	 * @return 所需要属性的权限值
	 */
	public int getAuthority(int authority, int position){
		int[] result = new int[20];

		int i = result.length - 1;
		while (authority != 0) {
			if (authority % 2 == 0)
				result[i--] = 0;
			else
				result[i--] = 1;
			authority /= 2;

		}

		for (int k = i; k >= 0; k--)
			result[k] = 0;
		return result[20 - position];
	}
	
	/**
	 * 权限运算
	 * @param authority 权限十进制值
	 * @return 权限二进制逆序数组
	 */
	public static int[] getAuthority(int authority){
		int[] result = new int[20];

		int i = result.length - 1;
		while (authority != 0) {
			if (authority % 2 == 0)
				result[i--] = 0;
			else
				result[i--] = 1;
			authority /= 2;

		}

		for (int k = i; k >= 0; k--)
			result[k] = 0;
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("~~" + getAuthority(2));
	}

    public static String formatNull(String src) {
        return (src == null || "null".equals(src)) ? "" : src;
    }

    /**
     * generate number like: #1#,#2#,#3#
     * @param k
     * @param id
     */
    public static String mergeNumber(String k, int id) {
        if (StringUtils.isEmpty(k)){
            return "#"+id+"#";
        }
        if (k.contains("#"+id+"#")){
            return k;
        } else {
            return k += ",#"+id+"#";
        }
    }

    public static String[] split(String keywords, String regex) {
        if (keywords == null){
            return new String[]{};
        } else {
            return keywords.split(regex);
        }
        
    }

    public static List<String> computeLess(String[] oldKS, String[] newKS) {
        List<String> less = new ArrayList<String>();
        for (String oldK : oldKS){
            boolean find = false;
            for (String newK : newKS){
                if (oldK.equals(newK)){
                    find = true;
                    break;
                }
            }
            if (!find)
                less.add(oldK);
        }
        return less;
    }
    
    public static List<String> computeMore(String[] oldKS, String[] newKS) {
        return computeLess(newKS, oldKS);
    }

    public static String removeItem(String as, int id) {
        String r = as.replace("#"+id+"#", "");
        r = r.replace(",,", ",");
        if (r.startsWith(",")) {
            r = r.substring(1);
        }
        return r;
    }

    public static String appendItem(String as, int id) {
        if (StringUtils.isEmpty(as))
            return "#"+id+"#";
        else {
            if (as.contains("#"+id+"#"))
                return as;
            else
                return as + ",#"+id+"#";
        }
    }
}
