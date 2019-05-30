$(document).ready(function () {
    $("#answers").dataTable({
        rowReorder: true,
        colReorder: true,
        order: [[ 1, "asc" ]],
        search: {
            smart: false
        }
    });

})
