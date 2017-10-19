package com.example.gson1612b;

/**
 * Created by wangye on 2017/8/23.
 */

public class NetRobot {

  String reason;
  String error_code;
  Result result;

  @Override
  public String toString() {
    return "reason:"+reason+",error_code:"+error_code+",result:"+result;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getError_code() {
    return error_code;
  }

  public void setError_code(String error_code) {
    this.error_code = error_code;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  //对于嵌套的对象  需要创建内部静态类
  public static class Result {

    String code;
    String text;

    @Override
    public String toString() {
      return "code:"+code+",text:"+text;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }


}
