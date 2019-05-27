$(document).ready(function () {
    $("#users").dataTable({
        rowReorder: true,
        colReorder: true,
        search: {
            smart: false
        }
    });

})