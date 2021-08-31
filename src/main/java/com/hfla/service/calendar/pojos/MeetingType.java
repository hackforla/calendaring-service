package com.hfla.service.calendar.pojos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class MeetingType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String meetingType; //should be an ENUM
	private String location;
	private int duration;
	private String color;
	private String description;
	private boolean isOnSite;

}
