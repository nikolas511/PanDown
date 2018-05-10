package cn.pjj.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import cn.pjj.bean.Upfile;
public class HdfsUtils {
	public static void uploadFile(InputStream in,String filename) {
		try {
			Configuration conf = new Configuration();
			conf.set(Config.hname, Config.hvalue);
			FileSystem fs = FileSystem.get(conf);
			Path path = new Path("/"+filename);
			FSDataOutputStream out = fs.create(path);
			byte[] b= new byte[1024];
			int len=0;
			while((len=in.read(b))!=-1) {
				out.write(b, 0, len);
			}
			out.close();
			in.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹
	 * @param dir
	 */
	public static void deletedir(String dir) {
		try {
			Configuration conf = new Configuration();
			conf.set(Config.hname, Config.hvalue);
			FileSystem fs = FileSystem.get(conf);
			Path path = new Path(dir);
			fs.delete(path);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void downFile(Upfile upfile,HttpServletResponse response) {
		try {
			String filename="/"+upfile.getUuidname();
			Configuration conf = new Configuration();
			conf.set(Config.hname, Config.hvalue);//配置
			FileSystem fs = FileSystem.get(conf);
			Path path = new Path(filename);
			FSDataInputStream in = fs.open(path);
			response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(upfile.getFilename(), "utf-8"));
			int len=0;
			byte[] b = new byte[1024];
			OutputStream out = response.getOutputStream();
			while((len=in.read(b))>0){
				out.write(b, 0, len);
			}
			in.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
