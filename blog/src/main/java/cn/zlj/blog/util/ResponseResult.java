package cn.zlj.blog.util;

import java.io.Serializable;
/**
 * 响应结果类型
 * @author ZLJ
 *
 * @param <T> 服务器想客户端响应的数据类型
 */
public class ResponseResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer state;
	private String message;
	private T data;
	
	/*
	 * 响应结果类型的各种构造方法，用于支持前端需要什么样的数据返回
	 */
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	

	/*
	 * 响应结果类型的get/set方法
	 */
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
}
