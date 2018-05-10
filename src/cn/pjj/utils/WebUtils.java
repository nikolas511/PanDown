package cn.pjj.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.pjj.bean.Upfile;
import cn.pjj.bean.User;
import cn.pjj.exception.UploadNULLException;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.web.bean.RegisterFormBean;

public class WebUtils {

	public static Upfile upload(HttpServletRequest request, String path)
			throws FileSizeLimitExceededException, UploadNULLException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");// 解决乱码
		File file = new File(path);
		factory.setRepository(file);// 设置缓存
		factory.setSizeThreshold(1024 * 1024 * 50);// 设置缓存大小
		upload.setFileSizeMax(1024 * 1024 * 500);// 设置上传最大文件500m
		Upfile upfile = new Upfile();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String username = item.getFieldName();
					String value = item.getString("utf-8");
					BeanUtils.setProperty(upfile, username, value);
				} else {
					// 代表当前处理的item里面封装的是上传文件
					String filename = item.getName();// 有些浏览器读取到的是完整名字e:/test/1.jpg等,此处直接得到1.jpg
					if (filename == null || filename.trim().equals("")) {// 控制上传的文件不为空
						throw new UploadNULLException();
					}
					String uuidname = generateFileName(filename);
					String savepath = path + uuidname;
					InputStream in = item.getInputStream();
					int len = 0;
					byte[] buffer = new byte[1024];
					/*
					 * 此处自己试验上传文件不会太多所以没有使用 generateSavePath(String path,String
					 * filename)
					 */
					/*FileOutputStream out = new FileOutputStream(savepath);
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}*/
					if(upfile.getId()!=null&&upfile.getId()!=""){
						BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
						Upfile find = bus.find(upfile.getId());
						HdfsUtils.deletedir("/"+find.getUuidname());
					}
					HdfsUtils.uploadFile(in, uuidname);
//					in.close();
//					out.close();
					item.delete();

					upfile.setFilename(filename);
					upfile.setUuidname(uuidname);
					upfile.setSavepath(savepath);
					upfile.setUptime(new Date());
				}
			}
			return upfile;
		} catch (FileSizeLimitExceededException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String generateFileName(String filename) {// 上传的文件名唯一
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		return str+ "_" + filename;
	}

	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void copyBean(RegisterFormBean registerFormBean, User user) {
		user.setUsername(registerFormBean.getUsername());
		user.setPassword(registerFormBean.getPassword());
		user.setBirthday(registerFormBean.getBirthday());
		user.setEmail(registerFormBean.getEmail());
		user.setNickname(registerFormBean.getNickname());
	}
}
