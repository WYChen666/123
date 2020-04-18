package com.nkl.admin.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nkl.common.action.BaseUploadAction;
import com.nkl.common.util.FindProjectPath;
import com.nkl.common.util.Print;
import com.nkl.common.util.UploadFile;

@SuppressWarnings("serial")
@Controller(value="uploadFileAction")
@Scope("prototype")
public class UploadFileAction extends BaseUploadAction{
	/**
	 * 文件上传
	 */
	public String execute() {
		Print.println("进入execute方法");
		//重命名该文件
		String old_name=getUploadFileName();
		String file_name="data1.txt";
		if ("1".equals(getNum())) {
			String savePath = super.getSavePath();
			String saveFile=FindProjectPath.getRootPath(savePath+"\\"+file_name).replaceAll("%20", " ");
			String tip = Excel2Txt.parseExcel(getUpload(), old_name, saveFile);
			if (tip!=null) {
				setErrorReason(tip);
				return "error";
			}
			return "success";
		}
		
		if ("2".equals(getNum())) {
			file_name="parameters"+old_name.substring(old_name.indexOf("."));
		}else if ("3".equals(getNum())) {
			file_name="one-data"+old_name.substring(old_name.indexOf("."));
		}

		//设置保存文件位置
		String savePath = super.getSavePath();
		String saveFile=FindProjectPath.getRootPath(savePath+"\\"+file_name).replaceAll("%20", " ");
		//文件类型限制
		String allowedTypes = super.getAllowedTypes();
		//上传文件
		String errorString=UploadFile.upload(getUpload(), saveFile, getUploadContentType(), getUpload().length(), allowedTypes, getMaximunSize());
		//判断上传结果
		if(!"".equals(errorString))
		{
			setErrorReason(errorString);
			return "error";
		}
		return "success";
	}
	
	private String num;
	private String savePath1;
	private String allowedTypes1;
	private String savePath2;
	private String allowedTypes2;
	private String savePath3;
	private String allowedTypes3;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSavePath2() {
		return savePath2;
	}

	public void setSavePath2(String savePath2) {
		this.savePath2 = savePath2;
	}

	public String getAllowedTypes2() {
		return allowedTypes2;
	}

	public void setAllowedTypes2(String allowedTypes2) {
		this.allowedTypes2 = allowedTypes2;
	}

	public String getSavePath3() {
		return savePath3;
	}

	public String getAllowedTypes3() {
		return allowedTypes3;
	}

	public void setSavePath3(String savePath3) {
		this.savePath3 = savePath3;
	}

	public void setAllowedTypes3(String allowedTypes3) {
		this.allowedTypes3 = allowedTypes3;
	}

	public String getSavePath1() {
		return savePath1;
	}

	public String getAllowedTypes1() {
		return allowedTypes1;
	}

	public void setSavePath1(String savePath1) {
		this.savePath1 = savePath1;
	}

	public void setAllowedTypes1(String allowedTypes1) {
		this.allowedTypes1 = allowedTypes1;
	}
	
	
	private static final String ERROR_MSG = "msg";
	private static final String ERROR_STACK = "errorStack";

	// action执行结果（true|flase）
	private boolean success = true;

	// action执行结果（json插件返回客户端）
	private Map<Object, Object> result = null;

	// action执行结果原因（json插件返回客户端）
	private Map<Object, Object> errorReason = new HashMap<Object, Object>();

	@JSON(name = "data")
	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}
	 
    /**
     * @Title: setResult
     * @Description: 设置输出接果
     * @param key
     * @param value
     * @return void
     */
    public void setResult(Object key, Object value) {
        if (result == null) {
            result = new HashMap<Object, Object>();
        }
        result.put(key, value);
    }
	
	@JSON(name = "err")
	public Map<Object, Object> getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(Map<Object, Object> errorReason) {
		this.errorReason = errorReason;
	}

	/**
	 * @Title: setErrorReason
	 * @Description: 设置失败原因
	 * @param errorMsg
	 * @return void
	 */
	protected void setErrorReason(String errorMsg) {
		if (errorReason == null) {
			errorReason = new HashMap<Object, Object>();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, "");
	}

	protected void setErrorReason(String errorMsg, Exception e) {
		if (errorReason == null) {
			errorReason = new HashMap<Object, Object>();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, generateStackTrace(e));
	}

	/**
	 * @Title: generateStackTrace
	 * @Description: 生成异常堆栈字符串
	 * @param e
	 * @return
	 * @return String
	 */
	private String generateStackTrace(Exception e) {
		if (e == null) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(byteArrayOutputStream));
			stringBuffer.append(byteArrayOutputStream.toString());
		} catch (Exception ex) {
		} finally {
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException ex2) {
				}
			}
		}
		return stringBuffer.toString();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
     * 得到http session.
     * 
     * @return http session
     */
    protected final HttpSession getSession() {
        return getHttpServletRequest().getSession(true);
    }

    /**
     * 得到http request.
     * 
     * @return http request
     */
    protected final HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request;
    }
    
    /**
     * 得到http session.
     * 
     * @return http request
     */
    protected final HttpSession getHttpSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession();
    }

    /**
     * 得到http response.
     * 
     * @return http response
     */
    protected final HttpServletResponse getHttpServletResponse() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response;
    }
    
}
