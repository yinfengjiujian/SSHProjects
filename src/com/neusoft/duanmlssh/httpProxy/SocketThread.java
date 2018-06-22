package com.neusoft.duanmlssh.httpProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SocketThread extends Thread {
	
	private ServletContext servletContext;
	private ServerSocket serverSocket;
	public Integer count = 0;
	public static int BUFSIZ = 1024;
	public static int CONNECT_RETRIES = 5;
	public static int CONNECT_PAUSE = 5;
	public static int TIMEOUT = 10;
	// 上级代理服务器，可选
	private static int parentPort = -1;
	private static String parent = null;

	public SocketThread(){}
	
	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		WebApplicationContext webAppCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		webAppCtx.getBean("JSPviewResolver");
		
		if (serverSocket == null) {
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		try {
			Integer count = 0;
			while (!this.isInterrupted()) {
				Socket socket = serverSocket.accept();
				count++;
				System.out.println("Server SocketThread start:" + count);
				if (socket != null) {
					String line;
					String host;
					int port = 80;
					Socket outbound = null;
					try {
						socket.setSoTimeout(TIMEOUT);
						//指定Socket的输入流
						InputStream is = socket.getInputStream();
						OutputStream os = null;
						try {
							line = "";
							host = "";
							int state = 0;
							boolean space;
							while (true) {
								int c = is.read();
								if (c == -1)
									break;
								space = Character.isWhitespace((char) c);
								switch (state) {
								case 0:
									if (space)
										continue;
									state = 1;
								case 1:
									if (space) {
										state = 2;
										continue;
									}
									line = line + (char) c;
									break;
								case 2:
									if (space)
										continue;  // 跳过多个空白字符
									state = 3;
								case 3:
									if (space) {
										state = 4;
										// 只取出主机名称部分 
										String host0 = host;
										int n;
										n = host.indexOf("//");
										if (n != -1)
											host = host.substring(n + 2);
										n = host.indexOf('/');
										if (n != -1)
											host = host.substring(0, n);
										// 分析可能存在的端口号
										n = host.indexOf(":");
										if (n != -1) {
											port = Integer.parseInt(host.substring(n + 1));
											host = host.substring(0, n);
										}
										host = processHostName(host0, host, port, socket);
										if (parent != null) {
											host = parent;
											port = parentPort;
										}
										int retry = CONNECT_RETRIES;
										while (retry-- != 0) {
											try {
												outbound = new Socket(host, port);
												break;
											} catch (Exception e) {
												e.printStackTrace();
											}
											// 等待 
//											Thread.sleep(CONNECT_PAUSE);
										}
										if (outbound == null)
											break;
										outbound.setSoTimeout(TIMEOUT);
										os = outbound.getOutputStream();
										os.write(line.getBytes());
										os.write(' ');
										os.write(host0.getBytes());
										os.write(' ');
										pipe(socket.getInputStream(),socket.getOutputStream(),outbound.getInputStream(), os);
										break;
									}
									host = host + (char) c;
									break;
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
			
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							socket.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						try {
							outbound.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}

	/**
	 * 
	 * @Description: 功能说明
	 * @return void
	 * @param clientProxyInput					//请求客户端和代理服务器之间的Socket输入流
	 * @param clientProxyOutput					//请求客户端和代理服务器之间的Socket输出流
	 * @param proxyServerInput					//代理服务器和目标服务器之间的Socket输入流
	 * @param proxyServerOutput					//代理服务器和目标服务器之间的Socket输出流
	 * @throws IOException
	 * @author 
	 * @date Feb 12, 2015
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	void pipe(InputStream clientProxyInput,OutputStream clientProxyOutput,InputStream proxyServerInput, OutputStream proxyServerOutput) throws IOException {
//		String charset = "";
		try {
			int ir;
			byte bytes[] = new byte[BUFSIZ];
			byte readByte[] = {};
			while (true) {
				try {
					if ((ir = clientProxyInput.read(bytes)) > 0) {
						proxyServerOutput.write(bytes, 0, ir);
					} else if (ir < 0)
						break;
				} catch (InterruptedIOException e) {
				}
				try {
					if ((ir = proxyServerInput.read(bytes)) > 0) {
						clientProxyOutput.write(bytes, 0, ir);
						/*charset = filter.getCharset(new String(new String(bytes))).toLowerCase();
						if ("charset=gb2312".equals(charset)) {
							String replace_old = new String(bytes,"gb2312"); 
							System.out.println(replace_old);
							if (replace_old.indexOf("林志容") > 0 || replace_old.indexOf("陈剑新") > 0) {
								String replace_new = filter.filter(replace_old);
								System.out.println(replace_new);
								clientProxyOutput.write(replace_new.getBytes("gb2312"), 0, replace_new.getBytes("gb2312").length);
								writeFile("\r\n=============gb2312====================\n",true);
								writeFile(new String(bytes, "gb2312"), true);
							}else{
								clientProxyOutput.write(bytes, 0, ir);
								writeFile("\r\n=============gb2312====================\n",true);
								writeFile(new String(bytes, "gb2312"), true);
							}
						}else if("charset=gbk".equals(charset)){
							String replace_old = new String(bytes,"gbk"); 
							System.out.println(replace_old);
							
							writeFile("\r\n=============gbk====================\n",true);
							writeFile(new String(bytes, "GBK"), true);
						}else if("charset=utf-8".equals(charset)){
							String replace_old = new String(bytes,"utf-8"); 
							System.out.println(replace_old);
							clientProxyOutput.write(bytes, 0, ir);
							writeFile("\r\n=============utf-8====================\n",true);
							writeFile(new String(bytes, "UTF-8"), true);
						}else if("charset=gb18030".equals(charset)){
							String replace_old = new String(bytes,"gb18030"); 
							System.out.println(replace_old);
							clientProxyOutput.write(bytes, 0, ir);
							writeFile("\r\n=============gb18030====================\n",true);
							writeFile(new String(bytes, "GB18030"), true);
						}else if("charset=iso-8859-1".equals(charset)){
							String replace_old = new String(bytes,"iso-8859-1"); 
							System.out.println(replace_old);
							clientProxyOutput.write(bytes, 0, ir);
							writeFile("\r\n=============iso-8859-1====================\n",true);
							writeFile(new String(bytes, "ISO-8859-1"), true);
						}else{
							clientProxyOutput.write(bytes, 0, ir);
							writeFile("\r\n=============UTF-8====================\n",true);
							writeFile(new String(bytes, "UTF-8"), true);
						}
						if (logging){
							writeLog(bytes, 0, ir, false);
						}
						readByte = this.copayy(readByte, bytes);
						InputStream in = new ByteArrayInputStream(readByte);
						byte[] cache = new byte[in.available()];
						int nRead = 0;
						while ((nRead = in.read(cache)) != -1) {   
							clientProxyOutput.write(cache, 0, nRead);
				        }*/
					}else if (ir < 0){
						break;
					}
				} catch (InterruptedIOException e) {
//					e.printStackTrace();
				}
			}
		} catch (Exception e0) {
			System.out.println("Pipe错误 " + e0);
			e0.printStackTrace();
		}
	}
	
	public void closeServerSocket() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}
	
	// 默认情况下，日志信息输出到  
    // 标准输出设备  
    // 派生类可以覆盖它  
	public String processHostName(String url, String host, int port, Socket sock) {
		java.text.DateFormat cal = java.text.DateFormat.getDateTimeInstance();
		System.out.println(cal.format(new java.util.Date()) + " - " + url + " " + sock.getInetAddress() + "\n");
		return host;
	}

	public byte[] copayy(byte[] old,byte[] new_){
		byte[] new_2 = new byte[new_.length + old.length];
		System.arraycopy(old, 0, new_2, 0, old.length);
		System.arraycopy(new_, 0, new_2, old.length, new_.length);
		return new_2;
	}

}
