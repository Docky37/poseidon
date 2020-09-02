package com.nnk.springboot.exceptions;

/**
 * Throwable exception that occurs when the CurvePoint entity we want to find is
 * not found in Database.
 *
 * @author Thierry Schreiner
 */
public class CurvePointNotFoundException extends Throwable {

    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = -5135976654933968945L;

    /**
     * Class constructor.
     * @param message
     */
    public CurvePointNotFoundException(final String message) {
    }

}
