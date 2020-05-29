package com.zhliang.springboot.request.response.wrapper.interceptor;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.request.response.wrapper
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/27 19:48
 * @version：V1.0
 */
public class ReadHttpServletRequestWarpper extends HttpServletRequestWrapper {

    /**
     * 业务场景一：
     *    需要打印所有请求的信息到log中，在Filter中拦截了所有请求，但是打印信息包含API的body请求体。
     *    而流只能获取一次，如果在Filter中调用request.getInputStream()获取后，会导致controller拿不到数据
     *
     *
     * 业务场景二：
     *    实现一个spring拦截器要读取request数据流，但是reqeust数据流只能读取一次，需要实现自己的HttpServletRequestWrapper对数据流包装
     *
     */

    //保存流中的数据
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public ReadHttpServletRequestWarpper(HttpServletRequest request) throws IOException {
        super(request);
        //从请求中拿到流数据，保存到outputStream
        IOUtils.copy(request.getInputStream(),outputStream);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())));
    }

    //在调用getInputStream函数时，创建新的流 包含原先流中的信息返回
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new ServletInputStream() {
            @Override
            public int readLine(byte[] b, int off, int len) throws IOException {
                return inputStream.read(b, off, len);
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    public void setBody(String body){
        outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(body.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBody(){
        return new String(outputStream.toByteArray());
    }

}
