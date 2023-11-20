package com.px.board.dtos;

public class CalDto {

	private int number;
	private String id;
	private String startdate;
	private String enddate;
	private String category;
	private String title;
	private String content;
	
	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalDto(int number, String id, String startdate, String enddate, String category, String title,
			String content) {
		super();
		this.number = number;
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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
		return "CalDto [number=" + number + ", id=" + id + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", category=" + category + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
	
}
