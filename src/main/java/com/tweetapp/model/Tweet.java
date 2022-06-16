package com.tweetapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String text;
	private String tag;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		String username = user.getEmail().split("@")[0];
		StringBuffer underline = new StringBuffer();
		for (int i = 0; i < username.length(); i++) {
			underline.append("-");
		}
		return "\n@" + username + "\n" + underline + "\n" + (text.isEmpty() ? "" : "|" + text) + "\n\t"
				+ (tag.isEmpty() ? "" : "|" + tag);
	}
}
