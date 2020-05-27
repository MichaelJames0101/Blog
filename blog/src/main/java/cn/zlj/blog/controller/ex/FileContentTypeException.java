package cn.zlj.blog.controller.ex;
/**
 * 上传的文件类型错误的异常
 * @author ZLJ
 *
 */
public class FileContentTypeException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileContentTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileContentTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
