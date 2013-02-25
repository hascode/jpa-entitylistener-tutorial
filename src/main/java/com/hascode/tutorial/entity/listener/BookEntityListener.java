package com.hascode.tutorial.entity.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.hascode.tutorial.entity.Book;

public class BookEntityListener {

	@PrePersist
	public void prePersist(final Book book) {
		System.out.println("prePersist: " + book.toString());
	}

	@PostPersist
	public void postPersist(final Book book) {
		System.out.println("postPersist: " + book.toString());
	}

	@PreUpdate
	public void preUpdate(final Book book) {
		System.out.println("preUpdate: " + book.toString());
	}

	@PostUpdate
	public void postUpdate(final Book book) {
		System.out.println("postUpdate: " + book.toString());
	}

	@PostLoad
	public void postLoad(final Book book) {
		System.out.println("postLoad: " + book.toString());
	}

	@PreRemove
	public void preRemove(final Book book) {
		System.out.println("preRemove: " + book.toString());
	}

	@PostRemove
	public void postRemove(final Book book) {
		System.out.println("postRemove: " + book.toString());
	}

}
