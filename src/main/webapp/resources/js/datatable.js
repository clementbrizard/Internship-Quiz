$(document).ready( function () {
    var table = $('#usersTable').DataTable({
        "sAjaxSource": "/data/users",
        'serverSide' : true,
        columns: [
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
