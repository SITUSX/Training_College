function logout(type) {
        let url = '/'+ type +'/logout';

        $.ajax({
            type: 'POST',
            url: url,
            data: {},
            success: function (response) {
                if (response.result){
                    window.location.href="../index.html";
                } else {
                    alert("错误");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status + ' ' + XMLHttpRequest.readyState + ' ' + textStatus);
            },
        })
};