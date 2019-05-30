$(document).ready(function () {
    $('#submit').click(function () {
        $('.formSubmit').each(function () {
            $(this).submit();
        });
    });
})