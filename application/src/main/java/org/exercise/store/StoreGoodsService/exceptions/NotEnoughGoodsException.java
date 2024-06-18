package org.exercise.store.StoreGoodsService.exceptions;

public class NotEnoughGoodsException extends RuntimeException {
    public NotEnoughGoodsException(String message) {
        super(message);
    }
}
