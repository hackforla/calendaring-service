package com.hfla.service.calendar.pojos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MeetingType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Id
	private String meetingType; //should be an ENUM
	private String location;
	private int duration;
	private String color;
	private String description;
	private boolean isOnSite;

}
