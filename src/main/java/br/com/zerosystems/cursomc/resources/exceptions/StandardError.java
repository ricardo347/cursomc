package br.com.zerosystems.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String erro;
	private Long timestamp;
	
	public StandardError() {
		
	}

	public StandardError(Integer status, String erro, Long timestamp) {
		super();
		this.status = status;
		this.erro = erro;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
