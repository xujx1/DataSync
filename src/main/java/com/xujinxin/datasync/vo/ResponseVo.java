package com.xujinxin.datasync.vo;

import com.xujinxin.datasync.enums.ResponseType;

/**
 * 公共返回数据接口
 * success 操作结果,默认true
 * msg 操作信息
 * errorCode 操作码
 * data 返回数据
 * <p>
 * 应该优先使用建造者模式生成对象
 * new ResponseVo.Builder().build()
 */
public class ResponseVo {
    private boolean flag = true;
    private String msg = ResponseType.SUCCESS.getCode();
    private Object data;

    public ResponseVo() {
    }

    public ResponseVo(Builder builder) {
        this.flag = builder.flag;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Builder {

        private boolean flag = true;

        private String msg = ResponseType.SUCCESS.getMsg();

        private Object data;

        public Builder() {
        }

        public Builder success(boolean success) {
            this.flag = success;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseVo build() {
            return new ResponseVo(this);
        }
    }
}
