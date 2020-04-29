package com.thornBird.sbd.modules.common.vo;

public class Result<T> {
	public final static int BIG_IMAGE_WIDTH = 500;
	public final static int BIG_IMAGE_HEIGHT = 500;
	public final static String BIG_IMAGE_PRE = "BIG_";
	public final static int MIDDLE_IMAGE_WIDTH = 100;
	public final static int MIDDLE_IMAGE_HEIGHT = 100;
	public final static String MIDDLE_IMAGE_PRE = "MIDDLE_";
	public final static int SMALL_IMAGE_WIDTH = 50;
	public final static int SMALL_IMAGE_HEIGHT = 50;
	public final static String SMALL_IMAGE_PRE = "SMALL_";
	
	public enum ImageEnum {
		BIG_IMAGE(500, 500, "BIG_"),
		MIDDLE_IMAGE(100, 100, "MIDDLE_"),
		SMALL_IMAGE(50, 50, "SMALL_");
		
		public int width;
		public int height;
		public String pre;
		
		private ImageEnum(int width, int height, String pre) {
			this.width = width;
			this.height = height;
			this.pre = pre;
		}
	}
	
	private int status;
	private String message;
	private T object;
	
	public Result() {
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, T object) {
		this.status = status;
		this.message = message;
		this.object = object;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public enum ResultStatus {
		SUCCESS(200), FAILED(500);
		
		public int status;

		private ResultStatus(int status) {
			this.status = status;
		}
	}

}
