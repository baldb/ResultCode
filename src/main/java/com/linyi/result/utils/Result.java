package com.linyi.result.utils;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对返回的结果集进行统一规划及其调用
 * @param <T>
 * @ApiModel(value = "全局统一返回结果"):Swagger包下的注解
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;
    //无参构造器
    public Result() {}

    //创建结果集，返回数据----》只传数据
    protected static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        //判断数据是否为空，否则给返回结果集的data数据添加数据
        if (data!=null) {
            result.setData(data);
        }
        return result;
    }

    //创建结果集，返回数据----》传数据及其result的枚举类型
    //调用上面的build去创建result并传入数据
    public static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    /**
     * 操作成功后返回的结果集设置。返回的是有数据的结果集
     * @param data
     * @param <T>
     * @return ：调用build的方法去返回结果集
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 返回的是一个没有数据的结果集。调用了ok的有参方法
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(){
        return ok(null);
    }


    /**
     * 操作失败，带数据的
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    /**
     * 操作失败，不带数据的，调用fail的有参方法
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 只有一个消息提醒
     * @param msg
     * @return
     */
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    /**
     * 只有一个状态码
     * @param code
     * @return
     */
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 判断是否成功
     * @return
     */
    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }






















}
