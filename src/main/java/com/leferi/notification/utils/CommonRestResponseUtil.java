package com.leferi.notification.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.leferi.notification.dto.CommonRestResponse;
import com.leferi.notification.exception.AbsBaseException;

import java.net.URI;

public class CommonRestResponseUtil {

    public static <T> ResponseEntity<CommonRestResponse<T>> OK(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.ok(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> OK(String msg) {
        return ResponseEntity.ok(CommonRestResponse.OK(msg));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> OK(String msg, T body) {
        return ResponseEntity.ok(CommonRestResponse.OK(msg, "", body));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> CREATED(String uri, CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.created(URI.create(uri)).body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> CREATED(String uri, String msg, T body) {
        return ResponseEntity.created(URI.create(uri)).body(CommonRestResponse.OK(msg, "", body));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> UNAUTHORIZED(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> UNAUTHORIZED(AbsBaseException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonRestResponse.ERROR(ex));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> FORBIDDEN(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> FORBIDDEN(AbsBaseException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommonRestResponse.ERROR(ex));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> NOT_FOUND(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> NOT_FOUND(AbsBaseException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CommonRestResponse.ERROR(ex));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> BAD_REQUEST(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.badRequest().body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> BAD_REQUEST(AbsBaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonRestResponse.ERROR(ex));
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> INTERNAL_SERVER_ERROR(CommonRestResponse<T> commonRestResp) {
        return ResponseEntity.internalServerError().body(commonRestResp);
    }

    public static <T> ResponseEntity<CommonRestResponse<T>> INTERNAL_SERVER_ERROR(AbsBaseException ex) {
        return ResponseEntity.internalServerError().body(CommonRestResponse.ERROR(ex));
    }




}
