view.apply({
    ajax: {
        base_url: view.core.get_bo_addr(),
        commonRequest: async function (method = 'POST', url, params, fn_progress = null) {
            let isFormData = false;
            let headerOption = {};
            let progressFunc = null;
            if (params != null && params instanceof FormData) {
                isFormData = true;
                headerOption = { 'Content-Type': 'multipart/form-data',
                    'LECOMMEND-AUTH-TOKEN': view.utils.getCookie('LECOMMEND-AUTH-TOKEN')
                };
                progressFunc = (progressEvent) => {
                    const totalLength = progressEvent.lengthComputable
                        ? progressEvent.total
                        : progressEvent.target ? progressEvent.target.getResponseHeader('content-length') ||
                        							progressEvent.target.getResponseHeader('x-decompressed-content-length')
                        						: null;
                    if (totalLength !== null) {
                        const percent = Math.round((progressEvent.loaded * 100) / totalLength);
                        if (fn_progress != null) {
                            fn_progress(totalLength, progressEvent.loaded, percent);
                        }
                        console.log(`onUploadProgress ${progressEvent.loaded}/${totalLength}(${percent}%)`);
                    }
                };
            }
            // else if (params != null && params instanceof URLSearchParams == false) {
            //     let isProcess = true;
            //     if (typeof params.isProcessing != 'undefined') {
            //         isProcess = params.isProcessing;
            //     }
            //
            //     if (isProcess) {
            //         const urlParms = new URLSearchParams();
            //         $(Object.entries(params)).each(function (idx, val) {
            //             urlParms.append(val[0], val[1]);
            //         });
            //         params = urlParms;
            //     }
            // }
            else{
                headerOption = { 'Content-Type': 'application/json',
                    'LECOMMEND-AUTH-TOKEN': view.utils.getCookie('LECOMMEND-AUTH-TOKEN')
                };
            }

            /*if (view.ajax.base_url == '') {
                return new Promise(function (resolve, reject) {
                    reject('BASE URL IS EMPTY');
                });
            }*/

             if (typeof axios != 'undefined' && axios != null) {
//            if (false   ) {
                return await (method == 'GET'
                        ? axios.get(view.ajax.base_url + url, { params: params })
                        : axios({
                            url: view.ajax.base_url + url,
                            method: method,
                            data: params,
                            // processData: false,	// 필수
                            // contentType: false,	// 필수
                            headers: headerOption,
                            onUploadProgress: progressFunc,
                        })
                )
                    // .then((res) => {
                    //     // if(res.status != 200) {
                    //     //     throw new Error(res.res);
                    //     // }
                    //     return res;
                    // })
                    .then((res) => {
                        // 데이터 체크
                        if (res.data != null) {
                            return res.data;
                        } else {
                            return res;
                        }
                    })
                    .catch((err) => {
                        console.debug(err.response);

//                        if(err.response.data.resCd == LcmdConsts.ERestErrorCode.FailAuth){
//                        	alert(err.response.data.msg);
//                        	location.reload();
//                        }
//                         if (err.response.data.resCd == LcmdConsts.ERestErrorCode.NotAuthorizedMember) {
//                             alert(err.response.data.msg);
//                             // location.reload();
//                         }else{
                            msg = '';
                            if (typeof err.response.data.messages != 'undefeind') {
                                msg = err.response.data.messages;
                                if (typeof msg == 'object') {
                                    let tmp = '';
                                    Object.entries(err.response.data.messages).forEach(function (item) {
                                        tmp += item[1] + '\n';
                                    });
                                    msg = tmp;
                                }
                            } else {
                                msg = err.response.statusText;
                            }

                            // alert(msg);
                            throw err.response;
                        // }
                        

                    });
            } else {
                // es6 promise type
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        url:  view.ajax.base_url + url, // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
                        data: params, // HTTP 요청과 함께 서버로 보낼 데이터
//                        method: 'POST', // HTTP 요청 메소드(GET, POST 등)
                        method: method, // HTTP 요청 메소드(GET, POST 등)
                        dataType: 'json', // 서버에서 보내줄 데이터의 타입
                        contentType:'application/json; charset=utf-8',
                    })
                        .done(function (json) {
                            if (json) {
                                // resp check
                                resolve(json);
                            } else {
                                reject('ajax error');
                            }
                        })
                        // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
                        .fail(function (xhr, status, errorThrown) {

                            var msg = '';
                            if(xhr.responseJSON){
                            	msg = xhr.responseJSON.msg;
                            }else{
                            	msg = '오류가 발생했습니다.';
                            }

//                            alert(JSON.stringify(msg));
                            reject(new Error(errorThrown));
                        })
                        .always(function (xhr, status) {
                            console.debug('ajax request logging');
                        });
                });
            }
        },
        commonRequestInstance: function (method = 'POST', url, params, fn_progress = null) {
            let isFormData = false;
            let headerOption = {};
            let progressFunc = null;
            if (params != null && params instanceof FormData) {
                isFormData = true;
                headerOption = { 'Content-Type': 'multipart/form-data' };
                progressFunc = (progressEvent) => {
                    const totalLength = progressEvent.lengthComputable
                        ? progressEvent.total
                        : progressEvent.target.getResponseHeader('content-length') ||
                        progressEvent.target.getResponseHeader('x-decompressed-content-length');
                    if (totalLength !== null) {
                        const percent = Math.round((progressEvent.loaded * 100) / totalLength);
                        if (fn_progress != null) {
                            fn_progress(totalLength, progressEvent.loaded, percent);
                        }
                        console.log(`onUploadProgress ${progressEvent.loaded}/${totalLength}(${percent}%)`);
                    }
                };
            }
            // else if (params != null && params instanceof URLSearchParams == false) {
            //     const urlParms = new URLSearchParams();
            //     $(Object.entries(params)).each(function (idx, val) {
            //         urlParms.append(val[0], val[1]);
            //     });
            //     params = urlParms;
            // }
            else{
                headerOption = { 'Content-Type': 'application/json',
                    'LECOMMEND-AUTH-TOKEN': view.utils.getCookie('LECOMMEND-AUTH-TOKEN')
                };
            }

            if (view.ajax.base_url == '') {
                return new Promise(function (resolve, reject) {
                    reject('BASE URL IS EMPTY');
                });
            }

            if (typeof axios != 'undefined' && axios != null) {
                return method == 'GET'
                    ? axios.get(view.ajax.base_url + url, { params: params })
                    : axios({
                        url: view.ajax.base_url + url,
                        method: method,
                        data: params,
                        headers: headerOption,
                        onUploadProgress: progressFunc,
                    })
            } else {
                // es6 promise type
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        url: url, // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
                        data: params, // HTTP 요청과 함께 서버로 보낼 데이터
                        method: 'POST', // HTTP 요청 메소드(GET, POST 등)
                        dataType: 'json', // 서버에서 보내줄 데이터의 타입
                    });
                });
            }
        },
        commonGet: function (url, params) {
            return view.ajax.commonRequest('GET', url, params);
        },
        commonPost: function (url, params, fn_progress = null) {
            return view.ajax.commonRequest('POST', url, params, fn_progress);
        },
        commonPostAsync: async function (url, params, fn_progress = null) {
            return await view.ajax.commonRequest('POST', url, params, fn_progress);
        },
        commonPut: function (url, params) {
            return view.ajax.commonRequest('PUT', url, params);
        },
        commonPatch: function (url, params) {
            return view.ajax.commonRequest('PATCH', url, params);
        },
        commonDelete: function (url, params) {
            return view.ajax.commonRequest('DELETE', url, params);
        },
        commonFrom: function (url, params) {
            var form = $('<form></form>');
            form.attr('action', url);
            form.attr('method', 'post');
            form.appendTo('body');

            $.each(params, function (key, value) {
                form.append($('<input type="hidden" value="' + value + '" name="' + key + '">'));
            });

            form.submit();
        },
        multipleRequestAxios: async function (...commonRequest) {
            return await axios.all(commonRequest).then(async (resp) => {
                return resp.map((resp) => resp.data);
            });
        },
        file_upload_form: async function (type='image', file, username, fn_progress = null){
        	if(!view.validation.checkInputFileType(type, file)){
        		$(file).val(null);
        		return false;
        	}
            
            var params = new FormData();
        	params.append("file",  file.files[0]);
        	params.append("regId", username);
            params.append("baseDir", 'file');
            params.append("width", 450);
            params.append("height", 450);
            
            let headerOption = {};
            let progressFunc = null;
            
            headerOption = { 'Content-Type': 'multipart/form-data',
                'LECOMMEND-AUTH-TOKEN': view.utils.getCookie('LECOMMEND-AUTH-TOKEN')
            };
            progressFunc = (progressEvent) => {
                const totalLength = progressEvent.lengthComputable
                    ? progressEvent.total
                    : progressEvent.target.getResponseHeader('content-length') ||
                    progressEvent.target.getResponseHeader('x-decompressed-content-length');
                if (totalLength !== null) {
                    const percent = Math.round((progressEvent.loaded * 100) / totalLength);
                    if (fn_progress != null) {
                        fn_progress(totalLength, progressEvent.loaded, percent);
                    }
                }
            };

            if (typeof axios != 'undefined' && axios != null) {
                return await (
                		axios({
                            url: view.ajax.fs_url+'/add',
                            method: 'POST',
                            data: params,
                            headers: headerOption,
                            onUploadProgress: progressFunc,
                        })
                )
                .then((res) => {
                    return res;
                })
                .then((res) => {
                    // 데이터 체크
                    if (res.data != null) {
                        return res.data;
                    } else {
                        return res;
                    }
                })
                .catch((err) => {
                    console.debug(err.response);

                    msg = '';
                    if (typeof err.response.data.messages != 'undefeind') {
                        msg = err.response.data.messages;
                        if (typeof msg == 'object') {
                            let tmp = '';
                            Object.entries(err.response.data.messages).forEach(function (item) {
                                tmp += item[1] + '\n';
                            });
                            msg = tmp;
                        }
                    } else {
                        msg = err.response.statusText;
                    }

                    // alert(msg);
                    throw err.response;
                });
            } else {
                // es6 promise type
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        url: view.ajax.fs_url+'/add', // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
                        data: params, // HTTP 요청과 함께 서버로 보낼 데이터
                        method: 'POST', // HTTP 요청 메소드(GET, POST 등)
                        dataType: 'json', // 서버에서 보내줄 데이터의 타입
                    })
                        .done(function (json) {
                        	console.log(json);
                            if (json) {
                                // resp check
                                resolve(json);
                            } else {
                                reject('ajax error');
                            }
                        })
                        // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
                        .fail(function (xhr, status, errorThrown) {
                            console.debug(err);

                            msg = '';
                            if (typeof err.response.data.messages.error == 'undefeind') {
                                msg = err.response.data.messages;
                            } else {
                                msg = err.response.data.messages.error;
                            }

                            alert(JSON.stringify(msg));
                            reject(new Error(errorThrown));
                        })
                        .always(function (xhr, status) {
                            console.debug('ajax request logging');
                        });
                });
            }
    	},
        user_modify_json : function(data) {
            return view.ajax.commonPut(LcmdConsts.EBoRestApiAddr.MEMBER_CRUD + data.mbrId,
                JSON.stringify(data));
        },
        user_delete_json : function(data) {
            return view.ajax.commonPost(LcmdConsts.EBoRestApiAddr.MEMBER_CRUD + data.mbrId,
                JSON.stringify(data));
        },


    }
});



