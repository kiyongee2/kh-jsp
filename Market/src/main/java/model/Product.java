package model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int pno;
	private String pid;
	private String pname;
	private int price;
	private String description;
	private String category;
	private long pstock;
	private String condition;
	private String pimage;
	private Timestamp regDate;
	private Timestamp updateDate;
}
