/*
    Fullscreen background
*/
$.backstretch("resources/img/backgrounds/images.jpg");


/*
    Form validation
*/
$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    $(this).removeClass('input-error');
    $("#login-message").text("Login to Genlab").css("color", "white");
});

//BORRAR CUANDO ESTE LISTA LA API DEL SERVIDOR////////////////////////
$('#login-buttons').on('click', 'button', function(e) {
    //De momento, pase lo que pase, validamos cualquier usuario (hasta que tengamos la api del servidor)

    $("#loginView").hide();
    $("#homeView").show();
    $("#homeNav").show();

    /*$(this).find('input[type="text"], input[type="password"], textarea').each(function() {
        if ($(this).val() == "") {
            e.preventDefault();
            $(this).addClass('input-error');

        } else {
            $(this).removeClass('input-error');

        }




    });*/

});
////////////////////////////////////////////////////////////////////

//DESCOMENTAR CUANDO ESTÉ LISTA LA API DEL SERVIDOR
/*$('#btn-login').on('click', event => {
    let user = $("#form-username").val();
    let password = $("#form-username").val();
    if (user === "" || password === "") {
        $("#login-message").text("User or password is empty").css("color", "red");
    } else {
        $.ajax({
            type: "POST",
            url: "", //COMPLETAR
            contentType: "application/json",
            data: JSON.stringify({ user: user, password: password }),
            success: function(data, textStatus, jqXHR) {
                //data es un booleano: true si el login tiene exito o false si no
                if (data) {
                    $("#loginView").hide();
                    $("#homeView").show();
                    $("#homeNav").show();
                } else {
                    $("#login-message").text(err).css("color", "red");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });
    }
});*/
/*$('#btn-register').on('click', event => {
    let user = $("#form-username").val();
    let password = $("#form-username").val();
    if (user === "" || password === "") {
        $("#login-message").text("User or password is empty").css("color", "red");
    } else {
        $.ajax({
            type: "POST",
            //url: "http://ingenias.fdi.ucm.es:60070/api/v1/login",
            url: "http://raspberrypablo.ddns.net:8080/api/v1/login",
            contentType: "application/json",
            data: JSON.stringify({ user: user, password: password }),
            success: function(data, textStatus, jqXHR) {
                //data es un booleano: true si el login tiene exito o false si no
                if (data) {
                    $("#loginView").hide();
                    $("#homeView").show();
                    $("#homeNav").show();
                } else {
                    $("#login-message").text(err).css("color", "red");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });
    }
});*/