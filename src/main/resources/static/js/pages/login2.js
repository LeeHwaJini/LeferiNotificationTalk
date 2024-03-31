
view.apply({
	validation: {
		check_login_form: function () {
			var mbrId = $("[name=mbrId]");
			var mbrPwd = $("[name=mbrPwd]");

			if (mbrId.val() == '') {
				alert("아이디를 입력해주세요.");
				mbrId.focus();
				return false;
			}

			if (mbrPwd.val() == '') {
				alert("비밀번호를 입력해주세요.");
				mbrPwd.focus();
				return false;
			}
			return true;
		}
	},
	ajax: {

		/**
		 *
		 * @param params : RfConsts.LoginMemberRequestDto
		 * @returns {*}
		 */
		req_auth_sign_in: function (params) {
			this.base_url = view.core.get_auth_addr()
			return this.commonPost("/auth/manager/login", params);
		},
	}
});


$(document).ready(function () {
	$('.apiTestBtn').on('click', function(){
		
		let data = {};
		if(location.href.split('#').length > 1){
			let param = location.href.split('#')[1].split('&');
			
			param.forEach((value, index) => {
				let keyValue = value.split('=');
				data[keyValue[0]] = decodeURIComponent(keyValue[1]);
			});
		}else{
			data = 'error';
		}
		window.opener.postMessage(data, 'http://localhost:8080/');
		self.close();
		
	});
	
	chk_save = view.utils.getCookie("CHK-SAVE-ID");
	if(chk_save) {
		$('#id_keep_check').prop('checked', true);
		const id = view.utils.getCookie("SAVE-ID");
		$("[name=mbrId]").val(id);
		$("[name=mbrPwd]").focus();
	}else{
		$("[name=mbrId]").focus();
	}

	fn_login = function () {
		if (view.validation.check_login_form()) {
			var data = {
				mngrId: $("[name=mbrId]").val(),
				mngrPw: $("[name=mbrPwd]").val()
			};
			
			view.ajax.req_auth_sign_in(data)
				.then(result => {
					/**
					 * '{"resCd":200,"msg":"로그인","desc":"","tracTime":"2021-12-12T19:37:02.885","data":{"mbrId":"bluewin11","mbrNm":"hi","jwtToken":""}}'
					 */

					const chk_save = $('#id_keep_check').prop('checked');

					//작업이 성공적으로 발생했을 경우
					view.utils.setCookie("LECOMMEND-AUTH-TOKEN", result.data.jwtToken, 1);
					//view.utils.setCookie("username", result.data.mbrId, 1);
					//view.utils.setCookie("authgrpnm", result.data.authGrpNm?encodeURIComponent(result.data.authGrpNm):'', 1);
					if (chk_save) {
						view.utils.setCookie("CHK-SAVE-ID", 1, 14);
						view.utils.setCookie("SAVE-ID", $("[name=mbrId]").val(), 14);
					} else {
						view.utils.setCookie("CHK-SAVE-ID", null, 0);
						view.utils.setCookie("SAVE-ID", null, 0);
					}

					location.href = "/dashboard/main";
				})
				.catch(err => {
					//에러가 났을 경우 실행시킬 코드
					alert("관리자 계정을 확인해주세요.");
				});
		}
	};


	$(".loginBtn").click(function () {
		fn_login();
	});


	view.utils.keyBinding('ENTER', $(document.body), function () {
		fn_login();
	});

});
