package com.nnk.springboot.exceptions;

/**
 * Throwable exception that occurs when the BidList entity we want to find is
 * not found in Database.
 *
 * @author Thierry Schreiner
 */
public class BidListNotFoundException extends Throwable {

    public BidListNotFoundException(String message) {
        // TODO Auto-generated constructor stub
    }

    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = -8334993208786831999L;

}
