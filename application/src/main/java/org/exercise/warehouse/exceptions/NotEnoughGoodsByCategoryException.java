package org.exercise.warehouse.exceptions;

public class NotEnoughGoodsByCategoryException extends RuntimeException {
    public NotEnoughGoodsByCategoryException(String message) {
        super(message);
    }
}
