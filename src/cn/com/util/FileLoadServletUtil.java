package cn.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class FileLoadServletUtil extends BaseServlet {
	/**
	 * 上传文件
	 * @param req
	 * @param res
	 * @return
	 * @throws FileUploadException
	 * @throws IOException
	 */
	public static String upload(HttpServletRequest req, HttpServletResponse res)
			throws FileUploadException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = upload.parseRequest(req);
		for (FileItem item : list) {
				String name = item.getName();
				InputStream input = item.getInputStream();
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
				String dateStr= format.format(new Date());
				String basePath = "D:/upload/"+dateStr ;
				File f = new File(basePath);
				if (!f.exists()) {
					f.mkdirs();
				}
				String realName = name
						.substring((name.lastIndexOf("/") == -1 ? name
								.lastIndexOf("\\") : name.lastIndexOf("/")) + 1);
				String newFileName = UUIDGenerator.getUUID() + realName;
				OutputStream output = new FileOutputStream(basePath
						+ newFileName);
				IOUtils.copy(input, output);
				output.close();
				input.close();
				return basePath+newFileName;
		}
		return "";
	}

	/**
	 * 下载文件
	 * @param fileName
	 * @param file
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public static void download(String fileName,String file,HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("content-disposition", "attachment;filename="
				+ URLEncoder.encode(fileName, "utf-8"));
		InputStream input = new FileInputStream(file);
		OutputStream output = res.getOutputStream();
		IOUtils.copy(input, output);
		output.close();
		input.close();
	}
}
