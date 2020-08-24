package br.com.zerosystems.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(Integer status, String erro, Long timestamp) {
		super(status, erro, timestamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addError(String fieldError, String message) {
		list.add(new FieldMessage(fieldError,message));
	}
	
	
	
	
	
}
