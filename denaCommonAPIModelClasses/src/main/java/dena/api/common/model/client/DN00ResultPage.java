package dena.api.common.model.client;

import lombok.Getter;

public record DN00ResultPage(@Getter int firstItemPosition,
							 @Getter int numberOfItemsToBeReturned) {
	public static DN00ResultPage create(final int firstItemPosition,final int numberOfItemsToBeReturned) {
		return new DN00ResultPage(firstItemPosition,numberOfItemsToBeReturned);
	}
}
