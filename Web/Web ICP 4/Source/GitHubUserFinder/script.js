function getGithubInfo(user) {//retrieves the info from github's API
    //REQUIREMENTS
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    var xhttp = new XMLHttpRequest();//created xhttp request object
    xhttp.open('GET', "https://api.github.com/users/" + user, true);//opens the request
    xhttp.send();//sending the request
    // console.log(xhttp);//USED FOR TESTING
    xhttp.onreadystatechange = function (){
        if(xhttp.readyState === 4){
            if(xhttp.status === 200){
                var response = JSON.parse(xhttp.responseText);
                console.log(response);
                showUser(response);
            }
            else{
                noSuchUser(username);
            }
        }
    };
}

function showUser(user) {
    //var html_url =
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    $('#profile .avatar img').remove()//removes image when function is called
    $('#profile .information li').remove()//removes text when function is called
    console.log(user)//USED FOR TESTING
    $('#profile h2').text(user['name'])//should display the user profile text
     $('#profile .avatar').prepend(`<img src="${user['avatar_url']}" alt="avatar"/>`)//should display the user avatar
    $('#profile .information').append(`<li><strong>URL:</strong> <a href=${user['html_url']}>${user['html_url']}</a></li>`)
    $('#profile .information').append(`<li><strong>USER ID:</strong> ${user['id']}</li>`)
    //$('#profile .information').text(user.user['html_url']);
    //$('#profile .information').text(user.id)

   //$('#name').text(user.name);
   //$('#avatar')
}

function noSuchUser(username) {
    $('#profile .avatar img').remove()//acts as an automatic clear to remove any remaining images after a search
    $('#profile .information li').remove()//acts as an auto clear to remove text after a search
    //$('#profile h2').text("No existing information\n")//leaving a generic statment saying no images/description
    $('#profile h2').text("No such user. Try again.")//states that there is no user like this in github
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            const username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the response
            let response = getGithubInfo(username);
            //if the response is successful show the user's details
            // console.log(response);
            // if (response.status == 200) {
            //     showUser(JSON.parse(response.responseText));
            //     //else display suitable message
            // } else {
            //     noSuchUser(username);
            // }
        }
    })
});
