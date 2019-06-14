$(document).ready(function () {
    $("#formList").dataTable({
        rowReorder: true,
        colReorder: true,
        search: {
            smart: false
        }
    });

    $("#trackList").dataTable({
        rowReorder: true,
        colReorder: true,
        search: {
            smart: false
        }
    });

})

// Get the modal
var modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

/*
// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}*/

if (score >= 0) {
    document.getElementById("score").innerHTML = score  ;
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
