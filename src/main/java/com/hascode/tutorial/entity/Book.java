package com.hascode.tutorial.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hascode.tutorial.entity.listener.BookEntityListener;
import com.hascode.tutorial.entity.listener.TitleValidator;

@Entity
@EntityListeners({ BookEntityListener.class, TitleValidator.class })
public class Book implements TitleEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@Temporal(TemporalType.DATE)
	private Date published;

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	@Override
	public final String getTitle() {
		return title;
	}

	public final void setTitle(final String title) {
		this.title = title;
	}

	public final Date getPublished() {
		return published;
	}

	public final void setPublished(final Date published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", published="
				+ published + "]";
	}
}
