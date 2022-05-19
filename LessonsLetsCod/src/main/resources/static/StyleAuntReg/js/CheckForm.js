var buttonCheck = document.getElementsByClassName("checkWindow")[0];
var teg = document.getElementsByClassName("tegChFor")[0];
var button = document.getElementsByClassName("forgotPas")[0];
var buttonBack = document.getElementsByClassName("backBut")[0];
// var zagCheck = document.getElementsByClassName("tegChFor")[0];
// var inputCheck = document.getElementsByClassName("emailCheck")[0];
// zagCheck.style.display="none";
buttonBack.style.display="none";
buttonCheck.style.display="none";
button.onmousedown = button.onmouseup = click;

function goAnimate(event){
    buttonCheck.style.display="block";
    buttonBack.style.display="block";
}
function click(event){
    if(event.type == "mouseup"){
        document.addEventListener('mouseup', goAnimate);
    };
}

buttonBack.onmouseover = buttonBack.onmouseout = backButAnimation;

function backButAnimation(event){
    if(event.type=="mouseover"){
        buttonBack.style.width="35px";
        buttonBack.style.height="35px";
    }
    if(event.type=="mouseout"){
        buttonBack.style.width="30px";
        buttonBack.style.height="30px";
    }
}

buttonBack.onmousedown = buttonBack.onmouseup = goBack;

function activeAnimation(event){
    buttonCheck.style.display="none";
    buttonBack.style.display="none";
}
function goBack(event){
    if(event.type=="mouseup"){
        document.addEventListener("mouseup", activeAnimation)
    }
}