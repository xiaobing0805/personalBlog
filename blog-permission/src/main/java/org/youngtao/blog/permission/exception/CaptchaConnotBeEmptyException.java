package org.youngtao.blog.permission.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaConnotBeEmptyException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new UsernameConnotBeEmptyException.
     */
    public CaptchaConnotBeEmptyException() {
        super();
    }

    /**
     * Constructs a new UsernameConnotBeEmptyException.
     *
     * @param message the reason for the exception
     */
    public CaptchaConnotBeEmptyException(String message) {
        super(message);
    }

    /**
     * Constructs a new UsernameConnotBeEmptyException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public CaptchaConnotBeEmptyException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new UsernameConnotBeEmptyException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public CaptchaConnotBeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
