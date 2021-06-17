//let socials = document.getElementsByClassName('.btn_social')
//    for (let social of socials) {
//        social.addEventListener('click', function () {
//            let socialType = this.getAttribute('data-social')
//            location.href = "/oauth2/authorization/" + socialType
//        })
//    }

$(document).ready(function() {
    $(".submit").click(function() {
        if($("#loginId").val() == "") {
                alert('아이디를 입력해주세요!')
        } else {
            alert('else')
        }
    })

})




