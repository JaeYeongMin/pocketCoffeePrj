/**
 * Thunder
 * kr.co.thunder.common.utils.StringUtil.java
 * <pre> String 관련 각종 Util.</pre>
 * @author click
 * @since 2012. 12. 10. 
 * @version 1.0
 *
 *
 * #########################################################################
 * #   일자		개발자		수정내역
 * #########################################################################
 * # 2012. 12. 10.	click		최초 개발
 * #########################################################################
 */
package com.springbook.view.common;

import java.lang.Character.UnicodeBlock;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author click
 *
 */
public class StringUtil {

	public static void main(String args[]) throws Exception {
		//System.out.println(getLpad("1", "0", 3));
		System.out.println(URLEncoder.encode("女式纯度 100% 卫衣".replaceAll("&", ""),"UTF-8"));
		System.out.println(URLDecoder.decode("%E5%A5%B3%E5%BC%8F%E7%BA%AF%E5%BA%A6+100%25+%E5%8D%AB%E8%A1%A3","UTF-8"));
	}

	/**
	public static String getStringDouble(Double s) {
		try {
			String ts = s.toString();
			if(Integer.parseInt(ts.substring(ts.indexOf(".")+1))==0) {
				return ts.substring(0,ts.indexOf("."));
			} else {
				return s+"";
			}
		} catch (NumberFormatException e) {
			return s+"";
		}
	}
	*/
	public static String getStringDouble(Double s) {
		try {
			DecimalFormat df = new DecimalFormat("#########################.############");
			return df.format(s);
			
			/*String ts = s.toString();
			if(ts.indexOf("E")!=-1) {
				DecimalFormat df = new DecimalFormat("#########################.############");
				return df.format(s);
			} else if(Integer.parseInt(ts.substring(ts.indexOf(".")+1))==0) {
				return ts.substring(0,ts.indexOf("."));
			} else {
				return s+"";
			}
			*/
		} catch (NumberFormatException e) {
			return s+"";
		}
	}

	/**
	 * String 값을 받아서 null 값인 경우 ""로 리턴.
	 * @param oStr
	 * @return
	 */
	public static String getEmptyString(String oStr)
	{
		if(oStr==null) return "";
		else return oStr;
	}

	/**
	 * String 값을 받아서 null 값인 경우 ""로 리턴.
	 * @param oStr
	 * @return
	 */
	public static String getEmptyString(Object oStr)
	{
		if(oStr==null) return "";
		else return oStr.toString();
	}
	
	/**
	 * 핸드폰 번호를 리턴한다.
	 * @param phoneNumber
	 * @return string[3]
	 */
	public static String[] getHandPhoneNumber(String phoneNumber) 
	{
		String rtNum[] = new String[]{"","",""};
		
		// 9자리 미만이면 무조건 에러임.
		if(phoneNumber==null || phoneNumber.length()<10)
		{
			return rtNum;
		}
		else if(phoneNumber.length()==10)
		{
			// 9자리라면..
			rtNum[0] = phoneNumber.substring(0,3);	// 010
			rtNum[1] = phoneNumber.substring(3,6);	// 123
			rtNum[2] = phoneNumber.substring(6,10);	// 4567
		}
		else 
		{
			rtNum[0] = phoneNumber.substring(0,3);	// 010
			rtNum[1] = phoneNumber.substring(3,7);	// 1234
			rtNum[2] = phoneNumber.substring(7,11);	// 5678
		}
		return rtNum;
	}
	
