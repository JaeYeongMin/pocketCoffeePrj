package com.springbook.view.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.springbook.biz.member.MemberService;
import com.springbook.biz.member.MemberVO;
import com.springbook.view.common.StringUtil;



@Controller
@SessionAttributes("member")  // 모델 속성을 세션에도 저장
public class MemberController{
    
    @Autowired
    private MemberService memberService;
    
    
    
    @ModelAttribute("conditionMap")
    public Map<String,String> serchConditionMap(){
        Map<String,String> conditionMap = new HashMap<String,String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
      
        return conditionMap;
    }
    


    
    

    // 회원 가입
    @RequestMapping("/member/createMember.do")
    public @ResponseBody HashMap<String, Object> createMember(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String, Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
		HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		
		try {
			
			if(StringUtil.getEmptyString(reqMap.get("MEMBER_ID")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_ID]";
				retnCode = "400";
			}else if(StringUtil.getEmptyString(reqMap.get("MEMBER_PW")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_PW]";
				retnCode = "400";
			}  else if(StringUtil.getEmptyString(reqMap.get("MEMBER_NAME")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_NAME]";
				retnCode = "400";
			} else if(StringUtil.getEmptyString(reqMap.get("MEMBER_NICK")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_NICK]";
				retnCode = "400";
			} else if(StringUtil.getEmptyString(reqMap.get("MEMBER_HPNUM")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_HPNUM]";
				retnCode = "400";
			} else if(StringUtil.getEmptyString(reqMap.get("MEMBER_GENDER")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_GENDER]";
				retnCode = "400";
			} else {
				
				// 회원등록
				String member_seq = memberService.createMember(reqMap);
				reqMap.put("MEMBER_SEQ", member_seq);
				
				// 회원등록후 정보 리턴
				resBody = memberService.getMemberDetail(reqMap);
				
				if(resBody == null) {
					retnMent = "회원등록중 오류가 발생했습니다.";
					retnCode = "999";
				}else {
					retnMent = "성공";
					retnCode = "200";
				}
			}

		} catch (Exception e){
			retnMent = "시스템 오류 입니다.";
			retnCode = "999";
			resBody = null;
		} finally {
			resHead.put("RETN_CODE", retnCode);
			resHead.put("RETN_MENT", retnMent);
		}
		
		resMap.put("RES_BODY", resBody);
		resMap.put("RES_HEAD", resHead);
		
        return resMap;  // View 이름 리턴
    }
    
    
    


    // 회원 로그인
    @RequestMapping("/member/loginMember.do")
    public @ResponseBody HashMap<String, Object> loginMember(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request, HttpSession session) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> loginMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String,Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
		HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		String loginResult = null;
		
		try {
			if(StringUtil.getEmptyString(reqMap.get("MEMBER_ID")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_ID]";
				retnCode = "400";
			} else if(StringUtil.getEmptyString(reqMap.get("MEMBER_PW")).equals("")) {
				retnMent = "필수값 누락 [MEMBER_PW]";
				retnCode = "400";
			} else {
				
				// 로그인 조회
				loginMap = memberService.loginMember(reqMap);
				
				if(StringUtil.getEmptyString(loginMap.get("MEMBER_SEQ")).equals("")) {
					retnMent = "회원정보가 없습니다.";
					retnCode = "500";
				}else {
					retnMent = "성공";
					retnCode = "200";
					
					
					session.setAttribute("SS_MEMBER_NICK" , loginMap.get("MEMBER_NICK"));
					session.setAttribute("SS_MEMBER_NAME" , loginMap.get("MEMBER_NAME"));
					session.setAttribute("SS_MEMBER_SEQ" , loginMap.get("MEMBER_SEQ"));
					session.setAttribute("MEMBER_GRADE" , loginMap.get("MEMBER_GRADE"));
					
					resBody.put("MEMBER_SEQ", loginMap.get("MEMBER_SEQ"));
					resBody.put("MEMBER_NAME", loginMap.get("MEMBER_NAME"));
					resBody.put("MEMBER_NICK", loginMap.get("MEMBER_NICK"));
					resBody.put("MENT", "로그인 성공");
					
					
					
				}
			}

		} catch (Exception e){
			retnMent = "시스템 오류 입니다.";
			retnCode = "999";
			resBody = null;
		} finally {
			resHead.put("RETN_CODE", retnCode);
			resHead.put("RETN_MENT", retnMent);
		}
		
		resMap.put("RES_BODY", resBody);
		resMap.put("RES_HEAD", resHead);
		
        return resMap;  // View 이름 리턴
    }
    
    

    
    
    
    
    // 회원 상세보기
    @RequestMapping("/member/getMemberDetail.do")
    public @ResponseBody HashMap<String, Object> getMemberDetail(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String,Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
		HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		try {
			
			resBody = memberService.getMemberDetail(reqMap);
			
			if(resBody == null) {
				retnMent = "조회하신 회원이 없습니다.";
				retnCode = "500";
			}else {
				retnMent = "성공";
				retnCode = "200";
			}
			

		} catch (Exception e){
			retnMent = "시스템 오류 입니다.";
			retnCode = "999";
			resBody = null;
		} finally {
			resHead.put("RETN_CODE", retnCode);
			resHead.put("RETN_MENT", retnMent);
		}
		
		resMap.put("RES_BODY", resBody);
		resMap.put("RES_HEAD", resHead);
		
        return resMap;  // View 이름 리턴
    }
    
    
    

    
    // 회원 상세보기
    //@RequestMapping("/member/getTest.do")
    @RequestMapping(value = "/member/getTest.do" , method=RequestMethod.POST)
    public void getTest(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	String test = "test";
    	System.out.println(test);
		
    }
    
}