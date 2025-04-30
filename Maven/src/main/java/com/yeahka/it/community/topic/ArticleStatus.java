package com.yeahka.it.community.topic;

public enum ArticleStatus {
    BEING_CREATED, PAID_FOR, FULFILLED, CANCELLED;

    /**
     * Verify the transition between {@link ArticleStatus} is valid.
     * NOTE: This is where any/all rules for state transitions should be kept and enforced.
     */
    static boolean valid(ArticleStatus currentStatus, ArticleStatus newStatus) {
        if (currentStatus == BEING_CREATED) {
            return newStatus == PAID_FOR || newStatus == CANCELLED;
        } else if (currentStatus == PAID_FOR) {
            return newStatus == FULFILLED;
        } else if (currentStatus == FULFILLED) {
            return false;
        } else if (currentStatus == CANCELLED) {
            return false;
        } else {
            throw new RuntimeException("Unrecognized situation.");
        }
    }

}
