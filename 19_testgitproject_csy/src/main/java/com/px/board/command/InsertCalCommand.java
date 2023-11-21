package com.px.board.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import lombok.Data; 

// 일정추가 페이지에서 입력내용 : ID, 일정날짜, 제목, 내용
// <select> 2023 11 07 06 10 </select> 선택해서 입력 -> Command
// DB : seq, id, title, content, mdate, regdate -> DTO

//@Data
public class InsertCalCommand {
	
	private int number;
	
	@NotBlank(message = "아이디를 입력하세요!") // 문자열만 가능	
	private String id;
	
	// start_date
	@NotNull(message = "년도를 입력하세요!")
	private int years;
	@NotNull(message = "월을 입력하세요!")
	private int months;
	@NotNull(message = "일을 입력하세요!")
	private int dates;
	@NotNull(message = "시간을 입력하세요!")
	private int hours;
	@NotNull(message = "분를 입력하세요!")
	private int mins;
	
	// end_date
	@NotNull(message = "년도를 입력하세요!")
	private int year;
	@NotNull(message = "월을 입력하세요!")
	private int month;
	@NotNull(message = "일을 입력하세요!")
	private int date;
	@NotNull(message = "시간을 입력하세요!")
	private int hour;
	@NotNull(message = "분를 입력하세요!")
	private int min;
	
	
	@NotBlank(message = "카테고리를 입력하세요!")
	private String category;
	@NotBlank(message = "제목을 입력하세요!") // 문자열만 가능	
	private String title;
	@NotBlank(message = "내용을 입력하세요!") // 문자열만 가능	
	private String content;
	
	public InsertCalCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertCalCommand(int number, @NotBlank(message = "아이디를 입력하세요!") String id,
			@NotNull(message = "년도를 입력하세요!") int years, @NotNull(message = "월을 입력하세요!") int months,
			@NotNull(message = "일을 입력하세요!") int dates, @NotNull(message = "시간을 입력하세요!") int hours,
			@NotNull(message = "분를 입력하세요!") int mins, @NotNull(message = "년도를 입력하세요!") int year,
			@NotNull(message = "월을 입력하세요!") int month, @NotNull(message = "일을 입력하세요!") int date,
			@NotNull(message = "시간을 입력하세요!") int hour, @NotNull(message = "분를 입력하세요!") int min,
			@NotBlank(message = "카테고리를 입력하세요!") String category, @NotBlank(message = "제목을 입력하세요!") String title,
			@NotBlank(message = "내용을 입력하세요!") String content) {
		super();
		this.number = number;
		this.id = id;
		this.years = years;
		this.months = months;
		this.dates = dates;
		this.hours = hours;
		this.mins = mins;
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.min = min;
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getDates() {
		return dates;
	}

	public void setDates(int dates) {
		this.dates = dates;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMins() {
		return mins;
	}

	public void setMins(int mins) {
		this.mins = mins;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "InsertCalCommand [number=" + number + ", id=" + id + ", years=" + years + ", months=" + months
				+ ", dates=" + dates + ", hours=" + hours + ", mins=" + mins + ", year=" + year + ", month=" + month
				+ ", date=" + date + ", hour=" + hour + ", min=" + min + ", category=" + category + ", title=" + title
				+ ", content=" + content + "]";
	}

}









