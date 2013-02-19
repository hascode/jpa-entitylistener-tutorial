package com.hascode.tutorial.entity.listener;

import javax.persistence.PersistenceException;
import javax.persistence.PrePersist;

import com.hascode.tutorial.entity.TitleEntity;

public class TitleValidator {
	private final String[] badWords = { "shitty", "xxx", "..." };

	@PrePersist
	public void checkBadWords(final TitleEntity titleEntity) {
		for (String badWord : badWords) {
			if (titleEntity.getTitle().contains(badWord)) {
				System.err.println("bad word in title detected: " + badWord);
				throw new PersistenceException("bad word in title detected: "
						+ badWord);
				// should use ValidationException if bean validation is present
			}
		}
	}
}
