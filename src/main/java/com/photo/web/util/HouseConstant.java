package com.photo.web.util;


public class HouseConstant {

	public static final String FRONTEND_SESSION = "memberPOForFrontend";
	public static final String BACKUP_SESSION = "memberPOForBackup";

	public static final String MESSAGE_LOGIN_FAILED = "登录账号或密码不正确！请核对后再登录！";
	
	public static final String MESSAGE_INPUT_INVALID = "请输入有效的信息";
	public static final String MESSAGE_REGISTER_LOGINACCT_ALREADY_EXIST = "注册账户已存在！";
	public static final String MESSAGE_REGISTER_SUCCESS = "注册成功！请登录！";
	public static final String MESSAGE_ID_INVALID = "Id不得为空！";
	public static final String MESSAGE_ERROR_INTERNAL = "发生服务器内部错误！请重新输入！";
	public static final String MESSAGE_LOGIN_SUCCESS = "登录成功！";
	public static final String MESSAGE_DATA_EMPTY = "数据不存在！";
	public static final String MESSAGE_MEMBER_DO_NOT_BE_USE = "用户不能使用！请联系管理员！";
	public static final String NOT_MANAGER = "暂时不是管理员！不得登录后台！";

	public static final class PathUrl{
		//Frontend
		public static final String FRONTEND_REGISTER = "/frontend/member-register";
		public static final String FRONTEND_LOGIN = "/frontend/member-login";
		public static final String FRONTEND_INFO_SECONDHAND = "/frontend/frontend-info-secondhand";
//		public static final String FRONTEND_INFO_SECONDHAND = "/frontend/frontend-info-secondhand";
		public static final String FRONTEND_INFO_OTHER = "/frontend/frontend-info-other";
		public static final String FRONTEND_INDEX = "/go/to/index";
		public static final String FRONTEND_INFO_PUBLISH = "/frontend/frontend-info-publish";
		public static final String FRONTEND_NEED_PUBLISH = "/frontend/frontend-need-publish";

		//backup
		public static final String BACKUP_LOGIN_DERECTION = "/backup/go/to/login";
		public static final String BACKUP_INDEX_DERECTION = "/backup/go/to/index";
		public static final String BACKUP_LOGIN = "/backup/member-login";
		public static final String BACKUP_INDEX = "/backup/index";
		public static final String BACKUP_MEMBER_MANAGE = "/backup/member-manager";
        public static final String BACKUP_IMAGE_MANAGE = "/backup/image-manager";
		public static final String BACKUP_Article_MANAGE = "/backup/article-manager";
		public static final String BACKUP_Question_MANAGE = "/backup/question-manager";
		public static final String BACKUP_SHARINGFILE_MANAGE = "/backup/sharingfile-manager";
    }
}
