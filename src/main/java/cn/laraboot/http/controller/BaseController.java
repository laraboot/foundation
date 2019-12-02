package cn.laraboot.http.controller;

import cn.laraboot.http.response.HttpResponseEntity;
import cn.laraboot.http.response.Response;
import org.springframework.http.HttpStatus;

abstract public class BaseController {
    protected <T> Response ok(T data) {
        return Response.ok(data);
    }

    protected <T> Response fail(T data) {
        return Response.fail(data);
    }

    protected HttpResponseEntity raw(Object data) {
        return new HttpResponseEntity<>(data, HttpStatus.OK);
    }
}
