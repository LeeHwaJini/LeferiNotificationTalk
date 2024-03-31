view.apply({
    validation: {
        check: function (data) {
          return true;
        },
        /*=============================================================================*
                   * 주민등록 여부를 확인한다.(내국인)
                   *
                   * param : sID 입력문자열(주민번호 13자리)
                   *
                   * return : Boolean true이면 적합한 주민번호
                   *============================================================================*/
        isSocialNO: function (ssn) {
            var digit = 0;
            for (var i = 0; i < ssn.length; i++) {
                var str_dig = ssn.substring(i, i + 1);
                if (str_dig < '0' || str_dig > '9') {
                    digit = digit + 1;
                }
            }
            if (digit > 0) {
                return false;
            }

            var year = parseInt(ssn.substring(0, 2));
            var month = parseInt(ssn.substring(3, 4));
            var day = parseInt(ssn.substring(5, 6));
            var gender = parseInt(ssn.substring(7, 7));
            var local = parseInt(ssn.substring(8, 11));
            var key = parseInt(ssn.substring(12));

            if (month < 0 || month > 12) {
                return false;
            }

            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day < 0 || day > 31) {
                    return false;
                }
            }

            if (month == 4 || month == 6 || month == 9 || month == 10) {
                if (day < 0 || day > 30) {
                    return false;
                }
            }

            if (month == 2) {
                if (year == 0 && (gender == 1 || gender == 2)) {
                    if (day < 0 || day > 28) {
                        return false;
                    }
                } else if (year == 0 && (gender == 3 || gender == 4)) {
                    if (day < 0 || day > 29) {
                        return false;
                    }
                } else if (year % 4 == 0) {
                    if (day < 0 || day > 29) {
                        return false;
                    }
                } else {
                    if (day < 0 || day > 28) {
                        return false;
                    }
                }
            }

            if (gender < 0 || gender > 4) {
                return false;
            }

            cBit = 0;
            sCode = '234567892345';

            for (i = 0; i < 12; i++) {
                cBit = cBit + parseInt(ssn.substring(i, i + 1)) * parseInt(sCode.substring(i, i + 1));
            }

            cBit = 11 - (cBit % 11);
            cBit = cBit % 10;

            if (key != cBit) {
                return false;
            } else {
                return true;
            }
        },
        /*=============================================================================*
         * 주민등록 여부를 확인한다.(외국인)
         *
         * param : sID 입력문자열(주민번호 13자리)
         *
         * return : Boolean true이면 적합한 주민번호
         *============================================================================*/
        isFgnSocialNO: function (ssn) {
            if (ssn.charAt(6) == '5' || ssn.charAt(6) == '6') {
                birthYear = '19';
            } else if (ssn.charAt(6) == '7' || ssn.charAt(6) == '8') {
                birthYear = '20';
            } else if (ssn.charAt(6) == '9' || ssn.charAt(6) == '0') {
                birthYear = '18';
            } else {
                return false;
            }

            birthYear += ssn.substr(0, 2);
            birthMonth = ssn.substr(2, 2) - 1;
            birthDate = ssn.substr(4, 2);
            birth = new Date(birthYear, birthMonth, birthDate);

            if (birth.getYear() % 100 != ssn.substr(0, 2) || birth.getMonth() != birthMonth || birth.getDate() != birthDate) {
                return false;
            }

            var sum = 0;
            var odd = 0;

            buf = new Array(13);
            for (i = 0; i < 13; i++) buf[i] = parseInt(ssn.charAt(i));

            odd = buf[7] * 10 + buf[8];

            if (odd % 2 != 0) {
                return false;
            }

            if (buf[11] != 6 && buf[11] != 7 && buf[11] != 8 && buf[11] != 9) {
                return false;
            }

            multipliers = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];
            for (i = 0, sum = 0; i < 12; i++) sum += buf[i] *= multipliers[i];

            sum = 11 - (sum % 11);

            if (sum >= 10) sum -= 10;

            sum += 2;

            if (sum >= 10) sum -= 10;

            if (sum != buf[12]) {
                return false;
            } else {
                return true;
            }
        },
        /*=============================================================================*
         * 사업자등록번호 유효성 여부를 확인한다.
         *
         * param : sID 입력문자열(사업자번호 10자리)
         *
         * return : Boolean true이면 적합한 사업자등록번호
         *============================================================================*/
        isBizNumber: function (value) {
            var valueMap = value
                .replace(/-/gi, '')
                .split('')
                .map(function (item) {
                    return parseInt(item, 10);
                });

            if (valueMap.length === 10) {
                var multiply = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5);
                var checkSum = 0;

                for (var i = 0; i < multiply.length; ++i) {
                    checkSum += multiply[i] * valueMap[i];
                }

                checkSum += parseInt((multiply[8] * valueMap[8]) / 10, 10);
                return Math.floor(valueMap[9]) === 10 - (checkSum % 10);
            }

            return false;
        },
        /**
         *
         결과값 : true/false
         조건  :
         0 = 첫글자 영문, 영문, 숫자, _ 사용가능
         1 = 영문만 사용가능
         2 = 숫자만 사용가능
         3 = 한글만 사용가능
         4 = 영문, 숫자 사용가능
         5 = 영문, 숫자, 한글 사용가능
         6 = 한글, 숫자 사용가능
         7 = 한글, 영문 사용가능
         8 = 한글을 포함하는지 여부
         * @param condition
         */
        //한글 유효성검사 가-힣 -> ㄱ-힣 모음도 처리되도록 변경
        isValueTestRegxStr(value, condition) {
            let objPattern = '';
            switch (condition) {
                case 0:
                    objPattern = /^[a-zA-Z]{1}[a-zA-Z0-9_]+$/;
                    break;
                case 1:
                    objPattern = /^[a-zA-Z]+$/;
                    break;
                case 2:
                    objPattern = /^[0-9]+$/;
                    break;
                case 3:
                    objPattern = /^[ㄱ-힣]+$/;
                    break;
                case 4:
                    objPattern = /^[a-zA-Z0-9]+$/;
                    break;
                case 5:
                    objPattern = /^[ㄱ-힣a-zA-Z0-9]+$/;
                    break;
                case 6:
                    objPattern = /^[ㄱ-힣0-9]+$/;
                    break;
                case 7:
                    objPattern = /^[ㄱ-힣a-zA-Z]+$/;
                    break;
                case 8:
                    objPattern = /[ㄱ-힣]/;
            }
            return view.validation.isValidFormat(value, objPattern);
        },
        isEmail: function (value) {
            var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
            return view.validation.isValidFormat(value, format);
        },
        isPhoneNumber: function (value) {
            // 000-000-000 포멧
            var format = /^(\d+)-(\d+)-(\d+)$/;
            return view.validation.isValidFormat(value, format);
        },
        isPhoneNumberWithSpliter: function (value, spliter) {
            // 000-000-000 포멧
            if (typeof spliter == 'undefined' || spliter == null) {
                spliter = '-';
            }

            var format = new RegExp(`^\\d{3}${spliter}\\d{3,4}${spliter}\\d{4}$`);
            return view.validation.isValidFormat(value, format);
        },
        isPasswordCharAndNum(value, min, max) {
            //var format = new RegExp(`^(?=.*[a-zA-Z])(?=.*[0-9]).{${min},${max}}$`);
            var format = new RegExp(`/^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/.{${min},${max}}$`);
            //var format = new RegExp(`/^[가-힣a-zA-Z].{${min},${max}}$`);
            return view.validation.isValidFormat(value, format);
        },
        checkInput: function (input, alert_msg) {
            let msg = $(input).attr('placeHolder');
            const isNotiText = $(input).siblings('.noti-text').length > 0;
            const noticonDom = $(input).siblings('.noticon');
            const notiDom = $(input).siblings('.noti-text');

            if (!view.validation.isValNull(alert_msg)) {
                msg = alert_msg;
            }

            try {
                input = input[0];
            } catch (err) {}
            if (view.validation.isNull(input)) {
                if (isNotiText) {
                    $(notiDom).text(alert_msg);
                    $(notiDom).show();
                    $(noticonDom).show();
                } else {
                    alert(msg);
                }
                // $(input).focus();
                return false;
            }

            if (isNotiText) {
                $(notiDom).hide();
                $(noticonDom).hide();
            }
            return true;
        },
        isValidFormat: function (input, format) {
            return format.test(input);
        },
        replaceOnlyKorean: function (v) {
            return v.replace(/[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g, '');
        },
        replaceOnlyKoreanEnglish: function (v) {
            //2020-02-22 이우호 한글영문만 입력 수정
            //return (v.replace(/[0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g,''));
            return v.replace(/[^(ㄱ-힣a-zA-Z)]/gi, '');
        },
        replaceOnlyEnglishNum: function (v) {
            //2020-02-22 이우호 한글영문만 입력 수정
            //return (v.replace(/[0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g,''));
            return v.replace(/[^(0-9a-zA-Z)]/gi, '');
        },
        replaceOnlyAll: function (v) {
            //2020-03-16 이우호 영어,숫자,특수문자 추가
            return v.replace(/[^(a-z0-9!~@#$%^&*()?+=\/)]/gi, '');
        },
        bindInputOnlyNumber: function (input) {
            $(input).keyup(function (event) {
                if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
                    var inputVal = $(this).val();
                    $(this).val(inputVal.replace(/[^a-zA-Z0-9ㄱ-힣]/gi, ''));
                }
            });
        },
        checkInputFileType: function (type, obj) {
            var file = obj.files[0];

            // 용량 체크
			if(file.size>=500*1024*1024) { //500M
				alert("20MB 이하 파일만 업로드 가능합니다.");
				return false;
			}
			
        	// 파일 체크
        	if (type=='image' && !file.type.match('image/*')) {
        		alert('이미지파일만 업로드 가능합니다.');
        		return false;
        	}
        	else if (type=='video' && !file.type.match('video/*')) {
                alert('영상파일만 업로드 가능합니다.');
                return false;
            }
        	
            return true;
        },
    }
});



