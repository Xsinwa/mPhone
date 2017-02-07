package com.Hemi.Contacts;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class AlphaUtil {

	private static HanyuPinyinOutputFormat format;
	public static String getAlpha(String personName) {
		if(format==null){
			format=new HanyuPinyinOutputFormat();
			format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		}
		char str;
		StringBuilder sb= new StringBuilder();
		try{
			char[] charArray=personName.toCharArray();
			for(char aCharArray:charArray){
				str=aCharArray;
				String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(str, format);
                if (pinyinStringArray != null && pinyinStringArray.length > 0) {
                    sb.append(pinyinStringArray[0]);
                }
			}
		}catch(BadHanyuPinyinOutputFormatCombination e){
			e.printStackTrace();
		}
		return sb.toString();
	}
}
