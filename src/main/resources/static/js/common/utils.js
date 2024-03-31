
view.apply({
    utils : {
         getQueryStringObject: function() {
            var a = window.location.search.substring(1).split('&');
            if (a == "") return {};
            var b = {};
            for (var i = 0; i < a.length; ++i) {
                var p = a[i].split('=', 2);
                if (p.length == 1)
                    b[p[0]] = "";
                else
                    b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
            }
            return b;
        },
        keyBinding: function (type, target, callback) {
            switch (type){
                case 'ENTER': { target.on('keyup', function (e) { if (e.keyCode === 13) { callback(); } } ) }break;
            }
        },

        showToast: function(title, text, type) {
            if(type == null) {
                type = 'info'
            }
            var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 15, "firstpos2": 15};
            new PNotify({
                title: title,
                text: text,
                type: type,
                shadow: true,
                addclass: 'stack-bottomright',
                stack: stack_bottomright
            });
        },
        // openPopupWindow: function (url, title) {
        //     let popup = window.open(url, title, 'width=800px,height=800px,scrollbars=yes,toolbar=no,status=no,menubar=no,resizable=yes,location=no');
        //     return popup;
        // },
        openPopupWindow: function (url, title, w, h) {
            if(typeof (w) == 'undefined') {
                w = 800;
            }
            if(typeof (h) == 'undefined') {
                h = 800;
            }
            let popup = window.open(url, title, 'width='+w+'px,height='+h+'px,scrollbars=yes,toolbar=no,status=no,menubar=no,resizable=no,location=no');
            return popup;
        },
        /**
         *  li a 태그 구조의 pagination을 click이벤트로 전환
         *  @USAGE
         *  view.ajax.pagination_html_set_ajax($('.pagination'), function(href, page){
         *      console.log('href=' + href + ', page=' + page);
         *  });
         * @param {CI Pagination DOM} _obj
         * @param {click action} click_func
         */
        pagination_html_set_ajax: function (_obj, click_func) {
            let page_list = $(_obj).find('a');
            page_list.each(function (idx, item) {
                let href = $(item).attr('href');
                let page = $(item).data('page');

                // clear achor
                $(item).attr('href', '#.');
                $(item)
                    .off()
                    .click(function () {
                        if (typeof click_func != 'undefined' && click_func != null && typeof click_func == 'function') {
                            click_func(href, page);
                        }
                    });
            });
        },
        bind_input_file_set_img: function (input, target) {
            $(input).on('change', function () {
                // 프로필사진 일때만
                //TODO: 마우스 오버시 확대표시 필요
                if ($(input)[0] == this) {
                    view.utils.input_read_img_url(this, $(target));
                }
            });
        },
        bind_input_file_set_bg_img: function (input, target) {
            $(input).on('change', function () {
                // 프로필사진 일때만
                //TODO: 마우스 오버시 확대표시 필요
                if ($(input)[0] == this) {
                    view.utils.input_read_img_url_for_bg(this, $(target));
                }
            });
        },
        input_read_img_url: function (input, target) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $(target).attr('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]); // convert to base64 string
            }
        },
        input_read_img_url_for_bg: function (input, target) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    // $(target).attr('src', e.target.result);
                    $(target).css({
                        'background-image': `url(${e.target.result})`,
                        'background-position': 'center',
                        'background-size': 'cover',
                    });
                };

                reader.readAsDataURL(input.files[0]); // convert to base64 string
            }
        },
        input_read_img_link_for_bg: function (link, target) {
            if (!view.validation.isValNull(link)) {
                $(target).css({ 'background-image': `url(${link})`, 'background-position': 'center', 'background-size': 'cover' });
            }
        },
        formToJson: function (_form) {
            var formSerializeArray = $(_form).serializeArray();
            var object = {};
            for (var i = 0; i < formSerializeArray.length; i++) {
                object[formSerializeArray[i]['name']] = formSerializeArray[i]['value'];
            }
            return object;
        },
        formToFormData: function (_form) {
            var formSerializeArray = $(_form).serializeArray();
            var frmData = new FormData();
            for (var i = 0; i < formSerializeArray.length; i++) {
                frmData.append(formSerializeArray[i]['name'], formSerializeArray[i]['value']);
            }
            return frmData;
        },
        is_input_file: function (_input) {
            try {
                return $(_input)[0].files.length > 0;
            } catch (e) {
                return false;
            }
        },
        set_file_upload_array: function (array, name, type) {
            if (view.utils.is_input_file($(`[name=${name}]`))) {
                let idx = $(`[name=${name}]`).data('idx');
                // if(view.validation.isValNull(idx) || idx == 0){
                //     idx = null;
                // }
                array.push({
                    name: name,
                    type: type,
                    file_seq: idx,
                });
            }
        },
        guuid: function () {
            var s4 = function () {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
            };
            return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
        },
        getBrowserType: function () {
            var _ua = navigator.userAgent;
            var rv = -1;
            //IE 11,10,9,8
            var trident = _ua.match(/Trident\/(\d.\d)/i);
            if (trident != null) {
                if (trident[1] == '7.0') return (rv = 'IE' + 11);
                if (trident[1] == '6.0') return (rv = 'IE' + 10);
                if (trident[1] == '5.0') return (rv = 'IE' + 9);
                if (trident[1] == '4.0') return (rv = 'IE' + 8);
            }
            //IE 7...
            if (navigator.appName == 'Microsoft Internet Explorer') return (rv = 'IE' + 7);
            //other
            var agt = _ua.toLowerCase();
            if (agt.indexOf('chrome') != -1) return 'Chrome';
            if (agt.indexOf('opera') != -1) return 'Opera';
            if (agt.indexOf('staroffice') != -1) return 'Star Office';
            if (agt.indexOf('webtv') != -1) return 'WebTV';
            if (agt.indexOf('beonex') != -1) return 'Beonex';
            if (agt.indexOf('chimera') != -1) return 'Chimera';
            if (agt.indexOf('netpositive') != -1) return 'NetPositive';
            if (agt.indexOf('phoenix') != -1) return 'Phoenix';
            if (agt.indexOf('firefox') != -1) return 'Firefox';
            if (agt.indexOf('safari') != -1) return 'Safari';
            if (agt.indexOf('skipstone') != -1) return 'SkipStone';
            if (agt.indexOf('netscape') != -1) return 'Netscape';
            if (agt.indexOf('mozilla/5.0') != -1) return 'Mozilla';
        },
        addCommas: function (num) {
            const parts = num.toString().split('.');
            parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            return parts.join('.');
            //num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        },
        zeroPad: function (nr, base) {
            var  len = (String(base).length - String(nr).length)+1;
            return len > 0? new Array(len).join('0')+nr : nr;
        },

        /*=============================================================================*
         * 콤마를 제거한 숫자형태 문자열로 반환(부호와 소수점도 없앰)
         *
         * param : val
         *
         * return : String
         *============================================================================*/
        getOnlyNumber: function (val) {
            var value = '';
            var abit;

            if (typeof val != 'number' && typeof val != 'string') {
                return '0';
            }
            if (val.length < 1) {
                return '0';
            }
            if (val == 'NaN') {
                return '0';
            }
            if (val == '-Infinity') {
                return '0';
            }

            for (i = 0; i < val.length; i++) {
                abit = parseInt(val.substring(i, i + 1));
                if ('0' < abit || '9' > abit) {
                    value = value + abit;
                }
            }
            return value;
        },
        get_date_range_with_diff: function (diff, type = 'D', ref = new Date()) {
            ref.setHours(0);
            ref.setMinutes(0);
            ref.setSeconds(0);

            if (typeof diff == 'undefined') {
                diff = 0;
            } else if (typeof diff == 'string') {
                diff = Number(diff);
            }

            let date2 = new Date(ref);

            if (type == 'M') {
                date2.setMonth(ref.getMonth() + diff);
            } else if (type == 'D') {
                date2.setDate(ref.getDate() + diff);
            } else if (type == 'Y') {
                date2.setYear(ref.getYear() + diff);
            }

            if (diff > 0) {
                return [ref, date2];
            } else {
                return [date2, ref];
            }
        },
        today: function (form, dday) {
            var date = new Date();
            if(dday){
            	date.setDate(date.getDate() + dday);
            }
            
            var year = date.getFullYear();
            var month = date.getMonth() + 1; // 0부터 시작하므로 1더함
            var day = date.getDate();
            

            //1자리 숫자인 경우 2자리로 만들어 줌
            if (('' + month).length == 1) {
                month = '0' + month;
            }
            if (('' + day).length == 1) {
                day = '0' + day;
            }

            //분리자 설정
            var separator = '';

            if (form.split('-')[1] != null) {
                separator = '-';
                form = form.replace(/-/gi, '');
            } else if (form.split('/')[1] != null) {
                separator = '/';
                form = form.replace(/\//gi, '');
            } else if (form.split('.')[1] != null) {
                separator = '.';
                form = form.replace(/\./gi, '');
            } else {
                separator = '';
            }

            //폼 별 형식 설정 후 리턴
            switch (form) {
                case 'YYYYMMDD':
                    return '' + year + separator + month + separator + day;
                    break;
                case 'YYYYMM':
                    return '' + year + separator + month;
                    break;
                case 'YYYY':
                    return '' + year;
                    break;
                case 'YYMMDD':
                    return ('' + year).substring(2) + separator + month + separator + day;
                    break;
                case 'YYMM':
                    return ('' + year).substring(2) + separator + month;
                    break;
                case 'YY':
                    return ('' + year).substring(2);
                    break;
                default:
                    return '형식 지정이 잘못되었습니다.';
                    break;
            }
        },

        /* 개인정보 마스킹 함수 https://goddaehee.tistory.com/236 */
        maskingFunc: {
            checkNull: function (str) {
                if (typeof str == 'undefined' || str == null || str == '') {
                    return true;
                } else {
                    return false;
                }
            },
            /* ※ 이메일 마스킹 ex1) 원본 데이터 : abcdefg12345@naver.com 변경 데이터 : ab**********@naver.com ex2) 원본 데이터 : abcdefg12345@naver.com 변경 데이터 : ab**********@nav****** */
            email: function (str) {
                let originStr = str;
                let emailStr = originStr.match(/([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\.[a-zA-Z0-9._-]+)/gi);
                let strLength;
                if (this.checkNull(originStr) == true || this.checkNull(emailStr) == true) {
                    return originStr;
                } else {
                    strLength = emailStr.toString().split('@')[0].length - 3; // ex1) abcdefg12345@naver.com => ab**********@naver.com // return originStr.toString().replace(new RegExp('.(?=.{0,' + strLength + '}@)', 'g'), '*'); // ex2) abcdefg12345@naver.com => ab**********@nav******
                    return originStr
                        .toString()
                        .replace(new RegExp('.(?=.{0,' + strLength + '}@)', 'g'), '*')
                        .replace(/.{6}$/, '******');
                }
            },
            /* ※ 휴대폰 번호 마스킹 ex1) 원본 데이터 : 01012345678, 변경 데이터 : 010****5678 ex2) 원본 데이터 : 010-1234-5678, 변경 데이터 : 010-****-5678 ex3) 원본 데이터 : 0111234567, 변경 데이터 : 011***4567 ex4) 원본 데이터 : 011-123-4567, 변경 데이터 : 011-***-4567 */
            phone: function (str) {
                let originStr = str;
                let phoneStr;
                let maskingStr;
                if (this.checkNull(originStr) == true) {
                    return originStr;
                }
                if (originStr.toString().split('-').length != 3) {
                    // 1) -가 없는 경우
                    phoneStr = originStr.length < 11 ? originStr.match(/\d{10}/gi) : originStr.match(/\d{11}/gi);
                    if (this.checkNull(phoneStr) == true) {
                        return originStr;
                    }
                    if (originStr.length < 11) {
                        // 1.1) 0110000000
                        maskingStr = originStr
                            .toString()
                            .replace(phoneStr, phoneStr.toString().replace(/(\d{3})(\d{3})(\d{4})/gi, '$1***$3'));
                    } else {
                        // 1.2) 01000000000
                        maskingStr = originStr
                            .toString()
                            .replace(phoneStr, phoneStr.toString().replace(/(\d{3})(\d{4})(\d{4})/gi, '$1****$3'));
                    }
                } else {
                    // 2) -가 있는 경우
                    phoneStr = originStr.match(/\d{2,3}-\d{3,4}-\d{4}/gi);
                    if (this.checkNull(phoneStr) == true) {
                        return originStr;
                    }
                    if (/-[0-9]{3}-/.test(phoneStr)) {
                        // 2.1) 00-000-0000
                        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/-[0-9]{3}-/g, '-***-'));
                    } else if (/-[0-9]{4}-/.test(phoneStr)) {
                        // 2.2) 00-0000-0000
                        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/-[0-9]{4}-/g, '-****-'));
                    }
                }
                return maskingStr;
            },
            /* ※ 주민등록 번호 마스킹 (Resident Registration Number, RRN Masking) ex1) 원본 데이터 : 990101-1234567, 변경 데이터 : 990101-1****** ex2) 원본 데이터 : 9901011234567, 변경 데이터 : 9901011****** */
            rrn: function (str) {
                let originStr = str;
                let rrnStr;
                let maskingStr;
                let strLength;
                if (this.checkNull(originStr) == true) {
                    return originStr;
                }
                rrnStr = originStr.match(/(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4]{1}[0-9]{6}\b/gi);
                if (this.checkNull(rrnStr) == false) {
                    strLength = rrnStr.toString().split('-').length;
                    maskingStr = originStr
                        .toString()
                        .replace(rrnStr, rrnStr.toString().replace(/(-?)([1-4]{1})([0-9]{6})\b/gi, '$1$2******'));
                } else {
                    rrnStr = originStr.match(/\d{13}/gi);
                    if (this.checkNull(rrnStr) == false) {
                        strLength = rrnStr.toString().split('-').length;
                        maskingStr = originStr.toString().replace(rrnStr, rrnStr.toString().replace(/([0-9]{6})$/gi, '******'));
                    } else {
                        return originStr;
                    }
                }
                return maskingStr;
            },
            /* ※ 이름 마스킹 ex1) 원본 데이터 : 갓댐희, 변경 데이터 : 갓댐* ex2) 원본 데이터 : 하늘에수, 변경 데이터 : 하늘** ex3) 원본 데이터 : 갓댐, 변경 데이터 : 갓* */
            name: function (str) {
                let originStr = str;
                let maskingStr;
                let strLength;
                if (this.checkNull(originStr) == true) {
                    return originStr;
                }
                strLength = originStr.length;
                if (strLength < 3) {
                    maskingStr = originStr.replace(/(?<=.{1})./gi, '*');
                } else {
                    maskingStr = originStr.replace(/(?<=.{2})./gi, '*');
                }
                return maskingStr;
            },
            card: function (ContentsData) {
                if (this.checkNull(ContentsData) == true) {
                    return ContentsData;
                }
                var cardArray = ContentsData.match(/(\d{4})-(\d{4})-(\d{4})-(\d{4})/gi);
                if (cardArray == null || cardArray == '') {
                    ContentsData = ContentsData;
                } else {
                    for (var i = 0; i < cardArray.length; i++) {
                        ContentsData = ContentsData.toString().replace(
                            cardArray[i],
                            cardArray[i].toString().replace(/(\d{4})-(\d{4})-(\d{4})-(\d{4})/gi, '$1-****-****-$4')
                        );
                    }
                }
                cardArray = ContentsData.match(/(5[1-5]\d{14})|(4\d{12})(\d{3}?)|3[47]\d{13}|(6011\d{12})/gi);
                if (cardArray == null || cardArray == '') {
                    ContentsData = ContentsData;
                } else {
                    for (var i = 0; i < cardArray.length; i++) {
                        ContentsData = ContentsData.toString().replace(
                            cardArray[i],
                            cardArray[i].toString().replace(/(\d{4})(\d{4})(\d{4})(\d{4})/gi, '$1********$4')
                        );
                    }
                }
                return ContentsData;
            },
        },
        href: function (move_page) {
            location.href = move_page;
        },
        excel_down: function(fileName, sheetName, sheetHtml) {
            var html = ''; html += '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
            html += ' <head>';
            html += ' <meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
            html += ' <xml>';
            html += ' <x:ExcelWorkbook>';
            html += ' <x:ExcelWorksheets>';
            html += ' <x:ExcelWorksheet>';
            html += ' <x:Name>' + sheetName + '</x:Name>'; // 시트이름
            html += ' <x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions>';
            html += ' </x:ExcelWorksheet>';
            html += ' </x:ExcelWorksheets>';
            html += ' </x:ExcelWorkbook>';
            html += ' </xml>';
            html += ' </head>';
            html += ' <body>';
            // ----------------- 시트 내용 부분 -----------------
            html += sheetHtml;
            // ----------------- //시트 내용 부분 -----------------
            html += ' </body>';
            html += '</html>';
            // 데이터 타입
            var data_type = 'data:application/vnd.ms-excel';
            var ua = window.navigator.userAgent;
            var blob = new Blob([html], {type: "application/csv;charset=utf-8;"});
            if ((ua.indexOf("MSIE ") > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) && window.navigator.msSaveBlob) {
                // ie이고 msSaveBlob 기능을 지원하는 경우
                navigator.msSaveBlob(blob, fileName);
            } else { // ie가 아닌 경우 (바로 다운이 되지 않기 때문에 클릭 버튼을 만들어 클릭을 임의로 수행하도록 처리)
                var anchor = window.document.createElement('a');
                anchor.href = window.URL.createObjectURL(blob);
                anchor.download = fileName;
                document.body.appendChild(anchor);
                anchor.click(); // 클릭(다운) 후 요소 제거
                document.body.removeChild(anchor);
            }
        },
        render: {
            render_select_options: function (options, val, def) {
                res = [];
                if(options != null) {
                    try{
                        options.forEach(function (item) {
                            sel = false;

                            if(typeof (val) == 'undefined' || val == null) {
                                if(item.val == def) {
                                    sel = true;
                                }
                            }else if(item.val == val){
                                sel = true;
                            }

                            res.push({
                                val: item.val,
                                txt: item.txt,
                                sel: sel
                            });

                        });
                    }catch (e){

                    }
                }
                return res;
            },
            render_mustache: function (tpl, target, data) {
                try{
                    var template = $(tpl).html();
                    Mustache.parse(template);

                    var rendered = Mustache.render(template, data);
                    $(target).html(rendered);
                }catch (e){
                    console.log(e);
                }
            },
            pagination_defaultOpts : {
                totalPages: 1,	// 총 페이지 번호 수
                visiblePages: 10,	// 하단에서 한번에 보여지는 페이지 번호 수
                startPage : 1, // 시작시 표시되는 현재 페이지
                initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
                first : '<img src="/resources/img/adm/btn-page-01-back-2.png" srcset="/resources/img/adm/btn-page-01-back-2@2x.png 2x, /resources/img/admbtn-page-01-back-2@3x.png 3x">',	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
                prev : '<img src="/resources/img/adm/btn-page-01-back-1.png" srcset="/resources/img/adm/btn-page-01-back-1@2x.png 2x, /resources/img/adm/btn-page-01-back-1@3x.png 3x">',	// 이전 페이지 버튼에 쓰여있는 텍스트
                next : '<img src="/resources/img/adm/btn-page-02-forward-1.png" srcset="/resources/img/adm/btn-page-02-forward-1@2x.png 2x, /resources/img/adm/btn-page-02-forward-1@3x.png 3x">',	// 다음 페이지 버튼에 쓰여있는 텍스트
                last : '<img src="/resources/img/adm/btn-page-02-forward-2.png" srcset="/resources/img/adm/btn-page-02-forward-2@2x.png 2x, /resources/img/adm/btn-page-02-forward-2@3x.png 3x">',	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
                 nextClass : "next",	// 이전 페이지 CSS class
                 prevClass : "prev", 		// 다음 페이지 CSS class
                 lastClass : "lastest",	// 마지막 페이지 CSS calss
                 firstClass : "lateless",	// 첫 페이지 CSS class
                 pageClass : "",	// 페이지 버튼의 CSS class
                 activeClass : "current",	// 클릭된 페이지 버튼의 CSS class
                // disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
                // anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
                anchorClass : "",	//버튼 안의 앵커에 대한 CSS class
                paginationClass: "",
            },
            pagination_layerPopOpts : {
                totalPages: 1,	// 총 페이지 번호 수
                visiblePages: 10,	// 하단에서 한번에 보여지는 페이지 번호 수
                startPage : 1, // 시작시 표시되는 현재 페이지
                initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
                first : false,
                prev : '<img src="/resources/img/adm/btn-page-01-back-1.png" srcset="/resources/img/adm/btn-page-01-back-1@2x.png 2x, /resources/img/adm/btn-page-01-back-1@3x.png 3x">',	// 이전 페이지 버튼에 쓰여있는 텍스트
                next : '<img src="/resources/img/adm/btn-page-02-forward-1.png" srcset="/resources/img/adm/btn-page-02-forward-1@2x.png 2x, /resources/img/adm/btn-page-02-forward-1@3x.png 3x">',	// 다음 페이지 버튼에 쓰여있는 텍스트
                last : false,
                nextClass : "next",	// 이전 페이지 CSS class
                prevClass : "prev", 		// 다음 페이지 CSS class
                pageClass : "",	// 페이지 버튼의 CSS class
                activeClass : "current",	// 클릭된 페이지 버튼의 CSS class
                // disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
                // anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
                anchorClass : "",	//버튼 안의 앵커에 대한 CSS class
                paginationClass: "",
            },
            pagination_init: function(target, defaultOpts) {
                defaultOpts = $.extend({}, this.pagination_defaultOpts, defaultOpts);
                $(target).twbsPagination(defaultOpts);
            },
            get_current_page: function(target) {
              return $(target).twbsPagination('getCurrentPage');
            },
             render_seq_start : function(total,size,page){
			  return total - (page*size);
			},
            render_pagination: function (target, total, callback) {
            	$(target).empty();
                var totalPages = total>0?total:1;
                var currentPage = $(target).twbsPagination('getCurrentPage');

                // $(target).twbsPagination("changeTotalPages",totalPages, currentPage);
                $(target).twbsPagination('destroy');
                $(target).twbsPagination($.extend({}, this.pagination_defaultOpts, {
                    startPage: currentPage,
                    totalPages: totalPages,
                    onPageClick: callback
                }));
            },
            render_pagination: function (target, total, callback, currentPage) {
            	$(target).empty();
            	var totalPages = total>0?total:1;
            	
            	if(!currentPage || isNaN(currentPage)){
            		currentPage = $(target).twbsPagination('getCurrentPage');
            	}
            	
            	// $(target).twbsPagination("changeTotalPages",totalPages, currentPage);
            	$(target).twbsPagination('destroy');
            	$(target).twbsPagination($.extend({}, this.pagination_defaultOpts, {
            		startPage: currentPage,
            		totalPages: totalPages,
            		onPageClick: callback
            	}));
            },
            render_pagination_inlayer: function (target, total, callback) {
            	$(target).empty();
                var totalPages = total>0?total:1;
                var currentPage = $(target).twbsPagination('getCurrentPage');

                // $(target).twbsPagination("changeTotalPages",totalPages, currentPage);
                $(target).twbsPagination('destroy');
                $(target).twbsPagination($.extend({}, this.pagination_layerPopOpts, {
                    startPage  : currentPage,
                    totalPages : totalPages,
                    onPageClick: callback
                }));
            },
            render_list_html: function (target, objArr, data) {
            	$(target).find('.list-body-tr').remove();

            	for (var i = 0; i < data.length; i++) {
//					var trObj = $('<tr class="list-body-tr"></tr>');
					var trObj = $('.list-body-tr-templete');
					trObj.removeClass('list-body-tr-templete');
					trObj.addClass('list-body-tr');

					objArr.forEach(function (item) {
						if(item == 'seq'){
							if(data[i]['seq']){
								trObj.append('<td>'+(data[i]['seq']?data[i]['seq']:'')+'</td>');
							}else{
								trObj.append('<td>'+(i+1)+'</td>');
							}
							return;
						}

						trObj.append('<td>'+(data[i][item]?data[i][item]:'')+'</td>');

                    });

					trObj.show();
					$(target).append(trObj);
				}
            },
        },
        date: {
            locale_ko: function () {
                moment.locale('ko');
                return this;
            },
            format: function (dateStr, format) {

                if (typeof (format) == 'undefined' || format == null || format === '') {
                    format = 'YYYY-MM-DD HH:mm:ss';
                }
                if (typeof (dateStr) == 'undefined' || dateStr == null || dateStr === '') {
                    return moment().format(format);
                }

                return moment(dateStr).format(format);
            },
            _format: function (dateStr, format) {
            	if(!dateStr)return '';
                if (typeof (format) == 'undefined' || format == null || format === '') {
                    format = 'YYYY-MM-DD HH:mm:ss';
                }
                if (typeof (dateStr) == 'undefined' || dateStr == null || dateStr === '') {
                    return moment().format(format);
                }

                return moment(dateStr).format(format);
            }
        },
        convert_clock_diff_val_to_disp: function (val) {
            val = Number(val);
            var res = 0;
            if(val > 2048) { // 음수
                res = (val - 2048) * (-1);
            }else{ // 양수
                res = val;
            }
            return res;
        }

    }
});
