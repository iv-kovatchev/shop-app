package org.exercise.warehouse.exceptions;

public class NotEnoughGoodsByCategoryException extends Exception {
    public NotEnoughGoodsByCategoryException(String message) {
        super(message);
    }
}
