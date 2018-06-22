package com.neusoft.duanmlssh.httpProxy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpProxy extends Thread {
	public static int CONNECT_RETRIES = 5;
	public static int CONNECT_PAUSE = 5;
	public static int TIMEOUT = 10;
	public static int BUFSIZ = 409600;
	public static boolean logging = true;
	public static OutputStream log = null;
	
	protected ContentFilter filter = new ContentFilter();
	protected FileWriter fw;
	//传入数据用的Socket
	protected Socket socket;
	// 上级代理服务器，可选
	private static String parent = null;
	private static int parentPort = -1;
	
	public static void setParentProxy(String name, int pport) {
		parent = name;
		parentPort = pport;
	}
	
	public HttpProxy(){}
	
	// 在给定Socket上创建一个代理线程
	public HttpProxy(Socket s) {
		socket = s;
		start();
	}

	public void writeLog(int c, boolean browser) throws IOException {
		log.write(c);
	}

	public void writeLog(byte[] bytes, int offset, int len, boolean browser)throws IOException {
		for (int i = 0; i < len; i++){
			writeLog((int) bytes[offset + i], browser);
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

	public void run() {  
        while (!this.isInterrupted()) {// 线程未中断执行循环  
            try {  
                Thread.sleep(2000); //每隔2000ms执行一次  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
              
//           ------------------ 开始执行 ---------------------------  
            System.out.println("____FUCK TIME:" + System.currentTimeMillis());  
        }  
    }  
	
	//执行操作的线程
//	public void run() {
//		String line;
//		String host;
//		int port = 80;
//		Socket outbound = null;
//		try {
//			socket.setSoTimeout(TIMEOUT);
//			//指定Socket的输入流
//			InputStream is = socket.getInputStream();
//			OutputStream os = null;
//			try {
//				line = "";
//				host = "";
//				int state = 0;
//				boolean space;
//				while (true) {
//					int c = is.read();
//					if (c == -1)
//						break;
//					if (logging)
//						writeLog(c, true);
//					space = Character.isWhitespace((char) c);
//					switch (state) {
//					case 0:
//						if (space)
//							continue;
//						state = 1;
//					case 1:
//						if (space) {
//							state = 2;
//							continue;
//						}
//						line = line + (char) c;
//						break;
//					case 2:
//						if (space)
//							continue;  // 跳过多个空白字符
//						state = 3;
//					case 3:
//						if (space) {
//							state = 4;
//							// 只取出主机名称部分 
//							String host0 = host;
//							int n;
//							n = host.indexOf("//");
//							if (n != -1)
//								host = host.substring(n + 2);
//							n = host.indexOf('/');
//							if (n != -1)
//								host = host.substring(0, n);
//							// 分析可能存在的端口号
//							n = host.indexOf(":");
//							if (n != -1) {
//								port = Integer.parseInt(host.substring(n + 1));
//								host = host.substring(0, n);
//							}
//							host = processHostName(host0, host, port, socket);
//							if (parent != null) {
//								host = parent;
//								port = parentPort;
//							}
//							int retry = CONNECT_RETRIES;
//							while (retry-- != 0) {
//								try {
//									outbound = new Socket(host, port);
//									break;
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//								// 等待 
////								Thread.sleep(CONNECT_PAUSE);
//							}
//							if (outbound == null)
//								break;
//							outbound.setSoTimeout(TIMEOUT);
//							os = outbound.getOutputStream();
//							os.write(line.getBytes());
//							os.write(' ');
//							os.write(host0.getBytes());
//							os.write(' ');
//							pipe(socket.getInputStream(),socket.getOutputStream(),outbound.getInputStream(), os);
//							break;
//						}
//						host = host + (char) c;
//						break;
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				socket.close();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				outbound.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}

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
		String charset = "";
		try {
			int ir;
			byte bytes[] = new byte[BUFSIZ];
			byte readByte[] = {};
			while (true) {
				try {
					if ((ir = clientProxyInput.read(bytes)) > 0) {
						proxyServerOutput.write(bytes, 0, ir);
						if (logging){
							writeLog(bytes, 0, ir, true);
						}
					} else if (ir < 0)
						break;
				} catch (InterruptedIOException e) {
				}
				try {
					if ((ir = proxyServerInput.read(bytes)) > 0) {
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
						}*/
						readByte = this.copayy(readByte, bytes);
						InputStream in = new ByteArrayInputStream(readByte);
						byte[] cache = new byte[in.available()];
						int nRead = 0;
						while ((nRead = in.read(cache)) != -1) {   
							clientProxyOutput.write(cache, 0, nRead);
				        }
					}else if (ir < 0){
						InputStream in = new ByteArrayInputStream(readByte);
						byte[] cache = new byte[1024];
						int nRead = 0;
						while ((nRead = in.read(cache)) != -1) {   
							clientProxyOutput.write(cache, 0, nRead);
				        }
						System.out.println("000000000000000000000000000");
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
	
	public byte[] write(byte[] readbyte,int size){
		
		return null;
	}
	
	
	
	public byte[] copayy(byte[] old,byte[] new_){
		byte[] new_2 = new byte[new_.length + old.length];
		System.arraycopy(old, 0, new_2, 0, old.length);
		System.arraycopy(new_, 0, new_2, old.length, new_.length);
		return new_2;
	}
	
	public static String BytesToStr(byte[] target) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0, j = target.length; i < j; i++) {
			buf.append((char) target[i]);
		}
		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	public void startProxy(int port, Class clobj) {
		System.out.println("启动8088代理服务器\n");
		ServerSocket ssock;
		try {
			ssock = new ServerSocket(port);
			while (true) {
				Class[] sarg = new Class[1];
				Object[] arg = new Object[1];
				sarg[0] = Socket.class;
				try {
					java.lang.reflect.Constructor cons = clobj.getDeclaredConstructor(sarg);
					arg[0] = ssock.accept();
					cons.newInstance(arg);// 创建HttpProxy或其派生类的实例 
				} catch (Exception e) {
					Socket esock = (Socket)arg[0];
					try {
						esock.close();
					} catch (Exception ec) {
						e.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFile(String str, Boolean flg) {
		try {
			fw = new FileWriter(new File("d:/tmp.log"), flg);
			fw.write("\n" + str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println("启动8088代理服务器\n");
		HttpProxy.log = System.out;
		HttpProxy.logging = false;
		HttpProxy httpProxy = new HttpProxy();
		httpProxy.startProxy(8088, HttpProxy.class);
	}
}