	/**
	 * 전화번호를 리턴한다. ( 핸드폰 번호 포함 )
	 * @param phoneNumber
	 * @return string[3]
	 */
	public static String[] getPhoneNumber(String phoneNumber) 
	{
		String rtNum[] = new String[]{"","",""};
		
		// 9자리 미만이면 무조건 에러임.
		if(phoneNumber==null || phoneNumber.length()<9)
		{
			return rtNum;
		}
		else if(phoneNumber.substring(0,3).equals("010") || phoneNumber.substring(0,3).equals("011") || 
				phoneNumber.substring(0,3).equals("016") || phoneNumber.substring(0,3).equals("017") || 
				phoneNumber.substring(0,3).equals("018") || phoneNumber.substring(0,3).equals("019"))	// 핸드폰 번호라면..
		{
			return getHandPhoneNumber(phoneNumber);
		}
		else if("02".equals(phoneNumber.substring(0,2)))	// 서울인 경우 9자리, 10자리임.
		{
			rtNum[0] = "02"; 
			if(phoneNumber.length()==9)	// 가운데자리가 세자리임.
			{
				rtNum[1] = phoneNumber.substring(2,5); 
				rtNum[2] = phoneNumber.substring(5,9);
			}
			else
			{
				rtNum[1] = phoneNumber.substring(2,6); 
				rtNum[2] = phoneNumber.substring(6,10);
			}
		}
		else
		{
			if(phoneNumber.length()==9)				// 두번째 자리 두자리, 세번째 자리 네자리임 :: 이런 번호는 없으나 에러 방지차원에서..
			{
				rtNum[0] = phoneNumber.substring(0,2); 	// 맨 앞자리 두자리(서울 외에는 세자리임 )
				rtNum[1] = phoneNumber.substring(2,5);	// 123
				rtNum[2] = phoneNumber.substring(5,9);	// 4567
			}
			else if(phoneNumber.length()==10)			// 두번째 자리 세자리, 세번째 자리 네자리임.
			{
				rtNum[0] = phoneNumber.substring(0,3); 	// 맨 앞자리 세자리(서울 외에는 세자리임 )
				rtNum[1] = phoneNumber.substring(3,6);		// 123
				rtNum[2] = phoneNumber.substring(6,10);	// 4567
			}
			else
			{
				rtNum[0] = phoneNumber.substring(0,3); 	// 맨 앞자리 세자리(서울 외에는 세자리임 )
				rtNum[1] = phoneNumber.substring(3,7);		// 123
				rtNum[2] = phoneNumber.substring(7,11);	// 4567
			}
		}
		
		return rtNum;
	}

    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }

    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     * 
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     * 
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우 
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 랜덤 String을 lengrth 길이만큼 리턴한다. 단 처음 문자는 무조건 알파벳임.
     * @param length
     * @return
     */
    public static String getLandomStrLower(int length)
    {
    	int idx = 0;
    	char[] charSet1 = new char[] {
    			  'a','b','c','d','e','f','g','h','i','j'
    			, 'k','l','m','n','o','p','q','r','s','t'
    			, 'u','v','w','x','y','z'};
    	char[] charSet2 = new char[] {
  			  	  '0','1','2','3','4','5','6','7','8','9'
  			  	, 'a','b','c','d','e','f','g','h','i','j'
  			  	, 'k','l','m','n','o','p','q','r','s','t'
  			  	, 'u','v','w','x','y','z'};
    	
    	StringBuffer sb = new StringBuffer();
    	// 첫문자는 무조건 알파벳.
    	Random random = new Random();
    	idx = random.nextInt(26);
    	sb.append(charSet1[idx]);
    	
    	// 두번째 문자부터 파라메터 length만큼 random 추가.
    	for(int i=0;i<length-1;i++)
    	{
    		sb.append(charSet2[random.nextInt(36)]);
    	}
    	return sb.toString();
    }
    
    /**
     * 랜덤 String을 lengrth 길이만큼 리턴한다. 단 처음 문자는 무조건 알파벳임.
     * @param length
     * @return
     */
    public static String getLandomStr(int length)
    {
    	// 소문자, 숫자로만 적용시킨다.
    	return getLandomStrLower(length);
    	/*
    	int idx = 0;
    	char[] charSet1 = new char[] {
    			  'A','B','C','D','E','F','G','H','I','J'
    			, 'K','L','M','N','O','P','Q','R','S','T'
    			, 'U','V','W','X','Y','Z'
    			, 'a','b','c','d','e','f','g','h','i','j'
    			, 'k','l','m','n','o','p','q','r','s','t'
    			, 'u','v','w','x','y','z'};
    	char[] charSet2 = new char[] {
    			  '0','1','2','3','4','5','6','7','8','9'
    			, 'A','B','C','D','E','F','G','H','I','J'
    			, 'K','L','M','N','O','P','Q','R','S','T'
    			, 'U','V','W','X','Y','Z'
    			, 'a','b','c','d','e','f','g','h','i','j'
    			, 'k','l','m','n','o','p','q','r','s','t'
    			, 'u','v','w','x','y','z'
    			};
    	
    	StringBuffer sb = new StringBuffer();
    	// 첫문자는 무조건 알파벳.
    	Random random = new Random();
    	idx = random.nextInt(50);
    	sb.append(charSet1[idx]);
    	
    	// 두번째 문자부터 파라메터 length만큼 random 추가.
    	for(int i=0;i<length-1;i++)
    	{
    		sb.append(charSet2[random.nextInt(60)]);
    	}
    	return sb.toString();
    	*/
    }
    
    /**
	 * String 배열을 ^로 구분하는 String 으로 변환
	 * @param String[] strArr : 변환할 String 배열
	 * @return String str : ^로 구분하는 String
	 * @throws Exception
	 */
	public static String getProcArrStr(String[] strArr)throws Exception {
		String str = "";
		
		if(strArr != null && !"".equals(strArr)){
			for(int i = 0 ; i < strArr.length ; i++){
				str += strArr[i];
				if( (i + 1) != strArr.length){
					str += "^";
				}
			}
		}else{
			str = "";
		}
		
		return str;
	}

	/**
	 * 한글 등 2byte 문자를 고려해 byte 단위로 substring
	 * @param str			문자열
	 * @param beginIndex	시작index
	 * @param endIndex		끝 index
	 * @param addStr		문자열이 종료보다 길다면 붙여주는 문자.
	 * @return
	 */
	public static String substrb(String str, int beginIndex, int endIndex, String addStr)
	{
		if(str==null || str.equals("")) return "";
		
		String tmp = str;
		int slen =0, blen=0;
		char c;

		if(tmp.getBytes().length > endIndex-1)
		{
			while(blen+1 < endIndex-1)
			{
				c = tmp.charAt(slen);
				blen++;
				slen++;
				
				if(c>127)
					blen = blen + (3-1);	// 3byte char인 경우.
			}
			if(addStr==null) addStr="";
			tmp = tmp.substring(beginIndex, slen)+addStr;
		}
		return tmp;
	}
	
	/**
	 * html 입력을 방지하기 위해 문자열 교체한다.
	 * @param value
	 * @return
	 */
	public static String clearHTMLTags(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		
		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		return returnValue;
	}
	
	
	/**
	 * html 입력을 방지하기 위해 문자열 교체한다.
	 * @param value
	 * @return
	 */
	public static String clearTagsHTML(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		
		String returnValue = value;

		returnValue = returnValue.replaceAll("&amp;", "&");
		returnValue = returnValue.replaceAll("&lt;", "<");
		returnValue = returnValue.replaceAll("&gt;", ">");
		returnValue = returnValue.replaceAll("&#34;", "\'");
		returnValue = returnValue.replaceAll("&#39;", "\'");
		return returnValue;
	}
	/**
	 * 입력문자 중 스크립트만 막는다.
	 * @param value
	 * @return
	 */
	public static String clearScriptTags(String value) {
		String returnValue = value;
		
		returnValue = replaceUp(returnValue, "<script", "&lt;script");
		returnValue = replaceUp(returnValue, "</script", "&lt;/script");

		return returnValue;
	}

    /**
     * 대소문자 구분 없이 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceUp(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        while (srcStr.toUpperCase().indexOf(subject.toUpperCase()) >= 0) {
            preStr = srcStr.substring(0, srcStr.toUpperCase().indexOf(subject.toUpperCase()));
            nextStr = srcStr.substring(srcStr.toUpperCase().indexOf(subject.toUpperCase()) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }
    
    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;
        
        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }
    
    /**
     * 행정동 코드 시/구/동으로 분리
     * @param accCod
     * @return
     */
    public static String[] getAccCod(String accCod){
    	String[] accCods = new String[3];
    	if(accCod != null && !"".equals(accCod)){
	    	char[] accCodArr = accCod.toCharArray();
	    	
	    	accCods[0] = new String(Arrays.copyOfRange(accCodArr, 0, 2));
	    	accCods[1] = new String(Arrays.copyOfRange(accCodArr, 2, 5));
	    	accCods[2] = new String(Arrays.copyOfRange(accCodArr, 5, 10));
    	}else{
    		accCods[0] = "";
	    	accCods[1] = "";
	    	accCods[2] = "";
    	}
    	return accCods;
    }

    /**
     * 랜덤한 문자열을 원하는 길이만큼 반환합니다.
     * @param length 문자열 길이 
     * @return 랜덤문자열 
     */
    public static String getRandomString(int length){ 
    	StringBuffer buffer = new StringBuffer();  
    	Random random = new Random();   
    	String chars[] =     "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0".split(",");  
    	for (int i=0 ; i<length ; i++)  {   
    		buffer.append(chars[random.nextInt(chars.length)]);  
    	}  
    	return buffer.toString();
    }
    
    /**
     * 문자열에 한글이 포함되어 있는지 여부를 리턴한다.
     * @param str
     * @return
     */
	public static boolean containsHangul(String str)
	{
	    for(int i = 0 ; i < str.length() ; i++)
	    {
	        char ch = str.charAt(i);
	        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
	        if(UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) ||
	        		UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock) ||
	        		UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))
	            return true;
	    }
	    return false;
	}


    /**
     * type가 1이면 숫자 2이면 영문
     * @param column
     * @param Type
     * @return
     */
    
    public static String getColumnNumber(String column,String type){
    	String testStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int index = 0;
		int nextIndex = 0;
		String result = null;
		if("1".equals(type)){
			int columnNum = Integer.parseInt(column);
			if(columnNum>25){
				index = (columnNum / 26) - 1;
				nextIndex = (columnNum % 26);
				result = testStr.charAt(index) + "" +testStr.charAt(nextIndex++);
			} else {
				result = String.valueOf(testStr.charAt(columnNum));
			}
		}else{
			if(column.length()==2){
				for(int i=26;i<256;i++){
					String temp = testStr.charAt(index) + "" +testStr.charAt(nextIndex++);
					if(temp.equals(column)){
						result = String.valueOf(i);
						break;
					}
					if(nextIndex>25){
						nextIndex=0;
						index++;
					}
				}
			}else{
				result = String.valueOf(testStr.indexOf(column));
			}
		}
		return result;
    }
    
    public static String getLpad(String str, String pad, int leng) {
    	if(str.length()>=leng) {
    		return str.substring(0,leng);
    	} else {
    		String rtStr = "";
    		for(int i=str.length();i<leng;i++) {
    			rtStr+=pad;
    		}
    		return rtStr+str;
    	}
    }
    
    public static String convertLatin2Char(String str) {
    	if(str == null) return null;
		String returnStr = str;
		returnStr = returnStr.replaceAll("<br>", "\n");
		returnStr = returnStr.replaceAll("&gt;", ">");
		returnStr = returnStr.replaceAll("&lt;", "<");
		returnStr = returnStr.replaceAll("&quot;", "\"");
		returnStr = returnStr.replaceAll("&apos;", "\'");
		returnStr = returnStr.replaceAll("&nbsp;", " ");
		returnStr = returnStr.replaceAll("&amp;", "&");
		returnStr = returnStr.replaceAll("&nbsp;", "");
		returnStr = returnStr.replaceAll("&iexcl;", "¡");
		returnStr = returnStr.replaceAll("&cent;", "¢");
		returnStr = returnStr.replaceAll("&pound;", "£");
		returnStr = returnStr.replaceAll("&curren;", "¤");
		returnStr = returnStr.replaceAll("&yen;", "¥");
		returnStr = returnStr.replaceAll("&brvbar;", "¦");
		returnStr = returnStr.replaceAll("&sect;", "§");
		returnStr = returnStr.replaceAll("&uml;", "¨");
		returnStr = returnStr.replaceAll("&copy;", "©");
		returnStr = returnStr.replaceAll("&ordf;", "ª");
		returnStr = returnStr.replaceAll("&laquo;", "«");
		returnStr = returnStr.replaceAll("&not;", "¬");
		returnStr = returnStr.replaceAll("&shy;", "­");
		returnStr = returnStr.replaceAll("&reg;", "®");
		returnStr = returnStr.replaceAll("&macr;", "¯");
		returnStr = returnStr.replaceAll("&deg;", "°");
		returnStr = returnStr.replaceAll("&plusmn;", "±");
		returnStr = returnStr.replaceAll("&sup2;", "²");
		returnStr = returnStr.replaceAll("&sup3;", "³");
		returnStr = returnStr.replaceAll("&acute;", "´");
		returnStr = returnStr.replaceAll("&micro;", "µ");
		returnStr = returnStr.replaceAll("&para;", "¶");
		returnStr = returnStr.replaceAll("&middot;", "·");
		returnStr = returnStr.replaceAll("&cedil;", "¸");
		returnStr = returnStr.replaceAll("&sup1;", "¹");
		returnStr = returnStr.replaceAll("&ordm;", "º");
		returnStr = returnStr.replaceAll("&raquo;", "»");
		returnStr = returnStr.replaceAll("&frac14;", "¼");
		returnStr = returnStr.replaceAll("&frac12;", "½");
		returnStr = returnStr.replaceAll("&frac34;", "¾");
		returnStr = returnStr.replaceAll("&iquest;", "¿");
		returnStr = returnStr.replaceAll("&times;", "×");
		returnStr = returnStr.replaceAll("&divide;", "÷");

    	return returnStr;
    }

    public static String convertLatin2Html(String str) {
    	if(str == null) return null;
    	String returnStr = null;
    	try{
	    	StringBuffer strTxt = new StringBuffer("");
	    	char chrBuff;
	    	int len = str.length();
	
	    	for(int i = 0; i < len; i++) {
	    		chrBuff = (char)str.charAt(i);
	    		switch(chrBuff) {
		    		case '"':strTxt.append("&quot;");break;
		    		case '&':strTxt.append("&amp;");break;
		    		case '<':strTxt.append("&lt;");break;
		    		case '>':strTxt.append("&gt;");break;
		    		case ' ':strTxt.append("&nbsp;");break;
		    		case '¡':strTxt.append("&iexcl;");break;
		    		case '¢':strTxt.append("&cent;");break;
		    		case '£':strTxt.append("&pound;");break;
		    		case '¤':strTxt.append("&curren;");break;
		    		case '¥':strTxt.append("&yen;");break;
		    		case '¦':strTxt.append("&brvbar;");break;
		    		case '§':strTxt.append("&sect;");break;
		    		case '¨':strTxt.append("&uml;");break;
		    		case '©':strTxt.append("&copy;");break;
		    		case 'ª':strTxt.append("&ordf;");break;
		    		case '«':strTxt.append("&laquo;");break;
		    		case '¬':strTxt.append("&not;");break;
		    		case '­':strTxt.append("&shy;");break;
		    		case '®':strTxt.append("&reg;");break;
		    		case '¯':strTxt.append("&macr;");break;
		    		case '°':strTxt.append("&deg;");break;
		    		case '±':strTxt.append("&plusmn;");break;
		    		case '²':strTxt.append("&sup2;");break;
		    		case '³':strTxt.append("&sup3;");break;
		    		case '´':strTxt.append("&acute;");break;
		    		case 'µ':strTxt.append("&micro;");break;
		    		case '¶':strTxt.append("&para;");break;
		    		case '·':strTxt.append("&middot;");break;
		    		case '¸':strTxt.append("&cedil;");break;
		    		case '¹':strTxt.append("&sup1;");break;
		    		case 'º':strTxt.append("&ordm;");break;
		    		case '»':strTxt.append("&raquo;");break;
		    		case '¼':strTxt.append("&frac14;");break;
		    		case '½':strTxt.append("&frac12;");break;
		    		case '¾':strTxt.append("&frac34;");break;
		    		case '¿':strTxt.append("&iquest;");break;
		    		case '×':strTxt.append("&times;");break;
		    		case '÷':strTxt.append("&divide;");break;
		    		
		    		default:strTxt.append(chrBuff);
		    	}
	    	}
	    	returnStr = strTxt.toString();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return returnStr;
    }
}
