/*$("#ctoolView").on("click", ".btn-calculate", (evt) => {
    let h = $("#Alleles-A-Testcross").val();
    $("#total-Testcross").text(34);
    console.log("Pulsado");
    alert("Hola! Has metido " + h);
});*/

/////////////////////////////////////////////////////////////////////////////
//ONE LOCUS
/////////////////////////////////////////////////////////////////////////////


$("#ctoolView").on("click", "#testcross-onelocus", (evt) => {
    let alleles_A = Number($("#Alleles-A-Testcross").val());
    let alleles_a = Number($("#Alleles-a-Testcross").val());
    if (isNaN(alleles_A) || isNaN(alleles_a)) {
        alert("Expected values must be numbers");
    } else {
        $.ajax({
            type: "POST",
            //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctool?CTid=10",
            url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=10",
            beforeSend: function(request) {
                request.setRequestHeader("Access-Control-Allow-Origin", "*");
            },
            contentType: "application/json",
            data: JSON.stringify({
                "A": alleles_A,
                "a": alleles_a
            }),
            success: function(data, textStatus, jqXHR) {
                console.log(data);
                if (!data.cleanInputs) {
                    $("#total-Testcross").text(alleles_A + alleles_a);

                    $("#Expected-A-Testcross").text(data.expectedValues.expA.toFixed(1));
                    $("#Expected-a-Testcross").text(data.expectedValues.expa.toFixed(1));

                    $("#value-Testcross").text(data.resultValues.chi.toFixed(2));
                    $("#agree-Testcross").text(data.agree.chi);
                    if (data.result) {
                        $("#result-message-Testcross").text(data.result);
                    }
                    if (data.feedbackMessage) {
                        alert(data.feedbackMessage);
                    }
                } else {
                    alert(data.feedbackMessage);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });
    }
});

$("#ctoolView").on("click", "#F2Dominance-onelocus", (evt) => {
    let alleles_A = Number($("#Alleles-A-F2Dominance").val());
    let alleles_a = Number($("#Alleles-a-F2Dominance").val());

    alert(alleles_A + " y el otro " + alleles_a);
    if (isNaN(alleles_A) || isNaN(alleles_a)) {
        alert("Expected values must be numbers");
    } else {
        $.ajax({
            type: "POST",
            //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctool?CTid=11",
            url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=11",
            beforeSend: function(request) {
                request.setRequestHeader("Access-Control-Allow-Origin", "*");
            },
            contentType: "application/json",
            data: JSON.stringify({ 
                "A": alleles_A, 
                "a": alleles_a 
            }),
            success: function(data, textStatus, jqXHR) {
                console.log(data);
                if (!data.cleanInputs) {
                    $("#total-F2Dominance").text(alleles_A + alleles_a);

                    $("#Expected-A-F2Dominance").text(data.expectedValues.expA.toFixed(1));
                    $("#Expected-a-F2Dominance").text(data.expectedValues.expa.toFixed(1));

                    $("#value-F2Dominance").text(data.resultValues.chi.toFixed(2));
                    $("#agree-F2Dominance").text(data.agree.chi);
                    if (data.result) {
                        $("#result-message-F2Dominance").text(data.result);
                    }
                    if (data.feedbackMessage) {
                        alert(data.feedbackMessage);
                    }
                } else {
                    alert(data.feedbackMessage);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Se ha producido un error: " + errorThrown);
            }
        });
    }
});

$("#ctoolView").on("click", "#F2Codominance-onelocus", (evt) => {
    let alleles_A1A1 = Number($("#Alleles-A1A1-F2Codominance").val());
    let alleles_A1A2 = Number($("#Alleles-A1A2-F2Codominance").val());
    let alleles_A2A2 = Number($("#Alleles-A2A2-F2Codominance").val());

    alert(alleles_A1A1 + " " + alleles_A1A2 + " " + alleles_A2A2);
    $.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctool?CTid=12",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=12",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: JSON.stringify({ 
            "A1A1": alleles_A1A1, 
            "A1A2": alleles_A1A2, 
            "A2A2": alleles_A2A2 
        }),
        success: function(data, textStatus, jqXHR) {
            console.log(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });
});

$("#ctoolView").on("click", "#Codominance3-onelocus", (evt) => {
    let alleles_A1A1 = Number($("#Alleles-A1A1-Codominance3").val());
    let alleles_A1A3 = Number($("#Alleles-A1A3-Codominance3").val());
    let alleles_A1A2 = Number($("#Alleles-A1A2-Codominance3").val());
    let alleles_A2A3 = Number($("#Alleles-A2A3-Codominance3").val());

    alert(alleles_A1A1 + " " + alleles_A1A3 + " " + alleles_A1A2 + " " + alleles_A2A3);
    $.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctool?CTid=13",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=13",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: JSON.stringify({ 
            "A1A1": alleles_A1A1, 
            "A1A3": alleles_A1A3, 
            "A1A2": alleles_A1A2, 
            "A2A3": alleles_A2A3 
        }),
        success: function(data, textStatus, jqXHR) {
            console.log(data);
            if(!data.cleanInputs) {
                $("#total-Codominance3").text(alleles_A1A1 + alleles_A1A3 + alleles_A1A2 + alleles_A2A3);

                $("#Expected-A1A1-Codominance3").text(data.expectedValues.expectedA1A1.toFixed(1));
                $("#Expected-A1A3-Codominance3").text(data.expectedValues.expectedA1A3.toFixed(1));
                $("#Expected-A1A2-Codominance3").text(data.expectedValues.expectedA1A2.toFixed(1));
                $("#Expected-A2A3-Codominance3").text(data.expectedValues.expectedA2A3.toFixed(1));

                $("#value-Codominance3").text(data.resultValues.chi.toFixed(2));
                $("#agree-Codominance3").text(data.agree.chi);
                if (data.result) {
                    $("#result-message-Codominance3").text(data.result);
                }
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });
});

$("#ctoolView").on("click", "#Codominance4-onelocus", (evt) => {
    let alleles_A1A3 = Number($("#Alleles-A1A3-Codominance4").val());
    let alleles_A1A4 = Number($("#Alleles-A1A4-Codominance4").val());
    let alleles_A2A3 = Number($("#Alleles-A2A3-Codominance4").val());
    let alleles_A2A4 = Number($("#Alleles-A2A4-Codominance4").val());


    alert(alleles_A1A3 + " " + alleles_A1A4 + " " + alleles_A2A3 + " " + alleles_A2A4);
    $.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctool?CTid=14",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=14",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: JSON.stringify({ 
            "A1A3": alleles_A1A3, 
            "A1A4": alleles_A1A4, 
            "A2A3": alleles_A2A3, 
            "A2A4": alleles_A2A4 
        }),
        success: function(data, textStatus, jqXHR) {
            console.log(data);

            if(!data.cleanInputs) {
                $("#total-Codominance4").text(alleles_A1A3 + alleles_A1A4 + alleles_A2A3 + alleles_A2A4);
            
                $("#Expected-A1A3-Codominance4").text(data.expectedValues.expectedA1A3.toFixed(1));
                $("#Expected-A1A4-Codominance4").text(data.expectedValues.expectedA1A4.toFixed(1));
                $("#Expected-A2A3-Codominance4").text(data.expectedValues.expectedA2A3.toFixed(1));
                $("#Expected-A2A4-Codominance4").text(data.expectedValues.expectedA2A4.toFixed(1));
                
                $("#value-Codominance4").text(data.resultValues.chi.toFixed(2));
                $("#agree-Codominance4").text(data.agree.chi);
                if (data.result) {
                    $("#result-message-Codominance4").text(data.result);
                }
            }
            else {
                alert(data.feedbackMessage);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });
});


/////////////////////////////////////////////////////////////////////////////
//TWO LOCI
/////////////////////////////////////////////////////////////////////////////


$("#ctoolView").on("click", "#F2Codom2-twoloci", (evt) => {

    let alleles_A1A1B = $("#Alleles-A1A1B-F2Codom2").val();
    let alleles_A1A2B = $("#Alleles-A1A2B-F2Codom2").val();
    let alleles_A2A2B = $("#Alleles-A2A2B-F2Codom2").val();
    let alleles_A1A1b = $("#Alleles-A1A1b-F2Codom2").val();
    let alleles_A1A2b = $("#Alleles-A1A2b-F2Codom2").val();
    let alleles_A2A2b = $("#Alleles-A2A2b-F2Codom2").val();


    alert(alleles_A1A1B + " " + alleles_A1A2B + " " + alleles_A2A2B + " " + alleles_A1A1b + " " + alleles_A1A2b + " " + alleles_A2A2b);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codom2",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_A1A1B: alleles_A1A1B, 
            alleles_A1A2B: alleles_A1A2B, 
            alleles_A2A2B: alleles_A2A2B, 
            alleles_A1A1b: alleles_A1A1b,
            alleles_A1A2b: alleles_A1A2b,
            alleles_A2A2b: alleles_A2A2b
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2Codom4-twoloci", (evt) => {

    let alleles_A1A3B = $("#Alleles-A1A3B-F2Codom4").val();
    let alleles_A1A3b = $("#Alleles-A1A3b-F2Codom4").val();
    let alleles_A1A4B = $("#Alleles-A1A4B-F2Codom4").val();
    let alleles_A1A4b = $("#Alleles-A1A4b-F2Codom4").val();
    let alleles_A2A3B = $("#Alleles-A2A3B-F2Codom4").val();
    let alleles_A2A3b = $("#Alleles-A2A3b-F2Codom4").val();
    let alleles_A2A4B = $("#Alleles-A2A4B-F2Codom4").val();
    let alleles_A2A4b = $("#Alleles-A2A4b-F2Codom4").val();


    alert(alleles_A1A3B + " " + alleles_A1A3b + " " + alleles_A1A4B + " " + alleles_A1A4b + " " + alleles_A2A3B + " " + alleles_A2A3b + " " + alleles_A2A4B + " " + alleles_A2A4b);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codom4",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_A1A3B: alleles_A1A3B, 
            alleles_A1A3b: alleles_A1A3b, 
            alleles_A1A4B: alleles_A1A4B, 
            alleles_A1A4b: alleles_A1A4b,
            alleles_A2A3B: alleles_A2A3B,
            alleles_A2A3b: alleles_A2A3b,
            alleles_A2A4B: alleles_A2A4B,
            alleles_A2A4b: alleles_A2A4b
        },
        success: function(data, textStatus, jqXHR) {
    
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2Codominance-twoloci", (evt) => {

    let alleles_A1A1B1B1 = $("#Alleles-A1A1B1B1-F2Codominance").val();
    let alleles_A1A1B1B2 = $("#Alleles-A1A1B1B2-F2Codominance").val();
    let alleles_A1A1B2B2 = $("#Alleles-A1A1B2B2-F2Codominance").val();
    let alleles_A1A2B1B1 = $("#Alleles-A1A2B1B1-F2Codominance").val();
    let alleles_A1A2B1B2 = $("#Alleles-A1A2B1B2-F2Codominance").val();
    let alleles_A1A2B2B2 = $("#Alleles-A1A2B2B2-F2Codominance").val();
    let alleles_A2A2B1B1 = $("#Alleles-A2A2B1B1-F2Codominance").val();
    let alleles_A2A2B1B2 = $("#Alleles-A2A2B1B2-F2Codominance").val();
    let alleles_A2A2B2B2 = $("#Alleles-A2A2B2B2-F2Codominance").val();


    alert(alleles_A1A1B1B1 + " " + alleles_A1A1B1B2 + " " + alleles_A1A1B2B2 + " " + alleles_A1A2B1B1 + " " + alleles_A1A2B1B2 + " " + alleles_A1A2B2B2 + " " + alleles_A2A2B1B1 + " " + alleles_A2A2B1B2 + " " + alleles_A2A2B2B2);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_A1A1B1B1: alleles_A1A1B1B1, 
            alleles_A1A1B1B2: alleles_A1A1B1B2, 
            alleles_A1A1B2B2: alleles_A1A1B2B2, 
            alleles_A1A2B1B1: alleles_A1A2B1B1,
            alleles_A1A2B1B2: alleles_A1A2B1B2,
            alleles_A1A2B2B2: alleles_A1A2B2B2,
            alleles_A2A2B1B1: alleles_A2A2B1B1,
            alleles_A2A2B1B2: alleles_A2A2B1B2,
            alleles_A2A2B2B2: alleles_A2A2B2B2
        },
        success: function(data, textStatus, jqXHR) {
        
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2Dominance-twoloci", (evt) => {


    let alleles_AB = $("#Alleles-AB-F2Dominance").val();
    let alleles_aB = $("#Alleles-aB-F2Dominance").val();
    let alleles_Ab = $("#Alleles-Ab-F2Dominance").val();
    let alleles_ab = $("#Alleles-ab-F2Dominance").val();


    alert(alleles_AB + " " + alleles_aB + " " + alleles_Ab + " " + alleles_ab);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Dominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_AB: alleles_AB, 
            alleles_aB: alleles_aB, 
            alleles_Ab: alleles_Ab, 
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {
        
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2TestcrossDominance-twoloci", (evt) => {

    let alleles_AB = $("#Alleles-AB-F2TestcrossDominance").val();
    let alleles_aB = $("#Alleles-aB-F2TestcrossDominance").val();
    let alleles_Ab = $("#Alleles-Ab-F2TestcrossDominance").val();
    let alleles_ab = $("#Alleles-ab-F2TestcrossDominance").val();


    alert(alleles_AB + " " + alleles_aB + " " + alleles_Ab + " " + alleles_ab);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_AB: alleles_AB, 
            alleles_aB: alleles_aB, 
            alleles_Ab: alleles_Ab, 
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {
        
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2TestcrossDom2_1-twoloci", (evt) => {

    let alleles_A1A1B = $("#Alleles-A1A1B-F2TestcrossDom2_1").val();
    let alleles_A1A2B = $("#Alleles-A1A2B-F2TestcrossDom2_1").val();
    let alleles_A2A2B = $("#Alleles-A2A2B-F2TestcrossDom2_1").val();
    let alleles_A1A1b = $("#Alleles-A1A1b-F2TestcrossDom2_1").val();
    let alleles_A1A2b = $("#Alleles-A1A2b-F2TestcrossDom2_1").val();
    let alleles_A2A2b = $("#Alleles-A2A2b-F2TestcrossDom2_1").val();

    alert(alleles_A1A1B + " " + alleles_A1A2B + " " + alleles_A2A2B + " " + alleles_A1A1b + " " + alleles_A1A2b + " " + alleles_A2A2b);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_A1A1B: alleles_A1A1B, 
            alleles_A1A2B: alleles_A1A2B, 
            alleles_A2A2B: alleles_A2A2B, 
            alleles_A1A1b: alleles_A1A1b,
            alleles_A1A2b: alleles_A1A2b,
            alleles_A2A2b: alleles_A2A2b
        },
        success: function(data, textStatus, jqXHR) {
        
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2TestcrossDom2_2-twoloci", (evt) => {

    let alleles_A1A3B = $("#Alleles-A1A3B-F2TestcrossDom2_2").val();
    let alleles_A1A3b = $("#Alleles-A1A3b-F2TestcrossDom2_2").val();
    let alleles_A1A4B = $("#Alleles-A1A4B-F2TestcrossDom2_2").val();
    let alleles_A1A4b = $("#Alleles-A1A4b-F2TestcrossDom2_2").val();
    let alleles_A2A3B = $("#Alleles-A2A3B-F2TestcrossDom2_2").val();
    let alleles_A2A3b = $("#Alleles-A2A3b-F2TestcrossDom2_2").val();
    let alleles_A2A4B = $("#Alleles-A2A4B-F2TestcrossDom2_2").val();
    let alleles_A2A4b = $("#Alleles-A2A4b-F2TestcrossDom2_2").val();

    alert(alleles_A1A3B + " " + alleles_A1A3b + " " + alleles_A1A4B + " " + alleles_A1A4b + " " + alleles_A2A3B + " " + alleles_A2A3b + " " + alleles_A2A4B + " " + alleles_A2A4b);
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: { 
            alleles_A1A3B: alleles_A1A3B, 
            alleles_A1A3b: alleles_A1A3b, 
            alleles_A1A4B: alleles_A1A4B, 
            alleles_A1A4b: alleles_A1A4b,
            alleles_A2A3B: alleles_A2A3B,
            alleles_A2A3b: alleles_A2A3b,
            alleles_A2A4B: alleles_A2A4B,
            alleles_A2A4b: alleles_A2A4b
        },
        success: function(data, textStatus, jqXHR) {
        
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Testcross-twoloci", (evt) => {

    let alleles_AB = Number($("#Alleles-AB-Testcross").val());
    let alleles_aB = Number($("#Alleles-aB-Testcross").val());
    let alleles_Ab = Number($("#Alleles-Ab-Testcross").val());
    let alleles_ab = Number($("#Alleles-ab-Testcross").val());

    alert(alleles_AB + " " + alleles_aB + " " + alleles_Ab + " " + alleles_ab);
    $.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/calctools",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/calctool?CTid=00",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: JSON.stringify({
            "AB": alleles_AB,
            "aB": alleles_aB,
            "Ab": alleles_Ab,
            "ab": alleles_ab
        }),
        success: function(data, textStatus, jqXHR) {
            console.log(data);
            if (!data.cleanInputs) {
                $("#total-Testcross").text(data.observed.total);
                if (data.expectedValues.expectedIndAB) {
                    $("#ExpectedInd-AB-Testcross").text(data.expectedValues.expectedIndAB.toFixed(1));
                    $("#ExpectedInd-Ab-Testcross").text(data.expectedValues.expectedIndAb.toFixed(1));
                    $("#ExpectedInd-aB-Testcross").text(data.expectedValues.expectedIndaB.toFixed(1));
                    $("#ExpectedInd-ab-Testcross").text(data.expectedValues.expectedIndab.toFixed(1));
                }
                if (data.expectedValues.contAB) {
                    $("#ExpectedCont-AB-Testcross").text(data.expectedValues.contAB.toFixed(1));
                    $("#ExpectedCont-Ab-Testcross").text(data.expectedValues.contAb.toFixed(1));
                    $("#ExpectedCont-aB-Testcross").text(data.expectedValues.contaB.toFixed(1));
                    $("#ExpectedCont-ab-Testcross").text(data.expectedValues.contab.toFixed(1));
                }

                $("#value-A-Testcross").text(data.observed.obsA);
                $("#value-a-Testcross").text(data.observed.obsa);
                $("#value-Total-Testcross").text(data.observed.total);

                $("#Expected-A-Testcross").text(data.expectedValues.expA.toFixed(1));
                $("#Expected-a-Testcross").text(data.expectedValues.expa.toFixed(1));

                $("#value-B-Testcross").text(data.observed.obsB);
                $("#value-b-Testcross").text(data.observed.obsb);

                $("#Expected-B-Testcross").text(data.expectedValues.expB.toFixed(1));
                $("#Expected-b-Testcross").text(data.expectedValues.expb.toFixed(1));

                $("#value-Testcross-Aa").text(data.resultValues.chiAa.toFixed(2));
                $("#agree-Testcross-Aa").text(data.agree.chiAa);
                $("#value-Testcross-Bb").text(data.resultValues.chiBb.toFixed(2));
                $("#agree-Testcross-Bb").text(data.agree.chiBb);
                if (data.resultValues.chiInd) {
                    $("#value-Testcross-Indep").text(data.resultValues.chiInd.toFixed(2));
                    $("#agree-Testcross-Indep").text(data.agree.chiInd);
                }
                if (data.resultValues.chiCont) {
                    $("#value-Testcross-Cont").text(data.resultValues.chiCont.toFixed(2));
                    $("#agree-Testcross-Cont").text(data.agree.chiCont);
                }
                alert(data.feedbackMessage);

            } else { alert(data.feedbackMessage); }

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });
});

/////////////////////////////////////////////////////////////////////////////
//LINKAGE
/////////////////////////////////////////////////////////////////////////////

$("#ctoolView").on("click", "#F2Codominance-linkage", (evt) => {

    let alleles_A1A1B1B1 = $("#Alleles-A1A1B1B1-F2Codominance-linkage").val();
    let alleles_A1A1B1B2 = $("#Alleles-A1A1B1B2-F2Codominance-linkage").val();
    let alleles_A1A1B2B2 = $("#Alleles-A1A1B2B2-F2Codominance-linkage").val();
    let alleles_A1A2B1B1 = $("#Alleles-A1A2B1B1-F2Codominance-linkage").val();
    let alleles_A1A2B1B2 = $("#Alleles-A1A2B1B2-F2Codominance-linkage").val();
    let alleles_A1A2B2B2 = $("#Alleles-A1A2B2B2-F2Codominance-linkage").val();
    let alleles_A2A2B1B1 = $("#Alleles-A2A2B1B1-F2Codominance-linkage").val();
    let alleles_A2A2B1B2 = $("#Alleles-A2A2B1B2-F2Codominance-linkage").val();
    let alleles_A2A2B2B2 = $("#Alleles-A2A2B2B2-F2Codominance-linkage").val();

    alert("Boton f2codominance");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_A1A1B1B1: alleles_A1A1B1B1,
            alleles_A1A1B1B2: alleles_A1A1B1B2,
            alleles_A1A1B2B2: alleles_A1A1B2B2,
            alleles_A1A2B1B1: alleles_A1A2B1B1,
            alleles_A1A2B1B2: alleles_A1A2B1B2,
            alleles_A1A2B2B2: alleles_A1A2B2B2,
            alleles_A2A2B1B1: alleles_A2A2B1B1,
            alleles_A2A2B1B2: alleles_A2A2B1B2,
            alleles_A2A2B2B2: alleles_A2A2B2B2
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2CodominanceDM-linkage", (evt) => {

    let recFreq = $("#Alleles-F2CodominanceDM-RecFreq").val();
    let ToS = $("#Alleles-F2CodominanceDM-ToS").val();

    alert("Boton f2codominancedm");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            recFreq: recFreq,
            ToS: ToS
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2Dominance-linkage", (evt) => {

    let alleles_AB = $("#Alleles-AB-F2Dominance-linkage").val();
    let alleles_aB = $("#Alleles-aB-F2Dominance-linkage").val();
    let alleles_Ab = $("#Alleles-Ab-F2Dominance-linkage").val();
    let alleles_ab = $("#Alleles-ab-F2Dominance-linkage").val();

    alert("Boton f2dominance");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_aB: alleles_aB,
            alleles_Ab: alleles_Ab,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#F2DominanceDM-linkage", (evt) => {

    let recFreq = $("#Alleles-F2DominanceDM-RecFreq").val();
    let ToS = $("#Alleles-F2DominanceDM-ToS").val();

    alert("Boton f2dominanceDM");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            recFreq: recFreq,
            ToS: ToS
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Testcross-linkage", (evt) => {

    let alleles_AB = $("#Alleles-AB-Testcross-linkage").val();
    let alleles_aB = $("#Alleles-aB-Testcross-linkage").val();
    let alleles_Ab = $("#Alleles-Ab-Testcross-linkage").val();
    let alleles_ab = $("#Alleles-ab-Testcross-linkage").val();

    alert("Boton Testcross");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_aB: alleles_aB,
            alleles_Ab: alleles_Ab,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Testcross3Loci-linkage", (evt) => {

    let alleles_ABC = $("#Alleles-Testcross3Loci-ABC").val();
    let alleles_abc = $("#Alleles-Testcross3Loci-abc").val();
    let alleles_ABc = $("#Alleles-Testcross3Loci-ABc").val();
    let alleles_abC = $("#Alleles-Testcross3Loci-abC").val();
    let alleles_aBC = $("#Alleles-Testcross3Loci-aBC").val();
    let alleles_Abc = $("#Alleles-Testcross3Loci-Abc").val();
    let alleles_AbC = $("#Alleles-Testcross3Loci-AbC").val();
    let alleles_aBc = $("#Alleles-Testcross3Loci-aBc").val();

    alert("Boton Testcross3Loci");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_ABC: alleles_ABC,
            alleles_abc: alleles_abc,
            alleles_ABc: alleles_ABc,
            alleles_abC: alleles_abC,
            alleles_aBC: alleles_aBC,
            alleles_Abc: alleles_Abc,
            alleles_AbC: alleles_AbC,
            alleles_aBc: alleles_aBc
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Testcross3LociDM-linkage", (evt) => {

    let recFreqR1 = $("#Alleles-Testcross3LociDM-RecFreqR1").val();
    let recFreqR2 = $("#Alleles-Testcross3LociDM-RecFreqR2").val();
    let CoC = $("#Alleles-Testcross3LociDM-CoC").val();
    let ToS = $("#Alleles-Testcross3LociDM-ToS").val();

    alert("Boton Testcross3LociDM");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            recFreqR1: recFreqR1,
            recFreqR2: recFreqR2,
            CoC: CoC,
            ToS: ToS
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#TestcrossDM-linkage", (evt) => {

    let recFreq = $("#Alleles-TestcrossDM-RecFreq").val();
    let ToS = $("#Alleles-TestcrossDM-ToS").val();

    alert("Boton TestcrossDM");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            recFreq: recFreq,
            ToS: ToS
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});


/////////////////////////////////////////////////////////////////////////////
//EPISTASIAS
/////////////////////////////////////////////////////////////////////////////

$("#ctoolView").on("click", "#DoubleDominant-epistasias", (evt) => {

    let alleles_ABAbaB = $("#Alleles-ABAbaB-DoubleDominant").val();
    let alleles_ab = $("#Alleles-ab-DoubleDominant").val();

    alert("Boton DoubleDominant");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_ABAbaB: alleles_ABAbaB,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#DoubleDominantRecesive-epistasias", (evt) => {

    let alleles_ABAbab = $("#Alleles-ABAbab-DoubleDominantRecesive").val();
    let alleles_aB = $("#Alleles-aB-DoubleDominantRecesive").val();

    alert("Boton DoubleDominantRecesive");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_ABAbab: alleles_ABAbab,
            alleles_aB: alleles_aB
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#DoubleRecesive-epistasias", (evt) => {

    let alleles_AB = $("#Alleles-AB-DoubleRecesive").val();
    let alleles_AbaBab = $("#Alleles-AbaBab-DoubleRecesive").val();

    alert("Boton DoubleRecesive");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_AbaBab: alleles_AbaBab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Segregation961-epistasias", (evt) => {

    let alleles_AB = $("#Alleles-AB-Segregation961").val();
    let alleles_AbaB = $("#Alleles-AbaB-Segregation961").val();
    let alleles_ab = $("#Alleles-ab-Segregation961").val();

    alert("Boton Segregation961");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_AbaB: alleles_AbaB,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Segregation1033-epistasias", (evt) => {

    let alleles_ABab = $("#Alleles-ABab-Segregation1033").val();
    let alleles_Ab = $("#Alleles-Ab-Segregation1033").val();
    let alleles_aB = $("#Alleles-aB-Segregation1033").val();

    alert("Boton Segregation1033");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_ABab: alleles_ABab,
            alleles_Ab: alleles_Ab,
            alleles_aB: alleles_aB
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Segregation6334-epistasias", (evt) => {

    let alleles_AaB = $("#Alleles-AaB-Segregation6334").val();
    let alleles_AAB = $("#Alleles-AAB-Segregation6334").val();
    let alleles_Ab = $("#Alleles-Ab-Segregation6334").val();
    let alleles_aBab = $("#Alleles-aBab-Segregation6334").val();

    alert("Boton Segregation6334");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AaB: alleles_AaB,
            alleles_AAB: alleles_AAB,
            alleles_Ab: alleles_Ab,
            alleles_aBab: alleles_aBab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#SingleDominant-epistasias", (evt) => {

    let alleles_ABAb = $("#Alleles-ABAb-SingleDominant").val();
    let alleles_aB = $("#Alleles-aB-SingleDominant").val();
    let alleles_ab = $("#Alleles-ab-SingleDominant").val();

    alert("Boton SingleDominant");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_ABAb: alleles_ABAb,
            alleles_aB: alleles_aB,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#SingleRecesive-epistasias", (evt) => {

    let alleles_AB = $("#Alleles-AB-SingleRecesive").val();
    let alleles_Ab = $("#Alleles-Ab-SingleRecesive").val();
    let alleles_aBab = $("#Alleles-aBab-SingleRecesive").val();

    alert("Boton SingleRecesive");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_Ab: alleles_Ab,
            alleles_aBab: alleles_aBab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#WhithoutModification-epistasias", (evt) => {

    let alleles_AB = $("#Alleles-AB-WhithoutModification").val();
    let alleles_Ab = $("#Alleles-Ab-WhithoutModification").val();
    let alleles_aB = $("#Alleles-aB-WhithoutModification").val();
    let alleles_ab = $("#Alleles-ab-WhithoutModification").val();

    alert("Boton WhithoutModification");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            alleles_AB: alleles_AB,
            alleles_Ab: alleles_Ab,
            alleles_aB: alleles_aB,
            alleles_ab: alleles_ab
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

/////////////////////////////////////////////////////////////////////////////
//POLYHYBRID
/////////////////////////////////////////////////////////////////////////////

$("#ctoolView").on("click", "#multiplesAlleles-polyhybrid", (evt) => {

    let locus1 = $("#locus1-multiplesAlleles").val();
    let locus2 = $("#locus2-multiplesAlleles").val();
    let locus3 = $("#locus3-multiplesAlleles").val();
    let locus4 = $("#locus4-multiplesAlleles").val();
    let locus5 = $("#locus5-multiplesAlleles").val();

    alert("Boton multiplesAlleles");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            locus1: locus1,
            locus2: locus2,
            locus3: locus3,
            locus4: locus4,
            locus5: locus5
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", "#Polyhybrid-polyhybrid", (evt) => {

    let n = $("#Data-n-polyhybrid").val();
    let T = $("#Data-T-polyhybrid").val();
    let h = $("#Data-h-polyhybrid").val();
    let R = $("#Data-R-polyhybrid").val();
    let d = $("#Data-d-polyhybrid").val();
    let D = $("#Data-D-polyhybrid").val();
    let r = $("#Data-r-polyhybrid").val();

    alert("Boton Polyhybrid");
    /*$.ajax({
        type: "POST",
        //url: "http://ingenias.fdi.ucm.es:60070/api/v1/problems",
        url: "http://raspberrypablo.ddns.net:8080/api/v1/twoloci/F2Codominance",
        beforeSend: function(request) {
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        contentType: "application/json",
        data: {
            n: n,
            T: T,
            h: h,
            R: R,
            d: d,
            D: D,
            r: r
        },
        success: function(data, textStatus, jqXHR) {

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Se ha producido un error: " + errorThrown);
        }
    });*/
});

$("#ctoolView").on("click", ".btn-clean", (evt) => {
    //ONE LOCUS

    //Codominance 3
    $("#Alleles-A1A1-Codominance3").val("");
    $("#Alleles-A1A3-Codominance3").val("");
    $("#Alleles-A1A2-Codominance3").val("");
    $("#Alleles-A2A3-Codominance3").val("");
    //Codominance 4
    $("#Alleles-A1A3-Codominance4").val("");
    $("#Alleles-A1A4-Codominance4").val("");
    $("#Alleles-A2A3-Codominance4").val("");
    $("#Alleles-A2A4-Codominance4").val("");
    //F2 codominance
    $("#Alleles-A1A1-F2Codominance").val("");
    $("#Alleles-A1A2-F2Codominance").val("");
    $("#Alleles-A2A2-F2Codominance").val("");
    //F2 dominance
    $("#Alleles-A-F2Dominance").val("");
    $("#Alleles-a-F2Dominance").val("");
    //Testcross
    $("#Alleles-A-Testcross").val("");
    $("#Alleles-a-Testcross").val("");

    //TWO LOCI

    //F2 Codom2
    $("#Alleles-A1A1B-F2Codom2").val("");
    $("#Alleles-A1A2B-F2Codom2").val("");
    $("#Alleles-A2A2B-F2Codom2").val("");
    $("#Alleles-A1A1b-F2Codom2").val("");
    $("#Alleles-A1A2b-F2Codom2").val("");
    $("#Alleles-A2A2b-F2Codom2").val("");
    ////F2 Codom4
    $("#Alleles-A1A3B-F2Codom4").val("");
    $("#Alleles-A1A3b-F2Codom4").val("");
    $("#Alleles-A1A4B-F2Codom4").val("");
    $("#Alleles-A1A4b-F2Codom4").val("");
    $("#Alleles-A2A3B-F2Codom4").val("");
    $("#Alleles-A2A3b-F2Codom4").val("");
    $("#Alleles-A2A4B-F2Codom4").val("");
    $("#Alleles-A2A4b-F2Codom4").val("");
    //F2 Codominance
    $("#Alleles-A1A1B1B1-F2Codominance").val("");
    $("#Alleles-A1A1B1B2-F2Codominance").val("");
    $("#Alleles-A1A1B2B2-F2Codominance").val("");
    $("#Alleles-A1A2B1B1-F2Codominance").val("");
    $("#Alleles-A1A2B1B2-F2Codominance").val("");
    $("#Alleles-A1A2B2B2-F2Codominance").val("");
    $("#Alleles-A2A2B1B1-F2Codominance").val("");
    $("#Alleles-A2A2B1B2-F2Codominance").val("");
    $("#Alleles-A2A2B2B2-F2Codominance").val("");
    //F2 Dominance
    $("#Alleles-AB-F2Dominance").val("");
    $("#Alleles-aB-F2Dominance").val("");
    $("#Alleles-Ab-F2Dominance").val("");
    $("#Alleles-ab-F2Dominance").val("");
    //F2 Testcross Dom
    $("#Alleles-AB-F2TestcrossDominance").val("");
    $("#Alleles-aB-F2TestcrossDominance").val("");
    $("#Alleles-Ab-F2TestcrossDominance").val("");
    $("#Alleles-ab-F2TestcrossDominance").val("");
    //F2 Testcross Dom 2_1
    $("#Alleles-A1A1B-F2TestcrossDom2_1").val("");
    $("#Alleles-A1A2B-F2TestcrossDom2_1").val("");
    $("#Alleles-A2A2B-F2TestcrossDom2_1").val("");
    $("#Alleles-A1A1b-F2TestcrossDom2_1").val("");
    $("#Alleles-A1A2b-F2TestcrossDom2_1").val("");
    $("#Alleles-A2A2b-F2TestcrossDom2_1").val("");
    //F2 Testcross Dom 2_2
    $("#Alleles-A1A3B-F2TestcrossDom2_2").val("");
    $("#Alleles-A1A3b-F2TestcrossDom2_2").val("");
    $("#Alleles-A1A4B-F2TestcrossDom2_2").val("");
    $("#Alleles-A1A4b-F2TestcrossDom2_2").val("");
    $("#Alleles-A2A3B-F2TestcrossDom2_2").val("");
    $("#Alleles-A2A3b-F2TestcrossDom2_2").val("");
    $("#Alleles-A2A4B-F2TestcrossDom2_2").val("");
    $("#Alleles-A2A4b-F2TestcrossDom2_2").val("");
    //Testcross
    $("#Alleles-AB-Testcross").val("");
    $("#Alleles-aB-Testcross").val("");
    $("#Alleles-Ab-Testcross").val("");
    $("#Alleles-ab-Testcross").val("");

    //LINKAGE

    //F2 Codominance
    $("#Alleles-A1A1B1B1-F2Codominance-linkage").val("");
    $("#Alleles-A1A1B1B2-F2Codominance-linkage").val("");
    $("#Alleles-A1A1B2B2-F2Codominance-linkage").val("");
    $("#Alleles-A1A2B1B1-F2Codominance-linkage").val("");
    $("#Alleles-A1A2B1B2-F2Codominance-linkage").val("");
    $("#Alleles-A1A2B2B2-F2Codominance-linkage").val("");
    $("#Alleles-A2A2B1B1-F2Codominance-linkage").val("");
    $("#Alleles-A2A2B1B2-F2Codominance-linkage").val("");
    $("#Alleles-A2A2B2B2-F2Codominance-linkage").val("");
    //F2 Codominance DM
    $("#Alleles-F2CodominanceDM-RecFreq").val("");
    $("#Alleles-F2CodominanceDM-ToS").val("");
    //F2 Dominance
    $("#Alleles-AB-F2Dominance-linkage").val("");
    $("#Alleles-aB-F2Dominance-linkage").val("");
    $("#Alleles-Ab-F2Dominance-linkage").val("");
    $("#Alleles-ab-F2Dominance-linkage").val("");
    //F2 Dominance DM
    $("#Alleles-F2DominanceDM-RecFreq").val("");
    $("#Alleles-F2DominanceDM-ToS").val("");
    //Testcross
    $("#Alleles-AB-Testcross-linkage").val("");
    $("#Alleles-aB-Testcross-linkage").val("");
    $("#Alleles-Ab-Testcross-linkage").val("");
    $("#Alleles-ab-Testcross-linkage").val("");
    //Testcross 3 loci
    $("#Alleles-Testcross3Loci-ABC").val("");
    $("#Alleles-Testcross3Loci-abc").val("");
    $("#Alleles-Testcross3Loci-ABc").val("");
    $("#Alleles-Testcross3Loci-abC").val("");
    $("#Alleles-Testcross3Loci-aBC").val("");
    $("#Alleles-Testcross3Loci-Abc").val("");
    $("#Alleles-Testcross3Loci-AbC").val("");
    $("#Alleles-Testcross3Loci-aBc").val("");
    //Testcross 3 loci DM
    $("#Alleles-Testcross3LociDM-RecFreqR1").val("");
    $("#Alleles-Testcross3LociDM-RecFreqR2").val("");
    $("#Alleles-Testcross3LociDM-CoC").val("");
    $("#Alleles-Testcross3LociDM-ToS").val("");
    //Testcross DM
    $("#Alleles-TestcrossDM-RecFreq").val("");
    $("#Alleles-TestcrossDM-ToS").val("");

    //EPISTASIAS

    //Double dominant
    $("#Alleles-ABAbaB-DoubleDominant").val("");
    $("#Alleles-ab-DoubleDominant").val("");
    //Double dominant recessive
    $("#Alleles-ABAbab-DoubleDominantRecesive").val("");
    $("#Alleles-aB-DoubleDominantRecesive").val("");
    //Double recessive
    $("#Alleles-AB-DoubleRecesive").val("");
    $("#Alleles-AbaBab-DoubleRecesive").val("");
    //Segregation 961
    $("#Alleles-AB-Segregation961").val("");
    $("#Alleles-AbaB-Segregation961").val("");
    $("#Alleles-ab-Segregation961").val("");
    //Segregation 1033
    $("#Alleles-ABab-Segregation1033").val("");
    $("#Alleles-Ab-Segregation1033").val("");
    $("#Alleles-aB-Segregation1033").val("");
    //Segregation 6334
    $("#Alleles-AaB-Segregation6334").val("");
    $("#Alleles-AAB-Segregation6334").val("");
    $("#Alleles-Ab-Segregation6334").val("");
    $("#Alleles-aBab-Segregation6334").val("");
    //Single Dominant
    $("#Alleles-ABAb-SingleDominant").val("");
    $("#Alleles-aB-SingleDominant").val("");
    $("#Alleles-ab-SingleDominant").val("");
    //Single Recessive
    $("#Alleles-AB-SingleRecesive").val("");
    $("#Alleles-Ab-SingleRecesive").val("");
    $("#Alleles-aBab-SingleRecesive").val("");
    //Without modification
    $("#Alleles-AB-WhithoutModification").val("");
    $("#Alleles-Ab-WhithoutModification").val("");
    $("#Alleles-aB-WhithoutModification").val("");
    $("#Alleles-ab-WhithoutModification").val("");

    //POLYHYBRID

    //Polyhybrid
    $("#Data-n-polyhybrid").val("");
    $("#Data-T-polyhybrid").val("");
    $("#Data-h-polyhybrid").val("");
    $("#Data-R-polyhybrid").val("");
    $("#Data-d-polyhybrid").val("");
    $("#Data-D-polyhybrid").val("");
    $("#Data-r-polyhybrid").val("");
    //Multiples alleles
    $("#locus1-multiplesAlleles").val("");
    $("#locus2-multiplesAlleles").val("");
    $("#locus3-multiplesAlleles").val("");
    $("#locus4-multiplesAlleles").val("");
    $("#locus5-multiplesAlleles").val("");

    cleanResults();
});

function cleanResults() {
    //ONE LOCUS

    //Testcross
    $("#total-Testcross").text("");
    $("#Expected-A-Testcross").text("");
    $("#Expected-a-Testcross").text("");
    $("#value-Testcross").text("");
    $("#agree-Testcross").text("");
    $("#result-message-Testcross").text("");

    //F2Dominance
    $("#total-F2Dominance").text("");
    $("#Expected-A-F2Dominance").text("");
    $("#Expected-a-F2Dominance").text("");
    $("#value-F2Dominance").text("");
    $("#agree-F2Dominance").text("");
    $("#result-message-F2Dominance").text("");

    //TWO LOCI

    //Testcross
    $("#total-Testcross").text("");
    $("#ExpectedInd-AB-Testcross").text("");
    $("#ExpectedInd-Ab-Testcross").text("");
    $("#ExpectedInd-aB-Testcross").text("");
    $("#ExpectedInd-ab-Testcross").text("");
    $("#ExpectedCont-AB-Testcross").text("");
    $("#ExpectedCont-Ab-Testcross").text("");
    $("#ExpectedCont-aB-Testcross").text("");
    $("#ExpectedCont-ab-Testcross").text("");
    $("#value-A-Testcross").text("");
    $("#value-a-Testcross").text("");
    $("#value-Total-Testcross").text("");
    $("#Expected-A-Testcross").text("");
    $("#Expected-a-Testcross").text("");
    $("#value-B-Testcross").text("");
    $("#value-b-Testcross").text("");
    $("#Expected-B-Testcross").text("");
    $("#Expected-b-Testcross").text("");
    $("#value-Testcross-Aa").text("");
    $("#agree-Testcross-Aa").text("");
    $("#value-Testcross-Bb").text("");
    $("#agree-Testcross-Bb").text("");
    $("#value-Testcross-Indep").text("");
    $("#agree-Testcross-Indep").text("");
    $("#value-Testcross-Cont").text("");
    $("#agree-Testcross-Cont").text("");


}