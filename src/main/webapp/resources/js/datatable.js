$(document).ready( function () {
    var table = $('#usersTable').DataTable({
        "sAjaxSource": "/users",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "username" },
            { "mData": "firstName" },
            { "mData": "secondName" },
            { "mData": "mail" },
            { "mData": "admin" },
            { "mData": "company" },
            { "mData": "phone" },
            { "mData": "creationDate" },
            { "mData": "valid" }
        ]
    })
});
