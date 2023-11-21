package com.px.board.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.px.board.dtos.CalDto;

@Component
public class Util {

	// 한 자리를 두 자리로 변환
	public static String isTwo(String str) {
		return str.length()<2?"0"+str:str; // 5 -> "05", 10 -> "10"
	}
	
	// mdate: 날짜 형식 변환 -> yy-MM-dd HH:mm
	public String toDates(String startdate) {
		//Date 형식: "yyyy-MM-dd HH:mm:ss"
		String s=startdate.substring(0,4)+"-"
				+startdate.substring(4,6)+"-"
				+startdate.substring(6,8)+" "
				+startdate.substring(8,10)+":"
				+startdate.substring(10)+":00";
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm");
		Timestamp tm=Timestamp.valueOf(s); //문자열값을 Date타입으로 변환하는 코드
		sdf.format(tm);// 문자열 타입의 경우 date타입으로 변환해서 사용
		return sdf.format(tm);
	}
	
	public String toDate(String enddate) {
		//Date 형식: "yyyy-MM-dd HH:mm:ss"
		String e=enddate.substring(0,4)+"-"
				+enddate.substring(4,6)+"-"
				+enddate.substring(6,8)+" "
				+enddate.substring(8,10)+":"
				+enddate.substring(10)+":00";
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm");
		Timestamp tm=Timestamp.valueOf(e); //문자열값을 Date타입으로 변환하는 코드
		sdf.format(tm);// 문자열 타입의 경우 date타입으로 변환해서 사용
		return sdf.format(tm);
	}
	
	// 요일별 날짜 색깔 적용하기 : 파라미터 -i, dayOfWeek 필요
	// (공백수+현재일)%7==0 토요일
	// (공백수+현재일)%7==1 일요일
	public static String fontColor(int i, int dayOfWeek) {
		String str="black"; //평일
		if((dayOfWeek-1+i)%7==0) {//토요일
			str="blue";
		}else if((dayOfWeek-1+i)%7==1){//일요일
			str="red";
		}
		return str;
	}
	
	// 일일별 일정 목록 구하는 기능
	// 예약이 1일부터 3일부터 잡혀있으면 1, 2, 3일 모두 표시가 될 수 있도록 해야 함
	public static String getCalViewList(int i, List<CalDto> clist) {
//		String d=isTwo(i+""); // 1 -> "01" 2자리로 변환
		String calList=""; //"<p>title</p><p>title</p><p>title</p>"
//		int sd = Integer.parseInt(startdate);
//		int ed = Integer.parseInt(enddate);
		
		for (int j = 0; j < clist.size(); j++) {
			// 예를 들어 sd = "01" -> 1 / ed = "03" -> 3 => Integer.parseInt()를 이용해 정수로 형변환
			// 그러나 안 되면 IndexOf("찾을 특정 문자") 이용해서 자를 것
			// if(1 <= d && 3 >= d) {}
			
			if(i >= Integer.parseInt(clist.get(j).getStartdate().substring(6,8)) &&
					i <= Integer.parseInt(clist.get(j).getEnddate().substring(6,8))) {
				calList+="<p>"
						+(clist.get(j).getTitle().length()>7?
						 clist.get(j).getTitle().substring(0,7)+"..":
						 clist.get(j).getTitle())
						+"</p>";
			}
		}
		return calList;
	}
	
}


