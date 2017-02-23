package com.xuema.util;

import java.util.Random;

public class RandomUtil {
	public static Random r = new Random(System.currentTimeMillis());
	
	public static int nextInt(int scope){
		return r.nextInt(scope);
	}
	
	public static int nextInt(int min, int max){
		int n = r.nextInt(max-min);
		return min + n;
	}
	/**
	 * 产生min~max之间的正态分布随机数，
	 * @param a 平均数
	 * @param b 方差
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static double nextGaussian(int a, int b, int min, int max){
		double d = Math.sqrt(b)*r.nextGaussian()+a;
		if (d < min || d > max){
			return nextGaussian(a,b,min,max);
		}
		return d;
	}
	
	public static double nextGaussianWithHappy(int a, int b, int min, int max, double happyRate, int happyMin, int happyMax){
		int rate = (int) (1/happyRate);
		int hit = rate/2;
		int num = nextInt(rate);
		if (num == hit){
			double n = nextInt(happyMin, happyMax)*1.0;
			System.out.println(n);
			return n;
		} else {
			return nextGaussian(a,b,min,max);
		}
	}
	
	public static String generateCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return Integer.valueOf(code).toString();
    }
	
	public static void main(String[] args) {
		System.out.println(generateCode());
	}
}
