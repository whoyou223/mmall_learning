package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 服务器响应对象
 * 请求返回封装成该对象返回给客户端
 * 封装状态和返回信息、返回数据
 * 支持序列化
 *
 * Created by feng on 2017/7/29.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// 保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable {

    private  int status;
    private  String msg;
    private  T data;

    // 私有构造器
    private ServerResponse(int status){
        this.status = status;
    }
    private  ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }
    private  ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }
    private  ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    // 判断响应是否成功
    @JsonIgnore // 该方法不再Json序列化结果当中
    public  boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public  int getStatus(){
        return  status;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }

    /**
     * 静态方法，响应成功时创建响应对象
     * @param <T>
     * @return
     */
    // 静态方法,创建响应成功的对象
    public  static  <T> ServerResponse<T> createBySuccess(){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    // 静态方法，创建响应成功和指定信息的对象
    public  static  <T> ServerResponse<T> createBySuccessMessage(String msg){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    // 静态方法，创建响应成功和指定数据的对象
    public  static  <T> ServerResponse<T> createBySuccess(T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    // 静态方法，创建响应成功和指定信息、指定数据的对象
    public  static  <T> ServerResponse<T> createBySuccess(String msg, T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 静态方法，响应失败时创建响应对象
     */

    public static  <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }
    public static  <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static  <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return  new ServerResponse<T>(errorCode, errorMessage);
    }


}
