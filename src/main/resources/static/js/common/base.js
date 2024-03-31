const view = (function ($) {
    const that = this;
    const extObj = {

        core: {
            profile: 'local',
            serverHost: '',
            serverPort: '',
            authHost: '',
            authPort: '',
            resVer: '',
            get_bo_addr: function() {
                if(this.serverPort === '') {
                    return `${this.serverHost}`;
                }else{
                    return `${this.serverHost}:${this.serverPort}`;
                }
            },
            get_auth_addr: function() {
                if(this.authPort === '') {
                    return `${this.authHost}`;
                }else{
                    return `${this.authHost}:${this.authPort}`;
                }
            },
        },
        validation: {
            /*=============================================================================*
            * Null값 여부 체크
            *
            * param : Null 체크 값
            *
            * return : null값이 아니거나 빈문자열이 아니거나 undefined가 아니라면
            *============================================================================*/
            isNull: function (input) {
                if (input.value == null || input.value == '' || typeof input == 'undefined') {
                    return true;
                } else {
                    return false;
                }
            },
            isValNull: function (value) {
                if (value == null || value == '' || typeof value == 'undefined') {
                    return true;
                } else {
                    return false;
                }
            },
            chkValNull: function (value, dispNull = false) {
                if (view.validation.isValNull(value)) {
                    return dispNull;
                } else {
                    return value;
                }
            },
            /*=============================================================================*
             * 공백값 여부 체크
             *
             * param : 공백여부 체크 값
             *
             * return : 공백외 값이 없다면
             *============================================================================*/
            isEmpty: function (input) {
                if (input.value == null || input.value.replace(/ /gi, '') == '') {
                    return true;
                } else {
                    return false;
                }
            },
            isEmptyVal: function (input) {
                if (input == null || input.replace(/ /gi, '') == '') {
                    return true;
                } else {
                    return false;
                }
            },
            /*=============================================================================*
             * 특정문자 포함체크
             *
             * param : 입력 체크 값, 체크할 문자열
             *
             * return : 체크할 문자가 포함되어있다면
             *============================================================================*/
            containsChars: function (input, chars) {
                for (var i = 0; i < input.value.length; i++) {
                    if (chars.indexOf(input.value.charAt(i)) != -1) {
                        return true;
                    }
                }
                return false;
            },
            isValueEqual: function (input) {
                if (input.value == null || input.value.replace(/ /gi, '') == '') {
                    return true;
                } else {
                    return false;
                }
            },
        },
        utils: {
            getCookie: function(name) {
                var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
                return value? value[2] : null;
            },
            setCookie: function(name, value, exp) {
                var date = new Date();
                date.setTime(date.getTime() + exp*24*60*60*1000);
                document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
            },
            nestedassign: function (target, source) {
                Object.keys(source).forEach((sourcekey) => {
                    if (
                        Object.keys(source).find((targetkey) => targetkey === sourcekey) !== undefined &&
                        typeof source[sourcekey] === 'object'
                    ) {
                        if (target[sourcekey] == null || typeof target[sourcekey] == 'undefined') {
                            target[sourcekey] = this.nestedassign({}, source[sourcekey]);
                        } else {
                            target[sourcekey] = this.nestedassign(target[sourcekey], source[sourcekey]);
                        }
                    } else {
                        target[sourcekey] = source[sourcekey];
                    }
                });
                return target;
            },
        },
        ajax: {
            base_url: '',
            init: function (fnObj) {
                if (typeof document != 'undefined') {
                    if ((typeof fnObj.start != 'undefined' || fnObj.start != null) && typeof fnObj.start == 'function') {
                        $(document).ajaxStart(function () {
                            fnObj.start();
                        });
                    }

                    if ((typeof fnObj.stop != 'undefined' || fnObj.stop != null) && typeof fnObj.stop == 'function') {
                        $(document).ajaxStop(function () {
                            fnObj.stop();
                        });
                    }

                    if ((typeof fnObj.success != 'undefined' || fnObj.success != null) && typeof fnObj.success == 'function') {
                        $(document).ajaxSucess(function () {
                            fnObj.success();
                        });
                    }

                    if ((typeof fnObj.error != 'undefined' || fnObj.error != null) && typeof fnObj.error == 'function') {
                        $(document).ajaxError(function () {
                            fnObj.error();
                        });
                    }

                    if ((typeof fnObj.complete != 'undefined' || fnObj.complete != null) && typeof fnObj.complete == 'function') {
                        $(document).ajaxComplete(function () {
                            fnObj.complete();
                        });
                    }
                }
            },
        },
        apply: function (_extends) {
            this.utils.nestedassign(this, _extends);
        },
    };

    /*
    * 기본 객체들 수정
    * */
    String.prototype.string = function (len) {
        var s = '',
            i = 0;
        while (i++ < len) {
            s += this;
        }
        return s;
    };

    String.prototype.zf = function (len) {
        return '0'.string(len - this.length) + this;
    };

    Number.prototype.zf = function (len) {
        return this.toString().zf(len);
    };



    return extObj;
})(jQuery);
