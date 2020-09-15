function upDate(previewPic) {//will update the image.This will not change the image back after the mouse is removed
    //REQUIREMENTS
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image

       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */

    let src_retrieval = previewPic.getAttribute("src");//variable that retrieves the src from html
    let alt_retrieval = previewPic.getAttribute("alt");//variable that retrieves the alt from html
    document.getElementById('image').style.backgroundImage = "url('"+src_retrieval+"')";//this will change the image
    document.getElementById('image').innerHTML = alt_retrieval;//this will change the text

}//END OF FUNCTION

function unDo() {//when mouse is moved, will remove image.
    //REQUIREMENTS
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the original-image.  You can use the css code to see what that original URL was

   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */

    let place_holder_val = document.getElementById('image');//creates a placeholder value that will retrieve the image from html page
    place_holder_val.style.backgroundImage = "url(' ')";//sets the image back to blank
    document.getElementById('image').innerHTML = "Hover over an image below to display here.";//sets the statement back to its original

}//END OF FUNCTION
