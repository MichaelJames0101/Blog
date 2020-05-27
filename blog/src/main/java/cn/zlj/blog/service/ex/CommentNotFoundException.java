package cn.zlj.blog.service.ex;
/**
 * 评论数据不存在异常
 * @author ZLJ
 *
 */
public class CommentNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CommentNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommentNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommentNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
